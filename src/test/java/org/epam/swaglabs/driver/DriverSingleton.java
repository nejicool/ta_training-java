package org.epam.swaglabs.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton{
    private static WebDriver driver;
    private DriverSingleton(){}

    public static WebDriver getDriver(){
        if(driver == null){
            //For custom properties creation
            switch (System.getProperty("browser")){
//                switch ("edge"){
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                }
                case "edge": {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                }
                    default: {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
            }
        }
        return driver;
    }
    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
