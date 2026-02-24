package actions;

import locators.ResetPasswordLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.HelperClass;

import java.time.Duration;
import java.util.List;

public class ResetPasswordActions {
    private WebDriver driver;
    private ResetPasswordLocators loc;
    private WebDriverWait wait;

    public ResetPasswordActions() {
        this.driver = HelperClass.getDriver();
        this.loc = new ResetPasswordLocators();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(this.driver, loc);
    }

    /**
     * Mengisi form reset password
     */
    public void fillResetPasswordForm(String newPassword, String confirmPassword) {
        typeInto(loc.passwordBaruField, newPassword);
        typeInto(loc.confirmPasswordField, confirmPassword);
    }

    /**
     * Memverifikasi apakah kriteria password tertentu sudah terpenuhi (berubah warna dari abu-abu)
     */
    public void verifyPasswordCriteriaMet(String criteriaText) {
        boolean isMet = false;
        for (WebElement criteria : loc.passwordCriteriaList) {
            if (criteria.getText().contains(criteriaText)) {
                // Berdasarkan HTML, kriteria belum terpenuhi menggunakan class 'text-gray-500'
                String className = criteria.getAttribute("class");
                Assert.assertFalse(className.contains("gray-500"),
                        "Kriteria '" + criteriaText + "' seharusnya sudah terpenuhi (bukan gray-500 lagi)!");
                isMet = true;
                break;
            }
        }
        Assert.assertTrue(isMet, "Kriteria teks '" + criteriaText + "' tidak ditemukan di list.");
    }

    /**
     * Memverifikasi semua kriteria password sudah terpenuhi
     */
    public void verifyAllCriteriaMet() {
        for (WebElement criteria : loc.passwordCriteriaList) {
            String className = criteria.getAttribute("class");
            Assert.assertFalse(className.contains("gray-500"),
                    "Ada kriteria yang belum terpenuhi: " + criteria.getText());
        }
    }

    /**
     * Klik tombol toggle untuk melihat password
     * @param fieldType "baru" atau "konfirmasi"
     */
    public void togglePasswordVisibility(String fieldType) {
        if (fieldType.equalsIgnoreCase("baru")) {
            loc.toggleShowPasswordBaru.click();
        } else {
            loc.toggleShowConfirmPassword.click();
        }
    }

    /**
     * Verifikasi tipe input (password vs text) untuk fitur Show/Hide
     */
    public void verifyPasswordVisibility(String fieldType, boolean isVisible) {
        WebElement field = fieldType.equalsIgnoreCase("baru") ? loc.passwordBaruField : loc.confirmPasswordField;
        String type = field.getAttribute("type");
        if (isVisible) {
            Assert.assertEquals(type, "text", "Password harusnya terlihat (type=text)");
        } else {
            Assert.assertEquals(type, "password", "Password harusnya tersembunyi (type=password)");
        }
    }

    /**
     * Memverifikasi apakah tombol Reset Password aktif atau tidak
     */
    public void verifyResetButtonEnabled(boolean expectedState) {
        Assert.assertEquals(loc.resetPasswordButton.isEnabled(), expectedState,
                "Status tombol Reset Password tidak sesuai!");
    }

    public void clickResetPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(loc.resetPasswordButton)).click();
    }

    /**
     * Verifikasi elemen-elemen dasar di halaman Reset Password
     */
    public void verifyResetPageLayout() {
        Assert.assertTrue(loc.titlePage.isDisplayed(), "Judul 'Reset Password' tidak tampil");
        Assert.assertTrue(loc.passwordBaruField.isDisplayed(), "Input password baru tidak tampil");
        Assert.assertTrue(loc.confirmPasswordField.isDisplayed(), "Input konfirmasi password tidak tampil");
        Assert.assertTrue(loc.heroImage.isDisplayed(), "Gambar hero di sisi kanan tidak tampil");
    }

    private void typeInto(WebElement element, String text) {
        if (text != null) {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
        }
    }

    /**
     * Helper untuk mendapatkan pesan error atau label secara dinamis (Opsional)
     */
    public String getLabelText(String fieldName) {
        if (fieldName.equalsIgnoreCase("password_baru")) {
            return loc.passwordBaruLabel.getText().replace("*", "").trim();
        } else if (fieldName.equalsIgnoreCase("konfirmasi_password")) {
            return loc.confirmPasswordLabel.getText().replace("*", "").trim();
        }
        return "";
    }
}