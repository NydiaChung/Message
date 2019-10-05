package com.example.stroage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//测试sp存储的界面

public class SpActivity extends AppCompatActivity {

    private EditText et_sp_key;
    private EditText et_sp_value;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        et_sp_key=findViewById(R.id.et_sp_key);
        et_sp_value=findViewById(R.id.et_sp_value);

        //得到sp对象
        sp=getSharedPreferences("123", Context.MODE_PRIVATE);

    }

    public void save(View view) {
        //2.得到editor对象
        SharedPreferences.Editor edit=sp.edit();
        //3.得到输入的key/value
        String key=et_sp_key.getText().toString();
        String value=et_sp_value.getText().toString();
        //4.使用editor保存key-value
        edit.putString(key,value).commit();
        //5.提示
        Toast.makeText(this,"保存完成！",Toast.LENGTH_LONG).show();
    }
    public void read(View view){
        //1.得到输入的key
        String key=et_sp_key.getText().toString();
        //2.根据key读取value
        String value=sp.getString(key,null);
        //3.显示
        if (value==null){
            Toast.makeText(this,"没有对应的value",Toast.LENGTH_SHORT).show();
        }else{
            et_sp_value.setText(value);
        }

    }
}
