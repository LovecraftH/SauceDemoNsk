package com.nsk.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Компонент Header (шапка сайта).
 */
public class HeaderComponent {
    private final SelenideElement headerContainer = $x("//div[@data-test='header-container']");
    private final SelenideElement logo = $x("//div[contains(@class,'app_logo')]");
    private final SelenideElement cartIcon = $x("//a[@data-test='shopping-cart-link']");
    private final SelenideElement menuButton = $x("//button[@id='react-burger-menu-btn']");

    @Step("Проверить отображение header")
    public HeaderComponent verifyVisible() {
        headerContainer.shouldBe(com.codeborne.selenide.Condition.visible);
        return this;
    }

    @Step("Клик по иконке корзины")
    public void clickCart() {
        cartIcon.shouldBe(com.codeborne.selenide.Condition.visible).click();
    }

    @Step("Открыть меню")
    public void openMenu() {
        menuButton.shouldBe(com.codeborne.selenide.Condition.visible).click();
    }
}
