package pm.academy.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SportPage extends BasePage {

    @AndroidFindBy(accessibility = "rvSportEvents")
    private MobileElement events;

    @AndroidFindBy(xpath = "(//*[contains(@resource-id, 'id/defaultEvent')])[1]//*[@content-desc = 'outcome_1']")
    private MobileElement betLink;

    @AndroidFindBy(xpath = "//*[@text = 'ЗРОБИТИ СТАВКУ']")
    private MobileElement makeBetButton;

    public boolean isEventsDisplayed() {
        return isElementDisplayed(events);
    }

    public void addBet() {
        waitForExpectedElement(betLink).click();
    }

    public void confirmBet() {
        waitForExpectedElement(makeBetButton).click();
    }
}
