package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ResetPasswordLocators {

    // --- Header & Navigation ---
    @FindBy(xpath = "//a[@href='/id-id']//img[@alt='logo']")
    public WebElement logoVocaGame;

    @FindBy(xpath = "//p[text()='Reset Password']")
    public WebElement titlePage;

    // --- Password Baru Field ---
    @FindBy(name = "password")
    public WebElement passwordBaruField;

    @FindBy(xpath = "//input[@name='password']/following-sibling::label")
    public WebElement passwordBaruLabel;

    @FindBy(xpath = "//input[@name='password']/following-sibling::button")
    public WebElement toggleShowPasswordBaru;

    // --- Konfirmasi Password Baru Field ---
    @FindBy(name = "confirmPassword")
    public WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@name='confirmPassword']/following-sibling::label")
    public WebElement confirmPasswordLabel;

    @FindBy(xpath = "//input[@name='confirmPassword']/following-sibling::button")
    public WebElement toggleShowConfirmPassword;

    // --- Password Validation Criteria ---
    // Mengambil semua list kriteria (minimal huruf besar, kecil, angka, karakter khusus)
    @FindBy(xpath = "//ul[contains(@class, 'text-sm')]/li")
    public List<WebElement> passwordCriteriaList;

    // Helper untuk mendeteksi kriteria yang masih abu-abu (belum terpenuhi)
    @FindBy(xpath = "//li[contains(@class, 'text-gray-500')]")
    public List<WebElement> unmetCriteria;

    // Helper untuk mendeteksi kriteria yang sudah terpenuhi (biasanya class warna berubah)
    @FindBy(xpath = "//li[not(contains(@class, 'text-gray-500'))]")
    public List<WebElement> metCriteria;

    // --- Action Buttons ---
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement resetPasswordButton;

    // --- Footer Links ---
    @FindBy(xpath = "//a[contains(@href, '/auth/register')]")
    public WebElement daftarSekarangLink;

    @FindBy(xpath = "//p[contains(text(), 'Belum punya akun?')]")
    public WebElement registerTextFooter;

    // --- Side Image (Hero) ---
    @FindBy(xpath = "//img[@alt='hero mobile legends']")
    public WebElement heroImage;
}