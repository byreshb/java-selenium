package com.automation.pageObjectModel.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(css = "input[data-test='username']")
    private WebElement usernameSelector;

    @FindBy(css = "input[data-test='password']")
    private WebElement passwordSelector;

    @FindBy(css = "input[data-test='login-button']")
    private WebElement loginButtonSelector;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static LoginPage by(WebDriver driver) {
        return new LoginPage(driver);
    }

    public LoginPage userNameOf(final String username) {
        this.usernameSelector.sendKeys(username);
        return this;
    }

    public LoginPage and() {
        return this;
    }

    public LoginPage with() {
        return this;
    }

    public LoginPage passwordOf(final String password) {
        this.passwordSelector.sendKeys(password);
        return this;
    }

    public LoginPage tryToLogin() {
        this.loginButtonSelector.click();
        return this;
    }

    public void navigateToProductsPage() {
        // For readability
    }
}
