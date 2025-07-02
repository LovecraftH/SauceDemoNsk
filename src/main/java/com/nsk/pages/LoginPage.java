package com.nsk.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import com.nsk.pages.base.BasePage;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Page Object для страницы логина Swag Labs.
 */
@Slf4j
public class LoginPage extends BasePage<LoginPage> {
    // URL страницы логина
    private static final String PAGE_URL = "https://www.saucedemo.com/";
    private final SelenideElement usernameInput = $x("//input[@data-test='username']");     // Поле "Username"
    private final SelenideElement passwordInput = $x("//input[@data-test='password']");     // Поле "Password"
    private final SelenideElement loginButton = $x("//input[@data-test='login-button']"); // Кнопка "Login"
    private final SelenideElement errorMessage = $x("//h3[@data-test='error']");           // Контейнер ошибок

    @Override
    protected String url() {
        return PAGE_URL;
    }

    @Step("Ввести username: {username}")
    public LoginPage setUsername(String username) {
        log.info("Ввод username: {}", username);
        waitVisible(usernameInput);
        usernameInput.clear();
        usernameInput.setValue(username);
        return this;
    }

    @Step("Ввести пароль: {password}")
    public LoginPage setPassword(String password) {
        log.info("Ввод пароля");
        waitVisible(passwordInput);
        passwordInput.clear();
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажать кнопку Login")
    public ProductsPage clickLogin() {
        log.info("Клик по кнопке Login");
        waitVisible(loginButton);
        loginButton.click();
        return new ProductsPage(); // Предполагаемый следующий Page Object
    }

    @Step("Нажать кнопку Login (ожидаем ошибку)")
    public LoginPage clickLoginExpectingFailure() {
        log.info("Клик по кнопке Login (ошибка)");
        waitVisible(loginButton);
        loginButton.click();
        return this;
    }

    @Step("Проверить сообщение об ошибке: {expectedText}")
    public LoginPage verifyErrorMessage(String expectedText) {
        errorMessage.shouldHave(Condition.text(expectedText));
        return this;
    }

    @Step("Получить текст ошибки")
    public String getErrorMessage() {
        return errorMessage.is(Condition.visible)
                ? errorMessage.getText()
                : "";
    }
}
