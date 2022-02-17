package com.sinensia.hellorobobartesting.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// http://localhost:3000/#!/review
public class CheckoutPage {

    private final WebDriver driver;

    @FindBy(css = ".btn-success")
    public WebElement orderButton;

    @FindBy(id = "ageInput")
    public WebElement ageInput;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public OrderPage order() {
        orderButton.click();
        return new OrderPage(driver);
    }
}