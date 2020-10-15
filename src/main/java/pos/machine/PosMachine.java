package pos.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<ProductInfo> product = getProductInfo(barcodes);
        Receipt receipt = computeProduct(product);
        return collectReceipt(receipt);
    }

    private String collectReceipt(Receipt receipt) {
        String ProductDetailStr = spliceToDetail(receipt);
        return generateReceipt(receipt.getTotal(),ProductDetailStr);
    }

    private String generateReceipt(int total, String productDetailStr) {
        return new StringBuilder()
                .append("***<store earning no money>Receipt***\n")
                .append(productDetailStr).append("\n")
                .append("----------------------\nTotal: ")
                .append(total)
                .append(" (yuan)\n**********************").toString();
    }

    private String spliceToDetail(Receipt receipt) {
        String productDetailStr=null;
        ArrayList<String> productDetailList = new ArrayList<String>();
        for (Product product : receipt.getProductDetail()){
            productDetailStr = String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)",
                    product.getName(), product.getQty(), product.getUnitPrice(), product.getSubTotal());
            productDetailList.add(productDetailStr);
        }
        return String.join("\n",productDetailList);;
    }

    private List<ProductInfo> getProductInfo(List<String> barcodes) {
        List<ProductInfo> product = new ArrayList<>();
        List<ProductInfo> itemLoader = ItemDataLoader.loadAllItemInfos();
        for(String codes : barcodes){
            for (ProductInfo item : itemLoader){
              if (codes.equals(item.getBarcode())){
                  product.add(item);
              }
            }
        }
        return product;
    }

    private Receipt computeProduct(List<ProductInfo> product) {

        List<Product> productsWithQty = countQtyProduct(product);

        return computeTotal(productsWithQty);
    }




    private List<Product> countQtyProduct(List<ProductInfo> product) {
        List<Product> productWithQty = new ArrayList<>();

        List<ProductInfo> distinctProduct = product.stream()
                .distinct()
                .collect(Collectors.toList());

        for(ProductInfo prod : distinctProduct ){

            int count= (int) product.stream().filter(counting -> counting.getBarcode().equals(prod.getBarcode())).count();

            Product item = new Product(prod.getName(),count,prod.getPrice(),prod.getPrice()*count);
            productWithQty.add(item);

        }
        return productWithQty;
    }

    private Receipt computeTotal(List<Product> productsWithQty) {
        Receipt receipt = new Receipt();
        int total = productsWithQty.stream().mapToInt(Product::getSubTotal).sum();
        receipt.setProductDetail(productsWithQty);
        receipt.setTotal(total);
        return receipt;
    }

}
