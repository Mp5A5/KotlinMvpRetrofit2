package www.mp5a5.com.kotlinmvp.util

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONException
import org.json.JSONObject


/**
 * ClassName：SpUtil
 *
 *
 * Fuction：SharedPreferences工具
 */
class SpUtils {
    
    companion object {
        
        private val SP_XML = "info_cache"
        
        val sharedPreferences: SharedPreferences
            get() = AppContextUtils.getContext().getSharedPreferences(SP_XML,
                    Context.MODE_PRIVATE)
        
        fun getString(key: String): String {
            return sharedPreferences.getString(key, "")
        }
        
        fun setString(key: String, value: String) {
            sharedPreferences.edit().putString(key, value).apply()
        }
        
        fun getBoolean(key: String): Boolean {
            return sharedPreferences.getBoolean(key, false)
        }
        
        fun setBoolean(key: String, value: Boolean) {
            sharedPreferences.edit().putBoolean(key, value).apply()
        }
        
        fun getInt(key: String): Int {
            return sharedPreferences.getInt(key, -1)
        }
        
        fun getInt(key: String, value: Int): Int {
            return sharedPreferences.getInt(key, value)
        }
        
        fun setInt(key: String, value: Int) {
            sharedPreferences.edit().putInt(key, value).apply()
        }
        
        fun getLong(key: String): Long {
            return sharedPreferences.getLong(key, -1)
        }
        
        fun setLong(key: String, value: Long) {
            sharedPreferences.edit().putLong(key, value).apply()
        }
        
        fun remove(key: String) {
            sharedPreferences.edit().remove(key).apply()
        }
        
        fun removeAll() {
            sharedPreferences.edit().clear().apply()
        }
        
        
        /**
         * 保存map集合
         */
        fun setMap(key: String, mMap: Map<String, String>) {
            val `object` = JSONObject()
            val iterator = mMap.entries.iterator()
            while (iterator.hasNext()) {
                val entry = iterator.next()
                val key_ = entry.key
                val value = entry.value
                try {
                    `object`.put(key_, value)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                
            }
            setString(key, `object`.toString())
        }
        
        fun getMap(key: String): Map<String, String> {
            val result = getString(key)
            val hashMap = HashMap<String, String>()
            val split = result.replace("{", "").replace("}", "").split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (i in split.indices) {
                val string = split[i]
                val split2 = string.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                hashMap[split2[0]] = split2[1]
            }
            
            return hashMap
            
        }
        
        fun setList(key: String, list: List<String>) {
            val `object` = JSONObject()
            for (i in list.indices) {
                try {
                    `object`.put(i.toString() + "", list[i])
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                
            }
            setString(key, `object`.toString())
        }
        
        fun getList(key: String): List<String> {
            val arrayList = ArrayList<String>()
            try {
                val result = getString(key)
                val split = result.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val jsonObject = JSONObject(result)
                for (i in split.indices) {
                    val string = jsonObject.getString("" + i)
                    arrayList.add(string)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            
            return arrayList
            
        }
    }
    
}
