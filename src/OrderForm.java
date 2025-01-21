import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OrderForm {
    private JFrame frame;
    private JTextField nameField, quantityField;
    private JComboBox<Flavor> flavorBox;
    private JPanel toppingPanel;
    private ArrayList<Topping> selectedToppings;
    private JTextArea orderSummary;
    private JLabel totalLabel;
    private ArrayList<OrderItem> orders;
    private double totalOrderPrice = 0.0;
    private String backgroundImagePath;

    // Global variable to store the customer's name
    private String customerName = null;

    private JPanel createFormPanelWithBackground() {
        JPanel formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and scale the background image
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\orderform\\icecream12.png"); // Correct the path to the image
                Image img = backgroundImage.getImage();
                Image scaledImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH); // Scale the image to fit the panel size
                g.drawImage(scaledImg, 0, 0, this); // Draw the image on the panel
            }
        };
        formPanel.setLayout(new BorderLayout()); // Layout for the form
        return formPanel;
    }

    public OrderForm() {
        frame = new JFrame("Ice Cream Order Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame size to 400x450
        frame.setSize(500, 400);



        // Sample flavors and toppings
        Flavor[] flavors = {
                new Flavor("Vanilla", 5.00, "C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\image\\vanilla (2).jpg"),
                new Flavor("Chocolate", 5.00, "C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\image\\chocolate (2).jpg"),
                new Flavor("Strawberry", 5.00, "C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\image\\strawberry (2).jpg"),
                new Flavor("Mint", 5.00, "C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\image\\mint (2).jpg")
        };

        Topping[] toppings = {
                new Topping("Sprinkles", 1.00),
                new Topping("Chocolate Sauce", 1.50)
        };

        selectedToppings = new ArrayList<>();
        orders = new ArrayList<>();

        backgroundImagePath = "C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\orderform\\icecream12.png";


        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the background image
                ImageIcon backgroundImage = new ImageIcon(backgroundImagePath);
                Image img = backgroundImage.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Add components to the background panel
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(createInputForm(flavors, toppings), BorderLayout.CENTER);
        backgroundPanel.add(createSummaryPanel(), BorderLayout.EAST);
        backgroundPanel.add(createActionButtons(), BorderLayout.SOUTH);

        frame.setContentPane(backgroundPanel);
        frame.pack();
        frame.setVisible(true);
    }

    // Custom JPanel to display a background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                System.out.println("Error loading background image: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Draw the image to fill the panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    // Method to set up the JComboBox with icons
    private void setupFlavorComboBox(JComboBox<Flavor> flavorBox) {
        flavorBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically
                panel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the content horizontally

                if (value instanceof Flavor) {
                    Flavor flavor = (Flavor) value;

                    // Flavor name label
                    JLabel label = new JLabel(flavor.getName());
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the name text

                    // Load and scale the image
                    ImageIcon icon = new ImageIcon(flavor.getIconPath());
                    Image scaledImage = icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                    JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
                    iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the icon

                    // Add the icon and name to the panel
                    panel.add(iconLabel);
                    panel.add(label);
                }

                // Return the panel containing both icon and name
                return panel;
            }
        });
    }

    private JPanel createInputForm(Flavor[] flavors, Topping[] toppings) {
        // Create the main input panel with BoxLayout for vertical alignment
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add margins

        // Add spacing helper constants
        final int SECTION_GAP = 20;      // Space between unrelated sections
        final int LABEL_TO_FIELD_GAP = 5; // Space between label and input field
        final int SMALLER_LABEL_GAP = 10; // Custom gap between labels and unrelated input boxes

        // Name Field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Left-align the label
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 30));
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Ensure the field stretches if needed
        nameField.setAlignmentX(Component.LEFT_ALIGNMENT); // Left-align the field

        // Add Name Components (Label + Field)
        inputPanel.add(nameLabel);
        inputPanel.add(Box.createVerticalStrut(LABEL_TO_FIELD_GAP)); // Space between label and field (5px)
        inputPanel.add(nameField);
        inputPanel.add(Box.createVerticalStrut(SMALLER_LABEL_GAP)); // Gap before Flavor label

        // Flavor Field
        JLabel flavorLabel = new JLabel("Flavor:");
        flavorLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Left-align the label
        flavorBox = new JComboBox<>(flavors);
        flavorBox.setPreferredSize(new Dimension(200, 100)); // Custom height and width
        flavorBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100)); // Allow stretching
        flavorBox.setBackground(Color.decode("#FFFFFF")); // Set white background
        flavorBox.setForeground(Color.decode("#000000")); // Set text color to black
        flavorBox.setAlignmentX(Component.LEFT_ALIGNMENT); // Left-align the combo box
        setupFlavorComboBox(flavorBox); // Call setup method for flavorBox

        // Add Flavor Components (Label + ComboBox)
        inputPanel.add(flavorLabel);
        inputPanel.add(Box.createVerticalStrut(LABEL_TO_FIELD_GAP)); // Space between label and combo box (5px)
        inputPanel.add(flavorBox);
        inputPanel.add(Box.createVerticalStrut(SMALLER_LABEL_GAP)); // Gap before Quantity label

        // Quantity Field
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Left-align the label
        quantityField = new JTextField();
        quantityField.setPreferredSize(new Dimension(200, 30));
        quantityField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        quantityField.setAlignmentX(Component.LEFT_ALIGNMENT); // Left-align the field

        // Add Quantity Components (Label + Field)
        inputPanel.add(quantityLabel);
        inputPanel.add(Box.createVerticalStrut(LABEL_TO_FIELD_GAP)); // Space between label and field (5px)
        inputPanel.add(quantityField);
        inputPanel.add(Box.createVerticalStrut(SMALLER_LABEL_GAP)); // Gap before Toppings label

        // Toppings Section
        JLabel toppingLabel = new JLabel("Toppings:");
        toppingLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Left-align the label
        toppingPanel = new JPanel();
        toppingPanel.setLayout(new BoxLayout(toppingPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment
        toppingPanel.setBackground(Color.decode("#FFFFFF"));

        JScrollPane scrollPane = new JScrollPane(toppingPanel);
        scrollPane.setPreferredSize(new Dimension(250, 100)); // Adjusted height for scroll area (50px)
        scrollPane.setMaximumSize(new Dimension(250, 100));   // Prevent resizing beyond 50px
        scrollPane.setMinimumSize(new Dimension(250, 100));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Populate topping checkboxes
        for (Topping topping : toppings) {
            JCheckBox toppingCheckBox = new JCheckBox(topping.getName() + " (RM" + topping.getPrice() + ")");
            toppingCheckBox.setBackground(Color.decode("#FFFFFF"));
            toppingCheckBox.setForeground(Color.decode("#000000"));
            toppingCheckBox.setPreferredSize(new Dimension(250, 30)); // Set checkbox height to 30
            toppingCheckBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            toppingCheckBox.addActionListener(e -> {
                if (toppingCheckBox.isSelected()) {
                    selectedToppings.add(topping);
                } else {
                    selectedToppings.remove(topping);
                }
            });
            toppingPanel.add(toppingCheckBox);
            toppingPanel.add(Box.createVerticalStrut(10)); // Adjust spacing between checkboxes
        }

// Add Topping Components (Label + ScrollPane)
        inputPanel.add(toppingLabel);
        inputPanel.add(Box.createVerticalStrut(LABEL_TO_FIELD_GAP));
        inputPanel.add(scrollPane);

        return inputPanel;
    }

    private JPanel createSummaryPanel() {
        // Create a wrapper panel for layout
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setOpaque(false);
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add spacing

        // Main summary panel
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Order Summary"));

        // Text area
        orderSummary = new JTextArea();
        orderSummary.setEditable(false);

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(orderSummary);
        scrollPane.setMaximumSize(new Dimension(300, 325));
        scrollPane.setPreferredSize(new Dimension(300, 325));

        // Total Label
        totalLabel = new JLabel("Total: RM0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to summaryPanel
        summaryPanel.add(scrollPane); // Add the scrollPane with a controlled height
        summaryPanel.add(Box.createVerticalStrut(10)); // Add spacing between components
        summaryPanel.add(totalLabel);

        // Add summaryPanel to wrapperPanel
        wrapperPanel.add(summaryPanel);

        return wrapperPanel;
    }

    private JPanel createActionButtons() {
        JPanel actionPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        actionPanel.setOpaque(false);
        JButton addFlavorButton = new JButton("Add Flavor");
        addFlavorButton.setPreferredSize(new Dimension(50, 30)); // Set button size (width, height)
        addFlavorButton.setBackground(Color.decode("#59282B"));  // Cyan (#00FFFF)
        addFlavorButton.setForeground(Color.decode("#FFFFFF"));  // Black (#000000)
        addFlavorButton.addActionListener(e -> addFlavor());
        actionPanel.add(addFlavorButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(50, 30));
        resetButton.setBackground(Color.decode("#59282B"));  // Orange (#FFA500)
        resetButton.setForeground(Color.decode("#FFFFFF"));  // Black (#000000)
        resetButton.addActionListener(e -> resetAll());
        actionPanel.add(resetButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(50, 30));
        submitButton.setBackground(Color.decode("#59282B"));  // Green (#008000)
        submitButton.setForeground(Color.decode("#FFFFFF"));  // White (#FFFFFF)
        submitButton.addActionListener(e -> finalizeOrder());
        actionPanel.add(submitButton);

        // Home Page Button
        JButton homeButton = new JButton("Home Page");
        homeButton.setPreferredSize(new Dimension(50, 30));
        homeButton.setBackground(Color.decode("#59282B")); // Example color
        homeButton.setForeground(Color.decode("#FFFFFF"));
        homeButton.addActionListener(e -> navigateToHomePage()); // Action for going back to the home page
        actionPanel.add(homeButton);

        return actionPanel;
    }

    private void navigateToHomePage() {
        frame.dispose(); // Close the current OrderForm frame

        // Open the HomePage (assumes you have a HomePage class defined)
        SwingUtilities.invokeLater(() -> {
            new HomePage(); // Replace with your home page initialization logic
        });
    }

    private void addFlavor() {
        try {
            // Record name only if customerName is not already set
            if (customerName == null || customerName.isEmpty()) {
                customerName = nameField.getText().trim();
                if (customerName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                nameField.setEditable(false); // Lock the name field after setting customerName
            }

            // Validate quantity input
            String quantityText = quantityField.getText().trim();
            int quantity;
            try {
                quantity = Integer.parseInt(quantityText);
                if (quantity <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Flavor selectedFlavor = (Flavor) flavorBox.getSelectedItem();

            double total = selectedFlavor.getPrice() * quantity;
            ArrayList<Topping> toppingsForOrder = new ArrayList<>(selectedToppings);

            for (Topping topping : toppingsForOrder) {
                total += topping.getPrice() * quantity;
            }

            OrderItem orderItem = new OrderItem(
                    customerName,
                    selectedFlavor,
                    toppingsForOrder,
                    total,
                    quantity
            );
            orders.add(orderItem);

            totalOrderPrice += total;
            updateOrderSummary();
            resetForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "An error occurred while adding the order.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetForm() {
        // Reset form inputs except the customer name
        quantityField.setText("");
        flavorBox.setSelectedIndex(0);
        selectedToppings.clear();
        for (Component component : toppingPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                ((JCheckBox) component).setSelected(false);
            }
        }
    }

    private void resetAll() {
        // Reset everything including the customer's name
        orders.clear();
        totalOrderPrice = 0.0;
        customerName = null; // Clear customer name
        nameField.setText("");
        nameField.setEditable(true); // Allow user to edit the name again
        updateOrderSummary();
        resetForm();
    }

    private void updateOrderSummary() {
        StringBuilder summary = new StringBuilder("Current Order:\n");
        int counter = 1;

        for (OrderItem order : orders) {
            summary.append("Order ").append(counter).append(": RM").append(String.format("%.2f", order.getTotal())).append("\n");
            counter++;
        }

        orderSummary.setText(summary.toString());
        totalLabel.setText(String.format("Total: RM%.2f", totalOrderPrice));
    }

    private void finalizeOrder() {
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "<html><body>No items in order. Add at least one flavor.</body></html>",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the first order's details (for simplicity, assuming one order at a time)
        OrderItem firstOrder = orders.get(0);

        String name = customerName;
        String flavor = firstOrder.getFlavor().getName();
        int quantity = firstOrder.getQuantity();
        boolean hasSprinkles = false;
        boolean hasChocolate = false;

        for (Topping topping : firstOrder.getToppings()) {
            if (topping.getName().equalsIgnoreCase("Sprinkles")) {
                hasSprinkles = true;
            } else if (topping.getName().equalsIgnoreCase("Chocolate Sauce")) {
                hasChocolate = true;
            }
        }

        // Create and display the receipt
        new Receipt(name, flavor, quantity, hasSprinkles, hasChocolate);

        // Clear the order after displaying the receipt
        resetAll();
    }


    // Inner class for OrderItem
    public static class OrderItem {
        private final String name;
        private final Flavor flavor;
        private final ArrayList<Topping> toppings;
        private final double total;
        private final int quantity;

        public OrderItem(String name, Flavor flavor, ArrayList<Topping> toppings, double total, int quantity) {
            this.name = name;
            this.flavor = flavor;
            this.toppings = toppings;
            this.total = total;
            this.quantity = quantity;
        }

        // Getter for flavor
        public Flavor getFlavor() {
            return flavor;
        }

        // Getter for quantity
        public int getQuantity() {
            return quantity;
        }

        // Getter for toppings
        public ArrayList<Topping> getToppings() {
            return toppings;
        }

        // Getter for total
        public double getTotal() {
            return total;
        }

        @Override
        public String toString() {
            StringBuilder toppingsText = new StringBuilder();
            for (Topping topping : toppings) {
                toppingsText.append(topping.getName()).append(", ");
            }
            if (toppingsText.length() > 0) {
                toppingsText.setLength(toppingsText.length() - 2);
            } else {
                toppingsText.append("No toppings");
            }
            return String.format("%d x %s with %s = RM%.2f", quantity, flavor.getName(), toppingsText, total);
        }
    }
}