public class Flavor {
    private String name;
    private double price;
    private String iconPath;

    public Flavor(String name, double price, String iconPath) {
        this.name = name;
        this.price = price;
        this.iconPath = iconPath;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getIconPath() {
        return iconPath;
    }
}
