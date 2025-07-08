package com.nsk.pages.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

/**
 * Базовый класс для всех Page Object.
 *
 * @param <T> тип наследника для метод-чейнинга
 */
@Slf4j
public abstract class BasePage<T extends BasePage<T>> {

    static {
        Configuration.baseUrl = "https://www.saucedemo.com";
        Configuration.browser = "chrome";
        Configuration.timeout = 5000;
    }

    // Каждый наследник указывает свой URL
    protected abstract String url();

    /**
     * Открыть страницу в браузере.
     */
    @Step("Открыть страницу {url()}")
    public T open() {
        log.info("Открытие URL: {}", url());
        Selenide.open(url());
        return (T) this;
    }

    /**
     * Ждать, пока элемент станет видимым.
     */
    @Step("Ожидание видимости элемента")
    protected void waitVisible(SelenideElement element) {
        element.shouldBe(Condition.visible);
    }
}