package ru.netology.bank.test;

import org.junit.jupiter.api.*;
import ru.netology.bank.data.DataHelper;
import ru.netology.bank.data.SQLHalper;
import ru.netology.bank.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.bank.data.SQLHalper.cleanAuthCodes;
import static ru.netology.bank.data.SQLHalper.cleanDatabase;


public class LoginTest {
    LoginPage loginPage;

    @BeforeEach
    void openSite(){
        loginPage = open("http://localhost:9999/", LoginPage.class);
    }

    @AfterEach
    void cleanAuth(){
        cleanAuthCodes();
    }
    @AfterAll
    static void cleanData(){
        cleanDatabase();
    }
    @Test
    @DisplayName("Should successfully login to dashboard with exist login and password from sut test")
    void shouldSuccessLogin(){
        var authInfo = DataHelper.getAuthInfoTest();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisi();
        var verificationCod = SQLHalper.getVerificationCode();
        verificationPage.validVerify(verificationCod.getCode());
    }

    @Test
    @DisplayName("Should show an error if the user is not registered")
    void shouldShowErrorUserNotRegistered(){
        var authInfo = DataHelper.generateRandomUser();
        loginPage.validLogin(authInfo);
        loginPage.veryfiErrorMessage("Ошибка! \nНеверно указан логин или пароль");
    }
    @Test
    @DisplayName("Should show an error if verification code not valid")
    void shouldShowErrorVerifiCodeNotValid(){
        var authInfo = DataHelper.getAuthInfoTest();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisi();
        var verificationCode = DataHelper.generateRanVerificationCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.veryfiErrorMessage("Ошибка! \nНеверно указан код! Попробуйте ещё раз.");

    }



}
