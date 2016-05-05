package com.muyang.campus;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import tyrantgit.explosionfield.ExplosionField;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login, btn_third_login,login;
    private ExplosionField explosionField;
    private LinearLayout third_login_layout, third_icon_layout, change_login, number_layout, password_layout;
    //View的显示和隐藏动画
    private Animation mShowAnimation, mHiddenAnimation,mFadeoutAnimation;
    private boolean judge = false;
    private ImageView tencent, wechat, sina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        initWidgets();
        //使用学号登陆
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judge = true;
                explosionField.explode(btn_third_login);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        explosionField.explode(btn_login);
                    }
                }, 800);
                btn_login.setOnClickListener(null);
                btn_third_login.setOnClickListener(null);
                change_login.startAnimation(mShowAnimation);
                change_login.setVisibility(View.VISIBLE);
                showNumberPasswordDialog();
                login.startAnimation(mShowAnimation);
                login.setVisibility(View.VISIBLE);
            }
        });
        //使用第三方登陆
        btn_third_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judge = false;
                explosionField.explode(btn_login);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        explosionField.explode(btn_third_login);
                    }
                }, 800);
                btn_login.setOnClickListener(null);
                btn_third_login.setOnClickListener(null);
                showThirdLoginDialog();
                change_login.startAnimation(mShowAnimation);
                change_login.setVisibility(View.VISIBLE);
            }
        });

        change_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //如果一开始选择的是第三方登陆
                if (!judge) {
                    hiddenThirdLoginDialog();
                    showNumberPasswordDialog();
                    login.startAnimation(mShowAnimation);
                    login.setVisibility(View.VISIBLE);
                    change_login.setClickable(false);
                    hiddenChangeLoginDialog();
                } else {
                    hiddenNumberPasswordDialog();
                    showThirdLoginDialog();
                    login.startAnimation(mHiddenAnimation);
                    login.setVisibility(View.INVISIBLE);
                    change_login.setClickable(false);
                    hiddenChangeLoginDialog();
                }
            }
        });
    }

    /**
     * 初始化控件
     */
    void initWidgets() {
        tencent = (ImageView) findViewById(R.id.tencent);
        wechat = (ImageView) findViewById(R.id.wechat);
        sina = (ImageView) findViewById(R.id.sina);
        btn_login = (Button) findViewById(R.id.btn_login);
        login= (Button) findViewById(R.id.login);
        btn_third_login = (Button) findViewById(R.id.btn_thirdlogin);
        third_login_layout = (LinearLayout) findViewById(R.id.third_login_layout);
        third_icon_layout = (LinearLayout) findViewById(R.id.third_icon_layout);
        number_layout = (LinearLayout) findViewById(R.id.number_layout);
        password_layout = (LinearLayout) findViewById(R.id.password_layout);
        change_login = (LinearLayout) findViewById(R.id.change_login);
        explosionField = ExplosionField.attach2Window(this);
        mShowAnimation = AnimationUtils.loadAnimation(this, R.anim.push_up_in);
        mHiddenAnimation = AnimationUtils.loadAnimation(this, R.anim.push_up_out);
        mFadeoutAnimation=AnimationUtils.loadAnimation(this,R.anim.fade_out);
        tencent.setOnClickListener(this);
        wechat.setOnClickListener(this);
        sina.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    /**
     * 隐藏学号密码输入框
     */
    void hiddenNumberPasswordDialog() {
        number_layout.startAnimation(mFadeoutAnimation);
        number_layout.setVisibility(View.INVISIBLE);
        password_layout.setVisibility(View.INVISIBLE);
        password_layout.startAnimation(mFadeoutAnimation);
    }

    /**
     * 显示学号密码输入框
     */
    void showNumberPasswordDialog() {
        change_login.setClickable(true);
        number_layout.startAnimation(mShowAnimation);
        number_layout.setVisibility(View.VISIBLE);
        password_layout.startAnimation(mShowAnimation);
        password_layout.setVisibility(View.VISIBLE);
    }

    /**
     * 显示使用第三方登陆对话框
     */
    void showThirdLoginDialog() {
        change_login.setClickable(true);
        third_login_layout.setAnimation(mShowAnimation);
        third_login_layout.setVisibility(View.VISIBLE);
        third_icon_layout.setAnimation(mShowAnimation);
        third_icon_layout.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏第三方登陆对话框
     */
    void hiddenThirdLoginDialog() {
        third_login_layout.setAnimation(mHiddenAnimation);
        third_login_layout.setVisibility(View.INVISIBLE);
        third_icon_layout.setVisibility(View.INVISIBLE);
        third_icon_layout.setAnimation(mHiddenAnimation);
    }

    /**
     * 显示切换登陆方式对话框
     */
    void showChangeLoginDoalog() {
        change_login.startAnimation(mShowAnimation);
        change_login.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏切换登陆方式对话框
     */
    void hiddenChangeLoginDialog() {
        change_login.setVisibility(View.INVISIBLE);
        change_login.startAnimation(mHiddenAnimation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tencent:
                Toast.makeText(LoginActivity.this, "tencent", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wechat:
                Toast.makeText(LoginActivity.this, "wechat", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sina:
                Toast.makeText(LoginActivity.this, "sina", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login:
                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
        }
    }
}
