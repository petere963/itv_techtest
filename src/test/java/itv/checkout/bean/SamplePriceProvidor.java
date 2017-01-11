package itv.checkout.bean;

public class SamplePriceProvidor {

    public final static PricingRule PRICING_RULE_A = new PricingRule(new Sku("A")).withUnitPrice(50).withSpecialPrice(130).withSpecialPriceUnit(3);
    public final static PricingRule PRICING_RULE_B =  new PricingRule(new Sku("B")).withUnitPrice(30).withSpecialPrice(45).withSpecialPriceUnit(2);
    public final static PricingRule PRICING_RULE_C =  new PricingRule(new Sku("C")).withUnitPrice(20);
    public final static PricingRule PRICING_RULE_D =  new PricingRule(new Sku("D")).withUnitPrice(15);

    public final static ScannedItem ITEM_A = new ScannedItem(new Sku("A"));
    public final static ScannedItem ITEM_B = new ScannedItem(new Sku("B"));
    public final static ScannedItem ITEM_C = new ScannedItem(new Sku("C"));
    public final static ScannedItem ITEM_D = new ScannedItem(new Sku("D"));

}
