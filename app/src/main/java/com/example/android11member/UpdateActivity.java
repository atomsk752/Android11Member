package com.example.android11member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {

    EditText update_num;
    EditText update_id;
    EditText update_pw;
    EditText update_name;
    EditText update_tel;
    Button btn_update;
    Button btn_delete;
    String num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        update_num = findViewById(R.id.update_num);
        update_id = findViewById(R.id.update_id);
        update_pw = findViewById(R.id.update_pw);
        update_name = findViewById(R.id.update_name);
        update_tel = findViewById(R.id.update_tel);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        Intent intent = getIntent();
        num = intent.getStringExtra("num");
        final SQLiteDatabase mDatabase = openOrCreateDatabase("member.db", MODE_PRIVATE, null);

        mDatabase.execSQL("create table if not exists member(num integer primary key autoincrement, id text, pw text, name text, tel text)");

        showItem();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("id", String.valueOf(update_id.getText()));
                values.put("pw", String.valueOf(update_pw.getText()));
                values.put("name", String.valueOf(update_name.getText()));
                values.put("tel", String.valueOf(update_tel.getText()));
                mDatabase.update("member", values, "num=?", new String[]{num});
                Intent intent = new Intent(UpdateActivity.this, ListAllActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int result = mDatabase.delete("member","num=?",new String[]{num});
                if(result>0){
                    Log.i("testLog","delete successed..."+result);
                    Intent intent = new Intent(UpdateActivity.this, ListAllActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                } else{
                    Log.i("testLog","delete failed...");
                }


            }
        });


    }


    public void showItem(){
        final SQLiteDatabase mDatabase = openOrCreateDatabase("member.db", MODE_PRIVATE, null);

        mDatabase.execSQL("create table if not exists member(num integer primary key autoincrement, id text, pw text, name text, tel text)");

        Cursor c = mDatabase.query("member",null,"num=?", new String[]{num}, null,null,"num desc");
        while (c.moveToNext()){
            String num = String.valueOf(c.getInt(c.getColumnIndex("num")));
            String id = c.getString(c.getColumnIndex("id"));
            String pw = c.getString(c.getColumnIndex("pw"));
            String name = c.getString(c.getColumnIndex("name"));

            String tel = c.getString(c.getColumnIndex("tel"));
            update_num.setText(num);
            update_id.setText(id);
            update_pw.setText(pw);
            update_name.setText(name);
            update_tel.setText(tel);
        }
    }
}
