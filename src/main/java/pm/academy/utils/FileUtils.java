package pm.academy.utils;

import pm.academy.configuration.capabilities.CapabilitiesReader;

import java.io.File;

import static org.apache.commons.lang3.SystemUtils.USER_DIR;

public final class FileUtils {

    private static final String USER_DIR_PROPERTY = USER_DIR;

    private FileUtils() {
    }

    public static String getAppLocation() {
        return getPath(CapabilitiesReader.get().app()).replace("/", File.separator);
    }

    private static String getPath(String path) {
        return USER_DIR_PROPERTY + path;
    }
}
