package com.lambdatest.manager;

import com.lambdatest.constants.SeleniumConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class FlipkartNavigate implements SeleniumConstants {
    public WebDriver driver;



    public  void printProductDetails(List<WebElement> searchelementList) {
        String value = null;
        for (WebElement element : searchelementList) {
            String key = element.findElement(By.className("s1Q9rs")).getAttribute("title");
            value = element.findElement(By.className("_30jeq3")).getText();
            System.out.println(key + " : " + value);
        }
    }

    public  void clickNextButton(WebElement next) {

        try {
            next=driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[12]/div/div/nav"));
            next=next.findElement(By.xpath("//*[@class='_1LKTO3']//*[text()='Next']"));
//            next = next.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[12]/div/div/nav/a[4]"));
            next.click();
        } catch (Exception e) {
            System.err.println("Cannot click, next not found " + e.getMessage());
        }

    }

    public  void flipkartProductJourney() throws InterruptedException {
        driver.get(flipkartUrl);
        WebElement webElement = driver.findElement(By.xpath(flipkartSearchBoxXpath));
        webElement.sendKeys(searchString);
        webElement.sendKeys(Keys.ENTER);

        Thread.sleep(5000); // sleeping to let the page load
        List<WebElement> next = driver.findElements(By.className(nextWindowXpath));
        next= next.stream().findFirst().map(elem-> elem.findElements(By.className(nextButtonXpath))).orElse(new ArrayList<>());
        int nextSize= next.size()-1;

        for (int i = 0; i <= next.size() ; i++) {
            List<WebElement> searchElementlist = driver.findElements(By.className("_4ddWXP"));
            printProductDetails(searchElementlist);
            if (nextSize-->0){
                System.out.println();
                clickNextButton(next.get(i));
                Thread.sleep(5000);
            }
        }
//        driver.quit();
    }
}
