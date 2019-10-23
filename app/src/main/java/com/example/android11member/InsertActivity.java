package com.example.android11member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {

    EditText ed_id;
    EditText ed_pw;
    EditText ed_name;
    EditText ed_tel;
    Button btn_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        ed_id = findViewById(R.id.ed_id);
        ed_pw = findViewById(R.id.ed_pw);
        ed_name = findViewById(R.id.ed_name);
        ed_tel = findViewById(R.id.ed_tel);
        btn_insert = findViewById(R.id.btn_insert);

        final SQLiteDatabase mDatabase = openOrCreateDatabase("member.db", MODE_PRIVATE, null);
        mDatabase.execSQL("create table if not exists member(num integer primary key autoincrement, id text, pw text, name text, tel text)");

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("id", String.valueOf(ed_id.getText()));
                values.put("pw", String.valueOf(ed_pw.getText()));
                values.put("name", String.valueOf(ed_name.getText()));
                values.put("tel", String.valueOf(ed_tel.getText()));
                long result = mDatabase.insert("member",null, values);
                if(result>0) Log.i("testLog","insert successed..."+result);
                else Log.i("testLog","insert failed...");

                finish();
            }
        });

    }
}
