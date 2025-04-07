package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    private JLabel image;

    public Home() {
        setTitle("✈ Airline Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create background label
        image = new JLabel();
        image.setLayout(null);
        add(image);

        // Resize image with window
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                resizeBackgroundImage();
            }
        });

        // Big heading label
        JLabel heading = new JLabel("✈ AIR INDIA WELCOMES YOU ✈");
        heading.setBounds(400, 70, 1200, 80);
        heading.setForeground(new Color(0,255,0));
        heading.setFont(new Font("Serif", Font.BOLD, 60));
        image.add(heading);

        // BIG menu bar
        JMenuBar menubar = new JMenuBar();
        menubar.setFont(new Font("SansSerif", Font.BOLD, 24));
        menubar.setBackground(Color.LIGHT_GRAY);
        setJMenuBar(menubar);

        JMenu details = new JMenu("✧ DETAILS");
        details.setFont(new Font("SansSerif", Font.BOLD, 24));
        menubar.add(details);

        JMenuItem flightDetails = new JMenuItem("✈ Flight Details");
        JMenuItem customerDetails = new JMenuItem("👤 Add Customer Details");
        JMenuItem bookFlight = new JMenuItem("🛫 Book Flight");
        JMenuItem journeyDetails = new JMenuItem("🧳 Journey Details");
        JMenuItem ticketCancellation = new JMenuItem("❌ Cancel Ticket");

        JMenuItem[] detailItems = {flightDetails, customerDetails, bookFlight, journeyDetails, ticketCancellation};
        for (JMenuItem item : detailItems) {
            item.setFont(new Font("SansSerif", Font.PLAIN, 20));
            item.addActionListener(this);
            details.add(item);
        }

        JMenu ticket = new JMenu("🎟 TICKET");
        ticket.setFont(new Font("SansSerif", Font.BOLD, 24));
        menubar.add(ticket);

        JMenuItem boardingPass = new JMenuItem("🎫 Boarding Pass");
        boardingPass.setFont(new Font("SansSerif", Font.PLAIN, 20));
        boardingPass.addActionListener(this);
        ticket.add(boardingPass);

        setVisible(true);
    }

    // Resize background image based on frame size
    private void resizeBackgroundImage() {
        ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/front_.jpg"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(scaledImage));
    }

    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();

        switch (text) {
            case "👤 Add Customer Details":
                new AddCustomer();
                break;
            case "✈ Flight Details":
                new FlightInfo();
                break;
            case "🛫 Book Flight":
                new BookFlight();
                break;
            case "🧳 Journey Details":
                new JourneyDetails();
                break;
            case "❌ Cancel Ticket":
                new Cancel();
                break;
            case "🎫 Boarding Pass":
                new BoardingPass();
                break;
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
