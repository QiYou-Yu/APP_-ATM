package com.example.app_hw4;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Secondary extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_code2;
    private Button btn_confirm,btn_clear;
    private String[] password=new String[2];
    private String[] del=new String[1];
    private int a,b;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        editText_code2 = findViewById(R.id.editText_code2);
        btn_confirm=findViewById((R.id.btn_confirm));
        btn_clear=findViewById((R.id.btn_clear));
        Welcome_Message();
        getSharedPreferences();
    }
    private void Welcome_Message() {
        String message;
        preferences = getSharedPreferences("preFile", MODE_PRIVATE);
        message = "歡迎使用雲科銀行ATM,請在主畫面進行初始設定";
        new AlertDialog.Builder(Secondary.this)
                .setTitle("簡易ATM程式")
                .setMessage(message)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
//讀取除存檔資料
    private boolean getSharedPreferences()
    {
        password[1]=preferences.getString("code","");
        return true;
    }

//檢查EditText內容是否為空白
    private boolean Check_Empty()
    {
        password[0]=editText_code2.getText().toString();
        a=Integer.valueOf(password[0]);//第二頁要登入所輸入的密碼
        b=Integer.valueOf(password[1]);//在第一頁中輸入的密碼

        if (a==b)//如果密碼相等，會傳true
        {
            return true;
        }
        return false;
    }
//按鈕共同監聽事件
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                if (Check_Empty()) //密碼輸入正確
                {
                    Toast.makeText(Secondary.this, "密碼輸入正確", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent();
                    intent1.setClass(Secondary.this, Thirdth.class);//跳至第三頁交易畫面
                    startActivity(intent1);
                }
                else//密碼輸入錯誤
                    {
                             new AlertDialog.Builder(Secondary.this)
                            .setTitle("密碼錯誤")
                            .setMessage("請重新輸入密碼")
                            .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editText_code2.setText("");
                                }
                            })
                            .show();
                     }
                break;
            case R.id.btn_clear:
                new AlertDialog.Builder(Secondary.this)
                        .setTitle("警告")
                        .setMessage("確定要清除資料")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                del[0] = ("");
                                preferences.edit()
                                        .putString("money", del[0])
                                        .putString("code", del[0])
                                        .apply();
                                Intent intent1 = new Intent();
                                intent1.setClass(Secondary.this, MainActivity.class);
                                startActivity(intent1);
                                finish();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
        }
    }
}

