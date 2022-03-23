package pages;

import managers.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
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

    @FindBy(xpath = "//button[contains(@class,'cookie')]")
    private WebElement cookie;

    protected WebDriverWait wait;
    protected WebDriver driver;

    public BasePage() {
        driver = DriverManager.getInstance();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
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

    public void closeCookies(){
        if (cookie.isDisplayed()) cookie.click();
    }

    public void scrollTo(WebElement we) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", we);
    }
    

    /**
     * Ожидаем пока текст в веб-элементе не перестанет изменяться
     * @param we веб-элемент
     */
    public void waitStopChanging(WebElement we){
        int timeout = 50; //*0.1sec
        waitStopChanging(we, timeout);
    }

    public void waitStopChanging(WebElement we, int timeout){
        String before;
        do {
            before = we.getText();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!we.getText().equals(before) && --timeout>0);
    }
}
