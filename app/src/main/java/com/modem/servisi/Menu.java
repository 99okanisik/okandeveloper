package com.modem.servisi;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Tam ekran modunu etkinleştir
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 2. Activity'nin içeriğini belirle
        setContentView(R.layout.activity_menu);

        // 3. WebView öğesini bul
        webView = findViewById(R.id.webView);

        // 4. WebView için ayarları al
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 5. JavaScript'i etkinleştir (isteğe bağlı)
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        // 6. WebView isteğe bağlı olarak diğer ayarları da ekle

        // 7. WebView için özel bir WebViewClient ayarla
        webView.setWebViewClient(new CustomWebViewClient());

        // 8. WebView'e bir web sitesini yükle
        webView.loadUrl("https://modemservisi.com/");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 9. Geri tuşuna basıldığında
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); // 10. WebView'de bir önceki sayfaya git
            return true;
        } else {
            // 11. WebView'de geri gidilecek bir sayfa yoksa, çıkış uyarısı göster
            showExitDialog();
            return true;
        }
    }

    // 12. Çıkış uyarısı gösteren metot
    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Uygulamadan çıkmak istiyor musunuz?");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // 13. Uygulamayı kapat
            }
        });
        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Kullanıcı "Hayır" derse uyarıyı kapat
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // 14. WebViewClient sınıfını özelleştir
    private class CustomWebViewClient extends WebViewClient {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            // 15. İzin kontrolleri burada yapılabilir
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            // 16. Hata yönetimi burada yapılabilir
        }
    }
}
