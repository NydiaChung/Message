package com.example.stroage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //测试sp存储
    public void onClickSP(View v){
        startActivity(new Intent(this,SpActivity.class));
    }

    //手机内部file存储
    public void onClickIF(View v){
        startActivity(new Intent(this,InnerFileActivity.class));
    }
    //手机外部file存储
    public void onClickOF(View v){
        startActivity(new Intent(this,OuterFileActivity.class));
    }
    //Sqlite数据库存储
    public void onClickDB(View v){
        startActivity(new Intent(this,DBActivity.class));
    }
    //远程服务器存储
    public void onClickNW(View v){
        startActivity(new Intent(this,NWActivity.class));
    }
}
