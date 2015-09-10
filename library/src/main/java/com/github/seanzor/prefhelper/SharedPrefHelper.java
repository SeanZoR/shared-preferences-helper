package com.github.seanzor.prefhelper;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;

import java.util.Map;

/**
 * This class helps in managing a SharedPreference with automatic use of resource id's,
 * and a quick support for apply/commit
 */
@SuppressLint("CommitPrefEdits")
public class SharedPrefHelper {

    protected Resources mRes;
    protected SharedPreferences mPref;

    public SharedPrefHelper(Resources res, SharedPreferences preferences) {
        mRes = res;
        mPref = preferences;
    }

    public boolean isPrefContains(int resId) {
        return (mPref.contains(mRes.getString(resId)));
    }

    public void removePref(int resId) {
        mPref.edit().remove(mRes.getString(resId)).commit();
    }

    public String getString(int resId, String defValue) {
        return (mPref.getString(mRes.getString(resId), defValue));
    }

    public String getString(int resId) {
        return getString(resId, null);
    }

    public void applyString(int resId, String value) {
        mPref.edit().putString(mRes.getString(resId), value).apply();
    }

    public void commitString(int resId, String value) {
        mPref.edit().putString(mRes.getString(resId), value).commit();
    }

    public boolean getBoolean(int resId, boolean defValue) {
        return (mPref.getBoolean(mRes.getString(resId), defValue));
    }

    public boolean getBoolean(int resId) {
        return getBoolean(resId, false);
    }

    public void applyBoolean(int resId, boolean value) {
        mPref.edit().putBoolean(mRes.getString(resId), value).apply();
    }

    public void commitBoolean(int resId, boolean value) {
        mPref.edit().putBoolean(mRes.getString(resId), value).commit();
    }

    public void applyBooleanReverseValue(int resId) {
        applyBoolean(resId, !getBoolean(resId));
    }

    public void commitBooleanReverseValue(int resId) {
        applyBoolean(resId, !getBoolean(resId));
    }

    public int getInt(int resId, int defValue) {
        return (mPref.getInt(mRes.getString(resId), defValue));
    }

    public int getInt(int resId) {
        return getInt(resId, Integer.MIN_VALUE);
    }

    public void applyInt(int resId, int value) {
        mPref.edit().putInt(mRes.getString(resId), value).apply();
    }

    public void commitInt(int resId, int value) {
        mPref.edit().putInt(mRes.getString(resId), value).commit();
    }

    public long getLong(int resId, long defValue) {
        return (mPref.getLong(mRes.getString(resId), defValue));
    }

    public long getLong(int resId) {
        return getLong(resId, Long.MIN_VALUE);
    }

    public void applyLong(int resId, long value) {
        mPref.edit().putLong(mRes.getString(resId), value).apply();
    }

    public void commitLong(int resId, long value) {
        mPref.edit().putLong(mRes.getString(resId), value).commit();
    }

    public float getFloat(int resId, float defValue) {
        return (mPref.getFloat(mRes.getString(resId), defValue));
    }

    public float getFloat(int resId) {
        return getFloat(resId, Float.MIN_VALUE);
    }

    public void applyLong(int resId, float value) {
        mPref.edit().putFloat(mRes.getString(resId), value).apply();
    }

    public void commitLong(int resId, float value) {
        mPref.edit().putFloat(mRes.getString(resId), value).commit();
    }

    /***
     * Runs on a map of values (key-value) and inserts into the shared preferences
     * This can be used also on values which are maps themselves
     * Limitation:
     * <ul>
     *     <li> Can't parse a value which is a list
     *     <li> Float will be case to Double
     * </ul>
     * @param map The Map containing values to commit
     */
    public void commitMap(Map<String, Object> map) {
        SharedPreferences.Editor edit = mPref.edit();

        runOverMap(map, edit);

        edit.commit();
    }

    /***
     * Runs on a map of values (key-value) and inserts into the shared preferences
     * This can be used also on values which are maps themselves
     * Limitation:
     * <ul>
     *     <li> Can't parse a value which is a list
     *     <li> Float will be case to Double
     * </ul>
     * @param map The Map containing values to apply
     */
    public void applyMap(Map<String, Object> map) {
        SharedPreferences.Editor edit = mPref.edit();

        runOverMap(map, edit);

        edit.apply();
    }

    private void runOverMap(Map<String, Object> map, SharedPreferences.Editor edit) {
        for (String key : map.keySet()) {
            Object value = map.get(key);

            if (value instanceof String) {
                edit.putString(key, (String) value);
            } else if (value instanceof Boolean) {
                edit.putBoolean(key, (Boolean) value);
            } else if (value instanceof Integer) {
                edit.putInt(key, (Integer) value);
            } else if (value instanceof Float) {
                edit.putFloat(key, (Float) value);
            } else if (value instanceof Double) {
                Log.w("PreferenceUtil", "Warning: Converting a Double into a Float");
                edit.putFloat(key, (float) ((double) ((Double) value)));
            } else if (value instanceof Long) {
                edit.putLong(key, (Long) value);
            } else if (value instanceof Map) {
                // Recursively call next map
                runOverMap((Map) value, edit);
            } else {
                Log.e("PreferenceUtil",
                        "Trying to enter unknown type inside a shared pref map. type=" +
                        value.getClass().getSimpleName());
            }
        }
    }
}
