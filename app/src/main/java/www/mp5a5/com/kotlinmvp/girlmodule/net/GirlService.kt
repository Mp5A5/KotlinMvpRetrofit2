
package www.mp5a5.com.kotlinmvp.girlmodule.net
import io.reactivex.Observable
import www.mp5a5.com.kotlinmvp.girlmodule.entity.GirlEntityResult
import www.mp5a5.com.kotlinmvp.net.BaseApiService
import www.mp5a5.com.kotlinmvp.net.RetrofitFactor

/**
 * @describe
 * @author ：king9999 on 2018/6/16 12：25
 * @email：wwb199055@enn.cn
 */
object GirlService : BaseApiService() {
    
    private val mBeautifulGirlUrl: GirlUrl = RetrofitFactor.create(GirlUrl::class.java)
    
    fun getBeautifulGirlData(type: Int, page: Int): Observable<GirlEntityResult> {
        val params = getPublicParams()
        params.put("type", type.toString())
        params.put("num", "20")
        params.put("page", page.toString())
        return mBeautifulGirlUrl!!.beautifulGirlEntity(params)
    }
}