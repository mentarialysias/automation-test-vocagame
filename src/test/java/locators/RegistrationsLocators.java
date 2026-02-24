package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class RegistrationsLocators {

    // --- Input Fields & Labels ---
    @FindBy(name = "first_name")
    public WebElement firstNameField;
    @FindBy(xpath = "//*[@id=\":R25efnj2f9ejsq:-form-item\"]")
    public WebElement firstNameLabel;

    @FindBy(name = "last_name")
    public WebElement lastNameField;
    @FindBy(xpath = "//input[@name='last_name']/parent::div/label")
    public WebElement lastNameLabel;

    @FindBy(name = "phone_number")
    public WebElement whatsappField;
    // Update Xpath untuk label WA agar lebih akurat dengan HTML terbaru
    @FindBy(xpath = "//input[@name='phone_number']/parent::div/following-sibling::label")
    public WebElement whatsappLabel;

    @FindBy(name = "email")
    public WebElement emailField;
    @FindBy(xpath = "//input[@name='email']/parent::div/label")
    public WebElement emailLabel;

    @FindBy(name = "password")
    public WebElement passwordField;
    @FindBy(xpath = "//input[@name='password']/parent::div/label")
    public WebElement passwordLabel;

    @FindBy(name = "confirm_password")
    public WebElement confirmPasswordField;
    @FindBy(xpath = "//input[@name='confirm_password']/parent::div/label")
    public WebElement confirmPasswordLabel;

    // --- Password Validation & Logic ---
    @FindBy(xpath = "//ul[contains(@class, 'text-sm')]/li")
    public List<WebElement> passwordCriteriaList;

    // Kriteria spesifik yang sudah terpenuhi (berwarna hijau/primary)
    @FindBy(xpath = "//li[contains(@class, 'text-primary')]")
    public List<WebElement> metPasswordCriteria;

    @FindBy(xpath = "//button[@role='checkbox']")
    public WebElement termsCheckbox;

    @FindBy(xpath = "//button[@type='submit' and text()='Buat Akun']")
    public WebElement buatAkunButton;

    @FindBy(xpath = "//li[@data-sonner-toast]")
    public WebElement toastContainer;

    // Mengambil teks pesan yang ada di dalam data-title
    @FindBy(xpath = "//li[@data-sonner-toast]//div[@data-title]")
    public WebElement toastMessage;

    // Pilihan: Mencari toast yang tipenya spesifik 'error'
    @FindBy(xpath = "//li[@data-sonner-toast and @data-type='error']")
    public WebElement toastErrorContainer;

}