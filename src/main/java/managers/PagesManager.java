package managers;

import pages.HomePage;
import pages.HypothecPage;

/**
 * @author Sergey Nesterov
 */
public class PagesManager {
    private HomePage homePage;
    private HypothecPage hypothecPage;

    private static PagesManager pagesManager;

    public static PagesManager getInstance() {
        if (pagesManager == null) pagesManager = new PagesManager();
        return pagesManager;
    }

    public static void deleteInstance() {
        pagesManager = null;
    }

    private PagesManager() {
    }

    public HypothecPage getHypothecPage() {
        if (hypothecPage == null) hypothecPage = new HypothecPage();
        return hypothecPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) homePage = new HomePage();
        return homePage;
    }
}