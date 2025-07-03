package com.nsk.configuration;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.MutableCapabilities;

import java.util.HashMap;
import java.util.Map;

public class DriverConfiguration {

    /**
     * Основной метод инициализации драйвера.
     * Читает системное свойство run.mode (local|selenoid),
     * по умолчанию — локальный режим.
     */
    public static void init() {
        String mode = System.getProperty("run.mode", "local").toLowerCase();
        if ("selenoid".equals(mode)) {
            initSelenoid();
        } else {
            initLocal();
        }
    }

    /**
     * Настройки для локального запуска
     */
    private static void initLocal() {
        Configuration.baseUrl = "https://www.saucedemo.com";
        Configuration.browser = "chrome";
        Configuration.timeout = 5000;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;

        // Общие опции Chrome:
        Configuration.browserCapabilities = chromeOptions();
    }

    /**
     * Настройки для запуска в Selenoid
     */
    /** Настройки для запуска в Selenoid */
    public static void initSelenoid() {
        Configuration.baseUrl       = "https://www.saucedemo.com";
        Configuration.browser       = "chrome";
        Configuration.browserVersion= "110.0";
        Configuration.timeout       = 5000;
        Configuration.browserSize   = "1920x1080";

        // Читаем URL Selenoid из свойства или fallback
        String selenoidUrl = System.getProperty("selenoid.url", "http://host.docker.internal:4444/wd/hub");
        Configuration.remote = selenoidUrl;

        // Опции Selenoid
        ChromeOptions options = new ChromeOptions();
        options.setCapability("selenoid:options", Map.of(
                "name", "Test containers",
                "enableVNC", true,
                "sessionTimeout", "15m"
        ));
        Configuration.browserCapabilities = options;
    }

    /** ChromeOptions + prefs для подавления попапов и стандартных настроек */
    private static MutableCapabilities chromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Отключаем менеджер паролей и проверку утечек
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        // Прочие флаги
        options.addArguments(
                "--disable-features=SafeBrowsing,PasswordLeakToggleMove",
                "--disable-extensions",
                "--start-maximized"
        );

        return options;
    }
}
