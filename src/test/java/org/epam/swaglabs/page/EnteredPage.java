package org.epam.swaglabs.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnteredPage extends AbstractPage{

    public EnteredPage(WebDriver driver){
        super(driver);
    }

    @Override
    public EnteredPage openPage() {
        throw new RuntimeException("Cannot open page without authentication first");
    }

    @Override
    public WebElement findSingleElementContainingText(String text){
        WebElement webElement = findElementsContainingText(text)
                .stream().filter(element -> element.isDisplayed())
                .findFirst().orElseThrow(()->new RuntimeException("No element"));
        return webElement;
    }
}
