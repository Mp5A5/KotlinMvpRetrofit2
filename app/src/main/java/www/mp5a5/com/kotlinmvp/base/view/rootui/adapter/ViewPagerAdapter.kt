package www.mp5a5.com.kotlinmvp.base.view.rootui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import www.mp5a5.com.kotlinmvp.base.view.rootui.frag.RootUiFragmentFactory

/**
 * @author ：king9999 on 2018/6/19 20：07
 * @describe
 * @email：wwb199055@enn.cn
 */
class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    
    
    override fun getItem(position: Int): Fragment {
        return RootUiFragmentFactory.createFragment(position)
    }
    
    override fun getCount(): Int {
        return RootUiFragmentFactory.getFragmentSize()
    }
    
    override fun getPageTitle(position: Int): CharSequence? {
        val fragment = RootUiFragmentFactory.createFragment(position)
        return fragment.setTitle()
    }
    
    
}
