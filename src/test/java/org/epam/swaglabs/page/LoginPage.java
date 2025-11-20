package org.epam.swaglabs.page;

//import jdk.jpackage.internal.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.swaglabs.User;
//import org.junit.platform.commons.logging.Logger;
//import org.junit.platform.commons.logging.LoggerFactory;
//import org.junit.platform.commons.logging.LoggerFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.apache.logging.log4j.*;

import java.time.Duration;

public class LoginPage extends AbstractPage{
    public static String HOMEPAGE_URL = "https://www.saucedemo.com/";
    private final Logger logger = LogManager.getRootLogger();
            //LoggerFactory.getLogger(LoginPage.class);

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement username;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;
    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public LoginPage openPage() {
        driver.get(HOMEPAGE_URL);
        logger.info("Login page opened");
        return this;
    }
    public LoginPage enterUsername(String username){
        this.username.sendKeys(username);
        return this;
    }
    public LoginPage enterPassword(String password){
        this.password.sendKeys(password);
        return this;
    }
    public LoginPage clearUsername(){
        username.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        return this;
    }
    public LoginPage clearPassword(){
        password.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        return this;
    }
    public AbstractPage login(){
        loginButton.click();
        return this;
    }
    public AbstractPage login(User user){
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());

        login();
            try {
                WebElement login = new WebDriverWait(driver, Duration.ofMillis(500))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\'login-button\']")));
            }catch (TimeoutException timeoutException){
                return successfulLogin();
            }
            return unsuccessfulLogin();
    }
    public LoginPage unsuccessfulLogin(){
        return this;
    }
    public EnteredPage successfulLogin(){
        return new EnteredPage(driver);
    }
}
