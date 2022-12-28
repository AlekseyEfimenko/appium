package pm.academy.cases;

import org.testng.annotations.Test;
import pm.academy.base.BaseTest;

public class AcademyTests extends BaseTest {

    @Test
    public void makeBetTest() {
        steps.closeLoginPageIfPresent();
        steps.openSportPage();
        steps.assertSportPageIsOpened();

        steps.makeBet();
        steps.assertLoginPageIsOpened();
    }

}
