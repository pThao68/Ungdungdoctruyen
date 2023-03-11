package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ungdungdoctruyen.database.databasedoctruyen;
import com.example.ungdungdoctruyen.model.Truyen;

public class UpdateActivity extends AppCompatActivity {

 EditText updateTen, updateNoiDung;
 Button btnUpdate;
    com.example.ungdungdoctruyen.database.databasedoctruyen databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateTen = findViewById(R.id.UpdateTenTruyen);
        updateNoiDung= findViewById(R.id.Updatenoidung);

        btnUpdate= findViewById(R.id.capnhat);
        databasedoctruyen = new databasedoctruyen(this);

        Intent intent= getIntent();
        int id_ng = intent.getIntExtra("id_ng",0);
        String tentruyen= intent.getStringExtra("tentruyen");
        String noidung= intent.getStringExtra("noidung");
        String img= intent.getStringExtra("anht");
        int idt= (int) intent.getIntExtra("idt",0);

        updateTen.setText(tentruyen);
       updateNoiDung.setText(noidung);

//       updateTen.addTextChangedListener(new TextWatcher() {
//           @Override
//           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//           }
//
//           @Override
//           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//           }
//
//           @Override
//           public void afterTextChanged(Editable editable) {
//
//               updateTen.setText(editable);
//
//           }
//       });
//
//       updateNoiDung.addTextChangedListener(new TextWatcher() {
//           @Override
//           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//           }
//
//           @Override
//           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//           }
//
//           @Override
//           public void afterTextChanged(Editable editable) {
//
//               updateNoiDung.setText(editable);
//           }
//       });
        // cho phép cuộn nội dung chuyện
        updateNoiDung.setMovementMethod(new ScrollingMovementMethod());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentruyen= updateTen.getText().toString();
                String noidung = updateNoiDung.getText().toString();

                int idt= (int) intent.getIntExtra("idt",0);
                String img= intent.getStringExtra("anht");
                int id_ng = intent.getIntExtra("id_ng",0);


                Truyen truyen= new Truyen(tentruyen,noidung,img,id_ng);

                if(tentruyen.equals("")|| noidung.equals("")){
                    Toast.makeText(UpdateActivity.this, "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    Log.e("ERR","Nhập đầy đủ thông tin");

                }
                // nếu nhập đầy đủ thông tin thì thực hiện thêm dữ liệu
                else{
                    databasedoctruyen.Update(truyen, idt);

                    // Chuyển qua màn hình admin và cập nhập lại dữ liệu
                }

                // Xóa dữ liệu

                // cập nhật lại activity
                Intent intent1= new Intent(UpdateActivity.this, manAdmin.class);

                finish();
                startActivity(intent1);
                Toast.makeText(UpdateActivity.this, "Cập nhật truyện thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }


}