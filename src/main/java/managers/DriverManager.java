package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.MyProp;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * @author Sergey Nesterov
 */
public class DriverManager {
    private static WebDriver webDriver;
    private static DesiredCapabilities capabilities;
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
            case "remote.chrome":
                capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("84.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                try {
                    webDriver = new RemoteWebDriver(
                            URI.create("http://51.250.100.60:4444/wd/hub").toURL(),
                            capabilities
                    );
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case "remote.firefox":
                capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("firefox");
                capabilities.setVersion("78.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);

                try {
                    RemoteWebDriver driver = new RemoteWebDriver(
                            URI.create("http://51.250.100.60:4444/wd/hub").toURL(),
                            capabilities
                    );
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
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
