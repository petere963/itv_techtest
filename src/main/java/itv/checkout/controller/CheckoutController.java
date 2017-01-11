package itv.checkout.controller;

import itv.checkout.bean.*;
import itv.checkout.marshaller.CheckoutMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

/**
 * A Controller which provides some simple rest functions to test the Checkout
 */
@RestController
@RequestMapping("/")
public class CheckoutController {

    private final CheckoutSession checkoutSession;

    @Autowired
    public CheckoutController(final CheckoutSession checkoutSession) {
        this.checkoutSession = checkoutSession;
    }

    /**
     * Display the Status - returns a list of the current scanned items
     */
    @RequestMapping( value = "/status", produces = { "application/xml" })
    @ResponseBody
    public String displayCheckout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        try {
            return CheckoutMarshaller.marshall(checkoutSession.getBasket());
        } catch (Exception e) {
            e.printStackTrace();
            return "<error>" + e.toString() + "</error>";
        }
    }

    /**
     * Display the current pricing rules
     */
    @RequestMapping( value = "/pricing", produces = { "application/xml" })
    @ResponseBody
    public String displayPricing(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        return getPricingContent();
    }

    /**
     * Set a Pricing rule. Existing rules will be updated
     *
     * @param sku
     * @param unitPrice
     * @param specialPrice
     * @param specialPriceUnits
     * @return xml representation of the pricing rules.
     */
    @RequestMapping( value = "/setPrice", produces = { "application/xml" })
    @ResponseBody
    public String setPrice(@RequestParam("sku") String sku,
            @RequestParam("unitPrice") int unitPrice,
            @RequestParam("specialPrice") int specialPrice,
            @RequestParam("specialPriceUnits") int specialPriceUnits) {

        PricingRule pricingRule = new PricingRule(new Sku(sku))
                .withUnitPrice(unitPrice)
                .withSpecialPrice(specialPrice)
                .withSpecialPriceUnit(specialPriceUnits);
        PricingEngine.getInstance().addPricingRule(pricingRule);

        return getPricingContent();
    }

    /**
     * Scan an item. The item's sku must have a pricing rule set for it.
     * @param sku
     * @return An xml representation of the current scanned items
     */
    @RequestMapping( value = "/scanItem", produces = { "application/xml" })
    @ResponseBody
    public String scanItem(@RequestParam("sku") String sku) {

        try {
            checkoutSession.scan(new ScannedItem(new Sku(sku)));
            return CheckoutMarshaller.marshall(checkoutSession.getBasket());
        } catch (Exception e) {
            e.printStackTrace();
            return "<error>" + e.toString() + "</error>";
        }
    }

    /**
     * Checkout the scanned items. The total value is returned. The scanned items list is reset
     *
     * @return
     */
    @RequestMapping( value = "/checkout", produces = { "application/xml" })
    @ResponseBody
    public String checkout() {

        try {
            String basketXml = CheckoutMarshaller.marshall(checkoutSession.getBasket());
            checkoutSession.checkout();
            return basketXml;
        } catch (JAXBException e) {
            e.printStackTrace();
            return "<error>" + e.toString() + "</error>";
        }
    }

    private String getPricingContent() {
        try {
            return CheckoutMarshaller.marshall(PricingEngine.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
            return "<error>" + e.toString() + "</error>";
        }
    }
}
