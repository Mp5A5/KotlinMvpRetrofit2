package www.mp5a5.com.kotlinmvp.net

import www.mp5a5.com.kotlinmvp.util.ConstantUtil

/**
 * @describe
 * @author ：king9999 on 2018/6/21 10：39
 * @email：wwb199055@enn.cn
 */
open class BaseApiService {
    
    protected fun getPublicParams(): MutableMap<String, String> {
        val map = mutableMapOf<String, String>()
        map.put("showapi_sign", ConstantUtil.SECRET)
        map.put("showapi_appid", ConstantUtil.APPID)
        return map
    }
    
    protected fun putParams(map: MutableMap<String, String>, key: String, value: String?) {
        map.put(key, value!!)
    }
    
}