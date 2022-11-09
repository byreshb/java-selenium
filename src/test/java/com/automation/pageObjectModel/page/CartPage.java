package com.automation.pageObjectModel.page;

import com.automation.pageObjectModel.model.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartPage {
    private final By itemNameSelector = By.className("inventory_item_name");
    private final By itemIdSelector = By.cssSelector("div[class='cart_item_label'] a");
    private final By itemPriceSelector = By.className("inventory_item_price");

    @FindBy(className = "cart_item")
    private List<WebElement> cartItemsSelector;

    @FindBy(css = "button[class='btn btn_action btn_medium checkout_button']")
    private WebElement checkoutButtonSelector;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static CartPage by(final WebDriver driver) {
        return new CartPage(driver);
    }

    public List<Item> getCartItems() {
        final List<Item> itemList = new ArrayList<>();
        cartItemsSelector.forEach(cartItem -> {
            final String itemName = cartItem.findElement(itemNameSelector).getText();
            final double itemPrice = Optional.of(cartItem.findElement(itemPriceSelector))
                    .map(WebElement::getText)
                    .map(priceText -> priceText.replace("$", "").trim())
                    .map(priceText -> priceText.replace(",", "").trim())
                    .map(Double::parseDouble)
                    .orElse((double) 0);
            final String itemId = cartItem.findElement(itemIdSelector).getAttribute("id");
            itemList.add(new Item(itemId, itemName, itemPrice));
        });
        return itemList;
    }


    public void navigateToCheckout() {
        checkoutButtonSelector.click();
    }
}
