package com.ramotion.expandingcollection;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ECPagerCardHead extends FrameLayout {

    private TextView headTitleTextView;
    private ImageView headImageView;

    public ECPagerCardHead(Context context) {
        super(context);
    }

    public ECPagerCardHead(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ECPagerCardHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        try {
            headImageView = (ImageView) getChildAt(0);
            headTitleTextView = (TextView) getChildAt(1);
        } catch (Exception e) {
            throw new IllegalStateException("Invalid children elements in ECPagerCardHeads.");
        }
    }

    public void setHeadImageDrawable(Drawable headImageDrawable) {
        if (this.headImageView != null)
            this.headImageView.setImageDrawable(headImageDrawable);
    }

    public void setHeadTitleText(String headTitleText) {
        if (this.headTitleTextView != null)
            this.headTitleTextView.setText(headTitleText);
    }

    public void animateHeight(int targetHeight, int duration, int delay) {
        final ViewGroup.LayoutParams cardHeaderLayoutParams = this.getLayoutParams();

        ValueAnimator cardHeadHeightAnimation = new ValueAnimator();
        cardHeadHeightAnimation.setInterpolator(new DecelerateInterpolator());
        cardHeadHeightAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                cardHeaderLayoutParams.height = (int) animation.getAnimatedValue();
                ECPagerCardHead.this.setLayoutParams(cardHeaderLayoutParams);
            }
        });

        cardHeadHeightAnimation.setIntValues(cardHeaderLayoutParams.height, targetHeight);

        cardHeadHeightAnimation.setDuration(duration);
        cardHeadHeightAnimation.setStartDelay(delay);
        cardHeadHeightAnimation.start();
    }

    public void setHeight(int height) {
        this.getLayoutParams().height = height;
    }

}