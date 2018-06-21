package www.mp5a5.com.kotlinmvp.util

import android.content.Context
import www.mp5a5.com.kotlinmvp.R
import www.mp5a5.com.kotlinmvp.customview.CustomProgressDialog

/**
 * @describe
 * @author ：king9999 on 2018/6/20 15：01
 * @email：wwb199055@enn.cn
 */
class CustomProgressDialogUtils {
    
    private var mProgressDialog: CustomProgressDialog? = null
    
    /**
     * 显示ProgressDialog
     */
    fun showProgress(context: Context, msg: String) {
        if (mProgressDialog == null) {
            mProgressDialog = CustomProgressDialog.Builder(context)
                    .setTheme(R.style.ProgressDialogStyle)
                    .setMessage(msg)
                    .build()
        }
        if (!mProgressDialog!!.isShowing) {
            mProgressDialog!!.show()
        }
    }
    
    /**
     * 显示ProgressDialog
     */
    fun showProgress(context: Context) {
        if (mProgressDialog == null) {
            mProgressDialog = CustomProgressDialog.Builder(context)
                    .setTheme(R.style.ProgressDialogStyle)
                    .build()
        }
        if (!mProgressDialog!!.isShowing) {
            mProgressDialog!!.show()
        }
    }
    
    /**
     * 取消ProgressDialog
     */
    fun dismissProgress() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
            mProgressDialog!!.cancel()
        }
    }
}