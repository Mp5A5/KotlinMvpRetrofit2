package www.mp5a5.com.kotlinmvp.util

import android.annotation.SuppressLint
import android.support.annotation.NonNull
import android.widget.Toast


/**
 * @author ：王文彬 on 2018/5/22 14：35
 * @describe：ToastUtils工具类
 * @email：wwb199055@126.com
 */
@SuppressLint("StaticFieldLeak")
class ToastUtils {
    
    companion object {
        
        @SuppressLint("StaticFieldLeak")
        private val context = AppContextUtils.getContext()
        
        private var toast: Toast? = null
        /**
         * Toast内容，支持String、资源ID（int）、CharSequence
         *
         * @param msg String、CharSequence
         */
        fun show(@NonNull msg: String) {
            showView(msg, Toast.LENGTH_SHORT)
        }
        
        /**
         * 对toast的封装。线程安全，可以在非UI线程调用。
         * Toast内容，支持String、资源ID（int）、CharSequence
         *
         * @param resId int、CharSequence
         */
        fun show(resId: Int) {
            showView(context.getString(resId), Toast.LENGTH_SHORT)
        }
        
        /**
         * 对toast的简易封装。线程安全，可以在非UI线程调用。
         * Toast内容，支持String、资源ID（int）、CharSequence
         *
         * @param msg      String、int、CharSequence
         * @param duration Toast时间。eg:Toast.LENGTH_SHORT
         */
        fun show(@NonNull msg: String, duration: Int) {
            showView(msg, duration)
        }
        
        /**
         * 对toast的简易封装。线程安全，可以在非UI线程调用。
         * Toast内容，支持String、资源ID（int）、CharSequence
         *
         * @param resId    String、int、CharSequence
         * @param duration Toast时间。eg:Toast.LENGTH_SHORT
         */
        fun show(resId: Int, duration: Int) {
            showView(context.getString(resId), duration)
        }
        
        
        @SuppressLint("ShowToast")
        private fun showView(text: CharSequence, duration: Int) {
            if (toast == null) {
                toast = Toast.makeText(context, text, duration)
            } else {
                toast!!.setText(text)
            }
            toast!!.show()
        }
        
        
        /**
         * 取消Toast
         */
        fun cancel() {
            if (toast != null) {
                toast!!.cancel()
            }
        }
    }
    
}
