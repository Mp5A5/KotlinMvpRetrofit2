package www.mp5a5.com.kotlinmvp

import www.mp5a5.com.kotlinmvp.base.app.BaseCommonApplication
import www.mp5a5.com.kotlinmvp.util.ConstantUtil

/**
 * @describe
 * @author ：king9999 on 2018/6/21 13：57
 * @email：wwb199055@enn.cn
 */
class BaseApplication : BaseCommonApplication() {
    
    override fun setBaseUrl(): String {
        return ConstantUtil.BASE_ULR
    }
    
    
}