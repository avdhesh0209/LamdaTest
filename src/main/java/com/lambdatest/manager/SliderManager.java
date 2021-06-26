package com.lambdatest.manager;

import com.lambdatest.constants.SeleniumConstants;
import com.lambdatest.exceptions.InvalidStepException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class SliderManager implements SeleniumConstants {
    private final String url="http://the-internet.herokuapp.com/horizontal_slider";
    private final String sliderXpath = "//*[@id=\"content\"]/div/div/input";
    public WebDriver driver;

    public static int getPixelsToMove(double value)
    {
        return (int) (-5 + value*2);
    }

    public boolean validateSlider(double num) throws InvalidStepException {
        if(num%0.5 != 0 && num >5)
            throw new InvalidStepException("Invalid step specified");
        driver.get(url);
        WebElement slider =driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/input"));

        Assert.assertTrue(slider.isDisplayed(),"slider displayed");
        Assert.assertTrue(slider.isEnabled(),"Slider enabled");

        Dimension sliderSize = slider.getSize();
        int sliderWidth = sliderSize.width;

        Actions builder = new Actions(driver);
        builder.dragAndDropBy
                        (slider,(((sliderWidth)/10)*getPixelsToMove(num)) , 0)
                .build()
                .perform();

        return Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"range\"]")).getText())==num;

    }
}
