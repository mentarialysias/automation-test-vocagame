package actions;

import locators.LandingPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.HelperClass; // Import pusat driver

import java.time.Duration;
import java.util.List;

public class LandingPageActions {
    private WebDriver driver;
    private LandingPageLocators locators;
    private WebDriverWait wait;

    public LandingPageActions() { // Constructor tidak butuh parameter lagi
        this.driver = HelperClass.getDriver(); // Ambil dari pusat
        this.locators = new LandingPageLocators();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, locators);
    }

    public void verifyUserIsOnLandingPage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("vocagame.com"), "URL tidak sesuai!");
        wait.until(ExpectedConditions.visibilityOf(locators.vocaGameLogo));
        Assert.assertTrue(locators.vocaGameLogo.isDisplayed(), "Logo VocaGame tidak tampil!");
    }

    public void closeInitialPopupIfDisplayed() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        try {
            if (locators.closePopupButton.isDisplayed()) {
                locators.closePopupButton.click();
                System.out.println("Pop-up closed.");
            }
        } catch (Exception e) {
            System.out.println("No pop-up blocker detected.");
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }
    public void clickLoginButton() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(locators.loginButton));
//        Thread.sleep(2000);
        Assert.assertTrue(locators.loginButton.isDisplayed(), "Tombol Masuk tidak tampil di layar!");
        Assert.assertTrue(locators.loginButton.isEnabled(), "Tombol Masuk dalam status disabled!");

        String btnText = locators.loginButton.getText();
        Assert.assertEquals(btnText, "Masuk", "Teks tombol bukan 'Masuk'!");
        locators.loginButton.click();
        wait.until(ExpectedConditions.urlContains("login"));
    }






}