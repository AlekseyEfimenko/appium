package pm.academy.steps;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pm.academy.pages.FooterForm;
import pm.academy.pages.LoginPage;
import pm.academy.pages.SportPage;

public class TestSteps {
    private static final Logger LOG = LogManager.getRootLogger();
    private static final String SUCCESS_MESSAGE = String.format("%1$s  SUCCESS  %1$s%n", "=".repeat(50));

    public void closeLoginPageIfPresent() {
        LOG.info("Closing \"Login page\" if it is displayed");
        if (new LoginPage().isRegistrationButtonDisplayed()) {
            new LoginPage().closeRegistrationPage();
        }
    }

    public void openSportPage() {
        LOG.info("Opening \"Sport page\"");
        new FooterForm().selectSportPage();
    }

    public void assertSportPageIsOpened() {
        LOG.info("Checking if \"Sport page\" is displayed");
        assertThat(new SportPage().isEventsDisplayed())
                .as("The Sport page is not displayed")
                .isTrue();
        LOG.info(SUCCESS_MESSAGE);
    }

    public void makeBet() {
        LOG.info("Adding bet to the bet slip");
        new SportPage().addBet();

        LOG.info("Confirming the bet in the bet slip");
        new SportPage().confirmBet();
    }

    public void assertLoginPageIsOpened() {
        LOG.info("Checking if the \"Login page\" is displayed");
        assertThat(new LoginPage().isRegistrationButtonDisplayed())
                .as("The Login page is not displayed")
                .isTrue();
        LOG.info(SUCCESS_MESSAGE);
    }
}
