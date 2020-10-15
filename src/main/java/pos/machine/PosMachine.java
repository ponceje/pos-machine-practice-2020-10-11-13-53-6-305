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
        String receitStr = collectReceipt(receipt);
        
        return receitStr;
    }

    private String collectReceipt(Receipt receipt) {
        String ProductDetailStr = spliceToDetail(receipt);
        String receiptStr= generateReceipt(receipt.getTotal(),ProductDetailStr);
        return receiptStr;
    }

    private String generateReceipt(int total, String productDetailStr) {
        String receipt="***<store earning no money>Receipt***\n"+productDetailStr+"\n"+
                "----------------------\nTotal: "+total+" (yuan)\n**********************";
        return receipt;
    }

    private String spliceToDetail(Receipt receipt) {
        String productDetailStr=null;
        ArrayList<String> productDetailList = new ArrayList<String>();
        for (Product product : receipt.getProductDetail()){
            productDetailStr = new StringBuilder().append("Name: ").append(product.getName()).append(", Quantity: ").append(product.getQty()).append(", Unit price: ").append(product.getUnitPrice()).append(" (yuan), Subtotal: ").append(product.getSubTotal()).append(" (yuan)").toString();
            productDetailList.add(productDetailStr);
        }
        productDetailStr=String.join("\n",productDetailList);

        return productDetailStr;
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
