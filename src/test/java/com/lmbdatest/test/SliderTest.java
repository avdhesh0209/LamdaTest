package com.lmbdatest.test;

import com.lambdatest.exceptions.InvalidStepException;
import com.lambdatest.manager.SliderManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SliderTest {
    SliderManager sliderManager = new SliderManager();

// Xpath=//*[@class='_1LKTO3']//*[text()='Testing']
    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        sliderManager.driver = new ChromeDriver();
    }

    @DataProvider
    public Object[][] data() {
        return new Object[][]{{5}, {1}, {0.5}, {3.5}};
    }

    @Test(dataProvider = "data")
    public void validateSlider(double num) throws InvalidStepException {
            Assert.assertTrue(sliderManager.validateSlider(num));
    }


    @AfterSuite
    public void afterSuite(){
        sliderManager.driver.quit();
    }
}
