package com.ninh.tuvungtienganh;

import com.zenapp.tuvung.R;
import com.zenapp.tuvung.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

public class helpscr extends Activity {
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(layout.web_view_home);

		findViewById(R.id.btnOption).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						finish();
					}
				});

		webView = (WebView) findViewById(R.id.web_view_help);
		webView.getSettings().setDomStorageEnabled(true);
		webView.setVerticalScrollBarEnabled(false);
		webView.setHorizontalScrollBarEnabled(false);
		webView.setFocusableInTouchMode(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/help.html");
	}
}
