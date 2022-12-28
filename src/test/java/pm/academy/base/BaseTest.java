package pm.academy.base;

import static pm.academy.driver.DriverManager.getDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pm.academy.driver.DriverManager;
import pm.academy.steps.TestSteps;

import java.net.MalformedURLException;

public class BaseTest {
    protected TestSteps steps = new TestSteps();

    @BeforeTest(alwaysRun = true)
    @Parameters({"UDID", "WDA", "DeviceName", "PlatformVersion"})
    public void setupSession(
            @Optional String udid,
            @Optional String wda,
            @Optional String deviceName,
            @Optional String platformVersion) {
        try {
            DriverManager.createDriver(udid, wda, deviceName, platformVersion);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @AfterSuite(alwaysRun = true)
    @Parameters("UDID")
    public void closeSession(@Optional String udid) {
        DriverManager.terminateDriver();
//        DriverManager.terminateAppium();
//        DriverManager.terminateEmulator(udid);
    }

    @AfterMethod(alwaysRun = true)
    public void resetApp() {
        getDriver().resetApp();
    }
}
