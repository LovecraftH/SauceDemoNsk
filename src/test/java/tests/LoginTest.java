package tests;

import com.nsk.constants.Credentials;
import com.nsk.enums.UserType;
import com.nsk.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

@DisplayName("Авторизация на Swag Labs")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Успешный вход стандартного пользователя")
    void successfulLoginTest() {
        new LoginPage()
                .loginAs(UserType.STANDARD.getUsername(), Credentials.PASSWORD)
                .verifyAtProductsPage();              // теперь реализован в ProductsPage
    }

    @Test
    @DisplayName("Попытка входа заблокированного пользователя")
    void lockedOutUserTest() {
        new LoginPage()
                .open()
                .setUsername(UserType.LOCKED_OUT.getUsername())
                .setPassword(Credentials.PASSWORD)
                .clickLoginExpectingFailure()
                .verifyErrorMessage("Epic sadface: Sorry, this user has been locked out.");
    }
}

