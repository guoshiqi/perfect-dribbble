package com.ilovelonely.guoshiqi.dribbbleu.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ilovelonely.guoshiqi.dribbbleu.Helper.DbService;
import com.ilovelonely.guoshiqi.dribbbleu.Helper.OkHttpHelper;
import com.ilovelonely.guoshiqi.dribbbleu.Helper.RequestParams;
import com.ilovelonely.guoshiqi.dribbbleu.R;
import com.ilovelonely.guoshiqi.dribbbleu.api.ApiConstants;
import com.ilovelonely.guoshiqi.dribbbleu.frameWork.AppConstants;
import com.ilovelonely.guoshiqi.dribbbleu.frameWork.BaseActivity;
import com.ilovelonely.guoshiqi.dribbbleu.frameWork.OwApplication;
import com.ilovelonely.guoshiqi.dribbbleu.utils.IntentUtils;
import com.ilovelonely.guoshiqi.dribbbleu.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Method;

import de.greenrobot.daoexample.Customer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyWebActivity extends BaseActivity {

	private WebView mWebView;
	private ProgressBar mProgress;
	private String url;
	private String email;
	private String password;

	@Override
	public void onParam(Bundle b) {
		url=b.getString("url");
		email=b.getString("email");
		password=b.getString("password");
	}

	@Override
	public void onCreateView() {
		setContentView(R.layout.frag_setting_protocol);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	}

	@Override
	public void setView() {

		mWebView = (WebView) this.findViewById(R.id.frag_setting_protocol_webview);
		mProgress = (ProgressBar) this.findViewById(R.id.frag_progress);
		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
				super.doUpdateVisitedHistory(view, url, isReload);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {

				Log.i("msg", "onPageStarted");

			}

			@Override
			public void onPageFinished(WebView view, String url) {

				if(url.contains("code")){
					Log.i("msg", url);
					String code=url.substring(url.indexOf("=")+1,url.length());
					getAccessToken(code);

				}
				super.onPageFinished(view, url);
				// isLoading = false;
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				return super.shouldOverrideUrlLoading(view, url);

			}

		});
		mWebView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				mProgress.setProgress(progress);
				if (progress >= 90) {
					mProgress.setVisibility(View.GONE);
				}
			}

		});

		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		settings.setBuiltInZoomControls(false);
		settings.setSupportZoom(false);
		settings.setCacheMode(WebSettings.LOAD_DEFAULT);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setDomStorageEnabled(true);
		settings.setAppCacheEnabled(true);


		settings.setAllowFileAccess(true);


		mWebView.postDelayed(new Runnable() {

			@Override
			public void run() {
				loadMyWeb(url);
			}
		}, 300);
	}




	public void loadMyWeb(String url) {
		mWebView.loadUrl(url);
	}

	private void getAccessToken(String code){
		OkHttpHelper okHttpHelper= OkHttpHelper.getInstance();
		final RequestParams params=new RequestParams();
		params.put("client_id", AppConstants.CLIENT_ID);
		params.put("code",code);
		params.put("client_secret",AppConstants.CLIENT_SECRET);
		okHttpHelper.post(ApiConstants.OAUTH_TOKEN_URL, params, new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				try {
					JSONObject jsonObject=new JSONObject(response.body().string());
					OwApplication.accessToken=jsonObject.getString("access_token");
					IntentUtils.startActivity(MyWebActivity.this,HomeActivity.class,null);
					MyWebActivity.this.finish();
					Long id=0l;
					id=(Long) SPUtils.get(MyWebActivity.this,"id",id);
					if (id==null){
						id=0l;
					}else{
						id++;
					}
					Customer customer=new Customer(id,email,password,OwApplication.accessToken);
					DbService db=DbService.getInstance(MyWebActivity.this);
					db.saveNote(customer);
					SPUtils.put(OwApplication.getmInstance(),"id",id);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}



}
