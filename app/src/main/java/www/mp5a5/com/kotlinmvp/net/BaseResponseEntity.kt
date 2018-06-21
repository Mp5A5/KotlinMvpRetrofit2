package www.mp5a5.com.kotlinmvp.net

import java.io.Serializable

/**
 * @describe
 * @author ：king9999 on 2018/6/20 14：41
 * @email：wwb199055@enn.cn
 */
open class BaseResponseEntity(val code: Int, val msg: String) : Serializable {
    
    val getMsg: String
        get() = msg
    
    val isSuccess: Boolean
        get() = 0 == code
    
    val tokenInvalid: Int
        get() = 401
    
    val loginFailure: String
        get() = "406"
    
    companion object {
        
        private const val serialVersionUID = 1L
    }
    
}