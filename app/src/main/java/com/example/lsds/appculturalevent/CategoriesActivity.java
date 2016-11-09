package com.example.lsds.appculturalevent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lsds.appculturalevent.data.model.categorylist.CategoryWeb;
import com.example.lsds.appculturalevent.data.model.categorylist.Message;
import com.example.lsds.appculturalevent.data.remote.IApiRemoute;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesActivity extends AppCompatActivity {
    private ListView ListCategory;
    private Retrofit client;
    private IApiRemoute iApiRemoute;
    private MoviAdapter mvadapter;
    private AlertDialog.Builder ad;

    // Класс адаптера для вывода категорий.
    public class MoviAdapter extends ArrayAdapter{
        private List<Message> mesCat;
        private int resorce;
        private LayoutInflater inflater;

        public MoviAdapter(Context context, int resource, List<Message> objects) {
            super(context, resource, objects);
            mesCat = objects;
            this.resorce = resource;
            inflater =  (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(resorce, null);
            }
            TextView textTitle;
            TextView textCount;
            textTitle = (TextView)convertView.findViewById(R.id.textTitle);
            textCount = (TextView)convertView.findViewById(R.id.textCount);
            textTitle.setText(mesCat.get(position).getTitle());
            textCount.setText(Integer.toString(mesCat.get(position).getEventsCount()));
            return  convertView;
        }
        public Integer getIdVal(Integer idx)
        {
            return mesCat.get(idx).getId();
        }
    }

    // Перехват кнопки, для вызова диалога выхода.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ad.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ListCategory = (ListView)findViewById(R.id.ListCategory);

        // Описываем диалог выхода из приложения.
        ad = new AlertDialog.Builder(this);
        ad.setTitle("Закрытие приложения.");  // заголовок
        ad.setMessage("Вы действительно хотите выйти ?"); // сообщение
        ad.setCancelable(false);
        ad.setPositiveButton("Выйти", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                Toast.makeText(getApplicationContext(), "Возможно вы правы",
                        Toast.LENGTH_LONG).show();
                moveTaskToBack(true);
                System.exit(0);
            }
        });
        ad.setNegativeButton("Остаться", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                Toast.makeText(getApplicationContext(), "Вы сделали правильный выбор", Toast.LENGTH_LONG)
                        .show();
            }
        });

        // Перехватываем событие нажания на элемент списка.
        ListCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Integer idCat = mvadapter.getIdVal(position);
                Toast.makeText(getApplicationContext(), "id cat "+Integer.toString(idCat),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), EventCategory.class);
                // передаем id активити.
                intent.putExtra("id", idCat);
                startActivity(intent);
            }
        });

        // Получение данных по категориям.
        client = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IApiRemoute.BASE_URL)
                .build();
        iApiRemoute = client.create(IApiRemoute.class);
        Call<CategoryWeb> call = iApiRemoute.getCategory();
        call.enqueue(new Callback<CategoryWeb>() {
            @Override
            public void onResponse(Call<CategoryWeb> call, Response<CategoryWeb> response) {
                // Передаем список категорий в список.
                List<Message> lm = response.body().getMessage();
                mvadapter = new MoviAdapter(getApplicationContext(), R.layout.layout_items_categ, lm);
                ListCategory.setAdapter(mvadapter);
            }
            @Override
            public void onFailure(Call<CategoryWeb> call, Throwable t) {
                Log.d("Failed", t.getMessage());
            }
        });
    }
}
