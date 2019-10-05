package com.example.stroage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
    }

    public void onClickCreateDB(View view){
        DBHelper dbHelper=new DBHelper(this,1);

        //获取连接
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        Toast.makeText(this,"创建数据库",Toast.LENGTH_LONG).show();
    }

    //更新数据库
    public void onClickUpdateDB(View view){
        DBHelper dbHelper=new DBHelper(this,2);

        //获取连接
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        Toast.makeText(this,"更新数据库",Toast.LENGTH_LONG).show();
    }

    //添加记录
    public void onClickInsert(View view){
        //得到连接
        DBHelper dbHelper=new DBHelper(this,2);
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        //执行insert
        ContentValues values=new ContentValues();
        values.put("name","tom15");
        values.put("age",16);
        long id=database.insert("person",null,values);
        //关闭
        database.close();
        //提示
        Toast.makeText(this,"id="+id,Toast.LENGTH_SHORT).show();
    }


    public void onClickUpdate(View view){
        DBHelper dbHelper=new DBHelper(this,2);
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        //执行update
        ContentValues values=new ContentValues();
        values.put("name","jack");
        values.put("age",13);
        int updateCount=database.update("person",values,"_id=?",new String[]{"4"});
        database.close();
        Toast.makeText(this,"updateCount"+updateCount,Toast.LENGTH_SHORT).show();
    }
    public void onClickDelete(View view){
        //得到连接
        DBHelper dbHelper=new DBHelper(this,2);
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        //执行delete    delete from person where _id=2
        int deleteCount=database.delete("person","id=2",null);
        //关闭
        database.close();
        //提示
        Toast.makeText(this,"deleteCount"+deleteCount,Toast.LENGTH_SHORT).show();

    }
    public void onClickQurry(View view){
        DBHelper dbHelper=new DBHelper(this,2);
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        //执行query    select * from person
        Cursor cursor=database.query("person",null,"_id=?",null,null,null,null);
        //得到匹配的总记录数
        int count=cursor.getCount();
        //取出cursor中所有的数据
        while (cursor.moveToNext()){
            //_id
            int id=cursor.getInt(0);
            //name
            String name=cursor.getString(1);
            //age
            int age=cursor.getInt(cursor.getColumnIndex("age"));
        }
        cursor.close();
        database.close();
        Toast.makeText(this,"count"+count,Toast.LENGTH_SHORT).show();

    }

    //测试事务处理
    //update person set age=13 where _id=1
    //update person set age=15 where _id=3
    //一个功能中对数据库进行的多个数据库，要么都成功，要么都失败
    /*
    * 事务处理的3步
    * 1.开启事务（获取连接后）
    * 2.设置事务成功(在全部正常执行后)
    * 3.结束事务（finally中）*/
    public void onClickTransaction(View view){
        SQLiteDatabase database=null;
        try {
            DBHelper dbHelper=new DBHelper(this,2);
            database=dbHelper.getReadableDatabase();

            //1.开启事务（获取连接后）
            database.beginTransaction();

            //执行update    update person set age=13 where _id=1
            ContentValues values=new ContentValues();//轻量级数据存储类型，类似于HashTable，但只能存储int,string等数据类型，不能存储对象
            values.put("age",13);
            int update1=database.update("person",values,"_id=?",new String[]{"1"});


            //出了异常
            boolean flag=true;
            if (flag){
                throw new RuntimeException("出异常啦！");
            }

            ContentValues values2=new ContentValues();
            values.put("age",15);
            int update2=database.update("person",values2,"_id=?",new String[]{"3"});

            //2.设置事务成功(在全部正常执行后)
            database.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this ,"出异常啦！",Toast.LENGTH_SHORT).show();
        }
        finally {
            //3.结束事务（finally中）
            if (database!=null){
                database.endTransaction();

                database.close();
            }
        }


    }


}
