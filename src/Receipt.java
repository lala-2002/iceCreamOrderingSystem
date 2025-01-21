import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Receipt {
    JFrame frame;

    Receipt(String name, String flavor, int quantity, boolean sprinkles, boolean chocolate) {
        frame = new JFrame("Receipt");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        JLabel headerInfoLabel = new JLabel("Receipt Information", JLabel.CENTER);
        headerInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        headerInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(headerInfoLabel);

        ImageIcon originalIcon = new ImageIcon("C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\receipt\\ice cream receipt.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(-1, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(logoLabel);
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
        gbc.insets = new Insets(5, 10, 5, 10); // Spacing of 10 pixels between columns

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

        gbc.gridy++; // Move to the next row

// Add styled line border above "Total Price"
        gbc.gridx = 0;
        gbc.gridwidth = 4; // Span across all columns
        JLabel line = new JLabel();
        line.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK)); // Top border only
        detailsPanel.add(line, gbc);

// Add "Total Price" label aligned with "Flavor" with inset of 2
        gbc.gridy++; // Move to the next row
        gbc.gridx = 0; // Align with the first column ("Flavor")
        gbc.gridwidth = 1; // Do not span multiple columns
        gbc.insets = new Insets(1, 10, 5, 10); // Top: 2, Left: 10, Bottom: 5, Right: 10
        JLabel totalPriceLabel = new JLabel("Total Price:");
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailsPanel.add(totalPriceLabel, gbc);

// Add "Total Price" value aligned with "Price"
        gbc.gridx = 3; // Align with the "Price" column
        gbc.gridwidth = 1; // Reset column span
        JLabel totalPriceValueLabel = new JLabel(String.format("RM%.2f", totalPrice));
        totalPriceValueLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailsPanel.add(totalPriceValueLabel, gbc);

        // Save Receipt to File
        saveReceiptToFile(name, flavor, quantity, toppings.toString(), totalPrice);

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

    private void saveReceiptToFile(String name, String flavor, int quantity, String toppings, double totalPrice) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("receipts.txt", true))) {
            writer.write("Customer Name: " + name);
            writer.newLine();
            writer.write("Flavor: " + flavor);
            writer.newLine();
            writer.write("Quantity: " + quantity);
            writer.newLine();
            writer.write("Toppings: " + toppings);
            writer.newLine();
            writer.write("Total Price: RM" + String.format("%.2f", totalPrice));
            writer.newLine();
            writer.write("--------------------------------------------");
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving receipt to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
