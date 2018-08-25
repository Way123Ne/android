package activity.wayne.com.slidefragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wayne.fragment.FouthFragment;
import com.wayne.fragment.OneFragment;
import com.wayne.fragment.ThreeFragment;
import com.wayne.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView title, item_weixin, item_tongxunlu, item_faxian, item_me;
    private ViewPager vp;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FouthFragment fouthFragmen;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

    String[] titles = new String[]{"微信", "通讯录", "发现", "我"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除工具栏
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initViews();

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        vp.setOffscreenPageLimit(4);//ViewPager的缓存为4帧
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);//初始设置ViewPager选中第一帧
        item_weixin.setTextColor(Color.parseColor("#66CDAA"));

        //ViewPager的监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*此方法在页面被选中时调用*/
                title.setText(titles[position]);
                changeTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
                arg0 ==1的时辰默示正在滑动，
                arg0==2的时辰默示滑动完毕了，
                arg0==0的时辰默示什么都没做。*/
            }
        });
    }

    /**
     * 初始化布局View
     */
    private void initViews() {
        title = (TextView) findViewById(R.id.title);
        item_weixin = (TextView) findViewById(R.id.item_weixin);
        item_tongxunlu = (TextView) findViewById(R.id.item_tongxunlu);
        item_faxian = (TextView) findViewById(R.id.item_faxian);
        item_me = (TextView) findViewById(R.id.item_me);

        item_weixin.setOnClickListener(this);
        item_tongxunlu.setOnClickListener(this);
        item_faxian.setOnClickListener(this);
        item_me.setOnClickListener(this);

        vp = (ViewPager) findViewById(R.id.mainViewPager);
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fouthFragmen = new FouthFragment();
        //给FragmentList添加数据
        mFragmentList.add(oneFragment);
        mFragmentList.add(twoFragment);
        mFragmentList.add(threeFragment);
        mFragmentList.add(fouthFragmen);
    }

    /**
     * 点击底部Text 动态修改ViewPager的内容
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_weixin:
                vp.setCurrentItem(0, true);
                break;
            case R.id.item_tongxunlu:
                vp.setCurrentItem(1, true);
                break;
            case R.id.item_faxian:
                vp.setCurrentItem(2, true);
                break;
            case R.id.item_me:
                vp.setCurrentItem(3, true);
                break;
        }
    }


    public class FragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    /*
     *由ViewPager的滑动修改底部导航Text的颜色
     */
    private void changeTextColor(int position) {
        if (position == 0) {
            item_weixin.setTextColor(Color.parseColor("#66CDAA"));
            item_tongxunlu.setTextColor(Color.parseColor("#000000"));
            item_faxian.setTextColor(Color.parseColor("#000000"));
            item_me.setTextColor(Color.parseColor("#000000"));
        } else if (position == 1) {
            item_tongxunlu.setTextColor(Color.parseColor("#66CDAA"));
            item_weixin.setTextColor(Color.parseColor("#000000"));
            item_faxian.setTextColor(Color.parseColor("#000000"));
            item_me.setTextColor(Color.parseColor("#000000"));
        } else if (position == 2) {
            item_faxian.setTextColor(Color.parseColor("#66CDAA"));
            item_weixin.setTextColor(Color.parseColor("#000000"));
            item_tongxunlu.setTextColor(Color.parseColor("#000000"));
            item_me.setTextColor(Color.parseColor("#000000"));
        } else if (position == 3) {
            item_me.setTextColor(Color.parseColor("#66CDAA"));
            item_weixin.setTextColor(Color.parseColor("#000000"));
            item_tongxunlu.setTextColor(Color.parseColor("#000000"));
            item_faxian.setTextColor(Color.parseColor("#000000"));
        }
    }
}