package org.epam.swaglabs.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.epam.swaglabs.User;
import org.epam.swaglabs.Util;
import org.epam.swaglabs.driver.DriverSingleton;
import org.epam.swaglabs.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.testng.annotations.Listeners;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AppTest 
    extends TestCase
{

    public AppTest( String testName )
    {
        super( testName );
    }


    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    @org.testng.annotations.Test
    public void emptyName() {
                WebElement some = new LoginPage(DriverSingleton.getDriver())
                .openPage()
                .enterUsername(Util.generateRandomStringWithSize(7))
                .enterPassword(Util.generateRandomStringWithSize(10))
                .clearUsername()
                .clearPassword()
                .login()
                .findSingleElementContainingText("Username is required");
        assertThat(some.getText().contains("Username is required"));
        DriverSingleton.closeDriver();
    }

    @org.testng.annotations.Test
    public void emptyPassword() {
                WebElement some = new LoginPage(DriverSingleton.getDriver())
                .openPage()
                .enterUsername(Util.generateRandomStringWithSize(7))
                .enterPassword("")
                .clearPassword()
                .login()
                .findSingleElementContainingText("Password is required");
        assertThat(some.getText().contains("Password is required"));
        DriverSingleton.closeDriver();
    }

    @org.testng.annotations.Test
    public void loginAndCheckTitle() {
        //For custom properties creation
//        User user = User.createUserFromProperties();
                WebElement
                some = new LoginPage(DriverSingleton.getDriver())
                .openPage()
                .login(new User("standard_user", "secret_sauce"))
//                .login(user)
                .findSingleElementContainingText("Swag Labs");

        assertThat(some.getText()).isEqualTo("Swag Labs");
        DriverSingleton.closeDriver();
    }
}
