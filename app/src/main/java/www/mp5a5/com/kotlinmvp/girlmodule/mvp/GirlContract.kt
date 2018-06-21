package www.mp5a5.com.kotlinmvp.girlmodule.mvp

import www.mp5a5.com.kotlinmvp.base.mvp.BaseView
import www.mp5a5.com.kotlinmvp.base.view.act.BaseActivity
import www.mp5a5.com.kotlinmvp.girlmodule.entity.GirlEntity

/**
 * @describe
 * @author ：king9999 on 2018/6/21 16：19
 * @email：wwb199055@enn.cn
 */
interface GirlContract {
    
    interface View : BaseView {
        fun addData(weaponEntityList: MutableList<GirlEntity>)
        fun setData(weaponEntityList: MutableList<GirlEntity>)
        fun showMsg(msg: String)
        fun isRefresh(refresh: Boolean)
        fun loadMoreComplete()
    }
    
    interface Presenter {
        fun requestNetworkData(activity: BaseActivity, startType: Int, startIndex: Int, refresh: Boolean)
    }
}