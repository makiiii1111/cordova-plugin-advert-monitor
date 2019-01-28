package busll;

import android.view.View;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import cn.com.mma.mobile.tracking.api.Countly;

public class adMonitor extends CordovaPlugin {

  public static final String CONFIG_URL = "http://www.busll.cn:9900/public/advert/sdkconfig.xml";

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
    Countly.sharedInstance().init(cordova.getActivity().getApplicationContext(), CONFIG_URL);
  }

  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    final String url = args.getString(0);
    if (action.equals("click")) {
      cordova.getThreadPool().execute(new Runnable() {
        @Override
        public void run() {
          sendClickMsg(cordova.getActivity().getCurrentFocus(),url);
          callbackContext.success();
        }
      });
      return true;
    }
    if (action.equals("expose")) {
      cordova.getThreadPool().execute(new Runnable() {
        @Override
        public void run() {
          sendExposeMsg(cordova.getActivity().getCurrentFocus(),url);
          callbackContext.success();
        }
      });
      return true;
    }
    return false;
  }

  /**
   * 点击广告，发送消息
   */
  public void sendClickMsg(View view,String url) {
    Countly.sharedInstance().onClick(url);
  }
  /**
   *曝光广告，发送消息
   */
  public void sendExposeMsg(View view,String url) {
    Countly.sharedInstance().onExpose(url);
  }
}
