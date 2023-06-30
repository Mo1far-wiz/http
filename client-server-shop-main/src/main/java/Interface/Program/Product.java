package Interface.Program;
public class Product {
    private String name;
    private String description;
    private String brand;
    private double price;
    private int number;

    public Product(String name, int number, double price, String brand,
                   String description) {
        this.name = name;
        this.price = price;
        this.number = number;
        this.brand = brand;
        this.description = description;
    }

    public Product(String name) {
        this.name = name;
    }

    public void add(int n) {
        number += n;
    }

    public void remove(int n) {
        if (number == 0) return;
        if (n > number) {
            number = 0;
        } else {
            number -= n;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public String toString() {
        return name + "\n" +
                " Price: " + price + " UAH \n" +
                " Amount: " + number + "\n" +
                " Manufacturer: " + brand + "\n" +
                " Description: " + description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}