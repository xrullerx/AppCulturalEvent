package com.example.lsds.appculturalevent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lsds.appculturalevent.data.model.categorylist.event.EventCategoryWeb;
import com.example.lsds.appculturalevent.data.model.categorylist.event.MessageEvent;
import com.example.lsds.appculturalevent.data.model.categorylist.event.Subevent;
import com.example.lsds.appculturalevent.data.remote.IApiRemoute;
import com.squareup.picasso.Picasso;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventCategory extends AppCompatActivity {

    private int category_id = 0;
    private ListView listEvent;
    private Retrofit client;
    private IApiRemoute iApiRemoute;
    private EventCategory.EventAdapter eventadapter;


    // Класс адаптера для событий по выбранной категории.
    public class EventAdapter extends ArrayAdapter {
        private List<MessageEvent> mesEvent;
        private int resorce;
        private LayoutInflater inflater;

        public EventAdapter(Context context, int resource, List<MessageEvent> objects) {
            super(context, resource, objects);
            mesEvent = objects;
            this.resorce = resource;
            inflater =  (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(resorce, null);
            }
            ImageView imageView1;
            TextView textTitle;
            TextView textDate;
            imageView1 = (ImageView)convertView.findViewById(R.id.imageView1);
            textTitle = (TextView)convertView.findViewById(R.id.textView1);
            textDate = (TextView)convertView.findViewById(R.id.textView2);

            // Получить первый subevents
            Subevent subevent = mesEvent.get(position).getSubevents().get(0);
            textTitle.setText(mesEvent.get(position).getTitle());
            textDate.setText(subevent.getDate());
            // Загрузить изображение
            String adr = "http://media.cultserv.ru/i/70x70/" + subevent.getImage();
            Picasso.with(getApplicationContext()).load(Uri.parse(adr)).into(imageView1);
            return  convertView;
        }
        // Получение данных о событии
        public int getIdSubevents(int idx){ return mesEvent.get(idx).getSubevents().get(0).getId();}
        public String getImg(int idx){
            return mesEvent.get(idx).getSubevents().get(0).getImage();
        }
        public String getTitle(int idx){
            return mesEvent.get(idx).getTitle();
        }
        public String getDate(int idx){ return  mesEvent.get(idx).getSubevents().get(0).getDate(); }
    }

    // Установка ид категории.
    public void setCategoryId(int id){
        category_id = id;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_category);
        listEvent = (ListView)findViewById(R.id.ListEvent);
        // Получить id
        Intent intent = getIntent();
        setCategoryId(intent.getIntExtra("id", 5));

        // Перехватываем событие нажания на элемент списка.
        listEvent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), DetalInfo.class);
                intent.putExtra("Subevents", eventadapter.getIdSubevents(position));
                intent.putExtra("ImageName", eventadapter.getImg(position));
                intent.putExtra("Title", eventadapter.getTitle(position));
                intent.putExtra("Date", eventadapter.getDate(position));
                startActivity(intent);
            }
        });
        // Выполняем запрос.
        client = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IApiRemoute.BASE_URL)
                .build();
       iApiRemoute = client.create(IApiRemoute.class);
        Call<EventCategoryWeb> call = iApiRemoute.getEvent(category_id);
        call.enqueue(new Callback<EventCategoryWeb>() {
            @Override
            public void onResponse(Call<EventCategoryWeb> call, Response<EventCategoryWeb> response) {
                // Передаем список событий в список.
                List<MessageEvent> levent = response.body().getMessage();
                eventadapter = new EventCategory.EventAdapter(getApplicationContext(), R.layout.layout_event, levent);
                listEvent.setAdapter(eventadapter);
            }
            @Override
            public void onFailure(Call<EventCategoryWeb> call, Throwable t) {
                Log.d("Failed", t.getMessage());
            }
        });
    }
}
