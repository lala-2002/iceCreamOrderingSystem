import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class HomePage {
    JFrame frame;

    // To store the image
    ImageIcon backgroundImage;
    Image scaledImage;

    public HomePage() {
        frame = new JFrame("Ice Cream Ordering System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the image
        backgroundImage = new ImageIcon("C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\image\\icecream1.jpg"); // Replace with your image path

        // Create a custom panel that draws the background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Scale the image to fit the panel size
                if (scaledImage != null) {
                    g.drawImage(scaledImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Set the layout for the background panel
        backgroundPanel.setLayout(new BorderLayout());

        // Listen for resizing events and update the image size
        backgroundPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Scale the image to fit the new size of the panel while maintaining the aspect ratio
                int panelWidth = backgroundPanel.getWidth();
                int panelHeight = backgroundPanel.getHeight();

                // Calculate the scaling factor to fit the panel size
                double scaleWidth = (double) panelWidth / backgroundImage.getIconWidth();
                double scaleHeight = (double) panelHeight / backgroundImage.getIconHeight();
                double scale = Math.max(scaleWidth, scaleHeight);  // Scale based on the larger dimension

                int newWidth = (int) (backgroundImage.getIconWidth() * scale);
                int newHeight = (int) (backgroundImage.getIconHeight() * scale);

                // Create a scaled version of the image
                scaledImage = backgroundImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                // Repaint the panel to show the scaled image
                backgroundPanel.repaint();
            }
        });

        JLabel welcomeLabel = new JLabel("Welcome to Ice Cream Ordering System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Footer button panel with a different background color
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#8C4646")); // Copper rust color for the footer button panel

        // Create buttons with custom colors
        JButton aboutButton = new JButton("About");
        aboutButton.setBackground(Color.decode("#F2E7DD")); // Button background color (light beige)
        aboutButton.setForeground(Color.BLACK); // Button text color

        JButton orderButton = new JButton("Order Now");
        orderButton.setBackground(Color.decode("#F2E7DD")); // Button background color (light beige)
        orderButton.setForeground(Color.BLACK); // Button text color

        aboutButton.addActionListener(actionEvent -> {
            frame.dispose();
            new AboutPage();
        });

        orderButton.addActionListener(actionEvent -> {
            frame.dispose();
            new OrderForm();
        });

        // Add buttons to the button panel
        buttonPanel.add(aboutButton);
        buttonPanel.add(orderButton);

        // Add the welcome label and button panel to the background panel
        backgroundPanel.add(welcomeLabel, BorderLayout.CENTER);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the background panel to the frame
        frame.add(backgroundPanel);

        frame.setVisible(true);
    }
}
