package com.zss.superShop.config;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.concurrent.TimeUnit;

/**
 * 代理工厂
 * @author zhushanshan
 *
 */
public class RetrofitFactory {

    public static Retrofit build(String baseUrl) {
        // 60s read / write timeout
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder().baseUrl(baseUrl)
                //.addConverterFactory(BaofooConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    /**
     * Retrofit 服务代理
     */
    public static <T> T proxyApi(Class<T> apiClaz, Retrofit retrofit) {
        if (retrofit ==  null || apiClaz == null) {
            return null;
        }
        return retrofit.create(apiClaz);
    }
}
