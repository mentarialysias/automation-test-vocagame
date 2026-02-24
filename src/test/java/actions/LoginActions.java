package actions;

import locators.LoginLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ConfigReader;
import utils.HelperClass;
import java.time.Duration;

public class LoginActions {
    private final RegistrationsActions regist;
    private WebDriver driver;
    private LoginLocators loc;
    private WebDriverWait wait;

    public LoginActions() {
        this.driver = HelperClass.getDriver();
        this.loc = new LoginLocators();
        this.regist = new RegistrationsActions();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(this.driver, loc);
    }

    // --- Judul & UI Verification ---
    public void isLoginPageReady() {
        wait.until(ExpectedConditions.visibilityOf(loc.loginPageTitle));
        wait.until(ExpectedConditions.attributeContains(loc.brandLogo, "class", "blur-0"));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(loc.brandLogo, "class", "blur-sm")));
    }
    public void verifyIsOnLoginPage() {
        wait.until(ExpectedConditions.visibilityOf(loc.loginPageTitle));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Bukan halaman login!");
    }

    public void verifyLoginPageUI() {
        wait.until(ExpectedConditions.visibilityOf(loc.loginPageTitle));
        Assert.assertTrue(loc.brandLogo.isDisplayed(), "Logo tidak tampil!");
        Assert.assertTrue(loc.heroImage.isDisplayed(), "Hero image tidak tampil!");
    }

    public void verifyTextTitle(String expected) {
        Assert.assertEquals(loc.loginPageTitle.getText().trim(), expected);
    }

    public void verifyTextSubtitle(String expected) {
        Assert.assertEquals(loc.loginPageSubtitle.getText().trim(), expected);
    }

    public void verifyIdentifierLabel(String expected) {
        wait.until(ExpectedConditions.visibilityOf(loc.identifierLabel));
        Assert.assertEquals(loc.identifierLabel.getText().trim(), expected);
    }

    // --- Button State Verification ---

    public void verifySubmitButtonDisabled() {
        Assert.assertFalse(loc.loginButton.isEnabled(), "Tombol Login seharusnya disabled!");
    }

    public void verifySubmitButtonEnabled() {
        Assert.assertTrue(loc.loginButton.isEnabled(), "Tombol Login seharusnya enabled!");
    }


    public void enterIdentifier(String data) throws InterruptedException {
        Thread.sleep(1500);
        loc.identifierField.sendKeys(data);
    }

    public void enterPasswordIfDisplayed(String pass) {
        if (pass.isEmpty() || pass.equals("-")) return;
        try {
            wait.until(ExpectedConditions.visibilityOf(loc.passwordField));
            smartType(loc.passwordField, pass);
            // Assert Post-Input
            Assert.assertFalse(loc.passwordField.getAttribute("value").isEmpty(), "Password tidak terisi!");
        } catch (Exception e) {
            System.out.println("Field password tidak muncul (tertahan validasi identifier).");
        }
    }

    /**
     * Metode sederhana untuk klik, clear (CTRL+A), dan ketik
     * Berguna agar label UI naik dan sistem auto-detect bekerja
     */
    private void smartType(WebElement element, String text) {
        if (text != null) {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            element.click();
            element.sendKeys(Keys.CONTROL + "a");
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(text);
            element.sendKeys(Keys.TAB); // Memicu event deteksi sistem
            System.out.println("Inputting: " + text);
        }
    }

    // --- Navigation & Clicks ---

    public void clickSubmitIfEnabled() {
        Assert.assertTrue(loc.loginButton.isEnabled(), "'Masuk Akun' Button is disable" );
        if (loc.loginButton.isEnabled()) {
            loc.loginButton.click();
        }
    }

    public void clickEditIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(loc.editIdentifierButton)).click();
    }

    public void clickDaftarSekarang() {
        wait.until(ExpectedConditions.elementToBeClickable(loc.daftarSekarangLink));
        loc.daftarSekarangLink.click();
        wait.until(ExpectedConditions.urlContains("register"));
    }

    public void verifySocialAndRegisterLinks(String registerText) {
        Assert.assertTrue(loc.googleLoginButton.isDisplayed());
        Assert.assertTrue(loc.daftarSekarangLink.getText().contains(registerText));
    }

    // --- Error & Navigation Success ---

    public void verifyErrorByType(String expectedMessage, String type) {
        if (type.equalsIgnoreCase("inline")) {
            WebElement el = expectedMessage.contains("Email") ? loc.emailFormatErrorLabel : loc.passwordLengthErrorLabel;
            wait.until(ExpectedConditions.visibilityOf(el));
            Assert.assertEquals(el.getText().trim(), expectedMessage);
        } else {
            regist.verifyToastMessageContains(expectedMessage);
        }
    }

    public void verifyBackToIdentifierStep(String val) {
//        wait.until(ExpectedConditions.invisibilityOf(loc.passwordField));
        Assert.assertTrue(loc.identifierField.isDisplayed());
        Assert.assertEquals(loc.identifierField.getAttribute("value"), val);
    }


    public boolean isPasswordFieldDisplayed() {
        WebElement paswordField = loc.passwordField;
        return paswordField.isDisplayed();
    }
    public void verifyPasswordFieldIsGone() {
        boolean isGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("password")));
        Assert.assertTrue(isGone, "GAGAL: Password Field masih muncul di layar!");
    }
    public boolean isPasswordFieldGone() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        boolean isGone = driver.findElements(By.name("password")).isEmpty();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return isGone;
    }

    public void verifyUrlPage(String pageName) {
        String configKey = pageName.toLowerCase() + ".url";
        String expectedURL = ConfigReader.get(configKey);

        Assert.assertNotNull(expectedURL, "URL untuk '" + pageName + "' tidak ditemukan di config!");
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "GAGAL: URL saat ini bukan halaman " + pageName);
    }


    public void clickLupaPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(loc.lupaPasswordLink)).click();
    }
    public void clickKirimOTP(){
        wait.until(ExpectedConditions.elementToBeClickable(loc.kirimOtpButton)).click();
    }

}