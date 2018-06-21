package www.mp5a5.com.kotlinmvp.net

import www.mp5a5.com.kotlinmvp.util.SpUtils

/**
 * @describe
 * @author ：king9999 on 2018/6/20 15：45
 * @email：wwb199055@enn.cn
 */
object ApiConfig {
    
    private var APP_TOKEN = "app_token"
    private var DEFAULT_TIMEOUT = 5000;
    private var URL: String = ""
    
    var serverUrl: String
        get() = this.URL
        set(value) {
            this.URL = value
        }
    
    var appToken: String
        get() = SpUtils.getString(APP_TOKEN)
        set(token) {
            SpUtils.setString(APP_TOKEN, token)
        }
    
    
    var defaultTime: Int
        get() = DEFAULT_TIMEOUT
        set(value) {
            this.DEFAULT_TIMEOUT = value
        }
}

