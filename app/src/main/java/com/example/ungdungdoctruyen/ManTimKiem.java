package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ungdungdoctruyen.adapter.adapterTruyen;
import com.example.ungdungdoctruyen.database.databasedoctruyen;
import com.example.ungdungdoctruyen.model.Truyen;

import java.util.ArrayList;

public class ManTimKiem extends AppCompatActivity {

    ListView listView;
    EditText edt;

    ArrayList<Truyen> TruyenArrayList;
    ArrayList<Truyen> arrayList;


    adapterTruyen adapterTruyen;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_tim_kiem);

        edt = findViewById(R.id.timkiem);
        listView= findViewById(R.id.lisviewTimKiem);

        initList();

       // bắt clock cho item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(ManTimKiem.this, MainNoiDung.class);
                String tent= arrayList.get(i).getTenTruyen();
                String noidungt= arrayList.get(i).getNoiDung();
                intent.putExtra("tentruyen", tent);
                intent.putExtra("noidung", noidungt);
                startActivity(intent);
            }
        });

        // edit text search
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());
            }
        });

    }
    // search
    private void filter(String text){
     // Xóa dữ liệu mảng
     arrayList.clear();
     ArrayList<Truyen> filteredList= new ArrayList<>();

     for(Truyen item :TruyenArrayList){
         if(item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
             // Thêm item cào filteredList
             filteredList.add(item);

             //thêm vào mảng
             arrayList.add(item);

         }
     }
     adapterTruyen.filterList(filteredList);
    }

    // phương thức lấy dữ liệu
    private void initList() {
        TruyenArrayList =new ArrayList<>();
        arrayList = new ArrayList<>();

        databasedoctruyen= new databasedoctruyen(this);
        Cursor cursor= databasedoctruyen.getdataTruyen();

        while(cursor.moveToNext()){
            int id= cursor.getInt(0);
            String tentruyen= cursor.getString(1);
            String noidung= cursor.getString(2);
            String anh = cursor.getString(3);
            int tk= cursor.getInt(4);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh, tk));

            arrayList.add(new Truyen(id,tentruyen,noidung,anh,tk));
            adapterTruyen= new adapterTruyen(getApplicationContext(),TruyenArrayList);
            listView.setAdapter(adapterTruyen);

        }
        cursor.moveToFirst();
        cursor.close();
    }
}