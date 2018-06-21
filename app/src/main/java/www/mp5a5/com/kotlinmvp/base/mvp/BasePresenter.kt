package www.mp5a5.com.kotlinmvp.base.mvp

import java.lang.ref.WeakReference

/**
 * @describe
 * @author ：king9999 on 2018/6/21 11：02
 * @email：wwb199055@enn.cn
 */
abstract class BasePresenter<V : BaseView> {
    
    protected lateinit var v: V
    
    protected var weakReference: WeakReference<V>? = null
    
    fun bind(view: BaseView) {
        this.v = view as V
        weakReference = WeakReference<V>(v)
    }
    
    fun unBind() {
        if (weakReference != null) {
            weakReference!!.clear()
            weakReference = null
        }
    }
    
    fun isUnBind(): Boolean {
        return weakReference != null && weakReference!!.get() != null
    }
    
    fun getView(): V? {
        if (weakReference != null) {
            return weakReference!!.get()
        }
        return null
    }
}