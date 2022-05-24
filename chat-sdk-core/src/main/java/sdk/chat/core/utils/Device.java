package sdk.chat.core.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.provider.Settings;

import sdk.chat.core.session.ChatSDK;

public class Device {

    public static boolean honor() {
        return honor(ChatSDK.ctx());
    }

    public static boolean honor(Context context) {
        return named(context, "Chairman Mao");
    }

    public static boolean nexus() {
        return nexus(ChatSDK.ctx());
    }

    public static boolean nexus(Context context) {
        return named(context, "Nexus 5");
    }

    public static boolean named(String name) {
        return named(ChatSDK.ctx(), name);
    }

    public static boolean galaxy() {
        return galaxy(ChatSDK.ctx());
    }

    public static boolean galaxy(Context context) {
        return named(context, "Ben's Galaxy A21s");
    }

    public static boolean pixel() {
        return pixel(ChatSDK.ctx());
    }

    public static boolean pixel(Context context) {
        return named(context, "Pixel 6");
    }

    public static boolean named(Context context, String name) {
        String deviceName = name(context);
        return deviceName != null && deviceName.equals(name);
    }

    public static String name(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "bluetooth_name");
    }

    public static String name() {
        return Settings.Secure.getString(ChatSDK.ctx().getContentResolver(), "bluetooth_name");
    }

    public static boolean isPortrait(Context context) {
        if (context == null) {
            context = ChatSDK.ctx();
        }
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static boolean isPortrait() {
        return isPortrait(null);
    }

}
