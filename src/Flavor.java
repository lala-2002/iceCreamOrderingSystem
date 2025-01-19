public class Flavor {
    private final String name;
    private final double price;

    public Flavor(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getter for the flavor name
    public String getName() {
        return name;
    }

    // Getter for the price
    public double getPrice() {
        return price;
    }

    // Display the flavor as a string (useful for combo box)
    @Override
    public String toString() {
        return String.format("%s (RM%.2f)", name, price);
    }
}