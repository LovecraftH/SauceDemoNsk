package com.nsk.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import com.nsk.pages.base.BasePage;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object для страницы продуктов (Inventory).
 */
@Slf4j
public class ProductsPage extends BasePage<ProductsPage> {
    private static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";
    private final SelenideElement pageTitle = $x("//span[@class='title' and text()='Products']");
    private final ElementsCollection productItems = $$x("//div[@class='inventory_item']");
    private final SelenideElement sortSelect = $x("//select[@class='product_sort_container']");
    private final SelenideElement cartIcon = $x("//a[@class='shopping_cart_link']");
    private final SelenideElement cartBadge = $x("//span[@class='shopping_cart_badge']");

    @Override
    protected String url() {
        return PAGE_URL;
    }

    @Step("Открыть страницу продуктов")
    public ProductsPage open() {
        log.info("Открываю страницу продуктов: {}", PAGE_URL);
        super.open();
        return this;
    }

    @Step("Проверить, что это страница Products")
    public ProductsPage verifyAtProductsPage() {
        log.info("Проверка заголовка страницы продуктов");
        pageTitle.shouldBe(Condition.visible);
        return this;
    }

    @Step("Сортировать товары по: {optionText}")
    public ProductsPage sortBy(String optionText) {
        log.info("Сортировка товаров: {}", optionText);
        sortSelect.selectOption(optionText);
        return this;
    }

    @Step("Добавить товар в корзину: {productName}")
    public ProductsPage addProductToCart(String productName) {
        log.info("Добавление в корзину товара: {}", productName);
        $x(String.format("//div[contains(@class,'inventory_item_name')][text()='%s']/ancestor::div[@class='inventory_item']//button", productName))
                .shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Удалить товар из корзины: {productName}")
    public ProductsPage removeProductFromCart(String productName) {
        log.info("Удаление из корзины товара: {}", productName);
        $x(String.format("//div[contains(@class,'inventory_item_name')][text()='%s']/ancestor::div[@class='inventory_item']//button", productName))
                .shouldHave(Condition.text("Remove")).click();
        return this;
    }

    @Step("Получить цену товара: {productName}")
    public String getProductPrice(String productName) {
        log.info("Чтение цены для товара: {}", productName);
        return $x(String.format("//div[contains(@class,'inventory_item_name')][text()='%s']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']", productName))
                .getText();
    }

    @Step("Получить количество в иконке корзины")
    public int getCartBadgeCount() {
        log.info("Чтение счётчика корзины");
        return cartBadge.shouldBe(Condition.visible).getText().trim().isEmpty()
                ? 0
                : Integer.parseInt(cartBadge.getText());
    }

    @Step("Перейти в корзину")
    public CartPage goToCart() {
        log.info("Переход в корзину");
        cartIcon.shouldBe(enabled).click();
        return new CartPage();
    }

    @Step("Проверить количество карточек товаров: {expectedCount}")
    public ProductsPage verifyProductsCount(int expectedCount) {
        log.info("Проверка числа карточек: {}", expectedCount);
        productItems.shouldHave(CollectionCondition.size(expectedCount));
        return this;
    }
}
