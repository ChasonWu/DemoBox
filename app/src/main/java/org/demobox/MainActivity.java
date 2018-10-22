package org.demobox;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.demobox.Animation.TestAnimationActivity;
import org.chason.demobox.R;
import org.demobox.alarm.activity.AlarmControllerActivity;
import org.demobox.general.AutoEditTextActivity;
import org.demobox.general.adapter.CommonAdapter;
import org.demobox.general.adapter.ViewHolder;
import org.demobox.general.base.BaseActivity;
import org.demobox.menu.customizemenu.TestCustomizeMenuActivity;
import org.demobox.menu.youkumenu.TestYouKuMenuActivity;
import org.demobox.toast.customtoast.TestToastActivity;
import org.demobox.toast.rocket.TestSmokeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序入口
 */
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private GridView mGridView;
    private CommonAdapter mGridItemAdapter;
    private List<String> mItemName;

    @Override
    protected void initWidget() {
        setContentView(R.layout.activity_main);
        mGridView = findViewById(R.id.gl_main_item);

        mItemName = new ArrayList();
        mItemName.add("1-自定义Toast");
        mItemName.add("2-火箭");
        mItemName.add("3-闹钟");
        mItemName.add("4-监听EditText内容变化");
        mItemName.add("5-动画");
        mItemName.add("6-优酷菜单");
        mItemName.add("7-自定义菜单");

        mGridItemAdapter = new CommonAdapter(this, mItemName, R.layout.item_main_gl) {
            @Override
            public void convert(ViewHolder helper, Object item) {
                TextView tvItem = helper.getView(R.id.tv_item_main_gl);
                tvItem.setText((String) item);
            }
        };

        mGridView.setAdapter(mGridItemAdapter);
        mGridView.setOnItemClickListener(this);
    }

    public void launchActivity(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
//        intent.setClass(context, clazz);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: //自定义Toast
                launchActivity(this, TestToastActivity.class);
                break;

            case 1: //火箭
                launchActivity(this, TestSmokeActivity.class);
                break;

            case 2: //闹钟
                launchActivity(this, AlarmControllerActivity.class);
                break;

            case 3: //监听EditText内容变化
                launchActivity(this, AutoEditTextActivity.class);
                break;

            case 4: //动画
                launchActivity(this, TestAnimationActivity.class);
                break;

            case 5: //优酷菜单
                launchActivity(this, TestYouKuMenuActivity.class);
                break;

            case 6: //自定义菜单
                launchActivity(this, TestCustomizeMenuActivity.class);
                break;

            case 7: //
                Toast.makeText(this, "7", Toast.LENGTH_SHORT).show();
                break;

            default:break;
        }

    }
}
