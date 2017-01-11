package itv.checkout.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sku")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sku {

    String skuValue;

    public Sku(){}

    public Sku(String skuValue) {
        this.skuValue = skuValue;
    }

    public String getSkuValue() {
        return skuValue;
    }

    public void setSkuValue(String skuValue) {
        this.skuValue = skuValue;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Sku sku = (Sku) o;

        return new EqualsBuilder()
                .append(skuValue, sku.skuValue)
                .isEquals();
    }

    @Override public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(skuValue)
                .toHashCode();
    }
}
