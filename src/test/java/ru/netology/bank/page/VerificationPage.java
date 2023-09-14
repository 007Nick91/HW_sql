package ru.netology.bank.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeFile = $("[data-test-id=code] input");
    private SelenideElement codeButton = $("[data-test-id=action-verify]");
    private SelenideElement errorMessage = $("[data-test-id=error-notification] .notification__content");

    public void verifyVerificationPageVisi() {
        codeFile.shouldBe(Condition.visible);
    }
    public void veryfiErrorMessage(String expectedText){
        errorMessage.shouldHave(Condition.exactText(expectedText)).shouldBe(Condition.visible);
    }

    public DashboardPage validVerify(String verificationCode) {
        verify(verificationCode);
        return new DashboardPage();
    }
    public void verify(String verificationCode){
        codeFile.setValue(verificationCode);
        codeButton.click();
    }

}
