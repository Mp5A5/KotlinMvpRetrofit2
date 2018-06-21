package www.mp5a5.com.kotlinmvp.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import java.util.*

/**
 * @describe
 * @author ：king9999 on 2018/6/20 18：47
 * @email：wwb199055@enn.cn
 */
object ViewManagerUtils {
    
    private var activityStack: Stack<Activity>? = null
    private var fragmentList: MutableList<Fragment>? = null
    
    
    fun addFragment(index: Int, fragment: Fragment) {
        if (fragmentList == null) {
            fragmentList = mutableListOf()
        }
        fragmentList!!.add(fragment)
    }
    
    fun getFragment(index: Int): Fragment? {
        return if (fragmentList != null) fragmentList!!.get(index) else null
    }
    
    
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }
    
    fun getCurrentActivity(): Activity {
        return activityStack!!.lastElement()
    }
    
    fun finishCurrentActivity() {
        finishActivity(activityStack!!.lastElement())
    }
    
    private fun finishActivity(activity: Activity?) {
        if (activity != null) {
            activityStack!!.remove(activity)
            activity.finish()
            activity == null
        }
    }
    
    /**
     * 结束指定Class的Activity
     */
    fun finishActivity(clazz: Class<*>) {
        activityStack!!.forEach {
            if (it.javaClass == clazz) {
                finishActivity(it.javaClass)
                return
            }
        }
    }
    
    
    /**
     * 结束全部的Activity
     */
    fun finishAllActivity() {
        for (i in 0 until activityStack!!.size) {
            if (activityStack!!.get(i) != null) {
                activityStack!!.get(i).finish()
            }
        }
        activityStack!!.clear()
    }
    
    
    /**
     * 退出应用程序
     */
    @SuppressLint("MissingPermission")
    fun exitApp(context: Context) {
        try {
            finishAllActivity()
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(0)
        } catch (e: Exception) {
            LogUtils.e("ActivityManager", "app exit" + e.message)
        }
    }
    
}