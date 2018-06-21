package www.mp5a5.com.kotlinmvp.girlmodule.mvp

import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import www.mp5a5.com.kotlinmvp.base.mvp.BasePresenter
import www.mp5a5.com.kotlinmvp.base.view.act.BaseActivity
import www.mp5a5.com.kotlinmvp.girlmodule.entity.GirlEntity
import www.mp5a5.com.kotlinmvp.girlmodule.entity.GirlEntityResult
import www.mp5a5.com.kotlinmvp.girlmodule.net.GirlService
import www.mp5a5.com.kotlinmvp.net.BaseObserver

/**
 * @describe
 * @author ：king9999 on 2018/6/21 16：23
 * @email：wwb199055@enn.cn
 */
class GirlPresenter : BasePresenter<GirlContract.View>(), GirlContract.Presenter {
    
    override fun requestNetworkData(activity: BaseActivity, startType: Int, startIndex: Int, refresh: Boolean) {
        GirlService.getBeautifulGirlData(startType, startIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activity!!.bindToLifecycle())
                .subscribe(object : BaseObserver<GirlEntityResult>() {
                    override fun onSuccess(response: GirlEntityResult) {
                        v.isRefresh(false)
                        setData(response.showapi_res_code, response, refresh)
                    }
                    
                    override fun onFailing(response: GirlEntityResult) {
                        super.onFailing(response)
                        v.isRefresh(false)
                    }
                    
                    override fun onError(e: Throwable) {
                        super.onError(e)
                        v.isRefresh(false)
                    }
                })
    }
    
    private fun setData(code: Int, response: GirlEntityResult, refresh: Boolean) {
        
        when (code) {
            0 -> {
                val iterator = response.showapi_res_body
                        .entrySet().iterator()
                val girlEntityList: MutableList<GirlEntity> = ArrayList()
                while (iterator.hasNext()) {
                    val next = iterator.next()
                    try {
                        val huaBan = Gson().fromJson(next.value, GirlEntity::class.java)
                        girlEntityList.add(huaBan)
                    } catch (e: Exception) {
                    }
                }
                if (refresh) {
                    girlEntityList.shuffle()
                    v.setData(girlEntityList)
                } else {
                    v.addData(girlEntityList)
                    v.loadMoreComplete()
                }
                
            }
        }
    }
}