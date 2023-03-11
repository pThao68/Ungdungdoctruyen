package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ungdungdoctruyen.database.databasedoctruyen;
import com.example.ungdungdoctruyen.model.TaiKhoan;

public class manDangKy extends AppCompatActivity {

    EditText edtDKTaiKhoan, edtDKMatKhau, edtDKEmail;
    Button btnDKDangKy, btnDKDangNhap;
    databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_ky);

        databasedoctruyen= new databasedoctruyen(this);

        AnhXa();

        // CLick cho button dang ký
        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKMatKhau.getText().toString();
                String email= edtDKEmail.getText().toString();

                TaiKhoan taikhoan1 = CreateTaikhoan();
                if(taikhoan.equals("")|| matkhau.equals("")|| email.equals("")){
                    Log.e("Thong báo: ", "Chua nhập đày đủ thông tin");

                }
                // nếu đầu đủ thông tin nhập vào thì add tào khoản và database
                else {
                    databasedoctruyen.AddTaiKhoan(taikhoan1);
                    Toast.makeText(manDangKy.this, "Đăng ký thành công", Toast.LENGTH_LONG);

                }
            }
        });

        // trở về đăng nhập
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    // phuwwownt thức tạo tài khoản
    private TaiKhoan CreateTaikhoan(){
        String taikhoan = edtDKTaiKhoan.getText().toString();
        String matkhau = edtDKMatKhau.getText().toString();
        String email = edtDKEmail.getText().toString();
        int phanquyen =1;

        TaiKhoan tk = new TaiKhoan(taikhoan, matkhau,email,phanquyen);
         return tk;
    }
    private void AnhXa() {
        edtDKTaiKhoan= findViewById(R.id.dkTaiKhoan);
        edtDKMatKhau= findViewById(R.id.dkmatkhau);
        edtDKEmail= findViewById(R.id.dkemail);
        btnDKDangKy= findViewById(R.id.btnDangKy);
        btnDKDangNhap= findViewById(R.id.btnDangNhap);
    }


}