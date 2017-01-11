package itv.checkout.marshaller;

import itv.checkout.bean.PricingEngine;
import itv.checkout.bean.Basket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class CheckoutMarshaller {

    public static String marshall(Basket basket) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Basket.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(basket, sw);

        return sw.toString();
    }

    public static String marshall(PricingEngine pricingEngine) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(PricingEngine.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(pricingEngine, sw);

        return sw.toString();
    }
}
