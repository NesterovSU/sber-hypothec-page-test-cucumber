package steps;

import io.cucumber.java.ru.И;
import managers.PagesManager;
import pages.HomePage;

/**
 * @author Sergey Nesterov
 */
public class HomePageStep {

    private HomePage homePage = PagesManager.getInstance().getHomePage();

    @И("^Выбрать меню '([^\"]+)'$")
    public void clickInMainMenu(String buttonName){
        homePage.clickInMainMenu(buttonName);
    }

    @И("^Выбрать подменю '([^\"]+)'$")
    public void clickInMainSubMenu(String buttonName){
        homePage.clickInMainSubMenu(buttonName);
    }

    @И("Закрыть сообщение куки")
    public void closeCookie(){
        homePage.closeCookies();
    }
}
