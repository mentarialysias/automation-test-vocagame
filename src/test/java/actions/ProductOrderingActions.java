package actions;

import locators.ProductOrderingLocators;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.HelperClass;
import java.time.Duration;

public class ProductOrderingActions {
    private WebDriver driver;
    private ProductOrderingLocators loc;
    private WebDriverWait wait;

    public ProductOrderingActions() {
        this.driver = HelperClass.getDriver();
        this.loc = new ProductOrderingLocators();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(this.driver, loc);
    }

    public void navigateToVoucherCategory() {
        // 1. Scroll ke elemen agar terlihat
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.xpath("//button[contains(@aria-label, 'Voucher')]")));

        // Scroll sampai element berada di tengah layar agar tidak tertutup header sticky
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", loc.voucherMenuTab);

        try {
            // Coba klik normal dulu
            wait.until(ExpectedConditions.elementToBeClickable(loc.voucherMenuTab)).click();
        } catch (Exception e) {
            // 2. Jika masih gagal (tertutup popup/banner), klik paksa via JavaScript
            js.executeScript("arguments[0].click();", loc.voucherMenuTab);
        }
    }

    public void selectNintendoProduct() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.visibilityOf(loc.nintendoProductCard));

        // Scroll lagi untuk produknya
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", loc.nintendoProductCard);

        try {
            loc.nintendoProductCard.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", loc.nintendoProductCard);
        }
    }
    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOf(loc.searchBarField));
        loc.searchBarField.clear();
        loc.searchBarField.sendKeys(productName);

        wait.until(ExpectedConditions.visibilityOf(loc.searchResultContainer));
    }

    public void selectProductFromSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(loc.nintendoSearchResultItem)).click();
    }
    public void fillOrderingEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(loc.emailInputField));
        if (loc.emailInputField.getAttribute("value").isEmpty()) {
            loc.emailInputField.sendKeys(email);
        }
    }

    public void selectDenominationTenDollar() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.xpath("//button[contains(@aria-label, 'Nintendo $10')]//button[contains(text(), 'Rp 152,300')]")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", loc.denom10DollarButton);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loc.denom10DollarButton)).click();
        } catch (Exception e) {
            System.out.println("Klik normal terhalang, mencoba klik via JavaScript...");
            js.executeScript("arguments[0].click();", loc.denom10DollarButton);
        }
    }

    public void proceedToPurchaseWithQris() throws InterruptedException {
        Thread.sleep(2000);
        loc.qrisGroupAccordion.click();
//        wait.until(ExpectedConditions.elementToBeClickable(loc.qrisGroupAccordion)).click();
        loc.qrisPaymentItem.click();
//        wait.until(ExpectedConditions.elementToBeClickable(loc.qrisPaymentItem)).click();
        loc.buyNowButton.click();
    }

    public void verifySuccessfulCheckout() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loc.waitingPaymentTitle));
        wait.until(ExpectedConditions.visibilityOf(loc.qrCodeSvg));
        boolean isQrDisplayed = loc.qrCodeSvg.isDisplayed();

        String orderID = loc.orderNumberText.getText();
        String method = loc.paymentMethodText.getText();
        String amount = loc.totalPaymentAmount.getText();

        System.out.println("========================================");
        System.out.println("      RESULT: SUCCESSFUL CHECKOUT       ");
        System.out.println("========================================");
        System.out.println("QR Code Status    : " + (isQrDisplayed ? "Tampil" : "TIDAK TAMPIL"));
        System.out.println("Nomor Pesanan     : " + orderID);
        System.out.println("Metode Pembayaran : " + method);
        System.out.println("Nominal Total     : " + amount);
        System.out.println("========================================");

        Assert.assertTrue(isQrDisplayed, "ERROR: QR Code tidak muncul di halaman!");
        Assert.assertTrue(orderID.startsWith("TXN"), "ERROR: Format Nomor Pesanan salah! Muncul: " + orderID);
        Assert.assertFalse(amount.isEmpty(), "ERROR: Nominal pembayaran tidak terbaca!");

        Assert.assertEquals(method, "QRIS", "ERROR: Metode pembayaran di invoice bukan QRIS!");
        Thread.sleep(3000);
    }
}