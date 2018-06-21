package www.mp5a5.com.kotlinmvp.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import www.mp5a5.com.kotlinmvp.net.interceptor.HttpCacheInterceptor
import www.mp5a5.com.kotlinmvp.net.interceptor.HttpHeaderInterceptor
import www.mp5a5.com.kotlinmvp.net.interceptor.LoggingInterceptor
import www.mp5a5.com.kotlinmvp.util.AppContextUtils
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @describe
 * @author ：king9999 on 2018/6/20 15：40
 * @email：wwb199055@enn.cn
 */
object RetrofitFactor {
    
    init {
        init()
    }
    
    private var retrofit: Retrofit? = null
    
    private fun init() {
        
        // 指定缓存路径,缓存大小100Mb
        val cacheFile = File(AppContextUtils.getContext().getCacheDir(), "HttpCache");
        val cache = Cache(cacheFile, 1024 * 1024 * 100);
        
        val httpClient: OkHttpClient = OkHttpClient().newBuilder()
                .readTimeout(ApiConfig.defaultTime.toLong(), TimeUnit.SECONDS)
                .connectTimeout(ApiConfig.defaultTime.toLong(), TimeUnit.SECONDS)
                .addInterceptor(LoggingInterceptor.loggingInterceptor)
                .addInterceptor(HttpHeaderInterceptor())
                .addNetworkInterceptor(HttpCacheInterceptor())
                .cache(cache)
                .build()
        
        val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        
        ApiConfig.appToken
        
        retrofit = Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConfig.serverUrl)
                .build()
    }
    
    
    fun <T> create(clazz: Class<T>): T {
        return retrofit!!.create(clazz)
    }
}