package com.muyang.campus;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import tyrantgit.explosionfield.ExplosionField;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login, btn_third_login;
    private ExplosionField explosionField;
    private LinearLayout third_login_layout, third_icon_layout, change_login;
    private Animation mShowAnimation, mHiddenAnimation;
    private boolean judge=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_third_login = (Button) findViewById(R.id.btn_thirdlogin);
        third_login_layout = (LinearLayout) findViewById(R.id.third_login_layout);
        third_icon_layout = (LinearLayout) findViewById(R.id.third_icon_layout);
        change_login = (LinearLayout) findViewById(R.id.change_login);
        explosionField = ExplosionField.attach2Window(this);
        mShowAnimation = AnimationUtils.loadAnimation(this, R.anim.push_up_in);
        mHiddenAnimation = AnimationUtils.loadAnimation(this, R.anim.push_up_out);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judge=false;
                explosionField.explode(btn_third_login);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        explosionField.explode(btn_login);
                        judge=true;
                    }
                }, 800);
                btn_login.setOnClickListener(null);
                btn_third_login.setOnClickListener(null);
                change_login.startAnimation(mShowAnimation);
                change_login.setVisibility(View.VISIBLE);
            }
        });
        btn_third_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judge=false;
                explosionField.explode(btn_login);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        explosionField.explode(btn_third_login);
                        judge=false;
                    }
                }, 800);
                btn_login.setOnClickListener(null);
                btn_third_login.setOnClickListener(null);
                third_login_layout.setAnimation(mShowAnimation);
                third_login_layout.setVisibility(View.VISIBLE);
                third_icon_layout.setAnimation(mShowAnimation);
                third_icon_layout.setVisibility(View.VISIBLE);
                change_login.startAnimation(mShowAnimation);
                change_login.setVisibility(View.VISIBLE);
            }
        });

        change_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!judge) {
                    change_login.setClickable(false);
                    third_login_layout.setVisibility(View.INVISIBLE);
                    third_login_layout.startAnimation(mHiddenAnimation);
                    third_icon_layout.setVisibility(View.INVISIBLE);
                    third_icon_layout.setAnimation(mHiddenAnimation);
                    change_login.setVisibility(View.INVISIBLE);
                    change_login.startAnimation(mHiddenAnimation);
                    third_icon_layout.setVisibility(View.INVISIBLE);
                    third_icon_layout.setAnimation(mHiddenAnimation);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btn_login.setVisibility(View.VISIBLE);
                            btn_third_login.setVisibility(View.VISIBLE);
                        }
                    }, 500);
                }else{
                    change_login.setClickable(false);
                    Toast.makeText(LoginActivity.this,"Text",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
