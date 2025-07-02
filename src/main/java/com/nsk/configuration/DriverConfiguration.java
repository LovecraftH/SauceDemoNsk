package com.nsk.configuration;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.MutableCapabilities;

import java.util.HashMap;
import java.util.Map;

public class DriverConfiguration {
    /**
     * Читает системное свойство run.mode (local|selenoid) и инициализирует драйвер.
     * По умолчанию — локальный режим.
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
        Configuration.remote        = null; // отключаем remote
        Configuration.browserCapabilities = chromeOptions();
    }

    /** Настройки для запуска в Selenoid */
    private static void initSelenoid() {
        Configuration.baseUrl       = "https://www.saucedemo.com";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "110.0";
        Configuration.timeout       = 5000;
        Configuration.browserSize   = "1920x1080";
        // URL Selenoid берём из свойства или по умолчанию
        Configuration.remote = "http://localhost:4444/wd/hub";

        // Дополнительные опции Selenoid: VNC, видео
        ChromeOptions options = new ChromeOptions();
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("name", "Test containers");
            put("enableVNC", true);
            put("sessionTimeout", "15m");
        }});
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
