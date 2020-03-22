package android.serviceintent;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

import java.util.Map;
import java.util.HashMap;

import android.content.ComponentName;
import android.util.Log;

/**
 * This class echoes a string called from JavaScript.
 */
public class ServiceIntent extends CordovaPlugin {
    private static final String LOG_TAG = "Cordova Intents ServiceIntent";
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("startService"))
        {
            if (args.length() != 1) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
                return false;
            }
            JSONObject obj = args.getJSONObject(0);
            String action2 = obj.has("action") ? obj.getString("action") : null;
            Intent intent = new Intent();
            if (action2 != null)
                intent.setAction(action2);

            JSONObject component = obj.has("component") ? obj.getJSONObject("component") : null;
            if (component != null)
            {
                //  User has specified an explicit intent
                String componentPackage = component.has("package") ? component.getString("package") : null;
                String componentClass = component.has("class") ? component.getString("class") : null;
                if (componentPackage == null || componentClass == null)
                {
                    Log.w(LOG_TAG, "Component specified but missing corresponding package or class");
                    throw new JSONException("Component specified but missing corresponding package or class");
                }

                else
                {
                    ComponentName componentName = new ComponentName(componentPackage, componentClass);
                    intent.setComponent(componentName);
                }
            }            

            JSONObject extras = obj.has("extras") ? obj.getJSONObject("extras") : null;
            Map<String, Object> extrasMap = new HashMap<String, Object>();
            if (extras != null) {
                JSONArray extraNames = extras.names();
                for (int i = 0; i < extraNames.length(); i++) {
                    String key = extraNames.getString(i);
                    Object extrasObj = extras.get(key);
                    extrasMap.put(key, extras.get(key));
                }
            }
            
            for (String key : extrasMap.keySet()) {
                Object value = extrasMap.get(key);
                String valueStr = String.valueOf(value);
                intent.putExtra(key, valueStr);
            }            
            
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
