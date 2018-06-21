package www.mp5a5.com.kotlinmvp.girlmodule.net
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.QueryMap
import www.mp5a5.com.kotlinmvp.girlmodule.entity.GirlEntityResult

/**
 * @describe
 * @author ：king9999 on 2018/6/15 13：31
 * @email：wwb199055@enn.cn
 */

interface GirlUrl {
    
    @POST("819-1/")
    fun beautifulGirlEntity(@QueryMap maps: Map<String, String>): Observable<GirlEntityResult>
}