package pos.machine;

import java.util.Objects;

public class ProductInfo {
    private final String barcode;
    private final String name;
    private final int price;

    public ProductInfo(String barcode, String name, int price) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfo that = (ProductInfo) o;
        return price == that.price &&
                Objects.equals(barcode, that.barcode) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode, name, price);
    }
}
