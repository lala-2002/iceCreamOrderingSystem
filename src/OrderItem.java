import java.util.ArrayList;

public class OrderItem {
    private final String name;
    private final Flavor flavor;
    private final ArrayList<Topping> toppings;
    private final double total;
    private final int quantity;

    // Constructor to initialize order item attributes
    public OrderItem(String name, Flavor flavor, ArrayList<Topping> toppings, double total, int quantity) {
        this.name = name;
        this.flavor = flavor;
        this.toppings = toppings;
        this.total = total;
        this.quantity = quantity;
    }

    // Getter for name (customer name)
    public String getName() {
        return name;
    }

    // Getter for total price
    public double getTotal() {
        return total;
    }

    // Override toString for order summary formatting
    @Override
    public String toString() {
        StringBuilder toppingsText = new StringBuilder();
        for (Topping topping : toppings) {
            toppingsText.append(topping.getName()).append(", ");
        }
        if (toppingsText.length() > 0) {
            toppingsText.setLength(toppingsText.length() - 2); // Remove trailing comma and space
        } else {
            toppingsText.append("No toppings");
        }
        return String.format("%d x %s with %s = RM%.2f", quantity, flavor.getName(), toppingsText.toString(), total);
    }
}