package itv.checkout.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PriceCalculator {
    private Map<Sku, PricingRule> pricingRules;
    private Map<Sku, AtomicInteger> itemsCount;
    private Map<Sku, ScannedItem> itemsList;
    private  int totalPrice = 0;

    public PriceCalculator(PricingEngine pricingEngine) {
        loadPricingRules(pricingEngine);

        itemsCount = new HashMap();
        itemsList = new HashMap();
    }

    private void loadPricingRules(PricingEngine pricingEngine) {
        pricingRules = new HashMap<>();
        pricingEngine.getPricingRules().values().stream().forEach(pricingRule -> {
            pricingRules.put(pricingRule.getSku(), pricingRule);
        });
    }

    public int calculate(List<ScannedItem> scannedItems) {
        itemsList.clear();
        itemsCount.clear();
        totalPrice = 0;

        // Count the number of each item (to get bulk discounts) and then calculate the item totals
        scannedItems.stream().forEach(this :: addItemCount);
        itemsList.values().stream().forEach(this :: accumulateItemTotal);
        return totalPrice;
    }

    public boolean hasPrice(ScannedItem scannedItem) {
        return pricingRules.get(scannedItem.getSku()) != null;
    }

    private void addItemCount(ScannedItem scannedItem) {
        if(itemsCount.get(scannedItem.getSku()) == null) {
            itemsCount.put(scannedItem.getSku(), new AtomicInteger());
        }
        itemsCount.get(scannedItem.getSku()).getAndIncrement();
        itemsList.put(scannedItem.getSku(), scannedItem);
    }

    private void accumulateItemTotal(ScannedItem scannedItem) {
        PricingRule pricingRule = pricingRules.get(scannedItem.getSku());
        int itemCount = itemsCount.get(pricingRule.getSku()).get();

        if(pricingRule.hasSpecialPrice()) {
            int specialPriceMultiplier = itemCount/ pricingRule.getSpecialPriceUnit();
            int standardPriceMultiplier = itemCount % pricingRule.getSpecialPriceUnit();
            totalPrice += (specialPriceMultiplier * pricingRule.getSpecialPrice());
            totalPrice += pricingRule.getUnitPrice() * standardPriceMultiplier;
        }
        else {
            totalPrice += pricingRule.getUnitPrice() * itemCount;
        }
    }
}
