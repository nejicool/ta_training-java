package org.epam.swaglabs.page;

import org.epam.swaglabs.User;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AbstractPage{
    public static String HOMEPAGE_URL = "https://www.saucedemo.com/";

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
        username.clear();
        return this;
    }
    public LoginPage clearPassword(){
        password.clear();
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
