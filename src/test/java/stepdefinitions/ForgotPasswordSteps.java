package stepdefinitions;

import actions.ResetPasswordActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import utils.HelperClass;

public class ForgotPasswordSteps {

    private final ResetPasswordActions resetActions = new ResetPasswordActions();

    @And("System show reset password page layout")
    public void system_show_reset_password_page_layout() {
        resetActions.verifyResetPageLayout();
    }

    @And("User enters new password as {string}")
    public void user_enters_new_password_as(String newPassword) throws InterruptedException {
        resetActions.fillResetPasswordForm(newPassword, newPassword);
        Thread.sleep(3000);
    }

}