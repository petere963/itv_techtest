package itv.checkout.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "basket")
@XmlAccessorType(XmlAccessType.FIELD)
public class Basket {

    private List<ScannedItem> scannedItemsList;
    private int value;

    public Basket() {
        scannedItemsList = new ArrayList<>();
    }

    public List<ScannedItem> getScannedItemsList() {
        return scannedItemsList;
    }

    public Basket setScannedItemsList(List<ScannedItem> scannedItemsList) {
        this.scannedItemsList = scannedItemsList;
        return this;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addItem(ScannedItem scannedItem) {
        scannedItemsList.add(scannedItem);
    }

    public void clear() {
        scannedItemsList.clear();
        value = 0;
    }
}
