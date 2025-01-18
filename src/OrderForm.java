import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderForm {
    JFrame frame;
    JTextField nameField, quantityField;
    JComboBox<String> flavorBox;
    JCheckBox toppingSprinkles, toppingChocolate;
    JLabel totalLabel;

    OrderForm() {
        frame = new JFrame("Order Form");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(8, 2, 10, 10));

        frame.add(new JLabel("Name:"));
        nameField = new JTextField();
        frame.add(nameField);

        frame.add(new JLabel("Flavor:"));
        flavorBox = new JComboBox<>(new String[]{"Vanilla", "Chocolate", "Strawberry", "Mint"});
        frame.add(flavorBox);

        frame.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        frame.add(quantityField);

        frame.add(new JLabel("Toppings:"));
        JPanel toppingPanel = new JPanel();
        toppingSprinkles = new JCheckBox("Sprinkles");
        toppingChocolate = new JCheckBox("Chocolate Sauce");
        toppingPanel.add(toppingSprinkles);
        toppingPanel.add(toppingChocolate);
        frame.add(toppingPanel);

        JButton calculateButton = new JButton("Calculate Total");
        calculateButton.addActionListener(new CalculateListener());
        frame.add(calculateButton);

        totalLabel = new JLabel("Total: RM0.00");
        frame.add(totalLabel);

        JButton submitButton = new JButton("Submit Order");
        submitButton.addActionListener(e -> {
            frame.dispose();
            new Receipt(nameField.getText(), flavorBox.getSelectedItem().toString(),
                    Integer.parseInt(quantityField.getText()),
                    toppingSprinkles.isSelected(), toppingChocolate.isSelected());
        });
        frame.add(submitButton);

        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> {
            frame.dispose();
            new HomePage();
        });
        frame.add(backButton);

        frame.setVisible(true);
    }

    class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int quantity = Integer.parseInt(quantityField.getText());
            double price = 5.00; // Base price
            if (toppingSprinkles.isSelected()) price += 1.00;
            if (toppingChocolate.isSelected()) price += 1.50;
            double total = price * quantity;
            totalLabel.setText(String.format("Total: RM%.2f", total));
        }
    }
}
