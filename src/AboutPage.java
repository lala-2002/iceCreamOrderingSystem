import javax.swing.*;
import java.awt.*;

public class AboutPage {
    JFrame frame;

    AboutPage() {
        frame = new JFrame("Store Details");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel detailsLabel = new JLabel("Welcome to the Ice Cream Paradise!", JLabel.CENTER);
        detailsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea detailsArea = new JTextArea();
        detailsArea.setText("At Ice Cream Paradise, we serve the finest and most delicious ice creams.\n" +
                "Our store offers a variety of flavors including Vanilla, Chocolate, Strawberry, and Mint.\n" +
                "Enjoy our delightful toppings such as Sprinkles and Chocolate Sauce to make your ice cream extra special.\n" +
                "We are open daily from 10:00 AM to 10:00 PM.\n" +
                "Come and treat yourself to the best ice cream experience!");
        detailsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        detailsArea.setEditable(false);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(detailsArea);

        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> {
            frame.dispose();
            new HomePage();
        });

        frame.add(detailsLabel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
