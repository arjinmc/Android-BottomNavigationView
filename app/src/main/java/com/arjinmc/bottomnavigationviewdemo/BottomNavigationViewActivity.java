package com.arjinmc.bottomnavigationviewdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arjinmc.bottomnavigationview.BottomNavigationView;
import com.arjinmc.bottomnavigationview.NavigationItemView;

/**
 * Created by Eminem Lo on 2019-12-10.
 * email: arjinmc@hotmail.com
 */
public class BottomNavigationViewActivity extends AppCompatActivity {

    private RadioGroup mRgItemGravity;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_bottom_navigation_view);

        initBottomNavigation();

        mRgItemGravity = findViewById(R.id.rg_item_layout_gravity);
        if (mBottomNavigationView.getCurrentItemGravity() == BottomNavigationView.ITEM_GRAVITY_MODE_CENTER) {
            ((RadioButton) mRgItemGravity.getChildAt(0)).setChecked(true);
        } else {
            ((RadioButton) mRgItemGravity.getChildAt(1)).setChecked(true);
        }
        mRgItemGravity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_mode_center:
                        mBottomNavigationView.setItemGravityMode(BottomNavigationView.ITEM_GRAVITY_MODE_CENTER);
                        break;
                    case R.id.rb_mode_bottom:
                        mBottomNavigationView.setItemGravityMode(BottomNavigationView.ITEM_GRAVITY_MODE_BOTTOM);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void initBottomNavigation() {
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view);

        NavigationItemView naviItem1 = mBottomNavigationView.newItem();
        naviItem1.setText(getString(R.string.tab_1));
        naviItem1.setId(R.id.tab_1);
        naviItem1.setIconDrawable(R.drawable.ic_home);
        naviItem1.setNumber(2);
        mBottomNavigationView.addItem(naviItem1);

        NavigationItemView naviItem2 = mBottomNavigationView.newItem();
        naviItem2.setText(getString(R.string.tab_2));
        naviItem2.setId(R.id.tab_2);
        naviItem2.setIconDrawable(R.drawable.ic_chart);
        naviItem2.setNumberBackgroundSize(20, 20);
        naviItem2.setNumberMargin(40, 6);
        naviItem2.setShowDot(true);
        mBottomNavigationView.addItem(naviItem2);

        NavigationItemView naviItem3 = mBottomNavigationView.newItem();
        naviItem3.setText(getString(R.string.tab_3));
        naviItem3.setId(R.id.tab_3);
        naviItem3.setIconDrawable(R.drawable.ic_library);
        naviItem3.setNumberBackgroundSize(20, 30);
        naviItem3.setNumber(100);
        mBottomNavigationView.addItem(naviItem3);

        naviItem3.setChecked();

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public void onItemSelected(int itemId) {
                switch (itemId) {
                    case R.id.tab_1:
                        Log.i("click", "tab1");
                        break;
                    case R.id.tab_2:
                        Log.i("click", "tab2");
                        break;
                    case R.id.tab_3:
                        Log.i("click", "tab3");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onItemReleaseSelected(int itemId) {
                switch (itemId) {
                    case R.id.tab_1:
                        Log.i("release", "tab1");
                        break;
                    case R.id.tab_2:
                        Log.i("release", "tab2");
                        break;
                    case R.id.tab_3:
                        Log.i("relase", "tab3");
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
