package actions;

import locators.OTPLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.HelperClass;
import java.time.Duration;

public class OTPActions {

    private WebDriver driver;
    private OTPLocators loc;
    private WebDriverWait wait;

    public OTPActions() {
        this.driver = HelperClass.getDriver();
        this.loc = new OTPLocators();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, loc);
    }

    public void verifyOTPModalDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(loc.otpModalTitle));
        Assert.assertTrue(loc.otpModalTitle.isDisplayed(), "Modal OTP tidak muncul!");
    }

    public void inputOTP(String otpCode) {
        wait.until(ExpectedConditions.elementToBeClickable(loc.otpInputField));
        loc.otpInputField.sendKeys(otpCode);
    }

    public void verifyVerifikasiButtonState(String expectedStatus, String expectedColor) {
        // 1. Verifikasi Status (Active/Enabled)
        if (expectedStatus.equalsIgnoreCase("active") || expectedStatus.equalsIgnoreCase("enabled")) {
            wait.until(d -> loc.verifikasiButton.isEnabled());
            Assert.assertTrue(loc.verifikasiButton.isEnabled(), "Tombol Verifikasi seharusnya aktif!");
        } else {
            Assert.assertFalse(loc.verifikasiButton.isEnabled(), "Tombol Verifikasi seharusnya disabled!");
        }

        // 2. Verifikasi Warna (Green)
        if (expectedColor.equalsIgnoreCase("green")) {
            // Di VocaGame, hijau didefinisikan oleh class 'bg-primary' atau hex code
            String classAttr = loc.verifikasiButton.getAttribute("class");
            String bgColor = loc.verifikasiButton.getCssValue("background-color");

            // Cek apakah memiliki class primary atau cek nilai RGB (biasanya hijau Vocagame)
            boolean isGreen = classAttr.contains("bg-primary") || bgColor.contains("rgba(70, 221, 107, 1)");
            Assert.assertTrue(isGreen, "Warna tombol bukan hijau! Current color: " + bgColor);
        }
    }

    public void clickVerifikasi() {
        wait.until(ExpectedConditions.elementToBeClickable(loc.verifikasiButton));
        loc.verifikasiButton.click();
    }

}