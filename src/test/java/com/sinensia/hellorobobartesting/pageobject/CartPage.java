package com.sinensia.hellorobobartesting.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// http://localhost:3000/#!/
public class CartPage {

    private final WebDriver driver;

    @FindBy(css = ".ng-scope:nth-child(1) > td .input-group-append > .btn")
    public WebElement addColaButton;

    @FindBy(css =".ng-scope:nth-child(2) > td .input-group-append > .btn")
    public WebElement addBeerButton;

    @FindBy(css=".ng-scope:nth-child(3) > td .input-group-append > .btn")
    public WebElement addWineButton;

    @FindBy(css = "th[class='ng-binding']")
    public WebElement totalField;

    @FindBy(css = ".btn-success")
    public WebElement checkoutButton;

    @FindBy(css = "html > body > robo-robobar > div > div:nth-of-type(2) > robo-place-order > div:nth-of-type(2) > table > tbody > tr:nth-of-type(3) > td:nth-of-type(3) > div > div > div:nth-of-type(2) > button")
    public WebElement btnButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage checkout() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }

}