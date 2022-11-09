package com.automation.pageObjectModel.tests;

import com.automation.pageObjectModel.io.EnvironmentVarUtil;
import com.automation.pageObjectModel.model.Item;
import com.automation.pageObjectModel.page.*;
import com.automation.pageObjectModel.service.AbstractSeleniumTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

public class ShoppingCartTest extends AbstractSeleniumTest {
    private final static int MINIMUM_PRICE = 10;
    private final static int MAXIMUM_PRICE = 50;

    @Test
    public void log_in_swag_lab() {
        final String baseUrl = getTestData().getUrl();
        webDriver().get(baseUrl);

        LoginPage.by(webDriver()).with()
                .userNameOf(EnvironmentVarUtil.getAppUsername()).and()
                .passwordOf(EnvironmentVarUtil.getAppPassword())
                .tryToLogin().and()
                .navigateToProductsPage();

        final List<Item> selectedItems = ProductsPage.by(webDriver()).and()
                .addItemsInPriceRangeToCart(MAXIMUM_PRICE, MINIMUM_PRICE);
        ProductsPage.by(webDriver())
                .navigateToCartPage();

        final List<Item> cartItems = CartPage.by(webDriver())
                .getCartItems();
        CartPage.by(webDriver())
                .navigateToCheckout();

        Assertions.assertTrue(selectedItems.containsAll(cartItems));

        CheckoutYourInfoPage.by(webDriver()).fill()
                .firstNameOf(getTestData().getCustomer().getCustomerFirstName()).and()
                .lastNameOf(getTestData().getCustomer().getCustomerLastName()).and()
                .postalCodeOf(getTestData().getCustomer().getCustomerPostalCode()).and()
                .clickContinueButton();

        final double cartPriceIncludingTax = CheckoutOverviewPage.by(webDriver())
                .getCartPrice();
        CheckoutOverviewPage.by(webDriver())
                .clickFinishButton();

        Assertions.assertEquals(getTotalPriceWithTax(cartItems), cartPriceIncludingTax);

        final String confirmationMessage = CheckoutCompletePage.by(webDriver())
                .getThankYouMessage();
        Assertions.assertEquals("THANK YOU FOR YOUR ORDER", confirmationMessage);
    }

    private double getTotalPriceWithTax(final List<Item> itemsList) {
        double calculatedPrice = itemsList
                .stream()
                .map(Item::getItemPrice)
                .reduce(0.0, Double::sum);

        return Optional.of(calculatedPrice)
                .map(price -> (price * 0.08) + price)
                .map(price -> Double.valueOf(new DecimalFormat("#.##").format(price)))
                .orElse(0.0);
    }
}
