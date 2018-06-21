package www.mp5a5.com.kotlinmvp.net.interceptor


import okhttp3.Interceptor
import okhttp3.Response
import www.mp5a5.com.kotlinmvp.net.ApiConfig
import java.io.IOException

/**
 * @author ：王文彬 on 2018/5/23 13：31
 * @describe：
 * @email：wwb199055@126.com
 */
class HttpHeaderInterceptor : Interceptor {
    
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authorised = originalRequest.newBuilder()
                .header("Content-type", "application/json")
                .addHeader("token", ApiConfig.appToken)
                .removeHeader("Pragma").build()
        return chain.proceed(authorised)
    }
}
