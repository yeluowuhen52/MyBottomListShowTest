package com.marsjiang.mybottomlistshowtest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.marsjiang.mybottomlistshowtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int beforePosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initViews();
        initDatas();
    }

    /**
     * 初始化布局
     */
    private void initViews() {
        int count = binding.mainBottomeSwitcherContainer.getChildCount();
        for (int i = 0; i < count; i++) {
            FrameLayout childAt = (FrameLayout) binding.mainBottomeSwitcherContainer.getChildAt(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = binding.mainBottomeSwitcherContainer.indexOfChild(view);
                    changeUI(index);
                    binding.tvShow.setText("当前选中" + index);
                }


            });
        }
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        changeUI(2);
    }


    //通过点击的时候来改变底部导航栏的UI
    private void changeUI(int index) {
//        ToastUtil.showText(MainActivity.this, index + "");
//        if (index == 2) {
//            binding.civVideo.setImageResource(R.mipmap.video_on);
//        } else {
//            binding.civVideo.setImageResource(R.mipmap.video_off);
//        }

        if (beforePosition != -1) {
            if (beforePosition == index) {
                Toast.makeText(this, "你又选中了" + index, Toast.LENGTH_LONG).show();
            }
        }
        int childCount = binding.mainBottomeSwitcherContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == index) {
                setEnable(binding.mainBottomeSwitcherContainer.getChildAt(i), false);
            } else {
                setEnable(binding.mainBottomeSwitcherContainer.getChildAt(i), true);
            }
        }
        beforePosition = index;
    }


    /**
     * 将每个Item中的所用控件状态一同改变
     * 由于我们处理一个通用的代码，那么Item可能会有很多层，所以我们需要使用递归
     *
     * @param item
     * @param b
     */
    private void setEnable(View item, boolean b) {
        if (!(item instanceof FrameLayout)) {
            item.setEnabled(b);
        } else {
            item.setEnabled(true);
        }
        if (item instanceof ViewGroup) {
            int childCount = ((ViewGroup) item).getChildCount();
            for (int i = 0; i < childCount; i++) {
                setEnable(((ViewGroup) item).getChildAt(i), b);
            }
        }
    }
}
