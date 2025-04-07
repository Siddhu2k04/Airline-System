package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener {

    JTable table;
    JTextField pnr, aadhar;
    JButton show, fetchByAadhar;
    JLabel fetchedInfo;

public JourneyDetails() {

    getContentPane().setBackground(Color.WHITE);
    setLayout(null);

    // Aadhar section (on top, enlarged)
    JLabel lblaadhar = new JLabel("Aadhar Number");
    lblaadhar.setFont(new Font("Tahoma", Font.BOLD, 18));
    lblaadhar.setBounds(50, 30, 160, 35);
    add(lblaadhar);

    aadhar = new JTextField();
    aadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
    aadhar.setBounds(220, 30, 200, 35);
    add(aadhar);

    fetchByAadhar = new JButton("Fetch by Aadhar");
    fetchByAadhar.setFont(new Font("Tahoma", Font.BOLD, 16));
    fetchByAadhar.setBackground(Color.BLACK);
    fetchByAadhar.setForeground(Color.WHITE);
    fetchByAadhar.setBounds(440, 30, 180, 35);
    fetchByAadhar.addActionListener(this);
    add(fetchByAadhar);

    // PNR section (below Aadhar, enlarged)
    JLabel lblpnr = new JLabel("PNR Details");
    lblpnr.setFont(new Font("Tahoma", Font.BOLD, 18));
    lblpnr.setBounds(50, 80, 160, 35);
    add(lblpnr);

    pnr = new JTextField();
    pnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
    pnr.setBounds(220, 80, 200, 35);
    add(pnr);

    show = new JButton("Show Details");
    show.setFont(new Font("Tahoma", Font.BOLD, 16));
    show.setBackground(Color.BLACK);
    show.setForeground(Color.WHITE);
    show.setBounds(440, 80, 180, 35);
    show.addActionListener(this);
    add(show);

    // Table (slightly enlarged)
    table = new JTable();
    table.setFont(new Font("Tahoma", Font.PLAIN, 14));
    table.setRowHeight(25);
    JScrollPane jsp = new JScrollPane(table);
    jsp.setBounds(20, 140, 750, 250);
    jsp.setBackground(Color.WHITE);
    add(jsp);

    // Label for fetched info display
    fetchedInfo = new JLabel("Fetched Data Will Be Shown Here...");
    fetchedInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
    fetchedInfo.setBounds(50, 410, 700, 30);
    add(fetchedInfo);

    setSize(800, 550);
    setLocation(400, 150);
    setVisible(true);
}


public void actionPerformed(ActionEvent ae) {
    try {
        Conn conn = new Conn();

        if (ae.getSource() == show) {
            ResultSet rs = conn.s.executeQuery("select * from reservation where PNR = '" + pnr.getText() + "'");

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found for given PNR");
                return;
            }

            table.setModel(DbUtils.resultSetToTableModel(rs));
        } else if (ae.getSource() == fetchByAadhar) {
            ResultSet rs = conn.s.executeQuery("select * from reservation where aadhar = '" + aadhar.getText() + "'");

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found for given Aadhar");
                return;
            }

            // Move cursor to first row
            rs.next();

            // Set PNR field based on fetched data
            pnr.setText(rs.getString("PNR"));

            // Reset cursor and set data to table
            rs.beforeFirst();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            // Optional: Show summary
            rs.next();
            String summary = "PNR: " + rs.getString("PNR") + ", Name: " + rs.getString("name") + ", Flight: " + rs.getString("flight_no") + ", Date: " + rs.getString("journey_date");
            fetchedInfo.setText(summary);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
 public static void main(String[] args) {
        new JourneyDetails();
    }
}
