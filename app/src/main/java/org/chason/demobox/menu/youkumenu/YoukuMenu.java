package org.chason.demobox.menu.youkumenu;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

import org.chason.demobox.R;

/**
 * 创建一个自定义的组合菜单控件，容器(viewgroup)
 */
public class YoukuMenu extends RelativeLayout {

    private RelativeLayout mRlYoukuMenuLeve1; //内层菜单
    private RelativeLayout mRlYoukuMenuLeve2; //中层菜单
    private RelativeLayout mRlYoukuMenuLeve3; //外层菜单

    //判断动画是否正在播放，true播放，false停止
    private boolean isAnimation;

    //判断菜单状态是显示还是隐藏，true显示，false隐藏
    private boolean youkumenuVisible = true;

    private YoukuMenuItemClickedListener listener;

    //优酷菜单项的监听器
    public interface YoukuMenuItemClickedListener {
        void onItemClicked(View view);
    }

    //设置优酷菜单项监听器的方法
    public void setYoukuMenuItemClickedListener(YoukuMenuItemClickedListener listener) {
        this.listener = listener;
    }

    public YoukuMenu(Context context) {
        super(context);
        inintView(context);
    }

    public YoukuMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        inintView(context);
    }

    //初始化自定义菜单组合控件的默认布局
    private void inintView(Context context) {
        View.inflate(context, R.layout.youku_menu, this);

        //初始化控件
        mRlYoukuMenuLeve1 = findViewById(R.id.rl_youkumenu_level1);
        mRlYoukuMenuLeve2 = findViewById(R.id.rl_youkumenu_level2);
        mRlYoukuMenuLeve3 = findViewById(R.id.rl_youkumenu_level3);

        for(int index = 0; index < mRlYoukuMenuLeve3.getChildCount(); index++) {
            mRlYoukuMenuLeve3.getChildAt(index).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onItemClicked(v);
                    }
                }
            });
        }
    }

    /**
     * 旋转动画，旋转菜单控件的方法
     * @param view 被旋转的控件
     * @param fromAngle 开始角度
     * @param toAngle 结束角度
     * @param delay 延迟时间
     */
    private void rotate(View view, int fromAngle, int toAngle, int delay) {
        //旋转的圆心是X轴的中心点0.5f，Y轴最下方1.0f
        RotateAnimation rotate = new RotateAnimation(fromAngle, toAngle,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);

        //设置动画持续时间
        rotate.setDuration(300);

        //设置动画播放完后停留的状态
        rotate.setFillAfter(true);

        //设置动画开始时的延迟时间
        rotate.setStartOffset(delay);

        //开始播放动画
        view.startAnimation(rotate);
    }

    //显示菜单
    private void showMenu() {
        //先判断动画是否在播放
        if(isAnimation) {
            return;
        }

        //控件设置为可点击
        for(int index = 0; index < mRlYoukuMenuLeve3.getChildCount(); index++) {
            mRlYoukuMenuLeve3.getChildAt(index).setClickable(true);
        }

        isAnimation = true;
        rotate(mRlYoukuMenuLeve1, -180, 0, 0);
        rotate(mRlYoukuMenuLeve2, -180, 0, 300);
        rotate(mRlYoukuMenuLeve3, -180, 0, 600);

        //开启线程等待动画播放完毕
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(900);
                isAnimation = false;
                youkumenuVisible = true;
            }
        }.start();
    }

    //隐藏菜单
    private void hideMenu() {
        //先判断动画是否在播放
        if(isAnimation) {
            return;
        }

        //控件设置为不可点击
        for(int index = 0; index < mRlYoukuMenuLeve3.getChildCount(); index++) {
            mRlYoukuMenuLeve3.getChildAt(index).setClickable(false);
        }

        isAnimation = true;
        rotate(mRlYoukuMenuLeve3, 0, -180, 0);
        rotate(mRlYoukuMenuLeve2, 0, -180, 300);
        rotate(mRlYoukuMenuLeve1, 0, -180, 600);
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(900);
                isAnimation = false;
                youkumenuVisible = false;
            }
        }.start();
    }

    //修改菜单的状态，显示隐藏
    public void visiableState() {
        if(youkumenuVisible) {
            hideMenu();
        } else {
            showMenu();
        }
    }

}
