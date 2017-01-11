package itv.checkout.bean;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static itv.checkout.bean.SamplePriceProvidor.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestPriceCalculator {

    private PriceCalculator priceCalculator;
    private List<ScannedItem> scannedItems;
    private PricingEngine pricingEngine;

    @Before
    public void setUp() {
        pricingEngine = PricingEngine.getInstance();
        pricingEngine.addPricingRule(PRICING_RULE_A);
        pricingEngine.addPricingRule(PRICING_RULE_B);
        priceCalculator = new PriceCalculator(pricingEngine);

        scannedItems = new ArrayList<>();
    }
    @Test
    public void shouldCalculatePriceWhenSingleItem() {
        scannedItems.add(ITEM_A);
        assertThat(priceCalculator.calculate(scannedItems), is(50));
    }

    @Test
    public void shouldCalculatePriceWhenMultipleItem() {
        scannedItems.add(ITEM_A);
        scannedItems.add(ITEM_B);
        assertThat(priceCalculator.calculate(scannedItems), is(80));
    }

    @Test
    public void shouldCalculatePriceWhenFreeItem() {
        pricingEngine.addPricingRule(new PricingRule(new Sku("C")));
        priceCalculator = new PriceCalculator(pricingEngine);
        scannedItems.add(ITEM_A);
        scannedItems.add(ITEM_B);
        scannedItems.add(new ScannedItem(new Sku("C")));
        assertThat(priceCalculator.calculate(scannedItems), is(80));
    }

    @Test
    public void shouldCalculatePriceWhenDuplicateItemWithoutSpecialPrice() {
        scannedItems.add(ITEM_A);
        scannedItems.add(ITEM_A);
        scannedItems.add(ITEM_B);
        assertThat(priceCalculator.calculate(scannedItems), is(130));
    }

    @Test
    public void shouldCalculatePriceWhenDuplicateItemWithSpecialPrice() {
        scannedItems.add(ITEM_A);       // 50
        scannedItems.add(ITEM_B);       // 80
        scannedItems.add(ITEM_B);       // 95  (2 at special price, so 50 + 45)
        scannedItems.add(ITEM_B);       // 125  (90 + 30)
        assertThat(priceCalculator.calculate(scannedItems), is(125));
    }

    @Test
    public void shouldCalculatePriceWhenOutOfSequence() {
        scannedItems.add(ITEM_B);       // 30
        scannedItems.add(ITEM_A);       // + 50 = 80
        scannedItems.add(ITEM_B);       // -30 + 45 = 95
        scannedItems.add(ITEM_A);       // + 50 = 145
        assertThat(priceCalculator.calculate(scannedItems), is(145));
    }
}
