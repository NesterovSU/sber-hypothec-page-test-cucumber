package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * @author Sergey Nesterov
 */
public class HypothecPage extends BasePage {

    private final String CALC_RES_BLOCK = "//*[contains(@data-e2e-id,'calculator/main')]";

    @FindAll({
            @FindBy(xpath = CALC_RES_BLOCK + "//*[text()='Сумма кредита']"),
            @FindBy(xpath = CALC_RES_BLOCK + "//*[text()='Ежемесячный платеж']"),
            @FindBy(xpath = CALC_RES_BLOCK + "//*[text()='Процентная ставка']"),
            @FindBy(xpath = CALC_RES_BLOCK + "//*[text()='Необходимый доход']"),
    })
    private List<WebElement> resultList;

    @FindBy(xpath = "//input[@class='switch-input-3-2-2']/ancestor::div[3]")
    private List<WebElement> checkBoxList;

    @FindBy(xpath = "//*[@class='dc-input__input-container-6-1-0']")
    private List<WebElement> formList;

    @FindBy(xpath = "//iframe")
    private WebElement iframe;

    @FindBy(xpath = "//h2[contains(text(),'Рассчитайте ипотеку')]")
    private WebElement title;

    public HypothecPage() {
        wait.until(ExpectedConditions.visibilityOf(title));
    }

    public void typeInForm(String name, Integer number) {
        driver.switchTo().frame(iframe);
        for (WebElement we : formList) {
            if (!we.getText().contains(name)) continue;
            WebElement in = we.findElement(By.xpath("./input"));
            in.click();
            in.click();
            in.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            in.sendKeys(number.toString());
            waitStopChanging(in);
            Assertions.assertEquals(
                    number.toString(),
                    in.getAttribute("value").replaceAll("\\D", ""),
                    "Значение '" + name + "' не ввелось в форму");
            driver.switchTo().defaultContent();
            return;
        }
        Assertions.fail("Не найдена форма " + name);
    }

    public void unCheckSelector(String name) {
        driver.switchTo().frame(iframe);
        for (WebElement we : checkBoxList) {
            if (!we.getText().contains(name)) continue;
            WebElement in = we.findElement(By.xpath(".//input"));
            if (in.isSelected()) in.click();
            Assertions.assertFalse(in.isSelected(), "Не выключился чекбокс" + name);
            driver.switchTo().defaultContent();
            return;
        }
        Assertions.fail("Не найден чекбокс " + name);
    }

    public void verifyValue(String name, Integer value) {
        driver.switchTo().frame(iframe);
        for (WebElement we : resultList) {
            if (!we.getText().contains(name)) continue;
            scrollTo(we);
            WebElement in = we.findElement(By.xpath("./following::span[1]"));
            waitStopChanging(in);
            System.out.println(value + " " + in.getText());
            Assertions.assertEquals(
                    value.toString(),
                    in.getText()
                            .split("%")[0]
                            .replaceAll("\\D", ""),
                    "Значение '" + name + "' не соответствует проверочному");
            driver.switchTo().defaultContent();
            return;
        }
        Assertions.fail("Не найден показатель " + name);
    }
}
