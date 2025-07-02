package tests;

import com.nsk.constants.Credentials;
import com.nsk.constants.ProductNames;
import com.nsk.enums.SortingOption;
import com.nsk.enums.UserType;
import com.nsk.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты страницы продуктов")
public class ProductsTest extends BaseTest {

    @Test
    @DisplayName("Сортировка и добавление товаров в корзину")
    void sortAndAddToCartTest() {
        int expectedItemsInCart = new LoginPage()
                .open()
                .setUsername(UserType.STANDARD.getUsername())
                .setPassword(Credentials.PASSWORD)
                .clickLogin()
                .verifyAtProductsPage()
                .sortBy(SortingOption.PRICE_LOW_TO_HIGH.getDisplayName())
                .verifyProductsCount(6)
                .addProductToCart(ProductNames.SAUCE_LABS_ONESIE)
                .addProductToCart(ProductNames.SAUCE_LABS_BACKPACK)
                .getCartBadgeCount();

        assertEquals(2, expectedItemsInCart, "В корзине должно быть 2 товара");
    }
}