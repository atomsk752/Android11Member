package com.example.android11member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListAllActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> memberList;
    ListView listView;
    Button btn_back;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        listView = findViewById(R.id.listView);
        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        showList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListAllActivity.this, UpdateActivity.class);

                intent.putExtra("num", memberList.get(position).get("num"));
                intent.putExtra("id", memberList.get(position).get("id"));
                intent.putExtra("pw", memberList.get(position).get("pw"));
                intent.putExtra("name", memberList.get(position).get("name"));
                intent.putExtra("tel", memberList.get(position).get("tel"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

    }

    public void showList(){

        memberList = new ArrayList<HashMap<String,String>>();
        final SQLiteDatabase mDatabase = openOrCreateDatabase("member.db", MODE_PRIVATE, null);

        mDatabase.execSQL("create table if not exists member(num integer primary key autoincrement, id text, pw text, name text, tel text)");

        Cursor c = mDatabase.query("member",null,null, null, null,null,"num asc");
        while (c.moveToNext()){
            Log.i("testlog", "" + c.getInt(c.getColumnIndex("num")));
            Log.i("testlog", "" + c.getString(c.getColumnIndex("name")));
            String num = String.valueOf(c.getInt(c.getColumnIndex("num")));
            String id = c.getString(c.getColumnIndex("id"));
            String pw = c.getString(c.getColumnIndex("pw"));
            String name = c.getString(c.getColumnIndex("name"));
            String tel = c.getString(c.getColumnIndex("tel"));

            HashMap<String,String> member = new HashMap<String,String>();
            member.put("num", num);
            member.put("id", id);
            member.put("pw", pw);
            member.put("name", name);
            member.put("tel", tel);
            memberList.add(member);

        }

        adapter = new SimpleAdapter(this, memberList, R.layout.list_items,new String[]{"num","id","pw","name","tel"}
        ,new int[]{R.id.list_num,R.id.list_id,R.id.list_pw,R.id.list_name,R.id.list_tel});

        listView.setAdapter(adapter);



    }
}
