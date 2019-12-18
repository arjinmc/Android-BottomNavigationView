package com.arjinmc.bottomnavigationview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
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
     * current item layout gravity mode
     */
    private int mItemGravityMode = ITEM_GRAVITY_MODE_CENTER;

    /**
     * mark current checked item id
     */
    private int mCurrentCheckedItemId;

    private OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    //NavigationItemView params
    /**
     * the icon size(with = height) of item
     */
    private int mItemIconSize;
    /**
     * the text color of item
     */
    private ColorStateList mItemTextColor;
    /**
     * the text size of item
     */
    private float mItemTextSize;
    /**
     * the text would be bold style when tab is selected
     */
    private boolean mItemTextSelectedBold;
    /**
     * the number text color
     */
    private ColorStateList mItemNumberTextColor;
    /**
     * the number text size
     */
    private float mItemNumberTextSize;
    /**
     * the background of number text
     */
    private Drawable mItemNumberBackgroundDrawable;
    /**
     * the margin left/top of item number text
     */
    private int mItemNumberMarginLeft, mItemNumberMarginTop;
    /**
     * the margin between text and icon
     */
    private float mItemDrawablePadding;
    /**
     * the padding of item bottom
     */
    private int mItemBottomPadding;

    public BottomNavigationView(Context context) {
        super(context);
        init(null);
    }

    public BottomNavigationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BottomNavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setOrientation(LinearLayout.HORIZONTAL);

        TypedArray lAttrs = getContext().obtainStyledAttributes(attrs, R.styleable.BottomNavigationView);
        mItemGravityMode = lAttrs.getInt(R.styleable.BottomNavigationView_BottomNavigationView_tabItemGravity, 0);
        //icon
        mItemIconSize = lAttrs.getDimensionPixelSize(R.styleable.BottomNavigationView_BottomNavigationView_tabIconSize
                , getResources().getDimensionPixelSize(R.dimen.bottom_navigation_view_item_icon_size));
        //tab text
        mItemTextColor = lAttrs.getColorStateList(R.styleable.BottomNavigationView_BottomNavigationView_tabTextColor);
        mItemTextSize = lAttrs.getDimension(R.styleable.BottomNavigationView_BottomNavigationView_tabTextSize
                , getResources().getDimension(R.dimen.bottom_navigation_view_item_text_size));
        mItemTextSelectedBold = lAttrs.getBoolean(R.styleable.BottomNavigationView_BottomNavigationView_tabTextSelectedBoldStyle, false);

        //number text
        mItemNumberTextColor = lAttrs.getColorStateList(R.styleable.BottomNavigationView_BottomNavigationView_tabNumberTextColor);
        mItemNumberTextSize = lAttrs.getDimension(R.styleable.BottomNavigationView_BottomNavigationView_tabNumberTextSize
                , getResources().getDimension(R.dimen.bottom_navigation_view_item_number_text_size));
        mItemNumberBackgroundDrawable = lAttrs.getDrawable(R.styleable.BottomNavigationView_BottomNavigationView_tabNumberBackground);
        mItemNumberMarginLeft = lAttrs.getDimensionPixelSize(R.styleable.BottomNavigationView_BottomNavigationView_tabNumberMarginLeft
                , getResources().getDimensionPixelSize(R.dimen.bottom_navigation_view_item_number_margin_left));
        mItemNumberMarginTop = lAttrs.getDimensionPixelSize(R.styleable.BottomNavigationView_BottomNavigationView_tabNumberMarginTop
                , getResources().getDimensionPixelSize(R.dimen.bottom_navigation_view_item_number_margin_top));

        mItemDrawablePadding = lAttrs.getDimension(R.styleable.BottomNavigationView_BottomNavigationView_tabDrawablePadding
                , getResources().getDimension(R.dimen.bottom_navigation_view_item_drawable_margin_top));
        mItemBottomPadding = lAttrs.getDimensionPixelSize(R.styleable.BottomNavigationView_BottomNavigationView_tabBottomPadding
                , getResources().getDimensionPixelSize(R.dimen.bottom_navigation_view_item_margin_bottom));
    }

    /**
     * new item
     *
     * @return
     */
    public NavigationItemView newItem() {
        NavigationItemView navigationItemView = new NavigationItemView(getContext(), this);
        navigationItemView.setIconSize(mItemIconSize);
        navigationItemView.setTextColorStateList(mItemTextColor);
        navigationItemView.setTextSize(mItemTextSize);
        navigationItemView.setTextSelectedBold(mItemTextSelectedBold);
        navigationItemView.setNumberTextColor(mItemNumberTextColor);
        navigationItemView.setNumberTextSize(mItemNumberTextSize);
        navigationItemView.setNumberBackground(mItemNumberBackgroundDrawable);
        navigationItemView.setNumberMargin(mItemNumberMarginLeft, mItemNumberMarginTop);
        navigationItemView.setDrawableGap(mItemDrawablePadding);
        navigationItemView.setItemMarginBottom(mItemBottomPadding);
        return navigationItemView;
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
            navigationItemView.setCheckedState(true);
        }
        mNavigationItemViewList.add(navigationItemView);

        LinearLayout.LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        addView(navigationItemView, params);

        setItemGravityMode(mItemGravityMode);
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
     * get current selected item id
     *
     * @return
     */
    public int getCurrentSelectedItemId() {
        return mCurrentCheckedItemId;
    }

    /**
     * get current item gravity mode
     *
     * @return
     */
    public int getCurrentItemGravity() {
        return mItemGravityMode;
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

        if (mOnNavigationItemSelectedListener != null) {
            mOnNavigationItemSelectedListener.onItemReleaseSelected(mCurrentCheckedItemId);
        }

        mCurrentCheckedItemId = itemId;
        for (NavigationItemView navigationItemView : mNavigationItemViewList) {
            if (navigationItemView.getId() == mCurrentCheckedItemId) {
                navigationItemView.setCheckedState(true);
            } else {
                navigationItemView.setCheckedState(false);
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
    }

    public interface OnNavigationItemSelectedListener {
        void onItemSelected(int itemId);

        void onItemReleaseSelected(int itemId);
    }
}
