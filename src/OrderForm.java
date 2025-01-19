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

    public OrderForm() {
        frame = new JFrame("Ice Cream Order Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Sample flavors and toppings
        Flavor[] flavors = {
                new Flavor("Vanilla", 5.00),
                new Flavor("Chocolate", 5.00),
                new Flavor("Strawberry", 5.00),
                new Flavor("Mint", 5.00)
        };

        Topping[] toppings = {
                new Topping("Sprinkles", 1.00),
                new Topping("Chocolate Sauce", 1.50),
                new Topping("Walnut", 2.00),
                new Topping("Marshmallow", 1.75)
        };

        selectedToppings = new ArrayList<>();
        orders = new ArrayList<>();

        JPanel inputPanel = createInputForm(flavors, toppings);
        JPanel summaryPanel = createSummaryPanel();
        JPanel actionPanel = createActionButtons();

        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(summaryPanel, BorderLayout.EAST);
        frame.add(actionPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createInputForm(Flavor[] flavors, Topping[] toppings) {
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Flavor:"));
        flavorBox = new JComboBox<>(flavors);
        inputPanel.add(flavorBox);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("Toppings:"));
        toppingPanel = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(toppingPanel);

        for (Topping topping : toppings) {
            JCheckBox toppingCheckBox = new JCheckBox(topping.getName() + " (RM" + topping.getPrice() + ")");
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
        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Order Summary"));

        orderSummary = new JTextArea(10, 30);
        orderSummary.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderSummary);

        totalLabel = new JLabel("Total: RM0.00");

        summaryPanel.add(scrollPane, BorderLayout.CENTER);
        summaryPanel.add(totalLabel, BorderLayout.SOUTH);

        return summaryPanel;
    }

    private JPanel createActionButtons() {
        JPanel actionPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JButton addFlavorButton = new JButton("Add Flavor");
        addFlavorButton.addActionListener(e -> addFlavor());
        actionPanel.add(addFlavorButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetAll());
        actionPanel.add(resetButton);

        JButton submitButton = new JButton("Submit");
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