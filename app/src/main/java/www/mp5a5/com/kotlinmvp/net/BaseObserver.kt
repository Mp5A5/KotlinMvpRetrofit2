package www.mp5a5.com.kotlinmvp.net

import android.app.Activity
import android.text.TextUtils
import android.widget.Toast
import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import www.mp5a5.com.kotlinmvp.R
import www.mp5a5.com.kotlinmvp.util.CustomProgressDialogUtils
import www.mp5a5.com.kotlinmvp.util.ToastUtils
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @describe
 * @author ：king9999 on 2018/6/20 14：38
 * @email：wwb199055@enn.cn
 */

abstract class BaseObserver<T : BaseResponseEntity>() : Observer<T> {
    
    private var mContext: Activity? = null
    private var mShowLoading = false
    private var progressDialogUtils: CustomProgressDialogUtils? = null
    
    /**
     * 如果传入上下文，那么表示您将开启自定义的进度条
     *
     * @param context 上下文
     */
    
    @Suppress("unused")
    constructor(context: Activity) : this() {
        this.mContext = context
        this.mShowLoading = true
    }
    
    
    override fun onSubscribe(d: Disposable) {
        onRequestStart()
    }
    
    override fun onNext(response: T) {
        onRequestEnd()
        if (response.isSuccess) {
            try {
                onSuccess(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            try {
                onFailing(response);
            } catch (e: Exception) {
                e.printStackTrace();
            }
        }
    }
    
    override fun onError(e: Throwable) {
        onRequestEnd()
        if (e is retrofit2.HttpException) {
            //HTTP错误
            onException(ExceptionReason.BAD_NETWORK)
        } else if (e is ConnectException || e is UnknownHostException) {
            //连接错误
            onException(ExceptionReason.CONNECT_ERROR)
        } else if (e is InterruptedIOException) {
            //连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT)
        } else if (e is JsonParseException || e is JSONException || e is ParseException) {
            //解析错误
            onException(ExceptionReason.PARSE_ERROR)
        } else {
            //其他错误
            onException(ExceptionReason.UNKNOWN_ERROR)
        }
    }
    
    override fun onComplete() {
        onRequestEnd()
    }
    
    /**
     * 网络请求成功并返回正确值
     *
     * @param response 返回值
     */
    abstract fun onSuccess(response: T)
    
    /**
     * 网络请求成功但是返回值是错误的
     *
     * @param response 返回值
     */
    open fun onFailing(response: T) {
        val message = response.getMsg;
        if (TextUtils.isEmpty(message)) {
            ToastUtils.show(R.string.response_return_error)
        } else {
            ToastUtils.show(message)
        }
    }
    
    fun onException(reason: ExceptionReason) {
        when (reason) {
            BaseObserver.ExceptionReason.CONNECT_ERROR -> ToastUtils.show(R.string.connect_error, Toast.LENGTH_SHORT)
            
            BaseObserver.ExceptionReason.CONNECT_TIMEOUT -> ToastUtils.show(R.string.connect_timeout, Toast.LENGTH_SHORT)
            
            BaseObserver.ExceptionReason.BAD_NETWORK -> ToastUtils.show(R.string.bad_network, Toast.LENGTH_SHORT)
            
            BaseObserver.ExceptionReason.PARSE_ERROR -> ToastUtils.show(R.string.parse_error, Toast.LENGTH_SHORT)
            
            BaseObserver.ExceptionReason.UNKNOWN_ERROR -> ToastUtils.show(R.string.unknown_error, Toast.LENGTH_SHORT)
            else -> ToastUtils.show(R.string.unknown_error, Toast.LENGTH_SHORT)
        }
    }
    
    /**
     * 网络请求失败原因
     */
    enum class ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR
    }
    
    /**
     * 网络请求开始
     */
    private fun onRequestStart() {
        if (mShowLoading) {
            showProgressDialog()
        }
    }
    
    /**
     * 网络请求结束
     */
    private fun onRequestEnd() {
        closeProgressDialog()
    }
    
    /**
     * 开启Dialog
     */
    private fun showProgressDialog() {
        progressDialogUtils = CustomProgressDialogUtils()
        progressDialogUtils!!.showProgress(mContext!!, "Loading...")
    }
    
    /**
     * 关闭Dialog
     */
    private fun closeProgressDialog() {
        if (progressDialogUtils != null) {
            progressDialogUtils!!.dismissProgress()
        }
    }
}