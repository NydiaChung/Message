package com.example.stroage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class OuterFileActivity extends AppCompatActivity {
    private EditText et_of_key;
    private EditText et_of_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outer_file);

        et_of_key=findViewById(R.id.et_of_key);
        et_of_value=findViewById(R.id.et_of_value);
    }


    public void save(View view) throws IOException {
        //1.判断sdcard状态，如果是挂载的状态则集序，否则提示
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //2.读取输入内容/文件名
            String fileName=et_of_key.getText().toString();
            String content=et_of_value.getText().toString();
            //3.得到指定文件的OutputStream
            //      得到sd卡下的files路径****需要存储权限
            String filesPath=getExternalFilesDir(null).getAbsolutePath();
            //      组成完整路径
            String filePath=filesPath+"/"+fileName;
            //      创建FileOutputStream
            FileOutputStream fileOutputStream=new FileOutputStream(filePath);
            //4.写数据
            fileOutputStream.write(content.getBytes("utf-8"));
            fileOutputStream.close();
            //5.提示
            Toast.makeText(this,"保存成功！",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"sd卡没有挂载",Toast.LENGTH_LONG).show();
        }
    }


    public void read(View view) throws IOException {
        //1.判断sdcard状态，如果是挂载的状态则集序，否则提示
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //2.读取输入内容
            String fileName=et_of_key.getText().toString();
            //3.得到指定文件的OutputStream
            //      得到sd卡下的files路径****需要存储权限
            String filesPath=getExternalFilesDir(null).getAbsolutePath();
            //      组成完整路径
            String filePath=filesPath+"/"+fileName;
            //      创建FileInputStream
            FileInputStream fileInputStream= new FileInputStream(filePath);
            //4.读取数据，成String
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            byte[] buffer=new byte[1024];
            int len = -1;
            while((len=fileInputStream.read(buffer))!=-1){
                byteArrayOutputStream.write(buffer,0,len);
            }
            String content=byteArrayOutputStream.toString();
            //5.显示
            Toast.makeText(this,"读取成功！",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"sd卡没有挂载",Toast.LENGTH_LONG).show();
        }
    }
    public void save2(View view) throws IOException {
        //1.判断sdcard状态，如果是挂载的状态则集序，否则提示
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //2.读取输入内容/文件名
            String fileName=et_of_key.getText().toString();
            String content=et_of_value.getText().toString();
            //3.得到指定文件的OutputStream
                // /storage/sdcard/
            String sdPath=Environment.getExternalStorageDirectory().getAbsolutePath();
                // /storage/sdcard/StroageTest/（创建文件夹）
            File file=new File(sdPath+"StroageTest");
            if (!file.exists()){
                file.mkdirs();//创建文件夹
            }
                //// /storage/sdcard/Stroage/xxx.txt
            String filePath=sdPath+"/StroageTest/"+fileName;
                //创建输出流
            FileOutputStream fileOutputStream=new FileOutputStream(filePath);
            //4.写数据
            fileOutputStream.write(content.getBytes("utf-8"));
            fileOutputStream.close();
            //5.提示
            Toast.makeText(this,"保存成功！",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"sd卡没有挂载",Toast.LENGTH_LONG).show();
        }
    }
    public void read2(View view) throws IOException {
        //1.判断sdcard状态，如果是挂载的状态则集序，否则提示
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //2.读取输入内容
            String fileName=et_of_key.getText().toString();
            //3.得到指定文件的OutputStream
            // /storage/sdcard/
            String sdPath=Environment.getExternalStorageDirectory().getAbsolutePath();
            // /storage/sdcard/StroageTest/（创建文件夹）
            File file=new File(sdPath+"StroageTest");
            //// /storage/sdcard/Stroage/xxx.txt
            String filePath=sdPath+"/StroageTest/"+fileName;
            //创建输入流
            FileInputStream fileInputStream=new FileInputStream(filePath);
            //4.读取数据，成String
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            byte[] buffer=new byte[1024];
            int len = -1;
            while((len=fileInputStream.read(buffer))!=-1){
                byteArrayOutputStream.write(buffer,0,len);
            }
            String content=byteArrayOutputStream.toString();
            //5.显示
            Toast.makeText(this,"读取成功！",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"sd卡没有挂载",Toast.LENGTH_LONG).show();
        }
    }
}
