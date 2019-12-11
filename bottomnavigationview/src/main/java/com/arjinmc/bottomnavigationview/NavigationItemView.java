package com.arjinmc.bottomnavigationview;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

/**
 * Item for Navigation
 * Created by Eminem Lo on 2019-12-10.
 * email: arjinmc@hotmail.com
 */
public class NavigationItemView extends FrameLayout {

    private BottomNavigationView mParentView;

    private LinearLayout mLlContent;
    private ImageView mIvIcon;
    private TextView mTvTitle;
    private TextView mTvNumber;

    /**
     * margin bottom for linear layout content
     */
    private int mMarginBottom;

    /**
     * above max number n will be shown as "n+"
     */
    private int mMaxNumber = 99;
    /**
     * mark is checked
     */
    private boolean mIsCheck;

    public NavigationItemView(Context context) {
        super(context);
        init();
    }

    public NavigationItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NavigationItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NavigationItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mParentView.dispatchItemSelected(getId());
            }
        });
        inflate(getContext(), R.layout.bottom_navigation_view_item_navigation_view, this);
        mLlContent = findViewById(R.id.bottom_navigation_view_ll_content);
        mIvIcon = findViewById(R.id.bottom_navigation_view_iv_icon);
        mTvTitle = findViewById(R.id.bottom_navigation_view_tv_title);
        mTvNumber = findViewById(R.id.bottom_navigation_view_tv_number);
    }

    public void bindParent(BottomNavigationView parentView){
        mParentView = parentView;
    }

    /**
     * set layout gravity
     *
     * @param layoutGravity
     */
    public void setLayoutGravity(@BottomNavigationView.ItemGravityMode int layoutGravity) {
        FrameLayout.LayoutParams layoutParams = (LayoutParams) mLlContent.getLayoutParams();
        switch (layoutGravity) {
            case BottomNavigationView.ITEM_GRAVITY_MODE_CENTER:
            default:
                layoutParams.gravity = Gravity.CENTER;
                layoutParams.bottomMargin = 0;
                break;
            case BottomNavigationView.ITEM_GRAVITY_MODE_BOTTOM:
                layoutParams.gravity = Gravity.BOTTOM;
                if (mMarginBottom == 0) {
                    layoutParams.bottomMargin = getResources()
                            .getDimensionPixelSize(R.dimen.bottom_navigation_view_item_margin_bottom);
                } else {
                    layoutParams.bottomMargin = mMarginBottom;
                }
                break;
        }
        mLlContent.setLayoutParams(layoutParams);
    }

    /**
     * set item content margin bottom
     *
     * @param dimenResId
     */
    public void setItemMarginBottomDimen(@DimenRes int dimenResId) {
        mMarginBottom = getResources().getDimensionPixelSize(dimenResId);
    }

    /**
     * set item content margin bottom
     *
     * @param dimen
     */
    public void setItemMarginBottom(int dimen) {
        mMarginBottom = dimen;
    }

    /**
     * set drawable gap between title and icon
     *
     * @param dimensResId
     */
    public void setDrawableGrapDimens(@DimenRes int dimensResId) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTvTitle.getLayoutParams();
        layoutParams.topMargin = getResources().getDimensionPixelSize(dimensResId);
        mTvTitle.setLayoutParams(layoutParams);
    }

    /**
     * set drawable gap between title and icon
     *
     * @param margin
     */
    public void setDrawableGrap(int margin) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTvTitle.getLayoutParams();
        layoutParams.topMargin = margin;
        mTvTitle.setLayoutParams(layoutParams);
    }

    /**
     * set or not show the number
     *
     * @param number
     */
    public void setNumber(Integer number) {
        if (number != null) {
            if (number > mMaxNumber) {
                mTvNumber.setText(mMaxNumber + "+");
            } else if (number > 0) {
                mTvNumber.setText(number + "");
            }
            if (number > 0) {
                setNumberBackgroundSize(null, null);
                mTvNumber.setVisibility(View.VISIBLE);
            } else {
                mTvNumber.setVisibility(View.GONE);
            }
        } else {
            mTvNumber.setVisibility(View.GONE);
        }
    }

    /**
     * set max number
     *
     * @param maxNumber
     */
    public void setMaxNumber(int maxNumber) {
        mMaxNumber = maxNumber;
    }

    /**
     * set show dot
     *
     * @param shown
     */
    public void setShowDot(boolean shown) {
        if (shown) {
            mTvNumber.setVisibility(View.VISIBLE);
        } else {
            mTvNumber.setVisibility(View.GONE);
        }
    }

    /**
     * set text
     *
     * @param text
     */
    public void setText(String text) {
        mTvTitle.setText(text);
    }

    /**
     * set text
     *
     * @param textResId
     */
    public void setText(@StringRes int textResId) {
        mTvTitle.setText(textResId);
    }

    /**
     * set text size
     *
     * @param textSizeResId
     */
    public void setTextSizeDimen(@DimenRes int textSizeResId) {
        mTvTitle.setTextSize(getResources().getDimensionPixelSize(textSizeResId));
    }

    /**
     * set text size
     *
     * @param textSize
     */
    public void setTextSize(int textSize) {
        mTvTitle.setTextSize(textSize);
    }

    /**
     * set text color
     *
     * @param textColorResId
     */
    public void setTextColor(@ColorRes int textColorResId) {
        mTvTitle.setTextColor(ContextCompat.getColor(getContext(), textColorResId));
    }

    /**
     * set icon drawable
     *
     * @param drawableResId
     */
    public void setIconDrawable(@DrawableRes int drawableResId) {
        mIvIcon.setImageResource(drawableResId);
    }

    /**
     * set icon drawable
     *
     * @param drawableResId
     */
    public void setIconBacgroundDrawable(@DrawableRes int drawableResId) {
        mIvIcon.setBackgroundResource(drawableResId);
    }

    /**
     * set icon padding
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public void setIconPadding(int left, int top, int right, int bottom) {
        mIvIcon.setPadding(left, top, right, bottom);
    }

    /**
     * set icon size
     *
     * @param sizeDimenRes
     */
    public void setIconSizeDimen(@DimenRes int sizeDimenRes) {
        int size = getResources().getDimensionPixelSize(sizeDimenRes);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mIvIcon.getLayoutParams();
        layoutParams.width = size;
        layoutParams.height = size;
        mIvIcon.setLayoutParams(layoutParams);
    }

    /**
     * set icon size
     *
     * @param size
     */
    public void setIconSize(int size) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mIvIcon.getLayoutParams();
        layoutParams.width = size;
        layoutParams.height = size;
        mIvIcon.setLayoutParams(layoutParams);
    }

    /**
     * set number margin
     *
     * @param marginLeft
     * @param marginTop
     */
    public void setNumberMargin(int marginLeft, int marginTop) {
        FrameLayout.LayoutParams layoutParams = (LayoutParams) mTvNumber.getLayoutParams();
        layoutParams.leftMargin = marginLeft;
        layoutParams.topMargin = marginTop;
        mTvNumber.setLayoutParams(layoutParams);
    }

    /**
     * set number background drawable
     *
     * @param drawableResId
     */
    public void setNumberBackground(@DrawableRes int drawableResId) {
        mTvNumber.setBackgroundResource(drawableResId);
    }

    /**
     * set number background size
     *
     * @param width
     * @param height
     */
    public void setNumberBackgroundSizeDimen(@DimenRes int width, @DimenRes int height) {
        setNumberBackgroundSize(getResources().getDimensionPixelSize(width), getResources().getDimensionPixelSize(height));
    }

    /**
     * set number background size
     *
     * @param width
     * @param height
     */
    public void setNumberBackgroundSize(Integer width, Integer height) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mTvNumber.getLayoutParams();
        if (width == null) {
            width = layoutParams.width;
        }
        if (height == null) {
            height = layoutParams.height;
        }
        String number = mTvNumber.getText().toString();
        if (!TextUtils.isEmpty(number)) {
            int numberWidth = (int) getCurrentNumberWidth();
            layoutParams.width = numberWidth > width ? numberWidth + Math.abs(mTvNumber.getLineHeight() - height) * 2 : width;
        } else {
            layoutParams.width = width;
        }
        layoutParams.height = height;
        mTvNumber.setLayoutParams(layoutParams);
    }

    /**
     * get current number width
     *
     * @return
     */
    private float getCurrentNumberWidth() {
        if (TextUtils.isEmpty(mTvNumber.getText().toString())) {
            return 0;
        }
        String number = mTvNumber.getText().toString();
        return mTvNumber.getPaint().measureText(number);
    }

    /**
     * set is checked state
     *
     * @param isCheck
     */
    public void setChecked(boolean isCheck) {
        mIsCheck = isCheck;
    }
}
