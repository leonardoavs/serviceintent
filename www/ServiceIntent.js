var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');


/**
 * This represents a thin service layer over the Android Intent implementation
 * @constructor
 */
function ServiceIntent() {
    var me = this;
}

ServiceIntent.prototype.ACTION_SEND = "android.intent.action.SEND";
ServiceIntent.prototype.ACTION_VIEW= "android.intent.action.VIEW";
ServiceIntent.prototype.ACTION_INSTALL_PACKAGE="android.intent.action.INSTALL_PACKAGE";
ServiceIntent.prototype.ACTION_UNINSTALL_PACKAGE= "android.intent.action.UNINSTALL_PACKAGE";
ServiceIntent.prototype.EXTRA_TEXT = "android.intent.extra.TEXT";
ServiceIntent.prototype.EXTRA_SUBJECT = "android.intent.extra.SUBJECT";
ServiceIntent.prototype.EXTRA_STREAM = "android.intent.extra.STREAM";
ServiceIntent.prototype.EXTRA_EMAIL = "android.intent.extra.EMAIL";
ServiceIntent.prototype.ACTION_CALL = "android.intent.action.CALL";
ServiceIntent.prototype.ACTION_SENDTO = "android.intent.action.SENDTO";
//  StartActivityForResult
ServiceIntent.prototype.ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT";
ServiceIntent.prototype.ACTION_PICK = "android.intent.action.PICK";
ServiceIntent.prototype.RESULT_CANCELED = 0; //  Activity.RESULT_CANCELED
ServiceIntent.prototype.RESULT_OK = -1; //  Activity.RESULT_OK

ServiceIntent.prototype.startService = function(params, successCallback, errorCallback) {
    argscheck.checkArgs('off', 'ServiceIntent.startService', arguments);
    exec(successCallback, errorCallback, "ServiceIntent", "startService", [params]);
};

window.serviceIntent = new ServiceIntent();
window.plugins = window.plugins || {};
window.plugins.serviceIntent = window.serviceIntent;