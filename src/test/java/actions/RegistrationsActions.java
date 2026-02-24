package actions;

import locators.RegistrationsLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.HelperClass;

import java.time.Duration;
import java.util.Map;

public class RegistrationsActions {
    private WebDriver driver;
    private RegistrationsLocators loc;
    private WebDriverWait wait;

    public RegistrationsActions() {
        this.driver = HelperClass.getDriver();
        this.loc = new RegistrationsLocators();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(this.driver, loc);
    }

    public void verifyElementStatus(String fieldName, String visibility, String state, String expectedPlaceholder) {
        WebElement element = getElementByName(fieldName);

        // 1. Validasi Visibility
        if (visibility.equalsIgnoreCase("displayed")) {
            Assert.assertTrue(element.isDisplayed(), fieldName + " tidak tampil!");
        }

        // 2. Validasi State (Enabled/Disabled)
        if (state.equalsIgnoreCase("enabled")) {
            Assert.assertTrue(element.isEnabled(), fieldName + " seharusnya enabled!");
        } else {
            Assert.assertFalse(element.isEnabled(), fieldName + " seharusnya disabled!");
        }

        // 3. Validasi Placeholder/Label (Menghapus '*' jika ada)
        if (expectedPlaceholder != null) {
            WebElement label = getLabelByFieldName(fieldName);
            String actualText = label.getText().replace("*", "").trim();
            Assert.assertEquals(actualText, expectedPlaceholder, "Label/Placeholder salah untuk: " + fieldName);
        }
    }

    public void verifyPasswordCriteriaDefault() {
        for (WebElement criteria : loc.passwordCriteriaList) {
            // Kita cek class-nya, biasanya 'text-gray-500' atau sejenisnya di awal
            String className = criteria.getAttribute("class");
            Assert.assertTrue(className.contains("gray"), "Kriteria password harusnya berwarna abu-abu di awal!");
        }
    }

    /**
     * Memverifikasi checkbox belum tercentang (Status Radix UI)
     */
    public void verifyTermsUnchecked() {
        String state = loc.termsCheckbox.getAttribute("data-state");
        Assert.assertEquals(state, "unchecked", "Syarat & Ketentuan seharusnya belum dicentang!");
    }

    public void fillFullRegistrationForm(Map<String, String> data) {
        typeInto(loc.firstNameField, data.get("first_name"));
        typeInto(loc.lastNameField, data.get("last_name"));
        typeInto(loc.whatsappField, data.get("whatsapp_number"));
        typeInto(loc.emailField, data.get("email"));
        typeInto(loc.passwordField, data.get("password"));
        typeInto(loc.confirmPasswordField, data.get("password"));

        verifyInputValues(data);
    }

    private void typeInto(WebElement element, String text) {
        if (text != null) {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
            System.out.println("Typing: " + text);
        }
    }

    private void verifyInputValues(Map<String, String> data) {
        Assert.assertEquals(loc.firstNameField.getAttribute("value"), data.get("first_name"), "First Name tidak terisi!");
        Assert.assertEquals(loc.lastNameField.getAttribute("value"), data.get("last_name"), "Last Name tidak terisi!");
        Assert.assertEquals(loc.emailField.getAttribute("value"), data.get("email"), "Email tidak terisi!");

        String actualWA = loc.whatsappField.getAttribute("value").replaceAll("[^0-9]", ""); // Ambil angka saja
        String expectedWA = data.get("whatsapp_number").replaceAll("[^0-9]", ""); // Ambil angka saja
        if (expectedWA.startsWith("0")) {
            expectedWA = "62" + expectedWA.substring(1);
        }
        Assert.assertEquals(actualWA, expectedWA, "WhatsApp tidak terisi atau format salah!");

        Assert.assertFalse(loc.passwordField.getAttribute("value").isEmpty(), "Password kosong!");
        Assert.assertEquals(loc.confirmPasswordField.getAttribute("value"), data.get("password"), "Confirm Password tidak sesuai!");
    }

    public void verifyAllPasswordCriteriaMet() {
        for (WebElement criteria : loc.passwordCriteriaList) {
            String className = criteria.getAttribute("class");
            // Kriteria terpenuhi jika class 'text-gray-500' (atau sejenisnya) sudah hilang
            Assert.assertFalse(className.contains("gray"), "Kriteria password belum terpenuhi!");
        }
    }

    public void checkTermsAndConditions() {
        if (loc.termsCheckbox.getAttribute("data-state").equals("unchecked")) {
            loc.termsCheckbox.click();
        }
    }

    public void verifyBuatAkunButtonEnabled(boolean state) {
        Assert.assertEquals(loc.buatAkunButton.isEnabled(), state, "Status tombol Buat Akun tidak sesuai!");
    }

    public void clickBuatAkun() {
        loc.buatAkunButton.click();
    }

    public boolean isToastErrorDisplayed() {
        try {
            // Gunakan polling sangat singkat (1-2 detik) agar tidak menghambat flow normal
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOf(loc.toastContainer));

            // Cek apakah background-nya mengandung warna merah/pink (opsional) atau atribut error
            String type = loc.toastContainer.getAttribute("data-type");
            return type.equalsIgnoreCase("error");
        } catch (Exception e) {
            return false;
        }
    }

    public String getToastMessage() {
        try {
            // Pastikan teks sudah ter-render sebelum diambil
            wait.until(ExpectedConditions.visibilityOf(loc.toastMessage));
            return loc.toastMessage.getText();
        } catch (Exception e) {
            return "Pesan tidak terbaca";
        }
    }

    public void verifyToastMessageContains(String expectedMessage) {
        wait.until(ExpectedConditions.visibilityOf(loc.toastMessage));
        String actualMessage = loc.toastMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Pesan toast tidak sesuai! Muncul: " + actualMessage);
    }

    private WebElement getElementByName(String name) {
        switch (name.toLowerCase().trim()) {
            case "first_name":
                return loc.firstNameField;
            case "last_name":
                return loc.lastNameField;
            case "whatsapp_number":
                return loc.whatsappField;
            case "email":
                return loc.emailField;
            case "password":
                return loc.passwordField;
            case "confirm_password":
                return loc.confirmPasswordField;
            case "buat_akun":
                return loc.buatAkunButton;
            default:
                throw new IllegalArgumentException("Field tidak dikenal: " + name);
        }
    }

    private WebElement getLabelByFieldName(String name) {
        switch (name.toLowerCase().trim()) {
            case "first_name":
                return loc.firstNameLabel;
            case "last_name":
                return loc.lastNameLabel;
            case "whatsapp_number":
                return loc.whatsappLabel;
            case "email":
                return loc.emailLabel;
            case "password":
                return loc.passwordLabel;
            case "confirm_password":
                return loc.confirmPasswordLabel;
            case "buat_akun":
                return loc.buatAkunButton; // Untuk button, teks ada di element itu sendiri
            default:
                throw new IllegalArgumentException("Label tidak dikenal: " + name);
        }
    }

    public boolean isToastErrorActiveInstantly() {
        // Gunakan findElements (plural) agar tidak melempar Exception dan tidak menunggu timeout
        return !driver.findElements(org.openqa.selenium.By.xpath("//li[@data-sonner-toast and @data-type='error']")).isEmpty();
    }
}