package com.transcendence.petrichor.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.core.global.Global;
import com.transcendence.petrichor.R;
import com.transcendence.petrichor.base.action.StatusAction;
import com.transcendence.petrichor.base.activity.PetrichorBaseActivity;
import com.transcendence.petrichor.widget.StatusLayout;

/**
 * @Author Joephone on 2021/12/29 0029 下午 2:09
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class WebViewActivity extends PetrichorBaseActivity implements View.OnClickListener
    , StatusAction {
    private StatusLayout mStatusLayout;
    private WebView mWebView;
    private ImageView ivBack;
    private ImageView ivMenu;
    private ImageView ivForward;
    private TextView tvTitle;

    private int mArticleId = -1;
    private String mTitle = "";
    private String mAuthor = "";
    private String mUrl = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        setBackVisibility();
        mWebView = findViewById(R.id.webView);
        tvTitle = findViewById(R.id.tv_title);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        ivMenu = findViewById(R.id.iv_menu);
        ivMenu.setOnClickListener(this);
        ivForward = findViewById(R.id.iv_forward);
        ivForward.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mArticleId = getIntent().getIntExtra("articleId", -1);
        mTitle = getIntent().getStringExtra(Global.INTENT_KEY.TITLE);
        mTitle = mTitle == null ? "" : mTitle;
        mAuthor = getIntent().getStringExtra("author");
        tvTitle.setText(mTitle = mTitle == null ? "" : mTitle);
        mUrl = getIntent().getStringExtra(Global.INTENT_KEY.URL);
        mUrl = mUrl == null ? "" : mUrl;
//        boolean collected = getIntent().getBooleanExtra("collected", false);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.loadUrl(mUrl);
    }

    public static void start(Context context, int articleId, String title, String url, String author, boolean collected) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("articleId", articleId);
        intent.putExtra(Global.INTENT_KEY.TITLE, title);
        intent.putExtra(Global.INTENT_KEY.URL, url);
        intent.putExtra("author", author);
        intent.putExtra("collected", collected);
        context.startActivity(intent);
    }

    public static void start(Context context, String url) {
        start(context,url,"");
    }

    public static void start(Context context, String url,String title) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.iv_menu:

                break;
            case R.id.iv_forward:
                if (mWebView.canGoForward()) {
                    mWebView.goForward();
                }
                break;
        }
    }

    @Override
    public StatusLayout getStatusLayout() {
        return mStatusLayout;
    }
}
