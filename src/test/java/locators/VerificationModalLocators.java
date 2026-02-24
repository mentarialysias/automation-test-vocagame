package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class VerificationModalLocators {

    // --- Container Modal ---
    @FindBy(xpath = "//div[@role='dialog']")
    public WebElement verificationModal;

    @FindBy(xpath = "//p[text()='Pilih Metode Verifikasi']")
    public WebElement modalTitle;

    // --- Pilihan Metode (Email/WA) ---
    // Mengambil item list metode verifikasi
    @FindBy(xpath = "//div[@role='dialog']//ul/li")
    public List<WebElement> verificationMethodsList;

    // Spesifik metode Email
    @FindBy(xpath = "//p[text()='Kirim OTP via Email']/parent::div")
    public WebElement emailMethodOption;

    @FindBy(xpath = "//p[text()='Kirim OTP via Email']/following-sibling::p")
    public WebElement emailAddressDisplay;

    // --- Tombol Aksi ---
    @FindBy(xpath = "//button[contains(., 'Kembali')]")
    public WebElement kembaliButton;

    // Ada dua tombol 'Lanjutkan' (mobile & desktop), kita ambil yang terlihat (sm:block)
    @FindBy(xpath = "//button[text()='Lanjutkan' and contains(@class, 'sm:block')]")
    public WebElement lanjutkanButton;

    @FindBy(xpath = "//div[@role='dialog']//button[contains(@class, 'absolute')]")
    public WebElement closeXButton;
}