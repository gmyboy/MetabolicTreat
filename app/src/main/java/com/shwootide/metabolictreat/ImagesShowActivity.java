package com.shwootide.metabolictreat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.shwootide.metabolictreat.widget.HackyViewPager;
import com.shwootide.metabolictreat.widget.imageview.PhotoView;
import com.shwootide.metabolictreat.widget.imageview.PhotoViewAttacher;

import java.util.ArrayList;
import java.util.List;

/**
 * 显示多张图片
 *
 * @author GMY
 * @mail 2275964276@qq.com
 * @date 2015年6月2日
 */
public class ImagesShowActivity extends BaseActivity {
    private HackyViewPager viewPager;
    private MyPageAdapter adapter;

    private List<String> res = new ArrayList<>();
    private int position = 0;
    private TextView txt;
    private Button back;
    int old = 0;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_imagesshow);
    }

    @Override
    public void setTitleBarOption() {

    }
    @Override
    public void wentTo(RadioGroup group, int checkedId) {

    }
    public void initView() {
        viewPager = (HackyViewPager) this.findViewById(R.id.viewpage);
        txt = (TextView) findViewById(R.id.show_txt);
        back = (Button) findViewById(R.id.show_back);
        res = getIntent().getStringArrayListExtra("mlist");
        String pos = getIntent().getStringExtra("pos");
        if (!pos.equals("")) {
            position = Integer.parseInt(pos);
        }

        if (res != null && res.size() != 0) {
            adapter = new MyPageAdapter(res, ImagesShowActivity.this);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        txt.setText(position + 1 + "/" + res.size());
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                txt.setText(arg0 + 1 + "/" + res.size());
                old = arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }


    public class MyPageAdapter extends PagerAdapter {
        private List<String> res;
        private Context context;

        public MyPageAdapter(List<String> res, Context context) {
            super();
            this.res = res;
            this.context = context;
        }

        @Override
        public int getCount() {
            return res.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View iv = LayoutInflater.from(context).inflate(
                    R.layout.item_viewpage, null);
            PhotoView img = (PhotoView) iv.findViewById(R.id.imageview);
            if (!res.get(position).equals("")) {
                img.setZoomable(true);
                img.setAllowParentInterceptOnEdge(true);
//                img.setImageResource(Integer.parseInt(res.get(position)));
//                img.setImageUri(res.get(position));
                Glide.with(ImagesShowActivity.this).load(res.get(position)).into(img);
            }
            //图片区域内单击之后退出
            img.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
            });
            container.addView(iv, 0);
            return iv;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        return false;
    }
}
