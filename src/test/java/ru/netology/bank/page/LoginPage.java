package ru.netology.bank.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.bank.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginFile = $("[data-test-id=login] input");
    private SelenideElement passwordFile = $("[data-test-id=password] input");
    private SelenideElement buttonFile = $("[data-test-id=action-login]");
    private SelenideElement errorMessage = $("[data-test-id=error-notification] .notification__content");



    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginFile.setValue(info.getLogin());
        passwordFile.setValue(info.getPassword());
        buttonFile.click();
        return new VerificationPage();
    }
    public void veryfiErrorMessage(String expectedText){
        errorMessage.shouldHave(Condition.exactText(expectedText)).shouldBe(Condition.visible);
    }

}
