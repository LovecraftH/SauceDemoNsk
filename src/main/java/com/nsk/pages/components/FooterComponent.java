package com.nsk.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Компонент Footer (подвал сайта).
 */
public class FooterComponent {
    private final SelenideElement footer = $x("//footer[@data-test='footer']");
    private final SelenideElement twitter = $x("//a[@data-test='social-twitter']");
    private final SelenideElement facebook = $x("//a[@data-test='social-facebook']");
    private final SelenideElement linkedin = $x("//a[@data-test='social-linkedin']");

    @Step("Проверить отображение footer")
    public FooterComponent verifyVisible() {
        footer.shouldBe(com.codeborne.selenide.Condition.visible);
        return this;
    }

    @Step("Клик по Twitter")
    public void clickTwitter() {
        twitter.click();
    }
    // Аналогично для facebook, linkedin
}
