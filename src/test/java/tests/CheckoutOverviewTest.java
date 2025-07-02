package tests;

import com.nsk.constants.Credentials;
import com.nsk.constants.ProductNames;
import com.nsk.enums.SortingOption;
import com.nsk.enums.UserType;
import com.nsk.pages.CheckoutCompletePage;
import com.nsk.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

@DisplayName("Тесты страницы «Checkout: Overview»")
public class CheckoutOverviewTest extends BaseTest {

    @Test
    @DisplayName("Отмена оформления на странице Overview")
    void cancelFromOverviewTest() {
        new LoginPage()
                .open()
                .setUsername(UserType.STANDARD.getUsername())
                .setPassword(Credentials.PASSWORD)
                .clickLogin()
                .verifyAtProductsPage()
                .sortBy(SortingOption.PRICE_LOW_TO_HIGH.getDisplayName())
                .addProductToCart(ProductNames.SAUCE_LABS_ONESIE)
                .addProductToCart(ProductNames.SAUCE_LABS_BACKPACK)
                .goToCart()
                .verifyAtCartPage()
                .clickCheckout()
                .verifyAtCheckoutInformationPage()
                .setFirstName("Ivan")
                .setLastName("Petrov")
                .setPostalCode("12345")
                .clickContinue()
                .verifyAtOverviewPage()
                .verifyItemsCount(2)
                .verifyItemTotal("Item total: $37.98")
                .verifyTax("Tax: $3.04")
                .verifyTotal("Total: $41.02")
                .clickCancel()
                .verifyAtProductsPage();
    }

    @Test
    @DisplayName("Полное оформление заказа")
    void finishCheckoutTest() {
        new LoginPage()
                .open()
                .setUsername(UserType.STANDARD.getUsername())
                .setPassword(Credentials.PASSWORD)
                .clickLogin()
                .verifyAtProductsPage()
                .addProductToCart(ProductNames.SAUCE_LABS_BACKPACK)
                .goToCart()
                .verifyAtCartPage()
                .clickCheckout()
                .verifyAtCheckoutInformationPage()
                .setFirstName("Anna")
                .setLastName("Ivanova")
                .setPostalCode("54321")
                .clickContinue()
                .verifyAtOverviewPage()
                .clickFinish()
                .verifyThankYouText("Thank you for your order!");
    }
}