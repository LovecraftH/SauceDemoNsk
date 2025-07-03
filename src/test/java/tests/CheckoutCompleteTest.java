package tests;

import com.nsk.constants.Credentials;
import com.nsk.constants.ProductNames;
import com.nsk.enums.UserType;
import com.nsk.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

@DisplayName("Тесты страницы Checkout: Complete!")
public class CheckoutCompleteTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешного завершения заказа")
    void orderCompleteTest() {
        new LoginPage()
                .loginAs(UserType.STANDARD.getUsername(), Credentials.PASSWORD)
                .addProductToCart(ProductNames.SAUCE_LABS_ONESIE)
                .goToCart()
                .clickCheckout()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setPostalCode("123456")
                .clickContinue()
                .clickFinish()
                .verifyAtCheckoutCompletePage()
                .verifyThankYouText("Thank you for your order!")
                .verifyDispatchText("Your order has been dispatched, and will arrive just as fast as the pony can get there!")
                .verifyHeaderVisible()
                .verifyHeaderVisible()
                .clickBackHome()
                .verifyAtProductsPage();
    }
}
