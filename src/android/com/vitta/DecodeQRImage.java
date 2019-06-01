package com.vitta;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DecodeQRImage extends CordovaPlugin {
  public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
    this.callbackContext = callbackContext;
    this.params = args.getJSONObject(0);
    if (action.equals("decode")) {
    }
    return true;
  }
}