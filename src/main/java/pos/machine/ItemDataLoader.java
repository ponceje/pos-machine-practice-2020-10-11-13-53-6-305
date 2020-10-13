package pos.machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemDataLoader {
    public static List<ProductInfo> loadAllItemInfos() {
        ProductInfo item1Info = new ProductInfo("ITEM000000", "Coca-Cola", 3);
        ProductInfo item2Info = new ProductInfo("ITEM000001", "Sprite", 3);
        ProductInfo item3Info = new ProductInfo("ITEM000002", "Apple", 5);
        ProductInfo item4Info = new ProductInfo("ITEM000003", "Litchi", 15);
        ProductInfo item5Info = new ProductInfo("ITEM000004", "Battery", 2);
        ProductInfo item6Info = new ProductInfo("ITEM000005", "Instant Noodles", 4);
        List<ProductInfo> itemInfos = new ArrayList<>();
        itemInfos.add(item1Info);
        itemInfos.add(item2Info);
        itemInfos.add(item3Info);
        itemInfos.add(item4Info);
        itemInfos.add(item5Info);
        itemInfos.add(item6Info);

        return itemInfos;
    }

    public static List<String> loadBarcodes() {
        return Arrays.asList("ITEM000000", "ITEM000000", "ITEM000000", "ITEM000000", "ITEM000001", "ITEM000001", "ITEM000004", "ITEM000004", "ITEM000004");
    }
}
