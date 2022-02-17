package com.sinensia.hellorobobartesting.pageobject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AgeSuiteTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    CartPage cartPage;

    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @BeforeEach
    public void setupTest() {
        cartPage = new CartPage(driver);
        driver.get("http://localhost:3000/");
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void underageCola() {
        cartPage.addColaButton.click();
        CheckoutPage checkoutPage = cartPage.checkout();

        // assert that element is not visible or does not exist
        boolean ageInputVisible = false;
        boolean ageInputExists = true;
        try {
            ageInputVisible = checkoutPage.ageInput.isDisplayed();
        } catch(NoSuchElementException e) {
            ageInputExists = false;
        };
        assertTrue(!ageInputExists && !ageInputVisible);

        // alternatively, assert that element is hidden or does not exist
        assertThrows(Exception.class, ()->{
            if(checkoutPage.ageInput.isDisplayed()) {
                throw new Exception("ageInput should be hidden");
            }
        });

        // ...check other conditions
        OrderPage orderPage = checkoutPage.order();
        assertTrue(orderPage.confirmationMessage.isDisplayed());
        assertThat(orderPage.confirmationMessage.getText(),
                containsString("Coming right up")
        );
    }

    @Test
    public void underageBeer() {
        cartPage.addBeerButton.click();
        CheckoutPage checkoutPage = cartPage.checkout();
        assertTrue(checkoutPage.ageInput.isDisplayed());
        checkoutPage.ageInput.sendKeys("17");
        OrderPage orderPage = checkoutPage.order();
        assertTrue(orderPage.alertDiv.isDisplayed());
    }

    @Test
    public void adultBeer() {
        cartPage.addBeerButton.click();
        CheckoutPage checkoutPage = cartPage.checkout();
        assertTrue(checkoutPage.ageInput.isDisplayed());
        checkoutPage.ageInput.sendKeys("19");
        OrderPage orderPage = checkoutPage.order();
        assertThat(orderPage.confirmationMessage.getText(),
                containsString("Coming right up")
        );
    }

}