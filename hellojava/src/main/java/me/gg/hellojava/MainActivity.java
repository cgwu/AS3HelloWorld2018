package me.gg.hellojava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.*;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Java对象名
        //参数2：Javascript对象名
        webView.addJavascriptInterface(this, "act");//AndroidtoJS类对象映射到js的act对象

    }

    public void loadHtmlPage(View view) {
        webView.loadUrl("file:///android_asset/stomp.html");
    }

    // 调用WebView里面的js方法
    public void callJs(View view) {
        // 法1
//        webView.loadUrl("javascript:callJS('arg1参数')");

        // 法2
        webView.evaluateJavascript("javascript:callJS('java参数')", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                //此处为 js 返回的结果
                Toast.makeText(MainActivity.this,"result:"+value, Toast.LENGTH_LONG).show();
            }
        });

    }

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void hello(String msg) {
        System.out.println("called by webview js,参数:" + msg);
    }

    @JavascriptInterface
    public int operate(String sender, String op, String arg1, String arg2) {
        int a1 = Integer.parseInt(arg1);
        int a2 = Integer.parseInt(arg2);
        if("add".equals(op)) {
            int r = a1 + a2;
            System.out.println("计算结果:"+r);
            return r;
//            webView.evaluateJavascript("javascript:send2user('" + sender + "', '" + r + "')",
//                    jsRet -> {
//                        System.out.println(jsRet);
//                    });
        }
        return 0;
    }

}
