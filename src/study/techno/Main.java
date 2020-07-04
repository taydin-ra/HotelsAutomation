package study.techno;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/yavuzaydin/Desktop/selenium/chromedriver 2");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.hotels.com/");

        WebElement destination = driver.findElement(By.xpath("//input[@name='q-destination']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(destination).click()
                .sendKeys("New")
                .perform();
        Thread.sleep(2000);
        actions.sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();

        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@aria-labelledby='f-price-min-label']")));
        WebElement slider = driver.findElement(By.xpath("//div[@aria-labelledby='f-price-min-label']"));
        actions.dragAndDropBy(slider, 77, 0).click();

        actions.build().perform();

        WebElement sliderRight = driver.findElement(By.xpath("//div[@aria-controls='f-price-max']"));
        actions.dragAndDropBy(sliderRight, -46, 0).click();
        actions.build().perform();

        List<WebElement> prices = driver.findElements(By.xpath("//div//ins"));
        for (int i = 0; i < prices.size(); i++) {
            String s1 = prices.get(i).getText();
            s1 = s1.replace("$", "");
            //  System.out.println(prices.get(i).getText());
            int price = Integer.parseInt(s1);
            if (price < 100) {
                System.out.println(price);
            }
        }
    }
}

