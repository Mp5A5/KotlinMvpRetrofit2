package www.mp5a5.com.kotlinmvp.base.view.rootui.act

import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import www.mp5a5.com.kotlinmvp.R
import www.mp5a5.com.kotlinmvp.base.view.act.BaseActivity
import www.mp5a5.com.kotlinmvp.base.view.rootui.adapter.ViewPagerAdapter
import www.mp5a5.com.kotlinmvp.customview.BottomNavigationViewHelper

/**
 * @describe
 * @author ：king9999 on 2018/6/21 18：26
 * @email：wwb199055@enn.cn
 */
class HomeActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    
    private var item: MenuItem? = null
    
    override fun initLayoutView(): View {
        return View.inflate(thisActivity, R.layout.activity_home, null)
    }
    
    override fun needHeader(): Boolean {
        return false
    }
    
    override fun initView() {
        super.initView()
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigation)
        setupViewPager(mViewpager)
        mBottomNavigation.setOnNavigationItemSelectedListener(this)
        mViewpager.addOnPageChangeListener(this)
        
        //禁止ViewPager滑动
        //mViewpager.setOnTouchListener { v, event -> false }
    }
    
    private fun setupViewPager(mViewpager: ViewPager?) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        mViewpager!!.offscreenPageLimit = 4
        mViewpager.adapter = adapter
    }
    
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_news -> mViewpager.setCurrentItem(0)
            R.id.item_lib -> mViewpager.setCurrentItem(1)
            R.id.item_find -> mViewpager.setCurrentItem(2)
            R.id.item_more -> mViewpager.setCurrentItem(3)
        }
        return false
    }
    
    override fun onPageScrollStateChanged(state: Int) {
    }
    
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }
    
    override fun onPageSelected(position: Int) {
        if (item != null) {
            item!!.setChecked(false)
        } else {
            mBottomNavigation.menu.getItem(0).setChecked(false)
        }
        item = mBottomNavigation.menu.getItem(position)
        item!!.setChecked(false)
    }
    
    
}