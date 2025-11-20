package org.epam.swaglabs.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.epam.swaglabs.*;
import org.epam.swaglabs.driver.DriverSingleton;
import org.epam.swaglabs.page.LoginPage;
import org.epam.swaglabs.TestListener;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
//import org.testng.annotations.Listeners;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//import static org.assertj.core.api.Assertions.*;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//@RunWith(Suite.class)
//@Suite.SuiteClasses({})
//@RunWith(MyTestRunner.class)
@Listeners({TestListener.class})
public class AppTest //extends TestListener
//        implements ITestListener
{

//    public AppTest( String testName )
//    {
//        super( testName );
//    }

    @AfterMethod
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

    @org.testng.annotations.Test
//    @org.junit.Test
    public void testEmptyName() {
        WebElement some = new LoginPage(DriverSingleton.getDriver())
                .openPage()
                .enterUsername(Util.generateRandomStringWithSize(7))
                .enterPassword(Util.generateRandomStringWithSize(10))
                .clearUsername()
                .clearPassword()
                .login()
                .findSingleElementContainingText("Username is required");

        assertThat(some.getText()).contains("Username is required");
    }

    @org.testng.annotations.Test
//    @org.junit.Test
    public void testEmptyPassword() {
        WebElement some = new LoginPage(DriverSingleton.getDriver())
                .openPage()
                .enterUsername(Util.generateRandomStringWithSize(7))
                .enterPassword("")
                .clearPassword()
                .login()
                .findSingleElementContainingText("Password is required");
        assertThat(some.getText()).contains("Password is required");
    }

    @org.testng.annotations.Test
//    @org.junit.Test
    public void testLoginAndCheckTitle() {
        //For custom properties creation
        User user = User.createUserFromProperties();
        WebElement
                some = new LoginPage(DriverSingleton.getDriver())
                .openPage()
//                .login(new User("standard_user", "secret_sauce"))
                .login(user)
                .findSingleElementContainingText("Swag Labs");
        assertThat(some.getText()).isEqualTo("Swag Labs");
    }

}
