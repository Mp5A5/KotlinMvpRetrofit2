package www.mp5a5.com.kotlinmvp.util

import android.util.Log


/**
 * Log日志打印操作
 */
class LogUtils {
    
    companion object {
        
        private val DEBUG = true
        /**
         * 获取当前类名
         */
        private // 这里的数组的index，即2，是根据你工具类的层级取的值，可根据需求改变
        val className: String
            get() {
                val thisMethodStack = Exception().stackTrace[2]
                var result = thisMethodStack.className
                val lastIndex = result.lastIndexOf(".")
                result = result.substring(lastIndex + 1, result.length)
                return result
            }
        
        
        fun w(logString: String) {
            if (DEBUG) {
                Log.w(className, logString)
            }
        }
        
        /**
         * debug log
         */
        fun d(tag: String, msg: String) {
            if (DEBUG) {
                Log.d(tag, msg)
            }
        }
        
        /**
         * error log
         */
        fun e(tag: String, msg: String) {
            if (DEBUG) {
                Log.e(tag, msg)
            }
        }
        
        /**
         * debug log
         */
        fun d(msg: String) {
            if (DEBUG) {
                Log.d(className, msg)
            }
        }
        
        /**
         * debug log
         */
        fun i(msg: String) {
            if (DEBUG) {
                Log.i(className, msg)
            }
        }
        
        /**
         * error log
         */
        fun e(msg: String) {
            if (DEBUG) {
                Log.e(className, msg)
            }
        }
        
        fun i(tag: String, logString: String) {
            if (DEBUG) {
                Log.i(tag, logString)
            }
        }
        
        
        fun w(tag: String, logString: String) {
            if (DEBUG) {
                Log.w(tag, logString)
            }
        }
    }
    
    
}
