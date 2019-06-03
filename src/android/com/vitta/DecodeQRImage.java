package com.vitta;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;


public class DecodeQRImage extends CordovaPlugin {

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext)
            throws JSONException {
        String imageUri = args.optString(0);
        if (action.equals("decode")) {
            parsePhoto(imageUri, callbackContext);
        }
        return true;
    }

    public void parsePhoto(final String path, final CallbackContext callbackContext) {
        @SuppressLint("StaticFieldLeak") AsyncTask myTask = new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                // 解析二维码/条码
                return QRCodeDecoder.syncDecodeQRCode(path);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                PluginResult pluginResult = null;
                if (null == s) {
                    pluginResult = new PluginResult(PluginResult.Status.OK, "No result");
                } else {
                    // 识别出图片二维码/条码，内容为s
                    pluginResult = new PluginResult(PluginResult.Status.OK, s);
                }
                callbackContext.sendPluginResult(pluginResult);
            }
        }.execute(path);
    }
}