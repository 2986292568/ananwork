package com.example.utils;

import com.example.webview05.R;

import android.app.AlertDialog;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class Utils {
	private Context mContext;

	public Utils(Context context) {
		this.mContext = context;
	}

	@JavascriptInterface
	public void showList() {
		new AlertDialog.Builder(mContext)
				.setTitle("ͼ���б�")
				.setIcon(R.drawable.ic_launcher)
				.setItems(
						new String[] { "���java����", "���Android����",
								"������java EE����" }, null)
				.setPositiveButton("ȷ��", null).create().show();

	}

	@JavascriptInterface
	public void showToast() {

		Toast.makeText(mContext, "���óɹ�", Toast.LENGTH_LONG).show();
	}

}
