package com.exercise.bao.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.exercise.bao.activities.R;
import com.exercise.bao.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrazyBun on 2016/7/11.
 */
public class MarqueeView extends ViewFlipper {

    private Context mContext;
    private List<String> notices;
    private boolean isSetAnimDuration = false;

    private int interval = 2000;
    private int animDuration = 500;
    private int textSize = 14;
    private int textColor = 0xffffffff;

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        if (notices == null) {
            notices = new ArrayList<>();
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeViewStyle, defStyleAttr, 0);
        interval = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvInterval, interval);
        isSetAnimDuration = typedArray.hasValue(R.styleable.MarqueeViewStyle_mvAnimDuration);
        animDuration = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvAnimDuration, animDuration);
        if (typedArray.hasValue(R.styleable.MarqueeViewStyle_mvTextSize)) {
            textSize = (int) typedArray.getDimension(R.styleable.MarqueeViewStyle_mvTextSize, textSize);
            textSize = DisplayUtil.px2sp(mContext, textSize);
        }
        textColor = typedArray.getColor(R.styleable.MarqueeViewStyle_mvTextColor, textColor);
        typedArray.recycle();

        setFlipInterval(interval);

        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
        if (isSetAnimDuration) animIn.setDuration(animDuration);
        setInAnimation(animIn);

        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
        if (isSetAnimDuration) animOut.setDuration(animDuration);
        setOutAnimation(animOut);
    }

    // 根据公告字符串启动轮播
    public void startWithText(final String notice) {
        if (TextUtils.isEmpty(notice)) return;
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                startWithFixedWidth(notice, getWidth());
            }
        });
    }

    // 根据公告字符串列表启动轮播
    public void startWithList(List<String> notices) {
        setNotices(notices);
        start();
    }

    // 根据宽度和公告字符串启动轮播
    private void startWithFixedWidth(String notice, int width) {
        int noticeLength = notice.length();
        int dpW = DisplayUtil.px2dip(mContext, width);
        int limit = dpW/textSize;
        if (dpW == 0) {
            throw new RuntimeException("Please set MarqueeView width !");
        }

        if (noticeLength <= limit) {
            notices.add(notice);
        } else {
            int size = noticeLength/limit + (noticeLength%limit != 0? 1:0);
            for (int i=0; i<size; i++) {
                int startIndex = i*limit;
                int endIndex = ((i+1)*limit >= noticeLength? noticeLength:(i+1)*limit);
                notices.add(notice.substring(startIndex, endIndex));
            }
        }
        start();
    }

    // 启动轮播
    public boolean start() {
        if (notices == null || notices.size() == 0) return false;
        removeAllViews();
        for (String notice:notices) {
            addView(createTextView(notice));
        }
        if (notices.size() > 1) {
            startFlipping();
        }
        return true;
    }

    // 创建ViewFlipper下的TextView
    private TextView createTextView(String text) {
        TextView tv = new TextView(mContext);
        tv.setGravity(Gravity.LEFT| Gravity.CENTER_VERTICAL);
        tv.setText(text);
        tv.setTextColor(textColor);
        tv.setTextSize(textSize);
        return tv;
    }

    public List<String> getNotices() {
        return notices;
    }

    public void setNotices(List<String> notices) {
        this.notices = notices;
    }
}
/*
属性

Attribute 属性	Description 描述
mvAnimDuration	一行文字动画执行时间
mvInterval	两行文字翻页时间间隔
mvTextSize	文字大小
mvTextColor	文字颜色
XML

<com.sunfusheng.marqueeview.MarqueeView
    android:id="@+id/marqueeView"
    android:layout_width="match_parent"
    android:layout_height="30dp"
    app:mvAnimDuration="1000"
    app:mvInterval="3000"
    app:mvTextColor="@color/white"
    app:mvTextSize="14sp"/>
设置列表数据

MarqueeView marqueeView = (MarqueeView) findViewById(R.id.marqueeView);

List<String> info = new ArrayList<>();
info.add("1. 大家好，我是孙福生。");
info.add("2. 欢迎大家关注我哦！");
info.add("3. GitHub帐号：sfsheng0322");
info.add("4. 新浪微博：孙福生微博");
info.add("5. 个人博客：sunfusheng.com");
info.add("6. 微信公众号：孙福生");
marqueeView.startWithList(info);
设置字符串数据

String notice = "心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！";
marqueeView.startWithText(notice);
 */