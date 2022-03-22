package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import managers.DriverManager;
import managers.PagesManager;
import managers.PropertiesManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * @author Sergey Nesterov
 */
public class Hook {

    @Before(value = "@myTag")
    public void openHomePage() {
        DriverManager.getInstance().get(
                PropertiesManager.getInstance().get("home.url"));
    }

    @After(value = "@myTag")
    public void closeBrowserWindow(Scenario sc) {
        if (sc.isFailed()) getScreenshot(sc);
        DriverManager.quit();
        PagesManager.deleteInstance();
    }

    public void getScreenshot(Scenario sc) {
        sc.attach(
                ((TakesScreenshot) DriverManager.getInstance()).getScreenshotAs(OutputType.BYTES),
                "image/png",
                "screenshot");
    }
}
