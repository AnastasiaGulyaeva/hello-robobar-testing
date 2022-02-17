package com.sinensia.hellorobobartesting.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// http://localhost:3000/#!/review
public class OrderPage {

    @FindBy(css = ".alert-danger")
    public WebElement alertDiv;

    @FindBy(css = "p")
    public WebElement confirmationMessage;

    @FindBy (css = "btn-default")
    public  WebElement cancelButton;

    public OrderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}