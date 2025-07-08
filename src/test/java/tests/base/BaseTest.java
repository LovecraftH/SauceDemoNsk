package tests.base;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.nsk.configuration.DriverConfiguration;
import com.nsk.configuration.DriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeEach
    void setUp() {
        DriverConfiguration.init();
        DriverManager.initDriver();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @AfterEach
    void tearDown() {
        DriverManager.quitDriver();
    }
}
