package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import managers.DriverManager;
import managers.PagesManager;
import managers.PropertiesManager;

/**
 * @author Sergey Nesterov
 */
public class Hook {
    @Before(value = "@myTag")
    public void openHomePage(){
        DriverManager.getInstance().get(PropertiesManager.getInstance().get("home.url"));
    }

    @After(value = "@myTag")
    public void closeBrowserWindow(){
        DriverManager.quit();
        PagesManager.deleteInstance();
    }
}
