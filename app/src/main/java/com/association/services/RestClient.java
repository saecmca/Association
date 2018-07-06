package com.association.services;

import com.association.registration.AssnLabReq;
import com.association.registration.AssnResp;
import com.association.registration.AssnSubReq;
import com.association.registration.AssnSubResp;
import com.association.registration.LangResp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Mani on 25-11-2017.
 */

public class RestClient {

  public static APIInterface apiInterface;
  public static String BASE_URL = "http://plot.kenbiolinks.com/assnService.svc/";


  public static APIInterface getapiclient() {

    if (apiInterface == null) {

      HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
      httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

      OkHttpClient okHttpClient1 = new OkHttpClient().newBuilder()
          .connectTimeout(60, TimeUnit.SECONDS)
          .readTimeout(130, TimeUnit.SECONDS)
          .writeTimeout(60, TimeUnit.SECONDS)
//                    .build();
          .addInterceptor(new BasicAuthInterceptor("assn", "assn@321*"))
          .addInterceptor(httpLoggingInterceptor).build();
      Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
          .client(okHttpClient1)
          .addConverterFactory(GsonConverterFactory.create()).build();

      apiInterface = retrofit.create(APIInterface.class);
    }

    return apiInterface;
  }

  public interface APIInterface {
   // @Headers("Content-Type: application/json")
    @POST("GetLanguage")
    Call<LangResp> getLangResp();

    @POST("GetScreenDetails")
    Call<AssnResp> getAssnLable(@Body AssnLabReq assnLabReq);

    @POST("Assn_registration")
    Call<AssnSubResp> getAssnSubmit(@Body AssnSubReq assnLabReq);

  }
}
