package com.github.seanzor.prefhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

/**
 * This class helps in managing a SharedPreference with automatic use of resource id's,
 * a quick support for apply/commit, and using some encryption/decryption of strings
 */
public class SecuredSharedPrefHelper extends SharedPrefHelper {

    private final SecureStringHandler mSecureString;

    interface SecureStringHandler{
        public String encrypt(Context ctx, String stringToEncrypt);
        public String decrypt(Context ctx, String stringToDecrypt);
    }

    public SecuredSharedPrefHelper(Resources res, SharedPreferences preferences, SecureStringHandler secureString) {
        super(res, preferences);
        mSecureString = secureString;
    }

    public String getSecuredString(Context ctx, int resId, String defValue) {
        return mSecureString.decrypt(ctx, getString(resId, defValue));
    }

    public String getSecuredString(Context ctx, int resId) {
        return getSecuredString(ctx, resId, null);
    }

    public void applySecuredString(Context ctx, int resId, String value) {
        String encryptedValue = mSecureString.encrypt(ctx, value);
        mPref.edit().putString(mRes.getString(resId), encryptedValue).apply();
    }

    public void commitSecuredString(Context ctx, int resId, String value) {
        String encryptedValue = mSecureString.encrypt(ctx, value);
        mPref.edit().putString(mRes.getString(resId), encryptedValue).commit();
    }

}
