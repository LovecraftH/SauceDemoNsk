package com.nsk.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import com.nsk.pages.base.BasePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Page Object для страницы «Checkout: Overview».
 */
@Slf4j
public class CheckoutOverviewPage extends BasePage<CheckoutOverviewPage> {
    private static final String PAGE_URL = "/checkout-step-two.html";
    private final SelenideElement pageTitle = $x("//span[@class='title' and text()='Checkout: Overview']");
    private final ElementsCollection overviewItems = $$x("//div[@class='cart_item']");
    private final SelenideElement itemTotalLabel = $x("//div[contains(@class,'summary_subtotal_label')]");
    private final SelenideElement taxLabel = $x("//div[contains(@class,'summary_tax_label')]");
    private final SelenideElement totalLabel = $x("//div[contains(@class,'summary_total_label')]");
    private final SelenideElement cancelButton = $x("//button[@data-test='cancel']");
    private final SelenideElement finishButton = $x("//button[@data-test='finish']");

    @Override
    protected String url() {
        return PAGE_URL;
    }

    @Step("Открыть страницу «Checkout: Overview»")
    public CheckoutOverviewPage open() {
        log.info("Открытие страницы Overview: {}", url());
        super.open();
        return this;
    }

    @Step("Проверить страницу «Checkout: Overview»")
    public CheckoutOverviewPage verifyAtOverviewPage() {
        log.info("Проверка заголовка Overview");
        pageTitle.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить количество позиций в обзорe: {expectedCount}")
    public CheckoutOverviewPage verifyItemsCount(int expectedCount) {
        log.info("Проверка числа позиций: {}", expectedCount);
        overviewItems.shouldHave(CollectionCondition.size(expectedCount));
        return this;
    }

    /**
     * @param expectedText например, "Item total: $75.97"
     */
    @Step("Проверить Item total: {expectedText}")
    public CheckoutOverviewPage verifyItemTotal(String expectedText) {
        itemTotalLabel.shouldHave(Condition.text(expectedText));
        return this;
    }

    /**
     * @param expectedText например, "Tax: $6.08"
     */
    @Step("Проверить Tax: {expectedText}")
    public CheckoutOverviewPage verifyTax(String expectedText) {
        taxLabel.shouldHave(Condition.text(expectedText));
        return this;
    }

    /**
     * @param expectedText например, "Total: $82.05"
     */
    @Step("Проверить Total: {expectedText}")
    public CheckoutOverviewPage verifyTotal(String expectedText) {
        totalLabel.shouldHave(Condition.text(expectedText));
        return this;
    }

    @Step("Нажать Cancel на Overview")
    public ProductsPage clickCancel() {
        log.info("Клик Cancel на Overview");
        cancelButton.shouldBe(Condition.visible).click();
        return new ProductsPage();
    }

    @Step("Нажать Finish на Overview")
    public CheckoutCompletePage clickFinish() {
        log.info("Клик Finish на Overview");
        finishButton.shouldBe(Condition.visible).click();
        return new CheckoutCompletePage();
    }
}
