package com.arjinmc.bottomnavigationview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

/**
 * BottomNavigationView for Common Style
 * Created by Eminem Lo on 2019-12-10.
 * email: arjinmc@hotmail.com
 */
public class BottomNavigationView extends LinearLayout {

    /**
     * item param mode match parent width
     */
    public static final int ITEM_PARAMS_MODE_MATCH = 0;
    /**
     * item param mode  wrap_content
     */
    public static final int ITEM_PARAMS_MODE_WRAP = 1;

    /**
     * item param mode
     */
    @IntDef(value = {ITEM_PARAMS_MODE_MATCH, ITEM_PARAMS_MODE_WRAP})
    public @interface ItemParamsMode {
    }

    /**
     * item layout gravity center mode
     */
    public static final int ITEM_GRAVITY_MODE_CENTER = 0;
    /**
     * item layout gravity bottom mode
     */
    public static final int ITEM_GRAVITY_MODE_BOTTOM = 1;

    /**
     * item layout gravity mode
     */
    @IntDef(value = {ITEM_GRAVITY_MODE_CENTER, ITEM_GRAVITY_MODE_BOTTOM})
    public @interface ItemGravityMode {
    }

    /**
     * children list NavigationItemView
     */
    private List<NavigationItemView> mNavigationItemViewList;
    /**
     * current item params mode
     */
    private int mItemParamsMode = ITEM_PARAMS_MODE_MATCH;
    /**
     * current item layout gravity mode
     */
    private int mItemGravityMode = ITEM_GRAVITY_MODE_CENTER;

    /**
     * mark current checked item id
     */
    private int mCurrentCheckedItemId;

    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    public BottomNavigationView(Context context) {
        super(context);
        init();
    }

    public BottomNavigationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomNavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.HORIZONTAL);
    }

    /**
     * new item
     *
     * @return
     */
    public NavigationItemView newItem() {
        return new NavigationItemView(getContext());
    }

    /**
     * add item
     *
     * @param navigationItemView
     */
    public void addItem(NavigationItemView navigationItemView) {

        if (mNavigationItemViewList == null) {
            mNavigationItemViewList = new ArrayList<>(2);
        }

        if (mNavigationItemViewList.isEmpty()) {
            mCurrentCheckedItemId = navigationItemView.getId();
            navigationItemView.setChecked(true);
        }
        mNavigationItemViewList.add(navigationItemView);

        switch (mItemParamsMode) {
            case ITEM_PARAMS_MODE_MATCH:
            default:
                LinearLayout.LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                params.weight = 1;
                addView(navigationItemView, params);
                break;
            case ITEM_PARAMS_MODE_WRAP:
                addView(navigationItemView, new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                break;
        }
    }

    /**
     * remove item
     *
     * @param itemId
     */
    public void removeItem(int itemId) {

        if (mNavigationItemViewList == null || mNavigationItemViewList.isEmpty()) {
            return;
        }

        for (NavigationItemView navigationItemView : mNavigationItemViewList) {
            if (navigationItemView.getId() == itemId) {
                mNavigationItemViewList.remove(navigationItemView);
                removeView(navigationItemView);
                break;
            }
        }

    }

    /**
     * remove  item by position
     *
     * @param itemPosition
     */
    public void removeItemPosition(int itemPosition) {
        if (mNavigationItemViewList == null || mNavigationItemViewList.isEmpty()) {
            return;
        }
        if (mNavigationItemViewList.size() <= itemPosition) {
            return;
        }
        removeView(mNavigationItemViewList.get(itemPosition));
        mNavigationItemViewList.remove(itemPosition);
    }

    public void setItemParamsMode(@ItemParamsMode int itemParamsMode) {
        mItemParamsMode = itemParamsMode;
    }

    public void setItemGravityMode(@ItemGravityMode int itemGravityMode) {
        mItemGravityMode = itemGravityMode;

        if (mNavigationItemViewList == null || mNavigationItemViewList.isEmpty()) {
            return;
        }
        for (NavigationItemView navigationItemView : mNavigationItemViewList) {
            navigationItemView.setLayoutGravity(itemGravityMode);
        }
    }

    /**
     * dispatch item selected
     *
     * @param itemId
     */
    public void dispatchItemSelected(int itemId) {

        if (mNavigationItemViewList == null || mNavigationItemViewList.isEmpty()) {
            return;
        }

        if (mCurrentCheckedItemId == itemId) {
            return;
        }
        mCurrentCheckedItemId = itemId;
        for (NavigationItemView navigationItemView : mNavigationItemViewList) {
            if (navigationItemView.getId() == mCurrentCheckedItemId) {
                navigationItemView.setChecked(true);
            } else {
                navigationItemView.setChecked(false);
            }
        }

        if (mOnNavigationItemSelectedListener != null) {
            mOnNavigationItemSelectedListener.onItemSelected(itemId);
        }
    }

    /**
     * set OnNavigationItemSelectedListener
     *
     * @param onNavigationItemSelectedListener
     */
    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        mOnNavigationItemSelectedListener = onNavigationItemSelectedListener;

        if (mNavigationItemViewList == null || mNavigationItemViewList.isEmpty()) {
            return;
        }
        for (NavigationItemView navigationItemView : mNavigationItemViewList) {
            navigationItemView.bindParent(this);
        }
    }

    public interface OnNavigationItemSelectedListener {
        void onItemSelected(int itemId);
    }
}
