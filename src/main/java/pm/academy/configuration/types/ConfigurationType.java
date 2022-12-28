package pm.academy.configuration.types;

import pm.academy.device.DeviceType;

import static pm.academy.configuration.types.RunType.CLOUD;
import static pm.academy.configuration.types.RunType.LOCAL;
import static pm.academy.device.DeviceType.ANDROID;
import static pm.academy.device.DeviceType.IOS;

public enum ConfigurationType {
    ANDROID_CLOUD,
    IOS_CLOUD,
    ANDROID_LOCAL,
    IOS_LOCAL;

    public static ConfigurationType getType(DeviceType deviceType, RunType runType) {
        if (deviceType == ANDROID && runType == CLOUD) return ANDROID_CLOUD;
        else if (deviceType == IOS && runType == CLOUD) return IOS_CLOUD;
        else if (deviceType == ANDROID && runType == LOCAL) return ANDROID_LOCAL;
        else if (deviceType == IOS && runType == LOCAL) return IOS_LOCAL;
        else throw new IllegalArgumentException("Incorrect device type or run type variable specified");
    }
}