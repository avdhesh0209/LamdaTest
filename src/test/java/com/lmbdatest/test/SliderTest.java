package com.lmbdatest.test;

import com.lambdatest.exceptions.InvalidStepException;
import com.lambdatest.manager.SliderManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SliderTest {
    SliderManager sliderManager = new SliderManager();


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

    @AfterClass
    public void afterClass() {
        sliderManager.driver.quit();
    }
}
