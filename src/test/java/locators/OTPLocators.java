package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OTPLocators {

    @FindBy(xpath = "//div[@role='dialog']//p[text()='Verifikasi 2 Langkah']")
    public WebElement otpModalTitle;

    // Locator input asli yang menangani pengisian angka
    @FindBy(xpath = "//input[@data-input-otp='true']")
    public WebElement otpInputField;

    // Tombol Verifikasi
    @FindBy(xpath = "//div[@role='dialog']//button[@type='submit']")
    public WebElement verifikasiButton;

    // Toast Message (Menggunakan Sonner Toast dari HTML)
    @FindBy(xpath = "//li[@data-sonner-toast]//div[@data-title]")
    public WebElement toastMessage;
}