package tests;

import com.nsk.constants.Credentials;
import com.nsk.constants.ProductNames;
import com.nsk.enums.UserType;
import com.nsk.pages.LoginPage;
import com.nsk.pages.ProductsPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

@DisplayName("Страница корзины")
public class CartTest extends BaseTest {

    @Test
    @DisplayName("Добавить и удалить товар в корзине")
    void addRemoveCartItemTest() {
        new LoginPage()
                .loginAs(UserType.STANDARD.getUsername(), Credentials.PASSWORD)
                .verifyAtProductsPage()
                .addProductToCart(ProductNames.SAUCE_LABS_BACKPACK)
                .addProductToCart(ProductNames.SAUCE_LABS_BIKE_LIGHT)
                .getCartBadgeCount();  // badge = 2

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
        new LoginPage()
                .loginAs(UserType.STANDARD.getUsername(), Credentials.PASSWORD)
                .verifyAtProductsPage()
                .addProductToCart("Sauce Labs Onesie")
                .goToCart()
                .verifyAtCartPage()
                .clickCheckout()
                .verifyAtCheckoutInformationPage();
    }
}
