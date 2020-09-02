package com.bksx.mobile.mvpdemo;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.bksx.mobile.common.base.BaseActivity;
import com.bksx.mobile.mvpdemo.epdemic_sutiation.EpidemicSituationFragment;
import com.bksx.mobile.mvpdemo.homepage.HomepageFragment;
import com.bksx.mobile.mvpdemo.mine.MineFragment;
import com.bksx.mobile.mvpdemo.people_management.PeopleManagementFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.layFrame)
    FrameLayout layFrame;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    private long exitTime = 0;
    private static final String TAG = "MainActivity";
    private static final String POSITION = "position";
    private static final String SELECT_ITEM = "bottomNavigationSelectItem";
    private static final int FRAGMENT_HOMEPAGE = 0;
    private static final int FRAGMENT_PEOPLE_MANAGEMENT = 1;
    private static final int FRAGMENT_EPIDEMIC_SITUATION = 2;
    private static final int FRAGMENT_MINE = 3;
    private HomepageFragment mHomepageFragment;
    private PeopleManagementFragment mPeopleManageFragment;
    private EpidemicSituationFragment mEpidemicSituationFragment;
    private MineFragment mMineFragment;

    @Override
    protected void initEvent() {

    }

    @Override
    protected int attachLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            mHomepageFragment = (HomepageFragment) getSupportFragmentManager().findFragmentByTag(HomepageFragment.class.getName());
            mPeopleManageFragment = (PeopleManagementFragment) getSupportFragmentManager().findFragmentByTag(PeopleManagementFragment.class.getName());
            mEpidemicSituationFragment = (EpidemicSituationFragment) getSupportFragmentManager().findFragmentByTag(EpidemicSituationFragment.class.getName());
            mMineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag(MineFragment.class.getName());
            // 恢复 recreate 前的位置
            showFragment(savedInstanceState.getInt(POSITION));
            bottomNavigationBar.selectTab(savedInstanceState.getInt(SELECT_ITEM));
        } else {
            showFragment(FRAGMENT_HOMEPAGE);
        }
    }

    private void showFragment(int index){

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        long currentTime = System.currentTimeMillis();
        if ((currentTime - exitTime) < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "再点一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = currentTime;
        }
    }
}
