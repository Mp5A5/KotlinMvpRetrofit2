package www.mp5a5.com.kotlinmvp.util


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context

/**
 * describe：app的上下文
 * author ：王文彬 on 2018/5/22 11：21
 * email：wwb199055@126.com
 */
class AppContextUtils {
    
    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }
    
    companion object {
        
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null
        
        @SuppressLint("StaticFieldLeak")
        private var activity: Activity? = null
        
        /**
         * 在Application中进行初始化
         *
         * @param context 上下文
         */
        fun init(context: Context) {
            AppContextUtils.context = context.applicationContext
        }
        
        /**
         * 在BaseCommonActivity中进行初始化
         *
         * @param act 上下文
         */
        fun initAct(act: Activity) {
            AppContextUtils.activity = act
        }
        
        
        /**
         * 获取ApplicationContext
         *
         * @return ApplicationContext
         */
        fun getContext(): Context {
            return if (context != null) {
                context!!
            } else {
                throw NullPointerException("u should init first")
            }
        }
        
        /**
         * 获取Activity
         *
         * @return Activity
         */
        fun getActivity(): Activity {
            return if (context != null) {
                activity!!
            } else {
                throw NullPointerException("u should init first")
            }
        }
    }
}