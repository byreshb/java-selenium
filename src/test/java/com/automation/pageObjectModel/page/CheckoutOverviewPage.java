package com.automation.pageObjectModel.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Optional;

public class CheckoutOverviewPage {

    @FindBy(css = "div[class='summary_total_label']")
    private WebElement cartItemPriceSelector;

    @FindBy(id = "finish")
    private WebElement finishButtonSelector;

    public CheckoutOverviewPage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public double getCartPrice() {
        return Optional.of(cartItemPriceSelector.getText())
                .map(item -> item.replace("Total: $", ""))
                .map(item -> item.replace("Total: $", ""))
                .map(Double::parseDouble)
                .orElse((double) 0);
    }

    public static CheckoutOverviewPage by(final WebDriver driver) {
        return new CheckoutOverviewPage(driver);
    }

    public void clickFinishButton() {
        finishButtonSelector.click();
    }
}
