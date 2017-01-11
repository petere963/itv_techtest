package itv.checkout.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "pricingEngine")
@XmlAccessorType(XmlAccessType.FIELD)
public class PricingEngine {

    private static PricingEngine instance;
    private Map<Sku, PricingRule> pricingRules = new HashMap<>();

    public static PricingEngine getInstance() {
        if(instance == null) {
            instance = new PricingEngine();
        }
        return instance;
    }

    public Map<Sku, PricingRule> getPricingRules() {
        return pricingRules;
    }

    public void setPricingRules(Map<Sku, PricingRule> pricingRules) {
        this.pricingRules = pricingRules;
    }

    public void addPricingRule(PricingRule pricingRule) {
        pricingRules.put(pricingRule.getSku(), pricingRule);
    }

    public void addPricingRules(List<PricingRule> pricingRuleList) {
        pricingRuleList.stream().forEach(this :: addPricingRule);
    }

    public void clear() {
        pricingRules.clear();
    }

    public PricingRule getPricingRule(Sku sku) {
        return pricingRules.get(sku);
    }
}
