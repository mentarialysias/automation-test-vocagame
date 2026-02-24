package stepdefinitions;

import actions.ProductOrderingActions;
import io.cucumber.java.en.*;

public class ProductOrderingSteps {

    private final ProductOrderingActions orderingActions = new ProductOrderingActions();

    @When("User clicks Voucher tab")
    public void user_clicks_voucher_tab() {
        orderingActions.navigateToVoucherCategory();
    }

    @When("User input {string} on search bar")
    public void user_input_on_search_bar(String productName) {
        orderingActions.searchProduct(productName);
    }

    @And("User selects Nintendo product")
    public void user_selects_nintendo_product() throws InterruptedException {
        orderingActions.selectProductFromSearch();
        Thread.sleep(3000);
    }

    @And("User verifies email field is filled correctly")
    public void user_verifies_email_field() {
        String email = RegistrationSteps.currentTestEmail;
        orderingActions.fillOrderingEmail(email);
    }

    @When("User selects $10 denomination")
    public void user_selects_10_dollar_denomination() {
        orderingActions.selectDenominationTenDollar();
    }

    @And("User completes payment using QRIS")
    public void user_completes_payment_using_qris() throws InterruptedException {
        orderingActions.proceedToPurchaseWithQris();
    }

    @And("System should display order number and QR code")
    public void system_should_display_order_info() throws InterruptedException {
        orderingActions.verifySuccessfulCheckout();
    }
}