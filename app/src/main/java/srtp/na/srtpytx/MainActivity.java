package srtp.na.srtpytx;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnGestureListener, OnTouchListener {

    private TextView date_TextView;
    private ImageButton set_ImageButton, regist_ImageButton, login_ImageButton;
    private ViewFlipper mallViewFlipper;
    private boolean showNextImg = true;
    private boolean isRun = true;
    private int currentImage = 0;
    private final int SHOW_NEXT = 0011;
    private static final int FLING_MIN_DISTANCE = 50;
    private static final int FLING_MIN_VELOCITY = 0;
    private GestureDetector mallGestureDetector;
    private LinearLayout home_img_bn_Layout, style_img_bn_layout, cam_img_bn_layout, shopping_img_bn_layout, show_img_bn_layout;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        date_TextView = (TextView) findViewById(R.id.home_date_tv);
        date_TextView.setText(getDate());

        set_ImageButton = (ImageButton) findViewById(R.id.title_set_bn);
        set_ImageButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                toastInfo("设置属性");
            }
        });


        home_img_bn_Layout = (LinearLayout) findViewById(R.id.bottom_home_layout_ly);
        home_img_bn_Layout.setOnClickListener(clickListener_home);

        style_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_style_layout_ly);
        style_img_bn_layout.setOnClickListener(clickListener_style);

        cam_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_cam_layout_ly);
        cam_img_bn_layout.setOnClickListener(clickListener_cam);

        shopping_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_shopping_layout_ly);
        shopping_img_bn_layout.setOnClickListener(clickListener_shopping);

        show_img_bn_layout = (LinearLayout) findViewById(R.id.bottom_show_layout_ly);
        show_img_bn_layout.setOnClickListener(clickListener_show);

        mallViewFlipper = (ViewFlipper) findViewById(R.id.mall_mViewFliper_vf);
        mallGestureDetector = new GestureDetector(this);
        mallViewFlipper.setOnTouchListener(this);
        mallViewFlipper.setLongClickable(true);
        mallViewFlipper.setOnClickListener(imageClickListener);
        displayRatio_selelct(currentImage);


        MyScrollView mallScrollView = (MyScrollView) findViewById(R.id.mall_viewflipper_scrollview);
        mallScrollView.setOnTouchListener(imageOnTouchListener);
        mallScrollView.setGestureDetector(mallGestureDetector);


        thread.start();
    }

    private OnClickListener imageClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            toastInfo("点击事件");
        }
    };
    private OnTouchListener imageOnTouchListener = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            return mallGestureDetector.onTouchEvent(event);
        }
    };

    Handler mallHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case SHOW_NEXT:
                    if (showNextImg) {
                        showNextImage();
                    } else {
                        showPreviousImage();
                    }
                    break;

                default:
                    break;
            }
        }

    };
    private String getDate(){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int w = c.get(Calendar.DAY_OF_WEEK) - 1 ;
        if (w < 0) {
            w = 0;
        }
        String mDate = c.get(Calendar.YEAR)+"年" + c.get(Calendar.MONTH) + "月" + c.get(Calendar.DATE) + "日  " + weekDays[w];
        return mDate;
    }
    private OnClickListener clickListener_home = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            home_img_bn_Layout.setSelected(true);
            style_img_bn_layout.setSelected(false);
            cam_img_bn_layout.setSelected(false);
            shopping_img_bn_layout.setSelected(false);
            show_img_bn_layout.setSelected(false);
//            Intent intent = new Intent();
//            intent.setClass(MainActivity.this, MyActivity.class);
//            intent.putExtra("clickble", true);
//            startActivity(intent);
            home_img_bn_Layout.setSelected(true);
        }
    };
    private OnClickListener clickListener_style = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            home_img_bn_Layout.setSelected(false);
            style_img_bn_layout.setSelected(true);
            cam_img_bn_layout.setSelected(false);
            shopping_img_bn_layout.setSelected(false);
            show_img_bn_layout.setSelected(false);
            toastInfo("点击我的有跳转");
        }
    };
    private OnClickListener clickListener_cam = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            home_img_bn_Layout.setSelected(false);
            style_img_bn_layout.setSelected(false);
            cam_img_bn_layout.setSelected(true);
            shopping_img_bn_layout.setSelected(false);
            show_img_bn_layout.setSelected(false);
            toastInfo("点击我的有跳转");
        }
    };
    private OnClickListener clickListener_shopping = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            home_img_bn_Layout.setSelected(false);
            style_img_bn_layout.setSelected(false);
            cam_img_bn_layout.setSelected(false);
            shopping_img_bn_layout.setSelected(true);
            show_img_bn_layout.setSelected(false);
            toastInfo("点击我的有跳转");
        }
    };
    private OnClickListener clickListener_show = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            home_img_bn_Layout.setSelected(false);
            style_img_bn_layout.setSelected(false);
            cam_img_bn_layout.setSelected(false);
            shopping_img_bn_layout.setSelected(false);
            show_img_bn_layout.setSelected(true);
            toastInfo("点击我的有跳转");
        }
    };
    @Override
    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        // TODO Auto-generated method stub
        Log.e("view", "onFling");
        if (e1.getX() - e2.getX()> FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY ) {
            Log.e("fling", "left");
            showNextImage();
            showNextImg = true;
//			return true;
        } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY){
            Log.e("fling", "right");
            showPreviousImage();
            showNextImg = false;
//			return true;
        }
        return false;
    }
    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        return mallGestureDetector.onTouchEvent(event);
    }

    Thread thread = new Thread(){

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(isRun){
                try {
                    Thread.sleep(1000 * 8);
                    Message msg = new Message();
                    msg.what = SHOW_NEXT;
                    mallHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    };

    private void showNextImage(){

        mallViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
        mallViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
        mallViewFlipper.showNext();
        currentImage ++;
        if (currentImage == mallViewFlipper.getChildCount()) {
            displayRatio_normal(currentImage - 1);
            currentImage = 0;
            displayRatio_selelct(currentImage);
        } else {
            displayRatio_selelct(currentImage);
            displayRatio_normal(currentImage - 1);
        }
        Log.e("currentImage", currentImage + "");

    }
    private void showPreviousImage(){
        displayRatio_selelct(currentImage);
        mallViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
        mallViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
        mallViewFlipper.showPrevious();
        currentImage --;
        if (currentImage == -1) {
            displayRatio_normal(currentImage + 1);
            currentImage = mallViewFlipper.getChildCount() - 1;
            displayRatio_selelct(currentImage);
        } else {
            displayRatio_selelct(currentImage);
            displayRatio_normal(currentImage + 1);
        }
        Log.e("currentImage", currentImage + "");
    }
    private void displayRatio_selelct(int id){
        int[] ratioId = {R.id.home_ratio_img_04, R.id.home_ratio_img_03, R.id.home_ratio_img_02, R.id.home_ratio_img_01};
        ImageView img = (ImageView)findViewById(ratioId[id]);
        img.setSelected(true);
    }
    private void displayRatio_normal(int id){
        int[] ratioId = {R.id.home_ratio_img_04, R.id.home_ratio_img_03, R.id.home_ratio_img_02, R.id.home_ratio_img_01};
        ImageView img = (ImageView)findViewById(ratioId[id]);
        img.setSelected(false);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isRun = false;
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        finish();
        super.onDestroy();
    }

    private void toastInfo(String string){
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
    }
}
