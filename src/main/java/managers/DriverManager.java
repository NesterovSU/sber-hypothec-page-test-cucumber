package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.MyProp;

import java.util.concurrent.TimeUnit;

/**
 * @author Sergey Nesterov
 */
public class DriverManager {
    private static WebDriver webDriver;
    private static PropertiesManager properties = PropertiesManager.getInstance();

    public static WebDriver getInstance() {
        return webDriver != null ? webDriver : create();
    }

    private static WebDriver create() {

        String browser = properties.get(MyProp.BROWSER);
        switch (browser == null ? "chrome" : browser) {
            case "chrome":
                webDriver = new ChromeDriver();
                break;
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            case "edge":
                webDriver = new EdgeDriver();
                break;
            default:
                webDriver = new ChromeDriver();
        }

        long implWait = Long.parseLong(properties.get(MyProp.IMPLWAIT));
        long pgLdTt = Long.parseLong(properties.get(MyProp.PGLDWAIT));
        webDriver.manage().timeouts().implicitlyWait(implWait, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(pgLdTt, TimeUnit.SECONDS);
//        webDriver.manage().window().setSize(new Dimension(1920, 1080));
        webDriver.manage().window().maximize();
        return webDriver;
    }

    public static void quit() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
