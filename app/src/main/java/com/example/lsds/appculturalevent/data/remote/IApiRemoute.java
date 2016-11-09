package com.example.lsds.appculturalevent.data.remote;

import android.content.Intent;

import com.example.lsds.appculturalevent.data.model.categorylist.CategoryWeb;
import com.example.lsds.appculturalevent.data.model.categorylist.event.EventCategoryWeb;
import com.example.lsds.appculturalevent.data.model.categorylist.event.info.InfoEvent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApiRemoute {
    static String BASE_URL = "https://api.cultserv.ru" ;
    @GET("/v4/categories/list?session=sesson_android_2015_ponominalu_msk")
    Call<CategoryWeb> getCategory();
    @GET("/v4/events/list?session=sesson_android_2015_ponominalu_msk")
    Call<EventCategoryWeb> getEvent(@Query("category_id") Integer catKey);
    @GET("/v4/subevents/description/get?session=sesson_android_2015_ponominalu_msk")
    Call<InfoEvent> getDetalInfo(@Query("id") Integer id);
}
