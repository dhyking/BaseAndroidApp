package com.dhy.library.utils;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by Administrator on 2016/5/3.
 */
public class UuidUtils {
    public static UUID uuid;
    protected static final String PREFS_FILE = "device_id.xml";
    protected static final String PREFS_DEVICE_ID = "device_id";

    public static String getMyUUID(Context mContext) {
        if (uuid == null) {
            synchronized (UuidUtils.class) {
                if (uuid == null) {
                    String id = (String) SpUtil.getInstance().get(PREFS_DEVICE_ID,null);
//                    final SharedPreferences prefs = mContext.getSharedPreferences(PREFS_FILE, 0);
//                    final String id = prefs.getString(PREFS_DEVICE_ID, null);
                    if (id != null) {
                        // Use the ids previously computed and stored in the prefs file
                        uuid = UUID.fromString(id);
                    } else {
                        final String androidId = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
                        // Use the Android ID unless it's broken, in which case fallback on deviceId,
                        // unless it's not available, then fallback on a random number which we store
                        // to a prefs file
                        try {
                            if (!"9774d56d682e549c".equals(androidId)) {
                                uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                            } else {
                                final String deviceId = ((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                                uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")) : UUID.randomUUID();
                            }
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                        // Write the value out to the prefs file
                        SpUtil.getInstance()
                                .edit()
                                .put(PREFS_DEVICE_ID,uuid.toString().trim()).commit();
                    }
                }
            }
        }
        return uuid.toString();
    }
}
