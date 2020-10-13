package pos.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            productDetailStr ="Name: "+product.getName()+", Quantity: "+product.getQty()+", Unit price: "+product.getUnitPrice()+" (yuan), Subtotal: "+product.getSubTotal()+" (yuan)";
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
                String getBarcode = item.getBarcode();
              if (codes.equals(getBarcode)){
                  product.add(item);
              }
            }
        }
        return product;
    }

    private Receipt computeProduct(List<ProductInfo> product) {
        Receipt receipt = null;

        List<Product> productsWithQty = countQtyProduct(product);
        receipt = computeTotal(productsWithQty);

        return receipt;
    }




    private List<Product> countQtyProduct(List<ProductInfo> product) {
        List<Product> productWithQty = new ArrayList<>();
        List<ProductInfo> distinctProduct = new ArrayList<>();
        Set<ProductInfo> uniqueValues = new HashSet<>();
        for (ProductInfo productInfo : product) {
            if (uniqueValues.add(productInfo)) {
                distinctProduct.add(productInfo);
            }
        }

        for(ProductInfo prod : distinctProduct ){
            int count=0;
            for (ProductInfo prodList : product){
                if(prod.getBarcode().equals(prodList.getBarcode())){
                    count++;
                }
            }
            Product item = new Product(prod.getName(),count,prod.getPrice(),prod.getPrice()*count);
            productWithQty.add(item);
        }
        return productWithQty;
    }


    private Receipt computeTotal(List<Product> productsWithQty) {
        Receipt receipt = new Receipt();
        int total = 0;
        for (Product product : productsWithQty){
            total = total+product.subTotal;
        }
        receipt.setProductDetail(productsWithQty);
        receipt.setTotal(total);
        return receipt;
    }

}
