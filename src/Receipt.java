import javax.swing.*;
import java.awt.*;

public class Receipt {
    JFrame frame;

    Receipt(String name, String flavor, int quantity, boolean sprinkles, boolean chocolate) {
        frame = new JFrame("Receipt");
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Ice Cream Paradise", JLabel.CENTER);
        titleLabel.setFont(new Font("Lucida Handwriting", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel thankYouLabel = new JLabel("Thank you for your order, " + name + "!", JLabel.CENTER);
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        thankYouLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        headerPanel.add(thankYouLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing


        // Main Details Panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridBagLayout());
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Spacing between rows/columns

        // Column Header
        gbc.gridx = 0;
        gbc.gridy = 0;
        detailsPanel.add(new JLabel("Flavor"), gbc);

        gbc.gridx = 1;
        JLabel quantityHeader = new JLabel("Quantity", JLabel.CENTER);
        quantityHeader.setHorizontalAlignment(SwingConstants.CENTER);
        detailsPanel.add(quantityHeader, gbc);

        gbc.gridx = 2;
        JLabel toppingsHeader = new JLabel("Toppings", JLabel.CENTER);
        toppingsHeader.setHorizontalAlignment(SwingConstants.CENTER);
        detailsPanel.add(toppingsHeader, gbc);

        gbc.gridx = 3;
        detailsPanel.add(new JLabel("Price"), gbc);

        // Add Row Details
        gbc.gridy++;
        gbc.gridx = 0;
        detailsPanel.add(new JLabel(flavor), gbc);

        gbc.gridx = 1;
        JLabel quantityLabel = new JLabel(String.valueOf(quantity), JLabel.CENTER);
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        detailsPanel.add(quantityLabel, gbc);

        gbc.gridx = 2;
        StringBuilder toppings = new StringBuilder();
        if (sprinkles) toppings.append("Sprinkles ");
        if (chocolate) toppings.append("Chocolate Sauce");
        if (toppings.length() == 0) toppings.append("None");
        JLabel toppingsLabel = new JLabel(toppings.toString().trim(), JLabel.CENTER);
        toppingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        detailsPanel.add(toppingsLabel, gbc);

        double basePrice = 5.00;
        double sprinklesPrice = sprinkles ? 1.00 : 0.00;
        double chocolatePrice = chocolate ? 1.50 : 0.00;
        double totalToppingsPrice = sprinklesPrice + chocolatePrice;
        double totalPricePerIceCream = basePrice + totalToppingsPrice;
        double totalPrice = totalPricePerIceCream * quantity;

        gbc.gridx = 3;
        detailsPanel.add(new JLabel(String.format("RM%.2f", totalPricePerIceCream)), gbc);

        // Total Price Row
        gbc.gridy++;
        gbc.gridx = 0; // Align "Total Price" with "Flavor"
        gbc.gridwidth = 3; // Span across multiple columns for spacing
        detailsPanel.add(new JLabel("  Total Price:"), gbc); // Add spacing before text

        gbc.gridx = 3; // Align the total price value with the "Price" column
        gbc.gridwidth = 1; // Reset column span
        JLabel totalPriceLabel = new JLabel(String.format("RM%.2f", totalPrice));
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailsPanel.add(totalPriceLabel, gbc);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(Color.WHITE);
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> frame.dispose());
        footerPanel.add(closeButton);

        // Add components to the frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(detailsPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

}
