package actions;

import locators.VerificationModalLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.HelperClass;

import java.time.Duration;

public class VerificationModalActions {
    private WebDriver driver;
    private VerificationModalLocators loc;
    private WebDriverWait wait;

    public VerificationModalActions() {
        this.driver = HelperClass.getDriver();
        this.loc = new VerificationModalLocators();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(this.driver, loc);
    }

    /**
     * Memilih metode verifikasi secara dinamis berdasarkan input Gherkin
     */
    public void selectVerificationMethod(String method) {
        // Tunggu modal benar-benar muncul sebelum interaksi
        wait.until(ExpectedConditions.visibilityOf(loc.verificationModal));
        Assert.assertTrue(loc.modalTitle.isDisplayed(), "Modal verifikasi tidak muncul!");

        switch (method.toLowerCase().trim()) {
            case "email":
//                wait.until(ExpectedConditions.elementToBeClickable(loc.emailMethodOption));
                loc.emailMethodOption.click();
                System.out.println("Metode verifikasi: Email dipilih.");
                break;

            case "whatsapp":
                // Tambahkan case ini jika suatu saat ada metode WhatsApp
                // loc.whatsappMethodOption.click();
                break;

            default:
                throw new IllegalArgumentException("Metode verifikasi '" + method + "' tidak tersedia di modal!");
        }
    }

    /**
     * Klik tombol Lanjutkan pada modal verifikasi
     */
    public void clickLanjutkan() {
        // Tombol Lanjutkan seringkali baru enabled setelah metode dipilih
        wait.until(ExpectedConditions.elementToBeClickable(loc.lanjutkanButton));

        Assert.assertTrue(loc.lanjutkanButton.isDisplayed(), "Tombol Lanjutkan tidak tampil!");
        Assert.assertTrue(loc.lanjutkanButton.isEnabled(), "Tombol Lanjutkan dalam status disabled!");

        loc.lanjutkanButton.click();
        System.out.println("Klik tombol Lanjutkan verifikasi.");
    }

    /**
     * Verifikasi teks email yang ditampilkan pada modal (masking check)
     */
    public void verifyDisplayedEmail(String expectedEmailPart) {
        String actualEmail = loc.emailAddressDisplay.getText();
        Assert.assertTrue(actualEmail.contains(expectedEmailPart),
                "Email yang ditampilkan salah! Found: " + actualEmail);
    }

    /**
     * Menutup modal melalui tombol X
     */
    public void closeModal() {
        Assert.assertTrue(loc.closeXButton.isDisplayed());
        loc.closeXButton.click();
        wait.until(ExpectedConditions.invisibilityOf(loc.verificationModal));
    }
}