package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Sergey Nesterov
 */
public class HypothecPage extends BasePage {

    @FindBy(xpath = "//*[text()='Стоимость недвижимости']/../input")
    private WebElement houseCost;

    @FindBy(xpath = "//*[text()='Первоначальный взнос']/../input")
    private WebElement initialFee;

    @FindBy(xpath = "//*[text()='Срок кредита']/../input")
    private WebElement creditTerm;

    @FindBy(xpath = "//*[text()='Страхование жизни и здоровья']/following::span[1]//input")
    private WebElement lifeInsurance;

    @FindBy(xpath = "//*[text()='Сумма кредита']/following::span[1]")
    private WebElement creditSum;

    @FindBy(xpath = "//*[text()='Ежемесячный платеж']/following::span[1]")
    private WebElement monthlyPayment;

    @FindBy(xpath = "//*[text()='Необходимый доход']/following::span[1]")
    private WebElement requiredIncome;

    @FindBy(xpath = "//*[text()='Процентная ставка']/following::span[1]")
    private WebElement interestRate;

    @FindBy(xpath = "//h2[contains(text(),'Рассчитайте ипотеку')]")
    private WebElement title;

    public HypothecPage() {
        wait.until(ExpectedConditions.visibilityOf(title));
    }

    public HypothecPage typeHouseCost(String text) {
        wait.until(ExpectedConditions.visibilityOf(houseCost)).click();
        return this;
    }

    public HypothecPage typeInitialFee(String text) {
        initialFee.sendKeys(text);
        return this;
    }

    public HypothecPage typeCreditTerm(String text) {
        initialFee.sendKeys(text);
        return this;
    }

    public HypothecPage unCheckLifeInsurance() {
        if (lifeInsurance.isSelected()) lifeInsurance.click();
        return this;
    }

    public int getCreditSum() {
        return Integer.parseInt(creditSum.getText().replaceAll("\\D", ""));
    }

    public int getMonthlyPayment() {
        return Integer.parseInt(monthlyPayment.getText().replaceAll("\\D", ""));
    }

    public int getRequiredIncome() {
        return Integer.parseInt(requiredIncome.getText().replaceAll("\\D", ""));
    }

    public String getInterestRate() {
        return interestRate.getText();
    }
}
