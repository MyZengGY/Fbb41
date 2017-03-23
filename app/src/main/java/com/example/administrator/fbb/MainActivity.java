package com.example.administrator.fbb;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.lp_back)
    ImageView lpBack;
    @InjectView(R.id.lp_phone)
    TextInputEditText lpPhone;
    @InjectView(R.id.lp_text_code)
    TextView lpTextCode;
    @InjectView(R.id.lp_code)
    TextInputEditText lpCode;
    @InjectView(R.id.lp_pw)
    TextInputEditText lpPw;
    @InjectView(R.id.lp_again_pw)
    TextInputEditText lpAgainPw;
    @InjectView(R.id.lp_button)
    Button lpButton;
    @InjectView(R.id.lg_phone)
    TextInputEditText lgPhone;
    @InjectView(R.id.lg_pw)
    TextView lgLostPw;
    @InjectView(R.id.rg_phone)
    TextInputEditText rgCode;
    @InjectView(R.id.rg_pw)
    TextInputEditText rgAgainPw;
    @InjectView(R.id.rg_recommend)
    TextInputEditText rgRecommend;
    @InjectView(R.id.lg_til_phone)
    TextInputLayout lgTilPhone;
    @InjectView(R.id.lg_til_pw)
    TextInputLayout lgTilPw;
    private boolean isSigninScreen = true;
    private TextView tvSignupInvoker, tvSigninInvoker, lg_lost_pw, lp_text_code, rg_git_code;
    private LinearLayout llSignup, llSignin;
    private Button btnSignup, btnSignin;
    private PercentRelativeLayout percentRL;
    private ImageView image_bg, lp_back;

    final int sj = 60;
    int djs = 0;
    int rgdjs = 0;
    private long firstTime = 0;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (djs == 0) {
                        lp_text_code.setText("获取验证码");
                        lp_text_code.setEnabled(true);
                        lp_text_code.setTextColor(Color.parseColor("#FFFFFF"));
                    } else {
                        lp_text_code.setText(djs + "s");
                    }
                    djs -= 1;
                    break;
                case 1:
                    if (rgdjs == 0) {
                        rg_git_code.setTextColor(Color.parseColor("#FFFFFF"));
                        rg_git_code.setText("获取验证码");
                        rg_git_code.setEnabled(true);
                    } else {
                        rg_git_code.setText(rgdjs + "s");
                    }
                    rgdjs -= 1;
                    break;
            }
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();
        initOnCl();


    }

    private void initmis() {
        lgTilPhone.setErrorEnabled(true);
        lgTilPw.setErrorEnabled(true);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {                                         //如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {                                                    //两次按键小于2秒时，退出应用
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    private void initOnCl() {
        lp_text_code.setOnClickListener(new View.OnClickListener() {//找回密码验证码
            @Override
            public void onClick(View v) {
                lp_text_code.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        djs = sj;
                        lp_text_code.setTextColor(Color.parseColor("#a01b1919"));
                        while (djs >= 0) {
                            try {
                                mHandler.sendEmptyMessage(0);
                                new Thread().sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
            }


        });
        rg_git_code.setOnClickListener(new View.OnClickListener() {//找回密码验证码
            @Override
            public void onClick(View v) {
                rg_git_code.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        rgdjs = sj;
                        rg_git_code.setTextColor(Color.parseColor("#a01b1919"));
                        while (rgdjs >= 0) {
                            try {
                                mHandler.sendEmptyMessage(1);
                                new Thread().sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
            }


        });
        tvSignupInvoker.setOnClickListener(new View.OnClickListener() {//注册那两个字
            @Override
            public void onClick(View view) {
                isSigninScreen = false;
                showSignupForm();
            }
        });

        tvSigninInvoker.setOnClickListener(new View.OnClickListener() {//登陆那两个字
            @Override
            public void onClick(View view) {
                isSigninScreen = true;
                showSigninForm();
            }
        });
        showSigninForm();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right_to_left);
                if (isSigninScreen)
                    btnSignup.startAnimation(clockwise);
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right_to_left);
                if (isSigninScreen == false){
                    btnSignin.startAnimation(clockwise);}
                initmis();
            }
        });
        lg_lost_pw.setOnClickListener(new View.OnClickListener() {//找回密码跳转
            @Override
            public void onClick(View v) {
                lostPass();
            }
        });
        lp_back.setOnClickListener(new View.OnClickListener() {//找回密码的返回
            @Override
            public void onClick(View v) {
                lostBack();

            }
        });
    }

    private void initView() {
        lp_text_code = (TextView) findViewById(R.id.lp_text_code);
        rg_git_code = (TextView) findViewById(R.id.rg_git_code);
        lp_back = (ImageView) findViewById(R.id.lp_back);
        image_bg = (ImageView) findViewById(R.id.image_bg);
        percentRL = (PercentRelativeLayout) findViewById(R.id.percentRL);
        tvSignupInvoker = (TextView) findViewById(R.id.tvSignupInvoker);
        tvSigninInvoker = (TextView) findViewById(R.id.tvSigninInvoker);
        lg_lost_pw = (TextView) findViewById(R.id.lg_lost_pw);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnSignin = (Button) findViewById(R.id.btnSignin);
        llSignup = (LinearLayout) findViewById(R.id.llSignup);
        llSignin = (LinearLayout) findViewById(R.id.llSignin);
    }

    private void lostBack() {
        percentRL.setVisibility(View.VISIBLE);
        image_bg.setVisibility(View.VISIBLE);
        lgPhone.setFocusable(true);
        lgPhone.setFocusableInTouchMode(true);
        lgPhone.requestFocus();
    }

    private void lostPass() {
        percentRL.setVisibility(View.INVISIBLE);
        image_bg.setVisibility(View.INVISIBLE);
        lpPhone.setFocusable(true);
        lpPhone.setFocusableInTouchMode(true);
        lpPhone.requestFocus();
    }

    private void showSignupForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.15f;
        llSignin.requestLayout();

        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.85f;
        llSignup.requestLayout();

        tvSignupInvoker.setVisibility(View.GONE);
        tvSigninInvoker.setVisibility(View.VISIBLE);
        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right_to_left);
        llSignup.startAnimation(translate);

        Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right_to_left);
        btnSignup.startAnimation(clockwise);

        rgCode.setFocusable(true);
        rgCode.setFocusableInTouchMode(true);
        rgCode.requestFocus();
    }

    private void showSigninForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.85f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.15f;
        llSignup.requestLayout();

        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left_to_right);
        llSignin.startAnimation(translate);

        tvSignupInvoker.setVisibility(View.VISIBLE);
        tvSigninInvoker.setVisibility(View.GONE);
        Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_left_to_right);
        btnSignin.startAnimation(clockwise);
        lgPhone.setFocusable(true);
        lgPhone.setFocusableInTouchMode(true);
        lgPhone.requestFocus();
    }
}
