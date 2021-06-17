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
import android.widget.TextView;
import android.widget.Toast;


public class Thirdth extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_money2;
    private Button btn_withdraw, btn_deposit, btn_close;
    private TextView sum;
    private String[] money1 = new String[2];
    private String input2;
    private int a, b, c, d;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdth);
        editText_money2 = findViewById(R.id.editText_money2);
        btn_withdraw = findViewById((R.id.btn_withdraw));
        btn_deposit = findViewById((R.id.btn_deposit));
        btn_close = findViewById(R.id.btn_close);
        sum = findViewById(R.id.tv_sum);
        preferences = getSharedPreferences("preFile", MODE_PRIVATE);
        getSharedPreferences();
        sum.setText(money1[0]);
    }

    private void getSharedPreferences()
    {
        money1[0] = preferences.getString("money", "");
    }

    private boolean Check_Empty() {
        getSharedPreferences();
        input2 = editText_money2.getText().toString();
        if (input2.isEmpty()) {
            return false;
        }
        return true;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            //新增
            case R.id.btn_deposit:
                if (Check_Empty()) {
                    getSharedPreferences();
                    a = Integer.valueOf(input2);
                    b = Integer.valueOf(money1[0]);
                    c = a + b;
                    money1[1] = String.valueOf(c);
                    new AlertDialog.Builder(Thirdth.this)
                            .setTitle("存款成功")
                            .setMessage("可用餘額:" + c)
                            .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editText_money2.setText("");
                                    preferences.edit()
                                            .putString("money", money1[1])
                                            .apply();
                                }
                            })
                            .show();
                    sum.setText(money1[1]);
                }
                else
                {
                    Toast.makeText(Thirdth.this, "請輸入交易金額", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_withdraw:
                if (Check_Empty())
                {
                    getSharedPreferences();
                    a = Integer.valueOf(input2);
                    b = Integer.valueOf(money1[0]);
                    d = b - a;
                    money1[1] = String.valueOf(d);
                    if (d > 0)
                    {
                        new AlertDialog.Builder(Thirdth.this)
                                .setTitle("提款成功")
                                .setMessage("可用餘額:" + d)
                                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        editText_money2.setText("");
                                        preferences.edit()
                                                .putString("money", money1[1])
                                                .apply();
                                    }
                                })
                                .show();
                        sum.setText(money1[1]);
                    }
                    else
                    {
                        new AlertDialog.Builder(Thirdth.this)
                                .setTitle("警告")
                                .setMessage("可用餘額不足")
                                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        editText_money2.setText("");
                                    }
                                })
                                .show();
                    }
                }
                else
                {
                    Toast.makeText(Thirdth.this, "請輸入交易金額", Toast.LENGTH_SHORT).show();
                }
                break;
            case (R.id.btn_close):
                Intent intent1 = new Intent();
                intent1.setClass(Thirdth.this, Secondary.class);
                startActivity(intent1);
        }
    }
}





