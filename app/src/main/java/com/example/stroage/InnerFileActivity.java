package com.example.stroage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InnerFileActivity extends AppCompatActivity {

    private ImageView iv_if;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_file);

        iv_if=findViewById(R.id.iv_if);
    }

    public void save(View v) throws IOException {

       // 1.得到InputStream-->读取asserts下的logo.png
            //得到AssetManager
        AssetManager manager=getAssets();
            //读取文件
        InputStream inputStream=manager.open("th1.png");

        //2.得到OutputStream-->/data/data/packagename/files/logo.png
        FileOutputStream fileOutputStream=openFileOutput("th1.png", Context.MODE_PRIVATE);
        //3.边读边写
        byte[] buffer=new byte[1024];
        int len=-1;
        while((len= inputStream.read(buffer))!=-1){
            fileOutputStream.write(buffer,0,len);
        }
        fileOutputStream.close();
        inputStream.close();
       // 4.提示
        Toast.makeText(this,"保存完成！",Toast.LENGTH_LONG).show();
    }

    public void read(View v){

        //1.得到图片文件的路径
        String filePath=getFilesDir().getAbsolutePath();
        String imagePath=filePath+"/th1.png";
        //2.读取加载图片文件得到bitmap对象
        Bitmap bitmap= BitmapFactory.decodeFile(imagePath);
        //3.将其设置到imageView中显示
        iv_if.setImageBitmap(bitmap);
    }
}
