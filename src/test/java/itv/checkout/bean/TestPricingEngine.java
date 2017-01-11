package itv.checkout.bean;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestPricingEngine {

    private PricingEngine pricingEngine;
    private Sku SKU_A = new Sku("A");
    private Sku SKU_B = new Sku("B");
    private Sku SKU_C = new Sku("C");

    @Before
    public void setUp() {
        pricingEngine = PricingEngine.getInstance();
        pricingEngine.clear();
    }

    @Test
    public void shouldInstantiateAsSingleton() {
        PricingEngine pricingEngine2 = PricingEngine.getInstance();
        assertThat(pricingEngine2, is(pricingEngine));
    }

    @Test
    public void shouldAddSinglePricingRule() {
        PricingRule pricingRule = new PricingRule(SKU_A);
        pricingEngine.addPricingRule(pricingRule);
        assertThat(pricingEngine.getPricingRules().size(), is(1));
    }

    @Test
    public void shouldAddMultiplePricingRule() {
        List<PricingRule> pricingRuleList = new ArrayList<>();
        pricingRuleList.add(new PricingRule(SKU_A));
        pricingRuleList.add(new PricingRule(SKU_B));
        pricingRuleList.add(new PricingRule(SKU_C));
        pricingEngine.addPricingRules(pricingRuleList);
        assertThat(pricingEngine.getPricingRules().size(), is(3));
    }

    @Test
    public void shouldUpdateExistingPricingRule() {
        List<PricingRule> pricingRuleList = new ArrayList<>();
        pricingRuleList.add(new PricingRule(SKU_A));
        pricingRuleList.add(new PricingRule(SKU_B));
        pricingRuleList.add(new PricingRule(SKU_A));
        pricingEngine.addPricingRules(pricingRuleList);
        assertThat(pricingEngine.getPricingRules().size(), is(2));
    }

    @Test
    public void shouldRetrievePricingRule() {
        Sku sku = new Sku("A");
        PricingRule pricingRule = new PricingRule(sku);
        pricingEngine.addPricingRule(pricingRule);
        PricingRule pricingRule2 = pricingEngine.getPricingRule(sku);
        assertThat(pricingRule2.getSku(), is(pricingRule.getSku()));
    }
}
