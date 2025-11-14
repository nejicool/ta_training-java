package org.epam.swaglabs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected static final Duration TIMEOUT_SECONDS = Duration.ofSeconds(5);
    protected AbstractPage(WebDriver driver){
        this.driver = driver;
    }
    protected abstract AbstractPage openPage();

    public WebElement findSingleElementContainingText(String text){
       WebElement webElement = findElementsContainingText(text)
               .stream()
               .findFirst().orElseThrow(()->new RuntimeException("No element"));
        return webElement;
    }
    public List<WebElement> findElementsContainingText(String text){
        StringBuilder builder = new StringBuilder("//*[contains(text(), ");
        builder.append("\"").append(text).append("\")]");
        return driver.findElements(By.xpath(builder.toString()));
    }
}
