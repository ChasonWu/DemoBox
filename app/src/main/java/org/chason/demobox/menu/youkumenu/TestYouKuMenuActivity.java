package org.chason.demobox.menu.youkumenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import org.chason.demobox.R;

public class TestYouKuMenuActivity extends Activity implements YoukuMenu.YoukuMenuItemClickedListener {

    private YoukuMenu mYoukuMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_youku);

        mYoukuMenu = findViewById(R.id.youkumenu_main);

        //设置优酷菜单项监听器
        mYoukuMenu.setYoukuMenuItemClickedListener(this);
    }

    public void menuState(View view) {
        mYoukuMenu.visiableState();
    }

    //优酷菜单每个Item的实现
    @Override
    public void onItemClicked(View view) {
        switch(view.getId()) {
            case R.id.channel1:
                Toast.makeText(this, "音乐", Toast.LENGTH_SHORT).show();
                break;

            case R.id.channel2:
                Toast.makeText(this, "视频", Toast.LENGTH_SHORT).show();
                break;

            case R.id.channel3:
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;

            case R.id.channel4:
                Toast.makeText(this, "排名", Toast.LENGTH_SHORT).show();
                break;

            case R.id.channel5:
                Toast.makeText(this, "节目", Toast.LENGTH_SHORT).show();
                break;

            case R.id.channel6:
                Toast.makeText(this, "录制", Toast.LENGTH_SHORT).show();
                break;

            case R.id.channel7:
                Toast.makeText(this, "主播", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //使用手机上菜单键打开关闭菜单
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            mYoukuMenu.visiableState();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
