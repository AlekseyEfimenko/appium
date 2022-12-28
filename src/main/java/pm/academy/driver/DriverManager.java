package pm.academy.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pm.academy.EventLogListener;
import pm.academy.configuration.capabilities.CapabilitiesConfigurator;
import pm.academy.configuration.capabilities.CapabilitiesReader;
import pm.academy.configuration.capabilities.TestDataReader;
import pm.academy.configuration.server.AppiumServerConfigurator;
import pm.academy.configuration.types.ConfigurationType;
import pm.academy.configuration.types.RunType;
import pm.academy.device.DeviceType;
import pm.academy.entity.Emulator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public class DriverManager {

    private static final Logger LOG = LogManager.getRootLogger();
    private static final DeviceType DEVICE_TYPE = DeviceType.valueOfIgnoringCase(CapabilitiesReader.get().deviceType());
    private static final RunType RUN_TYPE = RunType.valueOfIgnoringCase(CapabilitiesReader.get().runType());
    private static final ConfigurationType CONFIGURATION_TYPE = ConfigurationType.getType(DEVICE_TYPE, RUN_TYPE);
    private static final String LOCAL_PATH = TestDataReader.get().localPath();

    private static final ThreadLocal<AppiumDriver<MobileElement>> threadLocalDriver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void createDriver(String udid, String wda, String deviceName, String platformVersion) throws MalformedURLException {
        AppiumDriver<MobileElement> driver = switch (CONFIGURATION_TYPE) {
            case ANDROID_LOCAL -> new AndroidDriver<>(new URL(LOCAL_PATH),
                    CapabilitiesConfigurator.getAndroidLocalCapabilities(udid, deviceName, platformVersion));
            case IOS_LOCAL -> new IOSDriver<>(AppiumServerConfigurator.getService(),
                    CapabilitiesConfigurator.getIosLocalCapabilities(deviceName, wda, udid));
            default -> throw new IllegalStateException("Unexpected value: " + CONFIGURATION_TYPE);
        };
        driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new EventLogListener());
        threadLocalDriver.set(driver);
        LOG.info("Driver is created");
        LOG.info("Device type is {}", DEVICE_TYPE);
        LOG.info("Run type is {}", RUN_TYPE);
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return threadLocalDriver.get();
    }

    public static void terminateDriver() {
        Optional.ofNullable(getDriver()).ifPresent((AppiumDriver<MobileElement> driver) -> {
            driver.quit();
            threadLocalDriver.remove();
            LOG.info("Driver is closed");
        });
    }

    public static void terminateAppium() {
        AppiumServerConfigurator.stopServer();
    }

    public static void terminateEmulator(String udid) {
        new Emulator(udid).close();
    }
}
