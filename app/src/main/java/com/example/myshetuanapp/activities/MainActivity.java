package com.example.myshetuanapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myshetuanapp.R;
import com.example.myshetuanapp.adapter.MyFragmentPagerAdapter;
import com.example.myshetuanapp.fragments.PersonalFragment;
import com.example.myshetuanapp.fragments.SigninFragment;
import com.example.myshetuanapp.fragments.TaskListFragment;

import java.util.ArrayList;

/**
 *  作者：姚宇辉
 *  功能：主界面
 *  TODO：2022年3月11日12点47分
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ViewPager2 viewPager2;
    private LinearLayout sign,task,person;
    private ImageView sign1,task1,person1,current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPager();
        initTableView();
    }

    private void initTableView() {
        sign = findViewById(R.id.id_sign);
        sign.setOnClickListener(this);
        task = findViewById(R.id.id_task);
        task.setOnClickListener(this);
        person = findViewById(R.id.id_person);
        person.setOnClickListener(this);
        sign1 = findViewById(R.id.iv_sign_in);
        task1 = findViewById(R.id.iv_task);
        person1 = findViewById(R.id.iv_person);

        sign1.setSelected(true);

        current = sign1;
    }

    private void initPager() {
        viewPager2 = findViewById(R.id.id_viewpager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(SigninFragment.newInstance("签到"));
        fragments.add(TaskListFragment.newInstance("任务"));
        fragments.add(PersonalFragment.newInstance("个人"));
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager2.setAdapter(pagerAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changeTab(int position) {
        current.setSelected(false);
        switch (position){
            case R.id.id_sign:
                viewPager2.setCurrentItem(0);
            case 0:
                sign1.setSelected(true);
                current = sign1;
                break;
            case R.id.id_task:
                viewPager2.setCurrentItem(1);
            case 1:
                task1.setSelected(true);
                current = task1;
                break;
            case R.id.id_person:
                viewPager2.setCurrentItem(2);
            case 2:
                person1.setSelected(true);
                current = person1;
                break;

        }
    }


    @Override
    public void onClick(View v) {
        changeTab(v.getId());
    }

}
