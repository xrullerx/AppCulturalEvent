package com.example.lsds.appculturalevent;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lsds.appculturalevent.data.model.categorylist.event.EventCategoryWeb;
import com.example.lsds.appculturalevent.data.model.categorylist.event.MessageEvent;
import com.example.lsds.appculturalevent.data.model.categorylist.event.info.InfoEvent;
import com.example.lsds.appculturalevent.data.remote.IApiRemoute;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalInfo extends AppCompatActivity {
    private int id;
    private String sImage;
    private String sTitle;
    private String sDate;
    private ImageView imageViewImg;
    private TextView textViewTitle;
    private TextView textViewData;
    private WebView webView;
    private Retrofit client;
    private IApiRemoute iApiRemoute;
    private EventCategory.EventAdapter eventadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Получить параметры события.
        Intent intent = getIntent();
        id = intent.getIntExtra("Subevents", 0);
        sImage = intent.getStringExtra("ImageName");
        sTitle = intent.getStringExtra("Title");
        sDate = intent.getStringExtra("Date");
        // Определим объекты вывода.
        imageViewImg = (ImageView)findViewById(R.id.imageViewImg);
        textViewTitle = (TextView)findViewById(R.id.textViewTitle);
        textViewData = (TextView)findViewById(R.id.textViewData);
        webView = (WebView)findViewById(R.id.webView);

        // Выводим.
        String adr = "http://media.cultserv.ru/i/70x70/" + sImage;
        Picasso.with(getApplicationContext()).load(Uri.parse(adr)).into(imageViewImg);
        textViewTitle.setText(sTitle);
        textViewData.setText(sDate);

        // Выполняем запрос.
        client = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IApiRemoute.BASE_URL)
                .build();
        iApiRemoute = client.create(IApiRemoute.class);
        Call<InfoEvent> call = iApiRemoute.getDetalInfo(id);
        call.enqueue(new Callback<InfoEvent>() {
            @Override
            public void onResponse(Call<InfoEvent> call, Response<InfoEvent> response) {
                // Загружаем веб описание.
                WebSettings settings = webView.getSettings();
                settings.setDefaultTextEncodingName("utf-8");
                webView.loadDataWithBaseURL(null, response.body().getMessage(), "text/html", "en_US", null);
            }
            @Override
            public void onFailure(Call<InfoEvent> call, Throwable t) {
                Log.d("Failed", t.getMessage());
            }
        });
    }
}
