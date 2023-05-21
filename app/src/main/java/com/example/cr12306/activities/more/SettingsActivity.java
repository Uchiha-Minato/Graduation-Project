package com.example.cr12306.activities.more;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cr12306.R;
import com.example.cr12306.activities.crlines.LinesActivity;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageButton back_setting;
    public Button btn_login, btn_about, btn_news, btn_lines;//btn_trans;

    public Intent intent_settings = new Intent();

    public int resultCode;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //更新界面：登陆成功
        if(resultCode == 1) {
            btn_login.setText(data.getStringExtra("username"));
        }
    }

    /**
     * 初始化控件
     * */
    private void initViews(){
        back_setting = findViewById(R.id.back_setting);
        btn_about = findViewById(R.id.btn_about);
        btn_login = findViewById(R.id.btn_login);
        btn_news = findViewById(R.id.btn_news);
        btn_lines = findViewById(R.id.btn_lines);
        //btn_trans = findViewById(R.id.btn_trans);

        back_setting.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_about.setOnClickListener(this);
        btn_news.setOnClickListener(this);
        btn_lines.setOnClickListener(this);
        //btn_trans.setOnClickListener(this);
    }

    /**
     * 设置点击事件
     * */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_setting -> SettingsActivity.this.finish();
            case R.id.btn_login -> {
                intent_settings.setClass(SettingsActivity.this, LoginActivity.class);
                resultCode = 1;
                startActivityForResult(intent_settings, resultCode);
            }
            case R.id.btn_about -> showAboutDialog();
            case R.id.btn_news -> {
                intent_settings.setClass(SettingsActivity.this, NewsActivity.class);
                startActivity(intent_settings);
                SettingsActivity.this.finish();
            }
            case R.id.btn_lines -> {
                intent_settings.setClass(this, LinesActivity.class);
                startActivity(intent_settings);
                this.finish();
            }
        }
    }

    /**
     * 对话框显示与设置
     * */
    private void showAboutDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsActivity.this);
        dialog.setIcon(R.drawable.about);
        dialog.setTitle("关于");
        dialog.setMessage("这是一个陋野程序");
        dialog.setPositiveButton("好", (dialogInterface, i) -> Toast.makeText(SettingsActivity.this, "彳亍", Toast.LENGTH_SHORT).show());
        dialog.show();
    }
}
