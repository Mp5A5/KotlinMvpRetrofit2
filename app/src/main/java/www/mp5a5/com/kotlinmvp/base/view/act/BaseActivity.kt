package www.mp5a5.com.kotlinmvp.base.view.act

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import www.mp5a5.com.kotlinmvp.R
import www.mp5a5.com.kotlinmvp.util.AppContextUtils
import www.mp5a5.com.kotlinmvp.util.StatusBarUtils
import www.mp5a5.com.kotlinmvp.util.ViewManagerUtils

/**
 * @author ：king9999 on 2018/5/29 10：51
 * @describe：
 * @email： wwb199055@126.com
 */
abstract class BaseActivity : RxAppCompatActivity() {
    
    var thisActivity: BaseActivity? = null
        private set
    private var rightBtnTv: TextView? = null
    private var midTitleTv: TextView? = null
    private var leftBtnTv: TextView? = null
    
    /**
     * 得到右边的按钮
     */
    val rightBtn: TextView?
        get() = if (null != rightBtnTv) {
            rightBtnTv
        } else null
    
    val leftBtn: TextView?
        get() = if (null != leftBtnTv) {
            leftBtnTv
        } else null
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        thisActivity = this
        AppContextUtils.initAct(this)
        ViewManagerUtils.addActivity(this)
        initStatusBar()
        val view = initLayoutView()
        setContentView(if (needHeader()) getMergeView(view) else view)
        setImmersive()
        init()
        initParams()
        initData()
        initView()
        initAdapter()
        initNet()
    }
    
    open protected fun initNet() {}
    
    open protected fun initAdapter() {
    
    }
    
    abstract fun initLayoutView(): View
    
    open protected fun init() {
    
    }
    
    open protected fun initParams() {
    
    }
    
    open protected fun initData() {
    
    }
    
    open protected fun initStatusBar() {
        StatusBarUtils.setLightMode(this)
    }
    
    open protected fun initView() {}
    
    open protected fun needHeader(): Boolean {
        return true
    }
    
    private fun getMergeView(view: View): View {
        val rootView = layoutInflater.inflate(R.layout.title_bar, null, false)
        leftBtnTv = rootView.findViewById(R.id.tv_left_btn)
        rightBtnTv = rootView.findViewById(R.id.tv_right_btn)
        midTitleTv = rootView.findViewById(R.id.tv_mid_title)
        val baseContainerRl = rootView.findViewById(R.id.rl_base_container) as RelativeLayout
        leftBtnClickListener()
        midTitleTv!!.text = setTitle()
        baseContainerRl.addView(view)
        return rootView
    }
    
    
    /**
     * 左边按钮点击事件
     */
    private fun leftBtnClickListener() {
        leftBtnTv!!.setOnClickListener { v -> thisActivity!!.finish() }
    }
    
  open protected fun setTitle(): String {
        return ""
    }
    
    override fun setTitle(title: CharSequence) {
        if (midTitleTv != null) {
            midTitleTv!!.text = title
        }
    }
    
    /**
     * 左边按钮是否显示
     */
    override fun setVisible(visible: Boolean) {
        if (null != leftBtnTv) {
            if (visible) {
                leftBtnTv!!.visibility = View.VISIBLE
            } else {
                leftBtnTv!!.visibility = View.INVISIBLE
            }
        }
    }
    
    /**
     * 右侧按钮是否显示
     */
    fun setRightTextViewVisible(visible: Boolean, txt: String) {
        if (null != rightBtnTv) {
            if (visible) {
                rightBtnTv!!.visibility = View.VISIBLE
                rightBtnTv!!.text = txt
            } else {
                rightBtnTv!!.visibility = View.INVISIBLE
            }
        }
    }
    
    fun gotoActivity(clazz: Class<*>) {
        thisActivity!!.startActivity(Intent(thisActivity, clazz))
    }
    
    fun gotoActivity(clazz: Class<*>, bundle: Bundle, isFinish: Boolean) {
        val intent = Intent(this, clazz)
        intent.putExtras(bundle)
        thisActivity!!.startActivity(intent)
        if (isFinish) {
            thisActivity!!.finish()
        }
    }
    
    open protected fun setImmersive() {
        StatusBarUtils.setColor(thisActivity!!,ContextCompat.getColor(thisActivity!!,R.color.transparent))
    }
    
    override fun onResume() {
        super.onResume()
        thisActivity = this
    }
    
}
