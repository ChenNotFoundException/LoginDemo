package com.httpdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rxz.androidhttpdemo.R;
import com.web.WebServicePost;

public class Register extends Activity implements View.OnClickListener {
	private EditText reguser,regpassword;
	private Button btn_reg;
	// 创建等待框
	private ProgressDialog dialog;
	// 返回的数据
	private String info;
	// 返回主线程更新数据
	private static Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		reguser = (EditText)findViewById(R.id.reguser);
		regpassword = (EditText)findViewById(R.id.regpass);

        btn_reg = (Button)findViewById(R.id.reg);
        btn_reg.setOnClickListener(this);

		
	}

    @Override
    public void onClick(View v) {
        new Thread(new RegThread()).start();
    }

    private class RegThread implements Runnable{

        @Override
        public void run() {
            info = WebServicePost.executeHttpPost(reguser.getText().toString(),regpassword.getText().toString());

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(Register.this,info,Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            });
        }
    }
}
