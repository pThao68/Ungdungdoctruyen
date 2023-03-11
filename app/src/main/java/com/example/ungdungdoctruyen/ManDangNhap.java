package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ungdungdoctruyen.database.databasedoctruyen;

public class ManDangNhap extends AppCompatActivity {

    // tạo biến cho màn đăng nhập
    EditText edtTaiKhoan,edtMatKhau;
    Button btnDangNhap, btnDangKy;

    // tạo đối tượng cho databasedoctruyen
    databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_nhap);

        AnhXa();
        databasedoctruyen= new databasedoctruyen(this);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ManDangNhap.this, manDangKy.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gawbs cá biến là giá trị nhaạp vào từ editText
                String tentaikhoan= edtTaiKhoan.getText().toString();
                String matkhau= edtMatKhau.getText().toString();

                // sử dụng con trẻ để lấy dữ liệu, goi tới getDtaa() dể lấy tất cả tài khoản ở SQL
                Cursor cursor = databasedoctruyen.getData();
                // thực hiện vòng lặp để lấy dữ liệu từ cursor với moveToNet() di chuyển tiếp
                while(cursor.moveToNext()){
                    // lấy dữ liệu và gắn cvaof biến, dữ liệu tài khoản ở ô 1 và mật khẩu ở ô 2, ô 3 là idTK, ô 4 là email, ô 5 là phân quyên
                    String dataTenTK = cursor.getString(1);
                    String dataMK = cursor.getString((2));// columindex

                    // nếu tài khaonr và mật khẩu nhập vào từ bàn phím khớp với ở database
                    if(dataTenTK.equals(tentaikhoan)== false && dataMK.equals(matkhau)== false){
                        Toast.makeText(ManDangNhap.this, "Bạn nhập sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();


                        cursor.moveToLast();
                    }
                    else if(dataTenTK.equals(tentaikhoan)== true && dataMK.equals(matkhau)== true) {

                        int phanquyen = cursor.getInt(4);
                        int id= cursor.getInt(0);
                        String email= cursor.getString(3);
                        String tentk= cursor.getString(1);

                        // chuyển qua màn hình MainActivity
                        Intent intent= new Intent(ManDangNhap.this, MainActivity.class);

                        // gửi dữ liệu qua Activity là MainActivity
                        intent.putExtra("phanq",phanquyen);
                        intent.putExtra("id",id);
                        intent.putExtra("email", email);
                        intent.putExtra("tentaikhoan",tentk);
                        startActivity(intent);
                        cursor.moveToLast();
                    }


                }
                // thực hiện trả cursor về đầu
                cursor.moveToFirst();
                // đóng khi không dùng
                cursor.close();

            }
        });
    }

    private void AnhXa() {
        edtMatKhau= findViewById(R.id.matKhau);
        edtTaiKhoan= findViewById(R.id.taiKhoan);
        btnDangKy= findViewById(R.id.dangky);
        btnDangNhap= findViewById(R.id.dangnhap);
    }


}