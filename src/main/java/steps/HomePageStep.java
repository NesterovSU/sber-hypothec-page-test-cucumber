package steps;

import io.cucumber.java.ru.И;
import managers.PagesManager;

/**
 * @author Sergey Nesterov
 */
public class HomePageStep {

    private PagesManager pagesManager = PagesManager.getInstance();

    @И("^Выбрать меню '([^\"]*)'$")
    public void clickInMainMenu(String buttonName){
        pagesManager.getHomePage().clickInMainMenu(buttonName);
    }

    @И("^Выбрать подменю '([^\"]*)'$")
    public void clickInMainSubMenu(String buttonName){
        pagesManager.getHomePage().clickInMainSubMenu(buttonName);
    }
}
