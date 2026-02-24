package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginLocators {

    // --- Judul & Teks Halaman ---

    @FindBy(xpath = "//p[text()='Masuk Akun Vocagame']")
    public WebElement loginPageTitle;

    @FindBy(xpath = "//p[contains(text(), 'Masukan nomor WhatsApp / Email')]")
    public WebElement loginPageSubtitle;

    // --- Form Inputs ---

    @FindBy(name = "identifier")
    public WebElement identifierField;
    @FindBy(xpath = "//input[@name='identifier']/../../label")
    public WebElement identifierLabel;
    @FindBy(name = "password")
    public WebElement passwordField;

    // Button untuk melihat/sembunyikan password (lucide-eye/eye-off)
    @FindBy(xpath = "//button[.//svg[contains(@class, 'lucide-eye')]]")
    public WebElement togglePasswordVisibility;

    // Tombol Pensil untuk edit email/no hp yang sudah diinput
    @FindBy(xpath = "//*[local-name()='svg' and contains(@class, 'lucide-pencil')]")
    public WebElement editIdentifierButton;

    // --- Tombol Utama ---

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    // --- Pesan Error (Verifikasi Teks) ---

    // 1. Error format email salah (client-side)
    @FindBy(xpath = "//p[text()='Email harus berupa alamat email yang valid']")
    public WebElement emailFormatErrorLabel;

    // 2. Error password kurang dari 6 karakter (client-side)
    @FindBy(xpath = "//p[text()='Kata sandi minimal harus 6 karakter.']")
    public WebElement passwordLengthErrorLabel;

    // 3. Error email tidak terdaftar (server-side)
    @FindBy(xpath = "//*[text()='Email tidak ditemukan. Harap gunakan data yang valid dan terdaftar']")
    public WebElement emailNotFoundError;

    // 4. Error nomor telepon tidak terdaftar (server-side)
    @FindBy(xpath = "//*[text()='Phone number tidak ditemukan. Harap gunakan data yang valid dan terdaftar']")
    public WebElement phoneNotFoundError;

    // 5. Error password salah (server-side)
    @FindBy(xpath = "//*[text()='Kata sandi salah. Verifikasi dan coba lagi']")
    public WebElement wrongPasswordError;

    // --- Social Login & Links ---

    @FindBy(xpath = "//a[contains(@href, 'auth/sso/google')]")
    public WebElement googleLoginButton;

    @FindBy(xpath = "//span[text()='Daftar Sekarang']/parent::a")
    public WebElement daftarSekarangLink;

    @FindBy(xpath = "//p[text()='Lupa Password']/parent::a")
    public WebElement lupaPasswordLink;

    @FindBy(xpath = "//button[text()='Kirim Kode OTP']")
    public WebElement kirimOtpButton;

    // --- Brand & Assets ---

    @FindBy(xpath = "//img[@alt='logo' or @alt='vocagame logo']")
    public WebElement brandLogo;

    @FindBy(xpath = "//img[@alt='hero mobile legends']")
    public WebElement heroImage;

    // Selector bendera (untuk verifikasi auto-detect no telpon)
    @FindBy(className = "react-international-phone-country-selector")
    public WebElement countrySelector;

}