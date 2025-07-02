package com.nsk.pages;

import com.codeborne.selenide.SelenideElement;
import com.nsk.pages.base.BasePage;
import com.nsk.pages.components.FooterComponent;
import com.nsk.pages.components.HeaderComponent;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Page Object для страницы "Checkout: Complete!".
 */
@Slf4j
public class CheckoutCompletePage extends BasePage<CheckoutCompletePage> {

    private static final String PAGE_URL = "https://www.saucedemo.com/checkout-complete.html";
    private final SelenideElement pageTitle = $x("//span[@data-test='title' and text()='Checkout: Complete!']");
    private final SelenideElement ponyExpressImg = $x("//img[contains(@class,'pony_express')]");
    private final SelenideElement completeHeader = $x("//h2[@data-test='complete-header']");
    private final SelenideElement completeText = $x("//div[@data-test='complete-text']");
    private final SelenideElement backHomeBtn = $x("//button[@data-test='back-to-products']");
    public final HeaderComponent header = new HeaderComponent();
    public final FooterComponent footer = new FooterComponent();

    @Override
    protected String url() {
        return PAGE_URL;
    }

    @Step("Проверить, что это страница Checkout: Complete!")
    public CheckoutCompletePage verifyAtCheckoutCompletePage() {
        pageTitle.shouldBe(com.codeborne.selenide.Condition.visible);
        completeHeader.shouldHave(com.codeborne.selenide.Condition.text("Thank you for your order!"));
        completeText.shouldBe(com.codeborne.selenide.Condition.visible);
        ponyExpressImg.shouldBe(com.codeborne.selenide.Condition.visible);
        return this;
    }

    @Step("Нажать Back Home")
    public ProductsPage clickBackHome() {
        backHomeBtn.shouldBe(com.codeborne.selenide.Condition.visible).click();
        return new ProductsPage();
    }

    @Step("Проверить текст благодарности")
    public CheckoutCompletePage verifyThankYouText(String expected) {
        completeHeader.shouldHave(com.codeborne.selenide.Condition.text(expected));
        return this;
    }

    @Step("Проверить текст доставки")
    public CheckoutCompletePage verifyDispatchText(String expected) {
        completeText.shouldHave(com.codeborne.selenide.Condition.text(expected));
        return this;
    }

    @Step("Проверить header на Checkout Complete Page")
    public CheckoutCompletePage verifyHeaderVisible() {
        header.verifyVisible();
        return this;
    }

    @Step("Проверить footer на Checkout Complete Page")
    public CheckoutCompletePage verifyFooterVisible() {
        footer.verifyVisible();
        return this;
    }

}
