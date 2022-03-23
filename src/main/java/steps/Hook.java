package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import managers.DriverManager;
import managers.PagesManager;
import managers.PropertiesManager;
import utils.MyProp;


/**
 * @author Sergey Nesterov
 */
public class Hook {

    @Before(value = "@myTag")
    public void openHomePage() {
        DriverManager.getInstance().get(
                PropertiesManager.getInstance().get(MyProp.HOME_URL));
    }

    @After(value = "@myTag")
    public void closeBrowserWindow() {
        DriverManager.quit();
        PagesManager.deleteInstance();
    }
}
