import javax.swing.*;
import java.awt.*;

public class Receipt {
    JFrame frame;

    Receipt(String name, String flavor, int quantity, boolean sprinkles, boolean chocolate) {
        frame = new JFrame("Receipt");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1));

        frame.add(new JLabel("Thank you for your order, " + name + "!"));
        frame.add(new JLabel("Flavor: " + flavor));
        frame.add(new JLabel("Quantity: " + quantity));

        String toppings = "Toppings: ";
        if (sprinkles) toppings += "Sprinkles ";
        if (chocolate) toppings += "Chocolate Sauce ";
        if (!sprinkles && !chocolate) toppings += "None";
        frame.add(new JLabel(toppings));

        double price = 5.00;
        if (sprinkles) price += 1.00;
        if (chocolate) price += 1.50;
        double total = price * quantity;
        frame.add(new JLabel(String.format("Total Price: RM%.2f", total)));

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> frame.dispose());
        frame.add(closeButton);

        frame.setVisible(true);
    }
}
