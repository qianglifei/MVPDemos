package com.bksx.mobile.mvpdemo;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bksx.mobile.common.base.BaseActivity;
import com.bksx.mobile.mvpdemo.epdemic_sutiation.EpidemicSituationFragment;
import com.bksx.mobile.mvpdemo.homepage.HomepageFragment;
import com.bksx.mobile.mvpdemo.mine.MineFragment;
import com.bksx.mobile.mvpdemo.people_management.PeopleManagementFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements  BottomNavigationBar.OnTabSelectedListener{

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
    private int position;

    @Override
    protected void initEvent() {
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        //配置tab与之对应的fragment
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.icon_sy_pressed, "首页").setInactiveIconResource(R.mipmap.icon_sy))
                .addItem(new BottomNavigationItem(R.mipmap.icon_rygl_pressed, "人员管理").setInactiveIconResource(R.mipmap.icon_rygl))
                .addItem(new BottomNavigationItem(R.mipmap.icon_yqts_pressed, "疫情态势").setInactiveIconResource(R.mipmap.icon_yqts))
                .addItem(new BottomNavigationItem(R.mipmap.icon_wd_pressed, "我的").setInactiveIconResource(R.mipmap.icon_wd));

        bottomNavigationBar.setActiveColor("#333333");
        bottomNavigationBar.setInActiveColor("#999999");
        //设置Bottom
        bottomNavigationBar.setFirstSelectedPosition(0).initialise();
        //设置默认fragment
        onTabSelected(0);

        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    protected int attachLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mHomepageFragment = (HomepageFragment) getSupportFragmentManager().findFragmentByTag(HomepageFragment.class.getName());
            mPeopleManageFragment = (PeopleManagementFragment) getSupportFragmentManager().findFragmentByTag(PeopleManagementFragment.class.getName());
            mEpidemicSituationFragment = (EpidemicSituationFragment) getSupportFragmentManager().findFragmentByTag(EpidemicSituationFragment.class.getName());
            mMineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag(MineFragment.class.getName());
            //恢复 recreate 前的位置
            showFragment(savedInstanceState.getInt(POSITION));
            bottomNavigationBar.selectTab(savedInstanceState.getInt(SELECT_ITEM));
        } else {
            showFragment(FRAGMENT_HOMEPAGE);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // recreate 时记录当前位置 (在 Manifest 已禁止 Activity 旋转,所以旋转屏幕并不会执行以下代码)
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, position);
        outState.putInt(SELECT_ITEM, bottomNavigationBar.getId());
    }

    private void showFragment(int index){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        position = index;
        switch (index) {
            case FRAGMENT_HOMEPAGE:
                //toolbar.setTitle(R.string.app_name);
                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
                if (mHomepageFragment == null) {
                    mHomepageFragment = HomepageFragment.newInstance();
                    ft.add(R.id.layFrame, mHomepageFragment, HomepageFragment.class.getName());
                } else {
                    ft.show(mHomepageFragment);
                }
                break;

            case FRAGMENT_PEOPLE_MANAGEMENT:
                //toolbar.setTitle(R.string.title_photo);
                if (mPeopleManageFragment == null) {
                    mPeopleManageFragment = PeopleManagementFragment.newInstance();
                    ft.add(R.id.layFrame, mPeopleManageFragment, PeopleManagementFragment.class.getName());
                } else {
                    ft.show(mPeopleManageFragment);
                }
                break;
            case FRAGMENT_EPIDEMIC_SITUATION:
                //toolbar.setTitle(getString(R.string.title_video));
                if (mEpidemicSituationFragment == null) {
                    mEpidemicSituationFragment = EpidemicSituationFragment.newInstance("","");
                    ft.add(R.id.layFrame, mEpidemicSituationFragment, EpidemicSituationFragment.class.getName());
                } else {
                    ft.show(mEpidemicSituationFragment);
                }
                break;

            case FRAGMENT_MINE:
                //toolbar.setTitle(getString(R.string.title_media));
                if (mMineFragment == null) {
                    mMineFragment = MineFragment.newInstance();
                    ft.add(R.id.layFrame, mMineFragment, MineFragment.class.getName());
                } else {
                    ft.show(mMineFragment);
                }
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (mHomepageFragment != null) {
            ft.hide(mHomepageFragment);
        }
        if (mPeopleManageFragment != null) {
            ft.hide(mPeopleManageFragment);
        }
        if (mEpidemicSituationFragment != null) {
            ft.hide(mEpidemicSituationFragment);
        }
        if (mMineFragment != null) {
            ft.hide(mMineFragment);
        }
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

    @Override
    public void onTabSelected(int position) {
        switch (position){
            case 0:
            case 1:
            case 2:
            case 3: {
                showFragment(position);
            }break;
            default:
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
