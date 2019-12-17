package com.arjinmc.bottomnavigationview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
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
     * mark if selected to show text bold style
     */
    private boolean isTextSelectedBold;
    private int mCurrentGravity;

    public NavigationItemView(Context context, BottomNavigationView bottomNavigationView) {
        super(context);
        mParentView = bottomNavigationView;
        init();
    }

    private void init() {

        if (mParentView == null) {
            try {
                throw new IllegalAccessException("Use BottomNavigationView.newItem() to create");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return;
            }
        }

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

    /**
     * set checked
     */
    public void setChecked() {
        mParentView.dispatchItemSelected(getId());
    }

    /**
     * set layout gravity
     *
     * @param layoutGravity
     */
    public void setLayoutGravity(@BottomNavigationView.ItemGravityMode int layoutGravity) {

        if (mCurrentGravity != layoutGravity) {
            mCurrentGravity = layoutGravity;
        }

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
        setLayoutGravity(mCurrentGravity);
    }

    /**
     * set item content margin bottom
     *
     * @param dimen
     */
    public void setItemMarginBottom(int dimen) {
        mMarginBottom = dimen;
        setLayoutGravity(mCurrentGravity);
    }

    /**
     * set drawable gap between title and icon
     *
     * @param dimensResId
     */
    public void setDrawableGap(@DimenRes int dimensResId) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTvTitle.getLayoutParams();
        layoutParams.topMargin = getResources().getDimensionPixelSize(dimensResId);
        mTvTitle.setLayoutParams(layoutParams);
    }

    /**
     * set drawable gap between title and icon
     *
     * @param margin
     */
    public void setDrawableGap(float margin) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTvTitle.getLayoutParams();
        layoutParams.topMargin = (int) margin;
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
    public void setTextSize(@DimenRes int textSizeResId) {
        mTvTitle.setTextSize(getResources().getDimensionPixelSize(textSizeResId));
    }

    /**
     * set text size
     *
     * @param textSize (px)
     */
    public void setTextSize(float textSize) {
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    /**
     * set normal text color
     *
     * @param textColor
     */
    public void setTextColorRes(@ColorRes int textColor) {
        mTvTitle.setTextColor(ContextCompat.getColor(getContext(), textColor));
    }

    /**
     * set normal text color
     *
     * @param textColor
     */
    public void setTextColor(@ColorInt int textColor) {
        mTvTitle.setTextColor(textColor);
    }

    /**
     * set text color state list
     *
     * @param colorStateList
     */
    public void setTextColorStateList(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return;
        }
        mTvTitle.setTextColor(colorStateList);
    }

    /**
     * show text bold style when selected
     *
     * @param textSelectedBold
     */
    public void setTextSelectedBold(boolean textSelectedBold) {
        isTextSelectedBold = textSelectedBold;
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
     * set number text color
     *
     * @param colorStateList
     */
    public void setNumberTextColor(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return;
        }
        mTvNumber.setTextColor(colorStateList);
    }

    /**
     * set number text color
     *
     * @param color
     */
    public void setNumberTextColor(@ColorInt int color) {
        mTvNumber.setTextColor(color);
    }

    /**
     * set number text color
     *
     * @param colorRes
     */
    public void setNumberTextColorRes(@ColorRes int colorRes) {
        mTvNumber.setTextColor(ContextCompat.getColor(getContext(), colorRes));
    }

    /**
     * set size of number text
     *
     * @param textSize
     */
    public void setNumberTextSize(float textSize) {
        mTvNumber.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setNumberTextSize(@DimenRes int textSize) {
        mTvNumber.setTextSize(getResources().getDimensionPixelSize(textSize));
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
     * @param drawableRes
     */
    public void setNumberBackground(@DrawableRes int drawableRes) {
        if (drawableRes == -1) {
            return;
        }
        mTvNumber.setBackgroundResource(drawableRes);
    }

    /**
     * set number background drawable
     *
     * @param drawableRes
     */
    public void setNumberBackground(Drawable drawableRes) {
        if (drawableRes == null) {
            return;
        }
        mTvNumber.setBackground(drawableRes);
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
    public void setCheckedState(boolean isCheck) {
        mIvIcon.setSelected(isCheck);
        mTvTitle.setSelected(isCheck);
        mTvNumber.setSelected(isCheck);

        if (isTextSelectedBold) {
            if (isCheck) {
                mTvTitle.setTypeface(Typeface.DEFAULT_BOLD);
            } else {
                mTvTitle.setTypeface(Typeface.DEFAULT);
            }
        }
    }
}
