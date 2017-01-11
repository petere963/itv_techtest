package itv.checkout.bean;

import itv.checkout.exception.NoPriceException;
import org.springframework.stereotype.Component;

@Component
public class CheckoutSession {

    private Basket basket;
    private PriceCalculator priceCalculator;

    public CheckoutSession() {
        basket = new Basket();
    }

    public Basket getBasket() {
        return basket;
    }

    /**
     * Scan an item - when the first item is scanned, the Pricing information is updated, in case there have been any changes
     */
    public void scan(ScannedItem scannedItem) throws NoPriceException {

        // Reset prices if first scan
        if(basket.getScannedItemsList().size()==0) {
            priceCalculator = new PriceCalculator(PricingEngine.getInstance());
        }

        if(!priceCalculator.hasPrice(scannedItem)) {
            throw new NoPriceException();
        }
        basket.addItem(scannedItem);
        basket.setValue(getRunningTotal());
    }

    public int getRunningTotal() {
       return priceCalculator.calculate(basket.getScannedItemsList());
    }

    public int checkout() {
        int value = getRunningTotal();
        basket.clear();
        return value;
    }
}