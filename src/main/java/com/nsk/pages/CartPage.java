package com.nsk.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import com.nsk.pages.base.BasePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object для страницы "Your Cart".
 */
@Slf4j
public class CartPage extends BasePage<CartPage> {
    private static final String PAGE_URL = "https://www.saucedemo.com/cart.html";
    private final SelenideElement pageTitle = $x("//span[@class='title' and text()='Your Cart']");
    private final ElementsCollection cartItems = $$x("//div[@class='cart_item']");
    private final SelenideElement continueShoppingBtn = $x("//button[@data-test='continue-shopping']");
    private final SelenideElement checkoutBtn = $x("//button[@data-test='checkout']");

    @Override
    protected String url() {
        return PAGE_URL;
    }

    @Step("Открыть страницу корзины")
    public CartPage open() {
        log.info("Открываю страницу корзины: {}", PAGE_URL);
        super.open();
        return this;
    }

    @Step("Проверить страницу Cart")
    public CartPage verifyAtCartPage() {
        log.info("Проверка заголовка корзины");
        pageTitle.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверить количество товаров в корзине: {expectedCount}")
    public CartPage verifyCartItemsCount(int expectedCount) {
        log.info("Проверка количества элементов: {}", expectedCount);
        cartItems.shouldHave(CollectionCondition.size(expectedCount));
        return this;
    }

    @Step("Получить название товара под индексом: {index}")
    public String getItemName(int index) {
        return cartItems.get(index)
                .$x(".//div[contains(@class,'inventory_item_name')]").getText();
    }

    @Step("Получить цену товара под индексом: {index}")
    public String getItemPrice(int index) {
        return cartItems.get(index)
                .$x(".//div[@class='inventory_item_price']").getText();
    }

    @Step("Получить количество товара: {productName}")
    public int getItemQuantity(String productName) {
        String qty = $x(String.format(
                "//div[contains(@class,'inventory_item_name')][text()='%s']" +
                        "/ancestor::div[contains(@class,'cart_item')]" +
                        "//div[contains(@class,'cart_quantity')]", productName))
                .getText();
        return Integer.parseInt(qty);
    }

    @Step("Удалить товар из корзины: {productName}")
    public CartPage removeItem(String productName) {
        log.info("Удаление товара из корзины: {}", productName);
        $x(String.format(
                "//div[contains(@class,'inventory_item_name')][text()='%s']" +
                        "/ancestor::div[contains(@class,'cart_item')]" +
                        "//button[contains(@data-test,'remove')]", productName))
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    @Step("Нажать Continue Shopping")
    public ProductsPage clickContinueShopping() {
        log.info("Клик Continue Shopping");
        continueShoppingBtn.shouldBe(Condition.visible).click();
        return new ProductsPage();
    }

    @Step("Нажать Checkout")
    public CheckoutInformationPage clickCheckout() {
        log.info("Клик Checkout");
        checkoutBtn.shouldBe(Condition.visible).click();
        return new CheckoutInformationPage();
    }
}
