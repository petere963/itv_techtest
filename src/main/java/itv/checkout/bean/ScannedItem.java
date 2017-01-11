package itv.checkout.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "skannedItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class ScannedItem {


    private Sku sku;

    public ScannedItem(){}

    public ScannedItem(Sku sku) {
        this.sku = sku;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }
}
