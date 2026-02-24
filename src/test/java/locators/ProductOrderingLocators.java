package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductOrderingLocators {

    // --- Menu & Navigation ---
    @FindBy(xpath = "//button[contains(@aria-label, 'Voucher')]")
    public WebElement voucherMenuTab;

    @FindBy(xpath = "//a[@aria-label='Nintendo eShop Card (US)']")
    public WebElement nintendoProductCard;

    // --- Search Bar ---
    @FindBy(className = "cy-search-product")
    public WebElement searchBarField;

    // --- Search Results ---
    // Kontainer hasil pencarian
    @FindBy(className = "cy-content-product")
    public WebElement searchResultContainer;

    // Item spesifik Nintendo eShop Card di dalam hasil pencarian
    @FindBy(xpath = "//div[contains(@class, 'cy-content-product')]//p[contains(text(), 'Nintendo eShop Card')]")
    public WebElement nintendoSearchResultItem;

    // --- Order Form ---
    @FindBy(name = "identifier")
    public WebElement emailInputField;

    @FindBy(xpath = "//button[contains(@aria-label, 'Nintendo $10')]//button[contains(text(), 'Rp 152,300')]")
    public WebElement denom10DollarButton;

    // --- Checkout & Payment ---
    @FindBy(xpath = "//div[@role='dialog']//button[@data-cy='payment-group-qris']")
    public WebElement qrisGroupAccordion;

    @FindBy(xpath = "//div[@role='dialog']//div[@data-cy='payment-item-qris']")
    public WebElement qrisPaymentItem;

    @FindBy(xpath = "//div[@role='dialog']//button[contains(@class, 'bg-primary') and (text()='Buy' or text()='Beli Sekarang')]")
    public WebElement buyNowButton;

    // --- Confirmation Page ---

    @FindBy(id = "payment-qrcode")
    public WebElement qrCodeElement;

    // --- Container Utama Pembayaran ---
    @FindBy(xpath = "//section[contains(@class, 'md:min-h-svh')]")
    public WebElement mainPaymentContainer;

    // --- Container QR Code ---
    // Menggunakan ID langsung pada elemen SVG
    @FindBy(id = "payment-qrcode")
    public WebElement qrCodeSvg;

    // Container pembungkus QR untuk scroll
    @FindBy(xpath = "//div[contains(@class, 'rounded-lg border border-brand')]")
    public WebElement qrCodeContainer;

    // --- Nomor Pesanan (TXN...) ---
    // Mengambil dari section kanan (Desktop detail) yang lebih stabil
    @FindBy(xpath = "//h4[text()='Nomor Pesanan']/following-sibling::div/span")
    public WebElement orderNumberText;

    // --- Metode Pembayaran ---
    @FindBy(xpath = "//h4[text()='Metode Pembayaran']/following-sibling::div/span")
    public WebElement paymentMethodText;

    // --- Nominal Pembayaran (Total) ---
    @FindBy(xpath = "//h4[text()='Total Pembayaran']/following-sibling::div//span")
    public WebElement totalPaymentAmount;

    // Elemen pembungkus halaman untuk verifikasi halaman sudah load sempurna
    @FindBy(xpath = "//span[text()='Menunggu Pembayaran']")
    public WebElement waitingPaymentTitle;
}