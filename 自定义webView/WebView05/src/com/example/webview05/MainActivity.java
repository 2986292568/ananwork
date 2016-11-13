package com.example.webview05;

import com.example.utils.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends Activity {

	private WebView webview;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webview = (WebView) findViewById(R.id.webview);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				webview.loadUrl("javascript:doAlert()");
			}
		});
		// ��ȡWebSetting����
		WebSettings webSettings = webview.getSettings();
		// ����֧��javascript
		webSettings.setJavaScriptEnabled(true);
		// ��Android���涨�����Utils����¶��javascript
		webview.addJavascriptInterface(new Utils(MainActivity.this), "utils");
		webview.loadUrl("file:///android_asset/test.html");
		webview.setWebChromeClient(new WebChromeClient());
	}

	// �û������豸��ĳ��������ʱ������������
	// ����1:keycode���û�����������İ�����
	// ����2:keyEvent�������¼������е�keycode�����������
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		// �û����·��ؼ���ʱ���ӡ��־ canGoBack�жϵ�ǰwebView�Ƿ������ʷ��¼��������ڷ���true
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
			// ����webview����ʷ��¼
			webview.goBack();
			// �����Ѿ�����������¼�
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
