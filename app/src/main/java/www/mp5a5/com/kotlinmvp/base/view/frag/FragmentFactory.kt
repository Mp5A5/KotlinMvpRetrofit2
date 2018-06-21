package www.mp5a5.com.kotlinmvp.base.view.frag

import android.util.SparseArray

/**
 * @describe Fragment工厂类
 * @author ：king9999 on 2018/6/21 20：06
 * @email：wwb199055@enn.cn
 */
abstract class FragmentFactory {
    
    abstract fun createFragment(position: Int): BaseFragment
    
    abstract fun getFragmentSize(): Int
    
    protected var fragSparseArray = SparseArray<BaseFragment>()
    
}