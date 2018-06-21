package www.mp5a5.com.kotlinmvp.customview

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import www.mp5a5.com.kotlinmvp.R

/**
 * @describe
 * @author ：king9999 on 2018/6/20 14：54
 * @email：wwb199055@enn.cn
 */
class CustomProgressDialog : Dialog {
    
    private var mDialogView: View? = null
    private var cancelTouchOutside: Boolean = false
    private var animationDrawable: AnimationDrawable? = null
    
    constructor(builder: Builder) : super(builder.context) {
        mDialogView = builder.mDialogView
        cancelTouchOutside = builder.cancelTouchOutside
    }
    
    private constructor(builder: Builder, themeResId: Int) : super(builder.context, themeResId) {
        mDialogView = builder.mDialogView
        cancelTouchOutside = builder.cancelTouchOutside
    }
    
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(mDialogView!!)
        setCanceledOnTouchOutside(cancelTouchOutside)
    }
    
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (mDialogView == null) {
            return
        }
        //添加控件  执行帧动画
        val imageView = mDialogView!!.findViewById<ImageView>(R.id.iv_loading_img)
        animationDrawable = imageView.background as AnimationDrawable
        animationDrawable!!.start()
    }
    
    override fun onStop() {
        super.onStop()
        animationDrawable!!.stop()
    }
    
    
     class Builder(internal var context: Context) {
        internal var resStyle = -1
        internal val mDialogView: View
        internal var cancelTouchOutside: Boolean = false
        
        init {
            mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null)
        }
        
        /**
         * 设置主题
         *
         * @param resStyle style id
         * @return CustomProgressDialog.Builder
         */
        fun setTheme(resStyle: Int): Builder {
            this.resStyle = resStyle
            return this
        }
        
        fun setMessage(message: String): Builder {
            val tvMessage = mDialogView.findViewById<TextView>(R.id.tv_loading_msg)
            if (tvMessage != null) {
                tvMessage.text = message
            }
            return this
        }
        
        /**
         * 设置点击dialog外部是否取消dialog
         *
         * @param val 点击外部是否取消dialog
         */
        fun cancelTouchOutside(`val`: Boolean): Builder {
            cancelTouchOutside = `val`
            return this
        }
        
        fun build(): CustomProgressDialog {
            return if (resStyle != -1) {
                CustomProgressDialog(this, resStyle)
            } else {
                CustomProgressDialog(this)
            }
        }
    }
}