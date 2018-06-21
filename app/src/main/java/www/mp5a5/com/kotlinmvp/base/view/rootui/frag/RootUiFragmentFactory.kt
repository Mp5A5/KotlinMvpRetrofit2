package www.mp5a5.com.kotlinmvp.base.view.rootui.frag

import www.mp5a5.com.kotlinmvp.base.view.frag.BaseFragment
import www.mp5a5.com.kotlinmvp.base.view.frag.FragmentFactory

/**
 * @describe
 * @author ：king9999 on 2018/6/21 20：10
 * @email：wwb199055@enn.cn
 */
object RootUiFragmentFactory : FragmentFactory() {
    
    private const val NEWS_POSITION = 0
    private const val LIB_POSITION = 1
    private const val DISCOVERY_POSITION = 2
    private const val MORE_POSITION = 3
    private const val MAX_SIZE = 4
    
    override fun createFragment(position: Int): BaseFragment {
        var fragment = fragSparseArray.get(position)
        if (fragment != null) {
            return fragment
        } else {
            when (position) {
                NEWS_POSITION -> fragment = NewsFragment.newInstance()
                LIB_POSITION -> fragment = LibFragment.newInstance()
                DISCOVERY_POSITION -> fragment = DiscoveryFragment.newInstance()
                MORE_POSITION -> fragment = MoreFragment.newInstance()
            }
            fragSparseArray.put(position, fragment)
        }
        return fragment
    }
    
    override fun getFragmentSize(): Int {
        return MAX_SIZE
    }
    
}