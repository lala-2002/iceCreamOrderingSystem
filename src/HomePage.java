import javax.swing.*;
import java.awt.*;

public class HomePage {
    JFrame frame;

    HomePage() {
        frame = new JFrame("Ice Cream Ordering System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to Ice Cream Ordering System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel buttonPanel = new JPanel();
        JButton aboutButton = new JButton("About");
        JButton orderButton = new JButton("Order Now");

        aboutButton.addActionListener(e -> {
            frame.dispose();
            new AboutPage();
        });

        orderButton.addActionListener(e -> {
            frame.dispose();
            new OrderForm();
        });

        buttonPanel.add(aboutButton);
        buttonPanel.add(orderButton);

        frame.add(welcomeLabel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
