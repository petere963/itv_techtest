package itv.checkout.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pricingRule")
@XmlAccessorType(XmlAccessType.FIELD)
public class PricingRule {
    private Sku sku;
    private int unitPrice;
    private int specialPrice;
    private int specialPriceUnit;

    public PricingRule(){}

    public PricingRule(Sku sku) {
        this.sku = sku;
    }

    public Sku getSku() {
        return sku;
    }

    public PricingRule withUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public PricingRule withSpecialPriceUnit(int specialPriceUnit) {
        this.specialPriceUnit = specialPriceUnit;
        return this;
    }

    public PricingRule withSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
        return this;
    }

    public int getSpecialPrice() {
        return specialPrice;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getSpecialPriceUnit() {
        return specialPriceUnit;
    }

    public void setSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
    }

    public void setSpecialPriceUnit(int specialPriceUnit) {
        this.specialPriceUnit = specialPriceUnit;
    }

    public boolean hasSpecialPrice() {
        return (specialPrice > 0) && (specialPriceUnit > 0) ? true : false;
    }
}
