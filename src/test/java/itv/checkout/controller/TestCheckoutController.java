package itv.checkout.controller;

import itv.checkout.Checkout;
import itv.checkout.bean.Basket;
import itv.checkout.bean.CheckoutSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(MockitoJUnitRunner.class)
public class TestCheckoutController {

    @Mock
    private CheckoutSession checkoutSession;

    private CheckoutController checkoutController;
    private Basket basket;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        basket = new Basket();
        when(checkoutSession.getBasket()).thenReturn(basket);

        checkoutController = new CheckoutController(checkoutSession);
        mockMvc = MockMvcBuilders.standaloneSetup(checkoutController).build();
    }
    @Test
    public void shouldReturnEmptyBasket() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/checkout"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(xpath("basket/value").string("0"));
    }
}
