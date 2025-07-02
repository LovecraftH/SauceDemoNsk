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

    /** Настройки для локального запуска */
    private static void initLocal() {
        Configuration.baseUrl       = "https://www.saucedemo.com";
        Configuration.browser       = "chrome";
        Configuration.timeout       = 5000;
        Configuration.browserSize   = "1920x1080";
        Configuration.remote        = null; // локальный запуск, без remote

        // Общие опции Chrome:
        Configuration.browserCapabilities = chromeOptions();
    }

    /** Настройки для запуска в Selenoid */
    private static void initSelenoid() {
        Configuration.baseUrl       = "https://www.saucedemo.com";
        Configuration.browser       = "chrome";
        Configuration.timeout       = 5000;
        Configuration.browserSize   = "1920x1080";
        // URL Selenoid берём из параметра:
        Configuration.remote        = System.getProperty("selenoid.url", "http://localhost:4444/wd/hub");

        // Общие опции Chrome + Selenoid-специфичные capabilities:
        ChromeOptions options = (ChromeOptions) chromeOptions();
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", false);
        selenoidOptions.put("sessionTimeout", "15m");
        options.setCapability("selenoid:options", selenoidOptions);

        Configuration.browserCapabilities = options;
    }

    /**
     * Общие настройки ChromeOptions:
     * 1) Отключение автозаполнения и проверки утечек паролей.
     * 2) Флаги для корректного запуска в контейнере.
     * 3) Опция remote-debugging для новых версий ChromeDriver.
     */
    private static MutableCapabilities chromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // 1) prefs для отключения менеджера паролей
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        // 2) Основные аргументы
        options.addArguments(
                "--disable-features=SafeBrowsing,PasswordLeakToggleMove",
                "--disable-extensions",
                "--no-sandbox",                // важно для Docker/Selenoid
                "--disable-dev-shm-usage",     // важно для Docker/Selenoid
                "--start-maximized"
        );

        // 3) Устранение ошибки DevToolsActivePort
        options.addArguments("--remote-debugging-pipe");

        return options;
    }
}
