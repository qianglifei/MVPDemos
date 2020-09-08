package com.bksx.mobile.common.base;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bksx.mobile.common.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * @author :qlf
 */
public abstract class BaseListFragment<T extends IBasePresenter> extends LazyLoadFragment<T> implements IBaseListView<T>, OnRefreshListener , OnLoadmoreListener {

    public static final String TAG = "BaseListFragment";
    protected SmartRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;
    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_list;
    }
}
