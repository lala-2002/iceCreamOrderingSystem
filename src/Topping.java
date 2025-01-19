public class Topping {
    private final String name;
    private final double price;

    public Topping(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getter for topping name
    public String getName() {
        return name;
    }

    // Getter for topping price
    public double getPrice() {
        return price;
    }
}