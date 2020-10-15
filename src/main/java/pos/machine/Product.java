package pos.machine;

public class Product {
    String name;
    int qty;
    int unitPrice;
    int subTotal;

    public String getName() {
        return name;
    }


    public int getQty() {
        return qty;
    }


    public int getUnitPrice() {
        return unitPrice;
    }


    public int getSubTotal() {
        return subTotal;
    }


    public Product(String name, int qty, int unitPrice, int subTotal) {

        this.name = name;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }
}
