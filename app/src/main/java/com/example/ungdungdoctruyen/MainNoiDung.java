package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainNoiDung extends AppCompatActivity {

    TextView txtTenTruyen, txtNoiDung;
    Button btntroveHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_noi_dung);

        txtNoiDung= findViewById(R.id.noidung);
        txtTenTruyen= findViewById(R.id.TenTruyen);
        btntroveHome= findViewById(R.id.troveHome);


        btntroveHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(MainNoiDung.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        // Lấy dữ liệu
        Intent intent= getIntent();
        String tentruyen= intent.getStringExtra("tentruyen");
        String noidung= intent.getStringExtra("noidung");

        txtTenTruyen.setText(tentruyen);
        txtNoiDung.setText(noidung);

        // cho phép cuộn nội dung chuyện
        txtNoiDung.setMovementMethod(new ScrollingMovementMethod());
    }
}