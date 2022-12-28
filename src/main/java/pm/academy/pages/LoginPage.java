package pm.academy.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "close_button")
    private MobileElement closeButton;

    @AndroidFindBy(id = "com.parimatch:id/tvButtonTitle")
    private MobileElement registrationButton;

    public void closeRegistrationPage() {
        waitForExpectedElement(closeButton).click();
    }

    public boolean isRegistrationButtonDisplayed() {
        return isElementDisplayed(registrationButton);
    }
}
