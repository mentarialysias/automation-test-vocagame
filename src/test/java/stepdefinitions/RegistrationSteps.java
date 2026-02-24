package stepdefinitions;

import actions.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ConfigReader;
import utils.HelperClass;
import utils.MailosaurHelper;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class RegistrationSteps {

    private final LandingPageActions landingPage = new LandingPageActions();
    private final LoginActions loginPage = new LoginActions();
    private final RegistrationsActions registrationActions = new RegistrationsActions();
    private final VerificationModalActions verifModal = new VerificationModalActions();
    private final OTPActions otpActions = new OTPActions();
    public static String currentTestEmail;

    @Given("User is on VocaGame Landing page")
    public void user_is_on_voca_game_landing_page() {
        HelperClass.openPage(ConfigReader.get("landing_page.url"));
        landingPage.closeInitialPopupIfDisplayed();
        landingPage.verifyUserIsOnLandingPage();
    }

    @When("User clicks {string} button")
    public void user_clicks_button(String buttonName) throws InterruptedException {
        String btn = buttonName.trim().toLowerCase();
        switch (btn) {
            case "masuk" -> landingPage.clickLoginButton();
            case "daftar sekarang" -> loginPage.clickDaftarSekarang();
            case "buat akun" -> registrationActions.clickBuatAkun();
            case "lanjut" -> verifModal.clickLanjutkan();
            case "verifikasi" -> otpActions.clickVerifikasi();
            case "masuk akun" -> loginPage.clickSubmitIfEnabled();
            case "lupa password" -> loginPage.clickLupaPassword();
            case "kirim kode otp" -> loginPage.clickKirimOTP();
            default -> throw new IllegalArgumentException("Tombol '" + buttonName + "' belum didefinisikan!");
        }
    }

    @And("System navigate user to login page")
    public void system_navigate_user_to_login_page() {
        String expectedUrl = ConfigReader.get("login.url");
        String actualUrl = HelperClass.getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Gagal navigasi ke halaman Login!");
        loginPage.verifyIsOnLoginPage();
    }

    @Then("User is successfully navigated to the Registration page")
    public void user_is_successfully_navigated_to_the_registration_page() {
        String expectedUrl = ConfigReader.get("registration.url");
        String actualUrl = HelperClass.getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL Halaman Registrasi tidak sesuai!");
    }

    @Then("The registration page should display the following elements:")
    public void verifyRegistrationUI(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            registrationActions.verifyElementStatus(
                    columns.get("field"),
                    columns.get("visibility"),
                    columns.get("state"),
                    columns.get("placeholder") // Menambahkan kolom placeholder
            );
        }
    }

    @And("All password criteria indicators should be in default state")
    public void verify_password_criteria_default() {
        registrationActions.verifyPasswordCriteriaDefault();
    }

    @And("The {string} checkbox should be unchecked")
    public void verify_checkbox_unchecked(String checkboxName) {
        registrationActions.verifyTermsUnchecked();
    }

    @When("User fills the registration form with following data:")
    public void user_fills_registration_form(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        currentTestEmail = data.get("email");
        registrationActions.fillFullRegistrationForm(data);
    }

    @Then("User verifies all password criteria indicators are checked and green")
    public void verify_password_criteria_met() {
        registrationActions.verifyAllPasswordCriteriaMet();
    }

    @When("User checks the \"Syarat dan Ketentuan\" checkbox")
    public void user_checks_terms() {
        registrationActions.checkTermsAndConditions();
    }

    @And("User selects {string} as the verification method")
    public void user_selects_verification_method(String method) {
//        if (registrationActions.isToastErrorActiveInstantly()) {
//            handleToastError(method);
//            return;
//        }
        verifModal.selectVerificationMethod(method);

    }

    // Helper method agar kodingan rapi (Don't Repeat Yourself)
    private void handleToastError(String method) {
        String msg = registrationActions.getToastMessage();
        if (msg.contains("Failed to fetch")) {
            System.out.println("Retry: Failed to fetch detected. Retrying...");
            registrationActions.clickBuatAkun();
            verifModal.selectVerificationMethod(method);
        } else {
            Assert.fail("Registrasi terhenti oleh sistem: [" + msg + "]");
        }
    }

    @Then("The Buat Akun button status should be enabled")
    public void the_button_status_should_be_enabled() {
        registrationActions.verifyBuatAkunButtonEnabled(true);
    }

    @Then("User should see a toast with message {string}")
    public void verify_toast_message(String expectedMsg) {
        registrationActions.verifyToastMessageContains(expectedMsg);
    }

    @And("System show OTP verifications modal")
    public void verify_otp_modal() throws InterruptedException {
        otpActions.verifyOTPModalDisplayed();
//        Thread.sleep(10000);
    }


    @When("User inputs the valid OTP code from email")
    public void user_inputs_otp() {
        // Gantikan logika Scanner manual dengan MailosaurHelper
        System.out.println("Memulai pengambilan OTP otomatis untuk: " + currentTestEmail);

        String otpCode = MailosaurHelper.getOTPFromEmail(currentTestEmail);

        if (otpCode != null) {
            System.out.println("OTP Berhasil didapatkan: " + otpCode);
            otpActions.inputOTP(otpCode); // Memanggil action input yang sudah kamu buat
        } else {
            Assert.fail("Gagal mendapatkan OTP dari Mailosaur untuk email: " + currentTestEmail);
        }
    }

    @Then("The {string} button color should be green and status is active")
    public void verify_button_otp_state(String btnName) {
        otpActions.verifyVerifikasiButtonState("active", "green");
    }

    @When("User clicks {string} on OTP modal")
    public void click_verif_otp(String btnName) {
        otpActions.clickVerifikasi();
    }

    @Then("User should see a success toast {string}")
    public void verify_success_regis(String msg) {
        registrationActions.verifyToastMessageContains(msg);
    }

    @And("User is automatically redirected to the Login page")
    public void verify_redirect_login() {
        WebDriverWait wait = new WebDriverWait(HelperClass.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("auth/login"));
        Assert.assertEquals(HelperClass.getDriver().getCurrentUrl(), ConfigReader.get("login.url"));
    }


}