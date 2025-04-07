package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton submit, reset, close;
    JTextField tfusername;
    JPasswordField tfpassword;

    public Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Font labelFont = new Font("SansSerif", Font.BOLD, 22);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 20);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 20);

        int centerX = 250;
        int labelWidth = 150;
        int fieldWidth = 300;
        int height = 40;
        int spacingY = 70;

        JLabel lblusername = new JLabel("Username:");
        lblusername.setFont(labelFont);
        lblusername.setBounds(centerX - 200, 80, labelWidth, height);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setFont(fieldFont);
        tfusername.setBounds(centerX - 30, 80, fieldWidth, height);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setFont(labelFont);
        lblpassword.setBounds(centerX - 200, 80 + spacingY, labelWidth, height);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setFont(fieldFont);
        tfpassword.setBounds(centerX - 30, 80 + spacingY, fieldWidth, height);
        add(tfpassword);

        reset = new JButton("Reset");
        reset.setFont(buttonFont);
        reset.setBounds(centerX - 150, 80 + spacingY * 2, 120, height);
        reset.addActionListener(this);
        add(reset);

        submit = new JButton("Submit");
        submit.setFont(buttonFont);
        submit.setBounds(centerX + 30, 80 + spacingY * 2, 120, height);
        submit.addActionListener(this);
        add(submit);

        close = new JButton("Close");
        close.setFont(buttonFont);
        close.setBounds(centerX + 220, 80 + spacingY * 2, 120, height);
        close.addActionListener(this);
        add(close);

        setSize(800, 500);
        setLocationRelativeTo(null); // Center on screen
        setTitle("Login");
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String username = tfusername.getText();
            String password = tfpassword.getText();

            try {
                Conn c = new Conn();
                String query = "select * from login where username = '" + username + "' and password = '" + password + "'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    new Home();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    tfusername.setText("");
                    tfpassword.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == close) {
            setVisible(false);
        } else if (ae.getSource() == reset) {
            tfusername.setText("");
            tfpassword.setText("");
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
