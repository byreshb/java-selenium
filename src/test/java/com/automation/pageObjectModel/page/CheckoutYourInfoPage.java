package com.automation.pageObjectModel.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutYourInfoPage {

    @FindBy(id = "first-name")
    private WebElement firstNameSelector;

    @FindBy(id = "last-name")
    private WebElement lastNameSelector;

    @FindBy(id = "postal-code")
    private WebElement postalCodeSelector;

    @FindBy(id = "continue")
    private WebElement continueButtonSelector;

    public CheckoutYourInfoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static CheckoutYourInfoPage by(final WebDriver driver) {
        return new CheckoutYourInfoPage(driver);
    }

    public CheckoutYourInfoPage firstNameOf(final String username) {
        this.firstNameSelector.sendKeys(username);
        return this;
    }

    public CheckoutYourInfoPage fill() {
        return this;
    }

    public CheckoutYourInfoPage and() {
        return this;
    }

    public CheckoutYourInfoPage lastNameOf(final String password) {
        this.lastNameSelector.sendKeys(password);
        return this;
    }

    public CheckoutYourInfoPage postalCodeOf(final String password) {
        this.postalCodeSelector.sendKeys(password);
        return this;
    }

    public void clickContinueButton() {
        this.continueButtonSelector.click();
    }
}
