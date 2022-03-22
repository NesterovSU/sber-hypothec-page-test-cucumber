package steps;

import io.cucumber.java.ru.И;
import managers.PagesManager;
import pages.HypothecPage;

/**
 * @author Sergey Nesterov
 */

public class HypothecPageStep {

    private HypothecPage hypothecPage = PagesManager.getInstance().getHypothecPage();

    @И("^Ввести '([^\"]+)' (\\d+) .+$")
    public void typeInForm(String name, int number) {
        hypothecPage.typeInForm(name, number);
    }

    @И("^Выключить '([^\"]+)'$")
    public void unCheckSelector(String name) {
        hypothecPage.unCheckSelector(name);
    }

    @И("^проверить что '([^\"]+)' (\\d+) .+$")
    public void verify(String name, int number) {
        hypothecPage.verifyValue(name, number);
    }
}
