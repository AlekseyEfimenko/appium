package pm.academy.configuration.capabilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:data/capabilities.properties")
public interface Capabilities extends Config {

    @DefaultValue("android")
    String deviceType();

    @DefaultValue("local")
    String runType();

    @DefaultValue("qa")
    String buildType();

    @Key("apps.path")
    String appsPath();

    // Desired capabilities
    @Key("${deviceType}.${runType}.${buildType}.app")
    String app();

    @Key("${deviceType}.${runType}.${buildType}.app.zip")
    String archivedApp();

    @Key("${deviceType}.${runType}.platformName")
    String platformName();

    @Key("${deviceType}.${runType}.platformVersion")
    String platformVersion();

    @Key("${deviceType}.${runType}.automationName")
    String automationName();

    @Key("${deviceType}.${runType}.deviceName")
    String deviceName();

    @Key("${deviceType}.${runType}.${buildType}.appPackage")
    String appPackage();

    @Key("${deviceType}.${runType}.appActivity")
    String appActivity();

    @Key("${deviceType}.${runType}.udid")
    String udid();

    @Key("${deviceType}.${runType}.udids")
    String[] udids();

    @Key("${deviceType}.${runType}.wda")
    String wda();

    @Key("${deviceType}.${runType}.autoGrantPermissions")
    String autoGrantPermissions();
}
