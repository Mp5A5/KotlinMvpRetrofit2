package www.mp5a5.com.kotlinmvp.net.interceptor


import okhttp3.logging.HttpLoggingInterceptor
import www.mp5a5.com.kotlinmvp.util.LogUtils
import java.io.UnsupportedEncodingException
import java.net.URLDecoder

/**
 * @author ：王文彬 on 2018/5/23 13：24
 * @describe：
 * @email：wwb199055@126.com
 */
class LoggingInterceptor {
    
    companion object {
        //日志拦截器
        val loggingInterceptor: HttpLoggingInterceptor
            get() {
                val interceptor = HttpLoggingInterceptor { message ->
                    try {
                        val text = URLDecoder.decode(message, "utf-8")
                        LogUtils.e("OKHttp-----", text)
                    } catch (e: UnsupportedEncodingException) {
                        e.printStackTrace()
                        LogUtils.e("OKHttp-----", message)
                    }
                }
                
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                return interceptor
            }
    }
    
    
}
