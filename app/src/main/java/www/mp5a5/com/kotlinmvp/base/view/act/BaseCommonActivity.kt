package www.mp5a5.com.kotlinmvp.base.view.act

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import www.mp5a5.com.kotlinmvp.util.AppContextUtils
import www.mp5a5.com.kotlinmvp.util.StatusBarUtils
import www.mp5a5.com.kotlinmvp.util.ViewManagerUtils

open class BaseCommonActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppContextUtils.initAct(this)
        ViewManagerUtils.addActivity(this)
        initStatusBar()
    }
    
    private fun initStatusBar() {
        StatusBarUtils.setLightMode(this)
    }
}
