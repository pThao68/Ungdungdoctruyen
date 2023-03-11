package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainThongtin extends AppCompatActivity {

    TextView txtThongtinapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thongtin);
        txtThongtinapp= findViewById(R.id.textviewThongtin);

        String thongtin= "Ứng dụng được phát hành bởi 'Thao '\n"+
                " Uy tín đạt 100%\n"+
                "Ok";

        txtThongtinapp.setText(thongtin);

    }
}