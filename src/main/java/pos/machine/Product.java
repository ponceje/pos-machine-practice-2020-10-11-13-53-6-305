package pos.machine;

public class Product {
    String name;
    int qty;
    int unitPrice;
    int subTotal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public Product(String name, int qty, int unitPrice, int subTotal) {

        this.name = name;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }
}
