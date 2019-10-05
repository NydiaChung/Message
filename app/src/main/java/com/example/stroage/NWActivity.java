package com.example.stroage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NWActivity extends AppCompatActivity {

    //视图初始化
    private EditText et_network_url;
    private EditText et_network_result;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nw);

        et_network_url=findViewById(R.id.et_network_url);
        et_network_result=findViewById(R.id.et_network_result);

        //Volley创建请求队列
        queue= Volley.newRequestQueue(this);
    }


    //使用httpUrlConnection提交get请求
    public void testConnectionGet(View view){
    /*1.显示ProgressDialog
    2.启动分线程
    3.在分线程，发送请求，得到响应数据
        得到path，并带上参数name=Tom&age=11
        创建url对象
        打开链接，得到对象
        设置请求方式，连接超时，读写超时
        连接服务器
        发请求，得到响应数据
            得到响应码，必须时200才读取
            得到InputStream，并读取成String
        断开连接
    4.在主线程，显示得到的结果，移除dialog
        */

        //1.显示ProgressDialog
        final ProgressDialog dialog=ProgressDialog.show(this,null,"正在请求中");
        //2.启动分线程
        new Thread(){
            //3.在分线程，发送请求，得到响应数据
            public void run(){
                try {
                // 得到path，并带上参数name=Tom1&age=11
                String path=et_network_url.getText().toString()+"?name=Tom1&age=11";
                //创建url对象
                    URL url=new URL(path);
                    //打开链接，得到对象
                    HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                    //设置请求方式，连接超时，读写超时
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(6000);
                    //       连接服务器
                    connection.connect();
                    //发请求，得到响应数据
                        // 得到响应码，必须时200才读取
                    int responseCode=connection.getResponseCode();
                    if (responseCode==200) {
                        //得到InputStream，并读取成String
                        InputStream inputStream=connection.getInputStream();
                        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len =-1;
                        while((len=inputStream.read(buffer))!=-1){
                            byteArrayOutputStream.write(buffer,0,len);
                        }
                        final String result=byteArrayOutputStream.toString();

                        byteArrayOutputStream.close();
                        inputStream.close();

                        //4.在主线程，显示得到的结果，移除dialog
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et_network_result.setText(result);
                                dialog.dismiss();
                            }
                        });

                    }
                    //断开连接
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    //使用httpUrlConnection提交post请求
     /*1.显示ProgressDialog
    2.启动分线程
    3.在分线程，发送请求，得到响应数据
        得到path
        创建url对象
        打开链接，得到对象
        设置请求方式，连接超时，读写超时
        连接服务器
        发请求，得到响应数据
            得到输出流，写请求体：name=Tom&age=11
            得到响应码，必须时200才读取
            得到InputStream，并读取成String
        断开连接
    4.在主线程，显示得到的结果，移除dialog
        */
    public void testConnectionPost(View view){
        //1.显示ProgressDialog
        final ProgressDialog dialog=ProgressDialog .show(this,null,"正在加载中...");
        //2.启动分线程
        new Thread(new Runnable() {
            @Override

            //3.在分线程，发送请求，得到响应数据
            public void run(){
                try {
                    // 得到path，并带上参数name=Tom1&age=11
                    String path=et_network_url.getText().toString()+"?name=Tom1&age=11";
                    //创建url对象
                    URL url=new URL(path);
                    //打开链接，得到对象
                    HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                    //设置请求方式，连接超时，读写超时
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(6000);
                    //       连接服务器
                    connection.connect();
                    //发请求，得到响应数据
                        //得到输出流，写请求体：name=Tom&age=11
                    OutputStream outputStream=connection.getOutputStream();
                    String data="name=Tom&age=11";
                    outputStream.write(data.getBytes("utf-8"));
                     // 得到响应码，必须时200才读取
                    int responseCode=connection.getResponseCode();
                    if (responseCode==200) {
                        //得到InputStream，并读取成String
                        InputStream inputStream=connection.getInputStream();
                        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len =-1;
                        while((len=inputStream.read(buffer))!=-1){
                            byteArrayOutputStream.write(buffer,0,len);
                        }
                        final String result=byteArrayOutputStream.toString();

                        byteArrayOutputStream.close();
                        inputStream.close();

                        //4.在主线程，显示得到的结果，移除dialog
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et_network_result.setText(result);
                                dialog.dismiss();
                            }
                        });

                    }
                    outputStream.close();
                    //断开连接
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }



    //使用HttpClient提交get请求
    public void testHttpClientGet(View view){
  /*1.显示ProgressDialog
    2.启动分线程
    3.在分线程，发送请求，得到响应数据
        得到path，并带上参数name=Tom&age=11
        创建url对象
        打开链接，得到对象
        设置请求方式，连接超时，读写超时
        连接服务器
        发请求，得到响应数据
            得到响应码，必须时200才读取
            得到InputStream，并读取成String
        断开连接
    4.在主线程，显示得到的结果，移除dialog
        */

        //1.显示ProgressDialog
        final ProgressDialog dialog=ProgressDialog.show(this,null,"正在请求中");
        //2.启动分线程
        new Thread(){


            //3.在分线程，发送请求，得到响应数据
            public void run(){
                try {
                    // 得到path，并带上参数name=Tom1&age=11
                    String path=et_network_url.getText().toString()+"?name=Tom1&age=11";
                    //创建Client对象
                    HttpClient httpClient= new DefaultHttpClient();
                    //设置超时
                    HttpParams params=httpClient.getParams();
                    HttpConnectionParams.setConnectionTimeout(params,5000);
                    HttpConnectionParams.setSoTimeout(params,5000);

                    //创建请求对象
                    HttpGet request = new HttpGet(path);
                    //执行请求对象，得到响应对象
                    HttpResponse response=httpClient.execute(request);

                    int statusCode=response.getStatusLine().getStatusCode();
                    if(statusCode==200){

                    //得到响应文本
                        HttpEntity entity=response.getEntity();
                        final String result= EntityUtils.toString(entity);

                    //4.在主线程，显示得到的结果，移除dialog
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            et_network_result.setText(result);

                            dialog.dismiss();
                        }
                    });
                        //断开连接
                        httpClient.getConnectionManager().shutdown();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                    //如果出了异常要移除dialog
                    dialog.dismiss();
                }
            }
        }.start();
    }




    //使用HttpClient提交post请求
    public void testHttpClientPost(View view){
/*1.显示ProgressDialog
    2.启动分线程
    3.在分线程，发送请求，得到响应数据
        得到path，并带上参数name=Tom&age=11
        创建url对象
        打开链接，得到对象
        设置请求方式，连接超时，读写超时
        连接服务器
        发请求，得到响应数据
            得到响应码，必须时200才读取
            得到InputStream，并读取成String
        断开连接
    4.在主线程，显示得到的结果，移除dialog
        */

        //1.显示ProgressDialog
        final ProgressDialog dialog=ProgressDialog.show(this,null,"正在请求中");
        //2.启动分线程
        new Thread(){


            //3.在分线程，发送请求，得到响应数据
            public void run(){
                try {
                    // 得到path，并带上参数name=Tom1&age=11
                    String path=et_network_url.getText().toString();
                    //创建Client对象
                    HttpClient httpClient= new DefaultHttpClient();
                    //设置超时
                    HttpParams params=httpClient.getParams();
                    HttpConnectionParams.setConnectionTimeout(params,5000);
                    HttpConnectionParams.setSoTimeout(params,5000);

                    //创建请求对象
                    HttpPost request = new HttpPost(path);
                    //设置请求体
                    List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
                    parameters.add(new BasicNameValuePair("name","Tom4"));
                    parameters.add(new BasicNameValuePair("age","14"));

                    HttpEntity entity=new UrlEncodedFormEntity(parameters);
                    request.setEntity(entity);
                    //执行请求对象，得到响应对象
                    HttpResponse response=httpClient.execute(request);

                    int statusCode=response.getStatusLine().getStatusCode();
                    if(statusCode==200){
                        //得到响应文本
                        entity=response.getEntity();
                        final String result= EntityUtils.toString(entity);

                        //4.在主线程，显示得到的结果，移除dialog
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et_network_result.setText(result);

                                dialog.dismiss();
                            }
                        });
                        //断开连接
                        httpClient.getConnectionManager().shutdown();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                    //如果出了异常要移除dialog
                    dialog.dismiss();
                }
            }
        }.start();
    }



    //使用Volley提交get请求
    /*1.创建请求队列对象（一次）
    2.创建请求对象StringRequest
    3.将请求添加到队列中*/
    public void testVolleyGet(View view){
        final ProgressDialog dialog=ProgressDialog.show(this,null,"正在请求中");

        //2.创建请求对象StringRequest
        String path=et_network_url.getText().toString()+"?name=Tom5&age=15";
        StringRequest request=new StringRequest(path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//在主线程执行
                et_network_result.setText(response);
                dialog.dismiss();
            }
        },null);
        //3.将请求添加到队列中
        queue.add(request);
    }

    //使用Volley提交post请求
    public void testVolleyPost(View view){
        final ProgressDialog dialog=ProgressDialog.show(this,null,"正在请求中");

        //2.创建请求对象StringRequest
        String path=et_network_url.getText().toString();
        StringRequest request=new StringRequest(path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//在主线程执行
                et_network_result.setText(response);
                dialog.dismiss();
            }
        },null);
        //3.将请求添加到队列中
        queue.add(request);
    }
}
