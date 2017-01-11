package itv.checkout.bean;

import itv.checkout.exception.NoPriceException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static itv.checkout.bean.SamplePriceProvidor.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestCheckoutSession {

    private CheckoutSession checkoutSession;
    private PricingEngine pricingEngine;

    @Before
    public void setup() {
        checkoutSession = new CheckoutSession();
        pricingEngine = PricingEngine.getInstance();
        pricingEngine.clear();
    }

    @Test
    public void shouldCheckoutSingleItem() throws NoPriceException {
        pricingEngine.addPricingRule(PRICING_RULE_A);
        checkoutSession.scan(ITEM_A);
        assertThat(checkoutSession.checkout(), is(50));
    }

    @Test
    public void shouldCheckoutMultipleSingleItem() throws NoPriceException {
        pricingEngine.addPricingRule(PRICING_RULE_A);
        checkoutSession.scan(ITEM_A);
        checkoutSession.scan(ITEM_A);
        checkoutSession.scan(ITEM_A);
        checkoutSession.scan(ITEM_A);
        checkoutSession.scan(ITEM_A);
        assertThat(checkoutSession.checkout(), is(230));
    }

    @Test
    public void shouldCheckoutMixedItem() throws NoPriceException {
        pricingEngine.addPricingRule(PRICING_RULE_A);
        pricingEngine.addPricingRule(PRICING_RULE_B);
        pricingEngine.addPricingRule(PRICING_RULE_C);
        pricingEngine.addPricingRule(PRICING_RULE_D);
        checkoutSession.scan(ITEM_A);                //50
        assertThat(checkoutSession.getRunningTotal(), is(50));
        checkoutSession.scan(ITEM_B);
        assertThat(checkoutSession.getRunningTotal(), is(80));
        checkoutSession.scan(ITEM_C);               // 80 + 20 = 100
        assertThat(checkoutSession.getRunningTotal(), is(100));
        checkoutSession.scan(ITEM_D);               // 100 + 15 = 115
        assertThat(checkoutSession.getRunningTotal(), is(115));
        checkoutSession.scan(ITEM_A);               // 115 + 50 = 165
        assertThat(checkoutSession.getRunningTotal(), is(165));
        checkoutSession.scan(ITEM_B);               // 165 - 30 + 45 = 180
        assertThat(checkoutSession.getRunningTotal(), is(180));
        assertThat(checkoutSession.checkout(), is(180));

        // Should reset after checkout
        assertThat(checkoutSession.getRunningTotal(), is(0));
    }

    @Test
    public void shouldAddmultiplteRuelsAsList() throws NoPriceException {
        ArrayList<PricingRule> pricingRules = new ArrayList<>();
        pricingRules.add(PRICING_RULE_A);
        pricingRules.add(PRICING_RULE_B);
        pricingRules.add(PRICING_RULE_C);
        pricingRules.add(PRICING_RULE_D);
        pricingEngine.addPricingRules(pricingRules);
        checkoutSession.scan(ITEM_A);                //50
        checkoutSession.scan(ITEM_B);               // 50 + 30 = 80
        checkoutSession.scan(ITEM_C);               // 80 + 20 = 100
        checkoutSession.scan(ITEM_D);               // 100 + 15 = 115
        checkoutSession.scan(ITEM_A);               // 115 + 50 = 165
        checkoutSession.scan(ITEM_B);               // 165 - 30 + 45 = 180
        assertThat(checkoutSession.checkout(), is(180));
    }

    @Test(expected=NoPriceException.class)
    public void shouldThrowExceptionWhenScanningItemWithNoPricingRule() throws NoPriceException {
        checkoutSession.scan(new ScannedItem(new Sku("AAA")));
    }
}
