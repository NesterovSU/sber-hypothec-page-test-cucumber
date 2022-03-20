package pages;

import managers.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author Sergey Nesterov
 */
public class BasePage {
    @FindBy(xpath = "//nav[@aria-label='Основное меню']//a[@role='button']")
    private List<WebElement> mainMenu;

    @FindBy(xpath = "//a[contains(@class,'menu__link_second')]")
    private List<WebElement> mainSubMenu;

    protected WebDriverWait wait;
    protected WebDriver driver;

    public BasePage() {
        driver = DriverManager.getInstance();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public BasePage clickInMainMenu(String name) {
        wait.until(ExpectedConditions.visibilityOfAllElements(mainMenu));
        for (WebElement button : mainMenu) {
            if (button.getText().contains(name)) {
                wait.until(ExpectedConditions.visibilityOf(button)).click();
                return this;
            }
        }
        Assertions.fail("Не найдена кнопка " + name + " в основном меню");
        return this;
    }

    public BasePage clickInMainSubMenu(String name) {
        for (WebElement button : mainSubMenu) {
            if (button.getText().contains(name)) {
                button.click();
                return this;
            }
        }
        Assertions.fail("Не найдена кнопка " + name + " в подменю основного меню");
        return this;
    }
}
