package utils;

import org.openqa.selenium.WebDriver;
import stepdefinitions.Hooks; // Mengimpor Hooks untuk mengakses driver

public class HelperClass {

    private static WebDriver driver;

    /**
     * Metode untuk mendapatkan instance WebDriver yang sudah dibuat oleh Hooks.
     * @return WebDriver instance.
     */
    public static WebDriver getDriver() {
        driver = Hooks.driver;
        if (driver == null) {
            throw new IllegalStateException("WebDriver belum diinisialisasi. Pastikan @Before di Hooks telah dijalankan.");
        }
        return driver;
    }

    /**
     * Metode ini tidak lagi diperlukan karena inisialisasi driver
     * sudah ditangani oleh metode @Before di Hooks.
     */
    // public static void setUpDriver() { ... }

    /**
     * Metode ini juga tidak lagi diperlukan karena penutupan driver
     * sudah ditangani oleh metode @After di Hooks.
     */
    // public static void tearDown() { ... }

    /**
     * Metode untuk membuka URL pada WebDriver.
     * @param url URL yang akan dibuka.
     */
    public static void openPage(String url) {
        getDriver().get(url);
    }

    /**
     * Metode untuk mendapatkan URL saat ini.
     * @return String URL saat ini.
     */
    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public static void waitFor(int i) throws InterruptedException {

        Thread.sleep(i);
    }
}