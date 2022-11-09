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

public class ProductsPage {
    private final By itemNameSelector = By.className("inventory_item_name");
    private final By itemIdSelector = By.cssSelector("div[class='inventory_item_label'] a");
    private final By itemPriceSelector = By.className("inventory_item_price");
    private final By addToCartSelector = By.cssSelector("button[class='btn btn_primary btn_small btn_inventory']");

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItemListSelector;

    @FindBy(css = "a[class='shopping_cart_link']")
    private WebElement cartPageLinkSelector;

    public ProductsPage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static ProductsPage by(final WebDriver driver) {
        return new ProductsPage(driver);
    }

    public ProductsPage and() {
        return this;
    }

    public List<Item> addItemsInPriceRangeToCart(final Integer max, final Integer min) {
        final List<Item> itemList = new ArrayList<>();
        inventoryItemListSelector.forEach(inventoryItem -> {
            final String itemName = inventoryItem.findElement(itemNameSelector).getText();
            final double itemPrice = Optional.of(inventoryItem.findElement(itemPriceSelector))
                    .map(WebElement::getText)
                    .map(priceText -> priceText.replace("$", "").trim())
                    .map(priceText -> priceText.replace(",", "").trim())
                    .map(Double::parseDouble)
                    .orElse((double) 0);
            final String itemId = inventoryItem.findElement(itemIdSelector).getAttribute("id");
            if (itemPrice > min && itemPrice < max) {
                inventoryItem.findElement(addToCartSelector).click();
                itemList.add(new Item(itemId, itemName, itemPrice));
            }
        });
        return itemList;
    }

    public void navigateToCartPage() {
        cartPageLinkSelector.click();
    }
}
