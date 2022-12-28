package pm.academy.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FooterForm extends BasePage {

    @AndroidFindBy(accessibility = "sport-tab")
    private MobileElement sportButton;

    public void selectSportPage() {
        waitForExpectedElement(sportButton).click();
    }
}
