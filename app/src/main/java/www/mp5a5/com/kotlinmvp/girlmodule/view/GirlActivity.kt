package www.mp5a5.com.kotlinmvp.girlmodule.view

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.activity_girl.*
import www.mp5a5.com.kotlinmvp.R
import www.mp5a5.com.kotlinmvp.base.mvp.BaseMvpActivity
import www.mp5a5.com.kotlinmvp.girlmodule.entity.GirlEntity
import www.mp5a5.com.kotlinmvp.girlmodule.mvp.GirlAdapter
import www.mp5a5.com.kotlinmvp.girlmodule.mvp.GirlContract
import www.mp5a5.com.kotlinmvp.girlmodule.mvp.GirlPresenter
import www.mp5a5.com.kotlinmvp.util.ToastUtils

/**
 * @describe
 * @author ：king9999 on 2018/6/21 10：11
 * @email：wwb199055@enn.cn
 */
class GirlActivity : BaseMvpActivity<GirlPresenter>(), GirlContract.View {
    
    private var mAdapter: GirlAdapter? = null
    private val startIndex = 0
    private var endIndex = 1
    private val startType = 39
    private val endType = 40
    private val isRefresh = true
    
    
    override fun initLayoutView(): View {
        return View.inflate(thisActivity, R.layout.activity_girl, null)
    }
    
    
    override fun setTitle(): String {
        return "我的测试界面"
    }
    
    override fun initView() {
        super.initView()
        mGirlRefreshLayoutRl.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN)
        mGirlRecyclerView.setHasFixedSize(true)
        mGirlRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        (mGirlRecyclerView.layoutManager as StaggeredGridLayoutManager).gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallBack())
        itemTouchHelper.attachToRecyclerView(mGirlRecyclerView)
        
    }
    
    override fun initAdapter() {
        super.initAdapter()
        mAdapter = GirlAdapter()
        mGirlRecyclerView.adapter = mAdapter
        mAdapter!!.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        initListener()
    }
    
    override fun initNet() {
        super.initNet()
        presenter.requestNetworkData(thisActivity!!, startType, startIndex, isRefresh)
    }
    
    private fun initListener() {
        mGirlRefreshLayoutRl.setOnRefreshListener {
            mGirlRefreshLayoutRl.isRefreshing = true
            presenter.requestNetworkData(thisActivity!!, startType, startIndex, isRefresh)
        }
        mAdapter!!.setOnLoadMoreListener({
            presenter.requestNetworkData(thisActivity!!, startType, endIndex++, false)
        }, mGirlRecyclerView!!)
        
    }
    
    override fun getBasePresenter(): GirlPresenter {
        return GirlPresenter()
    }
    
    override fun addData(weaponEntityList: MutableList<GirlEntity>) {
        mAdapter!!.addData(weaponEntityList)
    }
    
    override fun setData(weaponEntityList: MutableList<GirlEntity>) {
        weaponEntityList.shuffle()
        mAdapter!!.setNewData(weaponEntityList)
    }
    
    override fun showMsg(msg: String) {
        ToastUtils.show(msg)
    }
    
    override fun isRefresh(refresh: Boolean) {
        mGirlRefreshLayoutRl.isRefreshing = refresh
    }
    
    override fun loadMoreComplete() {
        mAdapter!!.loadMoreComplete()
    }
    
    internal inner class ItemTouchHelperCallBack : ItemTouchHelper.Callback() {
        
        override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            return makeMovementFlags(dragFlags, swipeFlags)
        }
        
        override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
            mAdapter!!.onItemMoving(viewHolder!!.adapterPosition, target!!.adapterPosition)
            return false
        }
        
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
            mAdapter!!.onItemDismissing(viewHolder!!.adapterPosition)
        }
        
    }
    
}