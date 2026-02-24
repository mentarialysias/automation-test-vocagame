/**
 * ============================================================================
 * Project     : ProHukum Automation Tester
 * Author      : Mentari Ayu Alysia Sudrajat
 * Organization: ProHukum
 * Version     : 1.0.0
 * Since       : 2025-08-18
 * Last Update : 2025-08-18
 * ============================================================================
 */
package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Tidak bisa load config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}