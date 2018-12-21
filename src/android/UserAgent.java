package im.ltdev.cordova;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class UserAgent extends CordovaPlugin {

	private WebSettings settings = null;

	private TnFWebViewClient customClient = null;

	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);

		try {
			WebView appWebView = ((WebView) webView.getEngine().getView());
			this.settings = appWebView.getSettings();
			this.customClient = new TnFWebViewClient();
			webView.setWebViewClient(customClient);
		} catch (Exception error) {
			this.settings = null;
		}
	}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		try {
			if (action.equals("setUserAgent")) {
				String text = args.getString(0);
				this.settings.setUserAgentString(text);
				callbackContext.success(this.settings.getUserAgentString());
				return true;
			} else if (action.equals("getUserAgent")) {
				callbackContext.success(this.settings.getUserAgentString());
				return true;
			} else	if (action.equals("setOrigin")) {
					String text = args.getString(0);
					this.customClient.setOrigin(text);
					callbackContext.success(this.customClient.getOrigin());
					return true;
			} else if (action.equals("getOrigin")) {
					callbackContext.success(this.customClient.getOrigin());
					return true;
			} else if (action.equals("reset")) {
				this.settings.setUserAgentString(null);
				callbackContext.success(true);
				return true;
			}
			callbackContext.error("Invalid action");
			return false;
		} catch (Exception e) {
			callbackContext.error(e.getMessage());
			return false;
		}
	}

}
