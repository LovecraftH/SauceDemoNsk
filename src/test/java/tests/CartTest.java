package tests;

import com.nsk.constants.Credentials;
import com.nsk.constants.ProductNames;
import com.nsk.enums.UserType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.nsk.pages.CartPage;
import com.nsk.pages.LoginPage;
import com.nsk.pages.ProductsPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import tests.base.BaseTest;

/**
 * Тесты для страницы корзины.
 */
@DisplayName("Страница корзины")
public class CartTest extends BaseTest {

    @Test
    @DisplayName("Добавить и удалить товар в корзине")
    void addRemoveCartItemTest() {
        // Логинимся и добавляем товар
        new LoginPage().open()
                .setUsername(UserType.STANDARD.getUsername())
                .setPassword(Credentials.PASSWORD)
                .clickLogin()
                .verifyAtProductsPage()
                .addProductToCart(ProductNames.SAUCE_LABS_BACKPACK)
                .addProductToCart(ProductNames.SAUCE_LABS_BIKE_LIGHT)
                .getCartBadgeCount();  // badge = 2

        // Переходим в корзину и проверяем
        new ProductsPage().goToCart()
                .verifyAtCartPage()
                .verifyCartItemsCount(2)
                .removeItem(ProductNames.SAUCE_LABS_BACKPACK)
                .verifyCartItemsCount(1)
                .clickContinueShopping()
                .verifyAtProductsPage();
    }

    @Test
    @DisplayName("Переход к оформлению заказа")
    void proceedToCheckoutTest() {
        new LoginPage().open()
                .setUsername(UserType.STANDARD.getUsername())
                .setPassword(Credentials.PASSWORD)
                .clickLogin()
                .verifyAtProductsPage()
                .addProductToCart("Sauce Labs Onesie")
                .goToCart()
                .verifyAtCartPage()
                .clickCheckout()
                .verifyAtCheckoutInformationPage();
    }
}
