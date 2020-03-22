package cordova-plugin-serviceintent;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

/**
 * This class echoes a string called from JavaScript.
 */
public class ServiceIntent extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("startService"))
        {
            if (args.length() != 1) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
                return false;
            }
            JSONObject obj = args.getJSONObject(0);
            Intent intent = null; //populateIntent(obj, callbackContext);
            startService(intent);
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            return true;
        }
        return false;
    }

    private void startService(Intent intent)
    {
        this.cordova.getActivity().startService(intent);
    }

}
