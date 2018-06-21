package www.mp5a5.com.kotlinmvp.base.view.frag


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import www.mp5a5.com.kotlinmvp.R
import java.lang.ref.WeakReference


abstract class BaseFragment : RxFragment() {
    private var mContext: RxAppCompatActivity? = null
    private var iView: View? = null
    private var rightBtnTv: TextView? = null
    private var midTitleTv: TextView? = null
    private var leftBtnTv: TextView? = null
    private var titleRl: RelativeLayout? = null
    private var v: WeakReference<View>? = null
    
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
        mContext = activity as RxAppCompatActivity?
        initArgsData()
    }
    
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (v == null || v!!.get() == null) {
            iView = inflater.inflate(initLayout(), container, false)
            v = WeakReference(iView!!)
        }
        val parent = v!!.get()!!.getParent() as? ViewGroup
        if (parent != null) {
            parent.removeView(v!!.get()!!)
        }
        return getMergerView(v!!.get()!!)
    }
    
    private fun getMergerView(v: View): View {
        val rootView = View.inflate(activity, R.layout.title_bar_frag, null)
        leftBtnTv = rootView.findViewById(R.id.tv_left_btn)
        rightBtnTv = rootView.findViewById(R.id.tv_right_btn)
        midTitleTv = rootView.findViewById(R.id.tv_mid_title)
        titleRl = rootView.findViewById(R.id.rl_title_bar_fragment)
        midTitleTv!!.text = setTitle()
        val baseContainerRl = rootView.findViewById(R.id.rl_base_container) as RelativeLayout
        leftBtnClickListener()
        if (needHeader()) {
            titleRl!!.visibility = View.VISIBLE
        } else {
            titleRl!!.visibility = View.GONE
        }
        baseContainerRl.addView(v)
        return rootView
    }
    
    
    open fun setTitle(): String {
        return ""
    }
    
    open protected fun setTitle(title: String) {
        if (midTitleTv != null) {
            midTitleTv!!.text = title
        }
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initView()
        initAdapter()
        initNet()
        initListener()
        initRefresh()
    }
    
    
    override fun onResume() {
        super.onResume()
        mContext = activity as RxAppCompatActivity?
        initNetWork()
    }
    
    open protected fun needHeader(): Boolean {
        return true
    }
    
    protected abstract fun initLayout(): Int
    
    open protected fun initArgsData() {
    
    }
    
    open protected fun init() {}
    
    open protected fun initView() {}
    
    open protected fun initNet() {}
    
    open protected fun initAdapter() {}
    
    open protected fun initListener() {}
    
    open protected fun initRefresh() {}
    
    open protected fun initNetWork() {}
    
    
    private fun leftBtnClickListener() {
        leftBtnTv!!.setOnClickListener { mContext!!.finish() }
    }
    
    fun setLeftVisible(visible: Boolean) {
        if (null != leftBtnTv) {
            if (visible) {
                leftBtnTv!!.visibility = View.VISIBLE
            } else {
                leftBtnTv!!.visibility = View.INVISIBLE
            }
        }
    }
    
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
    
    
    val thisActivity: AppCompatActivity?
        get() = mContext
    
    val thisContext: Context?
        get() = mContext
    
    fun gotoActivity(clazz: Class<*>) {
        mContext!!.startActivity(Intent(mContext, clazz))
    }
    
}
