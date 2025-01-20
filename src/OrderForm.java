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

    // Global variable to store the customer's name
    private String customerName = null;

    private JPanel createFormPanelWithBackground() {
        JPanel formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and scale the background image
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\image\\file.jpg"); // Correct the path to the image
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
        frame.setSize(550, 500);

        // Sample flavors and toppings
        Flavor[] flavors = {
                new Flavor("Vanilla", 5.00, "C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\image\\vanilla (2).jpg"),
                new Flavor("Chocolate", 5.00, "C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\image\\chocolate (2).jpg"),
                new Flavor("Strawberry", 5.00, "C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\image\\strawberry (2).jpg"),
                new Flavor("Mint", 5.00, "C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\image\\mint (2).jpg")
        };


        Topping[] toppings = {
                new Topping("Sprinkles", 1.00),
                new Topping("Chocolate Sauce", 1.50),
                new Topping("Walnut", 2.00),
                new Topping("Marshmallow", 1.75)
        };

        selectedToppings = new ArrayList<>();
        orders = new ArrayList<>();

        // Custom panel for background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the background image
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\image\\file.jpg"); // Correct the path
                Image img = backgroundImage.getImage();
                Image scaledImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH); // Scale image to fit
                g.drawImage(scaledImg, 0, 0, this); // Draw scaled image
            }
        };

        backgroundPanel.setLayout(new BorderLayout()); // Set layout to BorderLayout
        // Panels
        JPanel inputPanel = createInputForm(flavors, toppings);
        JPanel summaryPanel = createSummaryPanel();
        JPanel actionPanel = createActionButtons();

        // Add components to the background panel
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(inputPanel, BorderLayout.CENTER);
        backgroundPanel.add(summaryPanel, BorderLayout.EAST);
        backgroundPanel.add(actionPanel, BorderLayout.SOUTH);

        frame.setContentPane(backgroundPanel);
        frame.pack();
        frame.setVisible(true);
    }

    // Custom JPanel to display a background image
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Load the background image
            backgroundImage = new ImageIcon("C:\\Users\\arifah zulaikha\\IdeaProjects\\iceCreamOrderingSystem\\res\\image\\file.jpg").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the background image, scaled to fit the panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
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
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 30)); // Adjust size
        namePanel.add(nameField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(namePanel);


        inputPanel.add(new JLabel("Flavor:"));
        flavorBox = new JComboBox<>(flavors);
        flavorBox.setPreferredSize(new Dimension(120, 20)); // Reduced size of the combo box
        flavorBox.setBackground(Color.decode("#FFFFFF")); // Set the background color to white using hex
        flavorBox.setForeground(Color.decode("#000000")); // Set the text color to black using hex
        setupFlavorComboBox(flavorBox); // Call the setup method here
        inputPanel.add(flavorBox);

        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quantityField = new JTextField();
        quantityField.setPreferredSize(new Dimension(200, 30)); // Adjust size
        quantityPanel.add(quantityField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityPanel);

        inputPanel.add(new JLabel("Toppings:"));
        toppingPanel = new JPanel(new GridLayout(0, 1));
        toppingPanel.setBackground(Color.decode("#FFFFFF")); // Set the background color of the topping panel to white
        JScrollPane scrollPane = new JScrollPane(toppingPanel);

        for (Topping topping : toppings) {
            JCheckBox toppingCheckBox = new JCheckBox(topping.getName() + " (RM" + topping.getPrice() + ")");
            toppingCheckBox.setBackground(Color.decode("#FFFFFF")); // Set the background color of each topping checkbox to white
            toppingCheckBox.setForeground(Color.decode("#000000")); // Set the text color of the checkbox to black
            toppingCheckBox.addActionListener(e -> {
                if (toppingCheckBox.isSelected()) {
                    selectedToppings.add(topping);
                } else {
                    selectedToppings.remove(topping);
                }
            });
            toppingPanel.add(toppingCheckBox);
        }
        inputPanel.add(scrollPane);

        return inputPanel;
    }

    private JPanel createSummaryPanel() {
        JPanel wrapperPanel = new JPanel(); // Intermediate wrapper panel
        wrapperPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Use FlowLayout
        wrapperPanel.setPreferredSize(new Dimension(150, 450)); // Force fixed size
        wrapperPanel.setMinimumSize(new Dimension(150, 450));
        wrapperPanel.setMaximumSize(new Dimension(150, 450));

        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Order Summary"));

        // Text area
        orderSummary = new JTextArea();
        orderSummary.setEditable(false);

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(orderSummary);
        scrollPane.setPreferredSize(new Dimension(150, 400));

        // Total Label
        totalLabel = new JLabel("Total: RM0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add components to summaryPanel
        summaryPanel.add(scrollPane, BorderLayout.CENTER);
        summaryPanel.add(totalLabel, BorderLayout.SOUTH);

        // Add the summaryPanel to the wrapper panel
        wrapperPanel.add(summaryPanel);

        return wrapperPanel;
    }

    private JPanel createActionButtons() {
        JPanel actionPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JButton addFlavorButton = new JButton("Add Flavor");
        addFlavorButton.setBackground(Color.decode("#59282B"));  // Cyan (#00FFFF)
        addFlavorButton.setForeground(Color.decode("#FFFFFF"));  // Black (#000000)
        addFlavorButton.addActionListener(e -> addFlavor());
        actionPanel.add(addFlavorButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setBackground(Color.decode("#59282B"));  // Orange (#FFA500)
        resetButton.setForeground(Color.decode("#FFFFFF"));  // Black (#000000)
        resetButton.addActionListener(e -> resetAll());
        actionPanel.add(resetButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(Color.decode("#59282B"));  // Green (#008000)
        submitButton.setForeground(Color.decode("#FFFFFF"));  // White (#FFFFFF)
        submitButton.addActionListener(e -> finalizeOrder());
        actionPanel.add(submitButton);

        return actionPanel;
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