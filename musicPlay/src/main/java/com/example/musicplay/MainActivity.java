package com.example.musicplay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<HashMap<String, Object>> list;
    int[] imgs={R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,
            R.drawable.p6,R.drawable.p7,R.drawable.p8,R.drawable.p9,R.drawable.p10};
    String[] titles={"天后","下一站","雾里看花","爱拼","最后一首歌","刘德华",
            "刘德华","刘德华","刘德华","刘德华"};
    String[] infos={"王菲","张学友","金瑾","刘德华","刘德华","刘德华",
            "刘德华","刘德华","刘德华","刘德华"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);
        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /*GridLayoutManager girdLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(girdLayoutManager);*/
       /* StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);*/

        RecyclerViewAdapter adapter =   new RecyclerViewAdapter(this,list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "click " + position, Toast.LENGTH_SHORT).show();

                Intent intent =  new Intent(MainActivity.this, MusicActivity.class);
                startActivityForResult(intent, 0);

            }
        });

    }

    private void initData() {
        list = new ArrayList<>();

        for (int i = 0; i < imgs.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("img", imgs[i] );
            map.put("title", titles[i]);
            map.put("info", infos[i]);
            list.add(map);
        }

    }
}