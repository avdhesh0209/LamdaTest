package com.lmbdatest.test;

import com.lambdatest.manager.FlipkartNavigate;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlipkartNavigateTest {

    FlipkartNavigate flipkartNavigate = new FlipkartNavigate();
    @BeforeClass
    public void beforeClass(){
        WebDriverManager.chromedriver().setup();
        flipkartNavigate.driver=new ChromeDriver();
    }


    @Test
    public void printFlipkartProductDetails() throws InterruptedException {
        flipkartNavigate.flipkartProductJourney();
    }

    @AfterClass
    public void afterClass(){
        flipkartNavigate.driver.quit();
    }
}
