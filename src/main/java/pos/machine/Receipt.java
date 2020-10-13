package pos.machine;

import java.util.List;

public class Receipt {
    List<Product> productDetail;
    int total;

    public Receipt() {

    }

    public List<Product> getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(List<Product> productDetail) {
        this.productDetail = productDetail;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
