import java.util.ArrayList;
import javax.swing.*;

public class Receipt {
    private final ArrayList<OrderForm.OrderItem> orders; // List of order items
    private final String customerName; // Customer's name

    // Constructor to initialize the orders and customer name
    public Receipt(ArrayList<OrderForm.OrderItem> orders, String customerName) {
        this.orders = orders;
        this.customerName = customerName;
    }

    // Method to calculate total order price
    private double calculateTotalPrice() {
        return orders.stream()
                .mapToDouble(order -> order.getTotal())
                .sum();
    }

    // Method to generate and display the receipt
    public void displayReceipt() {
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No items in the order. Please add at least one flavor.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Generate the receipt using HTML formatting
        StringBuilder receipt = new StringBuilder("<html><body>");
        receipt.append("<h1 style=\"font-family:'Comic Sans MS';\">Ice Cream Paradise</h1><br/>");
        receipt.append("<h3>Receipt</h3><br/>");
        receipt.append("Customer: ").append(customerName).append("<br/><br/>");

        for (OrderForm.OrderItem order : orders) {
            receipt.append(order).append("<br/>");
        }

        receipt.append(String.format("Grand Total: RM%.2f", calculateTotalPrice()));
        receipt.append("<br/><br/>Thank you for your order, ").append(customerName).append("!");
        receipt.append("</body></html>");

        // Display the receipt
        JOptionPane.showMessageDialog(null, receipt.toString(),
                "Order Receipt", JOptionPane.INFORMATION_MESSAGE);
    }
}
