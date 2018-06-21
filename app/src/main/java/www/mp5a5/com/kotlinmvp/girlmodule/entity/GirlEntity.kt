package www.mp5a5.com.kotlinmvp.girlmodule.entity

import com.google.gson.JsonObject
import www.mp5a5.com.kotlinmvp.net.BaseResponseEntity

/**
 * @describe
 * @author ：king9999 on 2018/6/15 09：54
 * @email：wwb199055@enn.cn
 */
data class GirlEntityResult(val showapi_res_code: Int, val showapi_res_error: String, val showapi_res_body: JsonObject): BaseResponseEntity(showapi_res_code, showapi_res_error)

data class GirlEntity(val title: String, val thumb: String, val url: String)
