package com.automation.pageObjectModel.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {

    @FindBy(className = "complete-header")
    private WebElement thanksYouMessageSelector;

    public CheckoutCompletePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static CheckoutCompletePage by(final WebDriver driver) {
        return new CheckoutCompletePage(driver);
    }

    public String getThankYouMessage() {
        return thanksYouMessageSelector.getText();
    }
}
