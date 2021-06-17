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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_code1,editText_money1;
    private Button btn_stored;
    private SharedPreferences preferences;
    private String[] input = new String[2];
    private int a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_code1=findViewById((R.id.editText_code1));
        editText_money1=findViewById(R.id.editText_money1);
        btn_stored=findViewById(R.id.btn_stored);
        preferences = getSharedPreferences("preFile", MODE_PRIVATE);
        input[0]=preferences.getString("money","");//金額
        input[1]=preferences.getString("code","");//密碼
//如果有輸入密碼 ，進入第二頁
        if(!(input[1]).equals("")){
            Intent intent1 = new Intent();
            intent1.setClass(MainActivity.this,Secondary.class);
            startActivity(intent1);
        }
        Welcome_Message();//歡迎訊息函示
    }
//歡迎訊息函示
    private void Welcome_Message() {
        String message;
        preferences = getSharedPreferences("preFile", MODE_PRIVATE);
        message = "歡迎使用雲科銀行ATM,請在主畫面進行初始設定";

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("簡易ATM程式")
                .setMessage(message)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
//檢查EditText內容是否為空白
    private boolean Check_Empty()
    {
        input[0]=editText_money1.getText().toString();//金額
        input[1]=editText_code1.getText().toString();//密碼
        for(int i=0;i<input.length;i++)
        {
            if (input[i].isEmpty() || input[i] == null)
            {
                return false;
            }
        }
        return true;
    }
//讀取除存檔資料
    private boolean getSharedPreferences()
    {
        input[0]=preferences.getString("money","");
        input[1]=preferences.getString("code","");
        return true;
    }
//按鈕共同監聽事件
    public void onClick(View v){
        switch(v.getId()){
         //按鈕選擇事件
            case R.id.btn_stored:
                if(Check_Empty())//檢查結果  密碼金額不為空值Check_Empty = true
                {
                    //儲存各項資料
                    preferences.edit()
                            .putString("money", input[0])
                            .putString("code", input[1])
                            .apply();
                    editText_money1.setText("");
                    editText_code1.setText("");
                    //讀取儲存檔
                    getSharedPreferences();
                    //顯示新增成功Toast
                    Toast.makeText(MainActivity.this, "設備儲存成功", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent();
                    intent1.setClass(MainActivity.this,Secondary.class);
                    startActivity(intent1);
                }
                else//Check_Empty = false
                {
                    Toast.makeText(MainActivity.this,"設定不完整，請檢查個欄位是否空白",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}