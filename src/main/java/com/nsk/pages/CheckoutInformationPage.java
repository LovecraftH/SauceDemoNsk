package com.nsk.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import com.nsk.pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Page Object для страницы оформления: ввод личной информации.
 */
@Slf4j
public class CheckoutInformationPage extends BasePage<CheckoutInformationPage> {

    private static final String PAGE_URL = "https://www.saucedemo.com/checkout-step-one.html";

    // Заголовок страницы "Checkout: Your Information"
    private final SelenideElement pageTitle = $x("//span[@class='title' and text()='Checkout: Your Information']");

    // Поля ввода
    private final SelenideElement firstNameInput = $x("//input[@data-test='firstName']");
    private final SelenideElement lastNameInput = $x("//input[@data-test='lastName']");
    private final SelenideElement postalCodeInput = $x("//input[@data-test='postalCode']");

    // Кнопки
    private final SelenideElement cancelButton = $x("//button[@data-test='cancel']");
    private final SelenideElement continueButton = $x("//input[@data-test='continue']");

    @Override
    protected String url() {
        return PAGE_URL;
    }

    /**
     * Открыть страницу оформления информации.
     */
    @Step("Открыть страницу «Your Information»")
    public CheckoutInformationPage open() {
        log.info("Открытие страницы оформления: {}", url());
        super.open();
        return this;
    }

    @Step("Проверить страницу «Checkout: Your Information»")
    public CheckoutInformationPage verifyAtCheckoutInformationPage() {
        log.info("Проверка заголовка оформления");
        pageTitle.shouldBe(Condition.visible);
        return this;
    }


    @Step("Ввести First Name: {firstName}")
    public CheckoutInformationPage setFirstName(String firstName) {
        log.info("Ввод First Name: {}", firstName);
        firstNameInput.shouldBe(Condition.visible).clear();
        firstNameInput.setValue(firstName);
        return this;
    }

    @Step("Ввести Last Name: {lastName}")
    public CheckoutInformationPage setLastName(String lastName) {
        log.info("Ввод Last Name: {}", lastName);
        lastNameInput.shouldBe(Condition.visible).clear();
        lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Ввести Postal Code: {postalCode}")
    public CheckoutInformationPage setPostalCode(String postalCode) {
        log.info("Ввод Postal Code: {}", postalCode);
        postalCodeInput.shouldBe(Condition.visible).clear();
        postalCodeInput.setValue(postalCode);
        return this;
    }

    @Step("Нажать Cancel на странице оформления")
    public CartPage clickCancel() {
        log.info("Клик по Cancel");
        cancelButton.shouldBe(Condition.visible).click();
        return new CartPage();
    }
    @Step("Нажать Continue на странице оформления")
    public CheckoutOverviewPage clickContinue() {
        log.info("Клик по Continue");
        continueButton.shouldBe(Condition.visible).click();
        return new CheckoutOverviewPage();
    }
}
