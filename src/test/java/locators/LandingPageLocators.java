package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class LandingPageLocators {

    // --- Header Elements ---

    @FindBy(xpath = "//img[@alt='logo-vocagame']")
    public WebElement vocaGameLogo;

    @FindBy(css = "input.cy-search-product")
    public WebElement searchField;

    @FindBy(xpath = "//button[text()='Masuk']")
    public WebElement loginButton;

    @FindBy(xpath = "//img[@alt='voca-coins']/parent::div")
    public WebElement vocaCoinsIcon;

    @FindBy(xpath = "//button[contains(., 'Indonesia')]")
    public WebElement languagePicker;

    // --- Navigation Menu (Navbar) ---

    @FindBy(xpath = "//a[text()='Beranda']")
    public WebElement homeMenu;

    @FindBy(xpath = "//a[text()='Riwayat Pesanan']")
    public WebElement orderHistoryMenu;

    @FindBy(xpath = "//a[text()='Kalkulator MLBB']")
    public WebElement mlbbCalculatorMenu;

    @FindBy(xpath = "//a[text()='Berita']")
    public WebElement newsMenu;

    @FindBy(xpath = "//div[@role='dialog']")
    public WebElement popupDialog;

    // Pop-up
    @FindBy(xpath = "//div[@role='dialog']//div[contains(@class, 'cursor-pointer')]")
    public WebElement closePopupButton;

    @FindBy(id = "pop-up-banner")
    public WebElement popupBannerImage;

    @FindBy(xpath = "//div[@role='dialog']//a")
    public WebElement popupBannerLink;

    // --- Product Tabs (Categories) ---

    @FindBy(xpath = "//button[@aria-label='button tabs Populer']")
    public WebElement popularTab;

    @FindBy(xpath = "//button[@aria-label='button tabs Mobile Games']")
    public WebElement mobileGamesTab;

    @FindBy(xpath = "//button[@aria-label='button tabs Voucher']")
    public WebElement voucherTab;

    // --- Product Cards ---

    // Menambil semua kartu produk di section "Populer"
    @FindBy(xpath = "//*[@id='Populer']//li//a")
    public List<WebElement> popularProductCards;

    @FindBy(xpath = "//a[@aria-label='Mobile Legends: Bang Bang']")
    public WebElement mlbbProductCard;

    @FindBy(xpath = "//a[@aria-label='Free Fire']")
    public WebElement freeFireProductCard;

    // --- Flash Deal Section ---

    @FindBy(id = "flash-deal")
    public WebElement flashDealSection;

    @FindBy(css = "div.flash-deal a")
    public List<WebElement> flashDealItems;

    // --- Cookie Consent ---

    @FindBy(xpath = "//button[text()='Terima Semua Cookie']")
    public WebElement acceptAllCookiesButton;

    @FindBy(xpath = "//button[text()='Tolak Semua Cookie']")
    public WebElement rejectAllCookiesButton;

    // --- Footer Elements ---

    @FindBy(xpath = "//p[contains(text(), 'VocaGame © 2025')]")
    public WebElement footerCopyrightText;
}