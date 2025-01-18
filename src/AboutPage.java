import javax.swing.*;
import java.awt.*;

public class AboutPage {
    JFrame frame;

    public AboutPage() {
        frame = new JFrame("Store Details");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header panel with an image and title
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.decode("#8C4646")); // Copper rust background

        // Image on top
        ImageIcon icon = new ImageIcon("icecream.png"); // Replace with your image path
        JLabel imageLabel = new JLabel(icon, JLabel.CENTER);
        headerPanel.add(imageLabel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("Welcome to the Ice Cream Paradise!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.decode("#FFFFFF")); // White color for the title
        headerPanel.add(titleLabel, BorderLayout.SOUTH);

        JTextPane detailsPane = new JTextPane();
        detailsPane.setContentType("text/html");
        detailsPane.setText("<html><body style='text-align: center; font-family: Futura, sans-serif; font-size: 11px; padding-top: 20px;'>" +
                "At Ice Cream Paradise, we serve the finest and most delicious ice creams.<br>" +
                "Our store offers a variety of flavors including Vanilla, Chocolate, Strawberry, and Mint.<br>" +
                "Enjoy our delightful toppings such as Sprinkles and Chocolate Sauce to make your ice cream extra special.<br>" +
                "We are open daily from 10:00 AM to 10:00 PM.<br>" +
                "Come and treat yourself to the best ice cream experience!" +
                "</body></html>");
        detailsPane.setEditable(false);
        detailsPane.setBackground(Color.decode("#F2E7DD")); // Light beige background
        JScrollPane scrollPane = new JScrollPane(detailsPane);

        // Add a GIF
        ImageIcon gifIcon = new ImageIcon("gificecream.gif"); // Replace with your GIF file path
        JLabel gifLabel = new JLabel(gifIcon, JLabel.CENTER);

        // Back button with custom color
        JButton backButton = new JButton("Back to Home");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.decode("#8C4646")); // Copper rust background
        backButton.setForeground(Color.decode("#FFFFFF")); // White text
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            frame.dispose();
            new HomePage();
        });

        // Footer panel for the button
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout());
        footerPanel.setBackground(Color.decode("#592828")); // Copper rust background as header
        footerPanel.add(backButton);

        // Add components to the frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
