package stepdefinitions;

import actions.LandingPageActions;
import actions.LoginActions;
import io.cucumber.java.en.*;
import org.testng.Assert;
import utils.ConfigReader;
import utils.HelperClass;

public class LoginSteps {
    LoginActions loginActions = new LoginActions();
    LandingPageActions landingPageActions = new LandingPageActions();
//    RegistrationSteps regSteps = new RegistrationSteps();
//    public String currentTestEmail;

    @Given("User is on Vocagame Login page")
    public void user_is_on_vocagame_login_page() {
//        regSteps.user_is_on_voca_game_landing_page();
//        regSteps.user_clicks_button("masuk");
//        regSteps.system_navigate_user_to_login_page();

//        loginActions = new LoginActions(); // Inisialisasi di sini
        HelperClass.openPage(ConfigReader.get("login.url"));
        loginActions.isLoginPageReady();
    }

    @Then("User should see brand logo and hero image")
    public void user_should_see_brand_logo_and_hero_image() {
        loginActions.verifyLoginPageUI();
    }

    @Then("The page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        loginActions.verifyTextTitle(expectedTitle);
    }

    @Then("The subtitle should be {string}")
    public void the_subtitle_should_be(String expectedSubtitle) {
        loginActions.verifyTextSubtitle(expectedSubtitle);
    }

    @Then("Identifier field is displayed with label {string}")
    public void identifier_field_is_displayed_with_label(String expectedLabel) {
        loginActions.verifyIdentifierLabel(expectedLabel);
    }

    @Then("Masuk Akun button should be disabled")
    public void masuk_akun_button_should_be_disabled() {
        loginActions.verifySubmitButtonDisabled();
    }

    @Then("Google login button and {string} link are visible")
    public void social_and_links_are_visible(String linkText) {
        loginActions.verifySocialAndRegisterLinks(linkText);
    }

    @When("User enters identifier as {string}")
    public void user_enters_identifier_as(String identifier) throws InterruptedException {
        loginActions.enterIdentifier(identifier);
        RegistrationSteps.currentTestEmail = identifier;
    }

    @Then("Masuk Akun button should be enabled")
    public void masuk_akun_button_should_be_enabled(String buttonName) {
        loginActions.verifySubmitButtonEnabled();
    }

    @Then("User should see password field")
    public void user_should_see_password_field() {
        loginActions.isPasswordFieldDisplayed();
    }

    @When("User enters password as {string}")
    public void user_enters_password_as(String password) {
        loginActions.enterPasswordIfDisplayed(password);
    }

    @When("User enters password as {string} if field is displayed")
    public void user_enters_password_as_if_field_is_displayed(String password) {
        loginActions.enterPasswordIfDisplayed(password);
    }

    @Then("User should see error {string} with type {string}")
    public void user_should_see_error_with_type(String message, String type) {
        loginActions.verifyErrorByType(message, type);
    }

    @Then("User should see inline error {string}")
    public void user_should_see_inline_error(String message) {
        loginActions.verifyErrorByType(message, "inline");
    }

    @Then("User should see toast message {string}")
    public void user_should_see_toast_message(String message) {
        loginActions.verifyErrorByType(message, "toast");
    }

    @Then("User should be navigated to {string} page")
    public void user_should_be_navigated_to_page(String pageName) throws InterruptedException {
        loginActions.verifyUrlPage(pageName);
        Thread.sleep(3000);
        landingPageActions.closeInitialPopupIfDisplayed();
    }

    @When("User clicks Edit pencil icon")
    public void user_clicks_pencil_icon() {
        loginActions.clickEditIcon();
    }

    @Then("User should be back to identifier step")
    public void user_should_be_back_to_identifier_step() {
        Assert.assertTrue(loginActions.isPasswordFieldGone(), "GAGAL: Password Field masih muncul!");
//        loginActions.verifyPasswordFieldIsGone();
    }

    @Then("Identifier field should contain {string}")
    public void identifier_field_should_contain(String expectedValue) {
        loginActions.verifyBackToIdentifierStep(expectedValue);
    }
}