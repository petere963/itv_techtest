package itv.checkout.bean;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestItem {

    private static final Sku SKU_A = new Sku("A");

    @Test
    public void shouldReturnSku() {
        PricingRule pricingRule = new PricingRule(SKU_A);
        assertThat(pricingRule.getSku().getSkuValue(), is("A"));
    }

    @Test
    public void shouldReturnUnitPrice() {
        PricingRule pricingRule = new PricingRule(SKU_A).withUnitPrice(50);
        assertThat(pricingRule.getUnitPrice(), is(50));
    }

    @Test
    public void shouldReturnSpecialPriceUnits() {
        PricingRule pricingRule = new PricingRule(SKU_A).withSpecialPriceUnit(3);
        assertThat(pricingRule.getSpecialPriceUnit(), is(3));
    }

    @Test
    public void shouldReturnSpecialPrice() {
        PricingRule pricingRule = new PricingRule(SKU_A).withSpecialPrice(130);
        assertThat(pricingRule.getSpecialPrice(), is(130));
    }

    @Test
    public void shouldKnowWhenHasSpecialPrice() {
        PricingRule pricingRule = new PricingRule(SKU_A).withSpecialPriceUnit(3);
        assertThat(pricingRule.hasSpecialPrice(), is(false));
        pricingRule = pricingRule.withSpecialPrice(50);
        assertThat(pricingRule.hasSpecialPrice(), is(true));
    }
}
