package pm.academy.entity;

import static java.lang.String.format;
import static pm.academy.configuration.types.RunType.LOCAL;
import static pm.academy.device.DeviceType.ANDROID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pm.academy.configuration.capabilities.CapabilitiesReader;

import java.io.IOException;

public class Emulator {

    private static final Logger LOG = LogManager.getRootLogger();
    private static final String KILL_AVD = "adb -s %s emu kill";

    private final String udid;

    public Emulator(String udid) {
        this.udid = udid == null ? CapabilitiesReader.get(ANDROID, LOCAL).udid() : udid;
    }

    public void close() {
        try {
            Runtime.getRuntime().exec(format(KILL_AVD, udid));
            LOG.info("AVD '{}' is closed", udid);
        } catch (IOException e) {
            LOG.info("AVD '{}' was not closed", udid, e);
        }
    }
}