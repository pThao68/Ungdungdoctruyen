package com.example.ungdungdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ungdungdoctruyen.adapter.adapterTruyen;
import com.example.ungdungdoctruyen.database.databasedoctruyen;
import com.example.ungdungdoctruyen.model.Truyen;

import java.util.ArrayList;

public class manAdmin extends AppCompatActivity {

    ListView listView;
    Button buttonThem;

    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;


    EditText edtAnhCS,edtTenTruyenCS,edtNoiDungCS;

    com.example.ungdungdoctruyen.database.databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_admin);

        listView = findViewById(R.id.listVIewAdmin);
        buttonThem = findViewById(R.id.buttonThemtruyen);



        databasedoctruyen = new databasedoctruyen(this);

        // Update


        initList();

        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Lấy id tài khoản để biết tài khoản admin nào đã đổ vào chỉnh cửa
                Intent intent1= getIntent();
                int id= intent1.getIntExtra("Id",0);
                // Tiếp tục gửi id qua màn hinh thêm truyện

                Intent intent = new Intent(manAdmin.this, MainDangBai.class);

                intent.putExtra("Id", id);
                startActivity(intent);
            }
        });

        // click item long sẽ xóa item
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                DialogDelete(i);
                return false;
            }
        });

        // update truyen


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent1= getIntent();

                int id = intent1.getIntExtra("Id",0);

                Intent intent = new Intent(manAdmin.this, UpdateActivity.class);
                String tent = TruyenArrayList.get(i).getTenTruyen();
                String noidungt= TruyenArrayList.get(i).getNoiDung();
                String anht= TruyenArrayList.get(i).getAnh();
                int idt = TruyenArrayList.get(i).getiD();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                intent.putExtra("id_ng", id);
                intent.putExtra("idt", idt);
                intent.putExtra("img", anht);


                startActivity(intent);

                //DialogUpdate(i);
            }
        });
    }


    private void DialogDelete(int position ){
        // Tạo đối tượng dialog
        Dialog dialog= new Dialog(this);
        // Nạp layout vào dialog
        dialog.setContentView(R.layout.dialogdelete);
        //  Tắt click ra ngoài là đóng, chỉ clock no mới đóng
        dialog.setCanceledOnTouchOutside(false);

        // Ánh xạ
         Button btnYes= dialog.findViewById(R.id.buttonYes);
         Button btnNo= dialog.findViewById(R.id.buttonNo);
          btnYes.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  int idtruyen= TruyenArrayList.get(position).getiD();

                  // Xóa dữ liệu
                  databasedoctruyen.Delete(idtruyen);

                  // cập nhật lại activity
                  Intent intent= new Intent(manAdmin.this, manAdmin.class);

                  finish();
                  startActivity(intent);
                  Toast.makeText(manAdmin.this, "Cập nhật truyện thành công", Toast.LENGTH_SHORT).show();


              }
          });


          btnNo.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  // thực hiện đóng dialog
                  dialog.cancel();
              }
          });
          // run dialog
          dialog.show();
    }


    public void DialogUpdate(int position ){
        // Tạo đối tượng dialog
        Dialog dialog= new Dialog(this);
        // Nạp layout vào dialog
        dialog.setContentView(R.layout.dialogupdate);
        //  Tắt click ra ngoài là đóng, chỉ clock no mới đóng
        dialog.setCanceledOnTouchOutside(false);

        // Ánh xạ
        Button btnYes= dialog.findViewById(R.id.buttonChinhSua);
        Button btnNo= dialog.findViewById(R.id.buttonTuChoi);

        EditText edtAnhCS = dialog.findViewById(R.id.edtImage);
        EditText edtTenTruyenCS = (EditText)dialog.findViewById(R.id.edtTenTruyen);
        EditText edtNoiDungCS= (EditText)dialog.findViewById(R.id.edtNoiDung);



        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentruyen= edtTenTruyenCS.getText().toString();
                String noidung = edtNoiDungCS.getText().toString();
                String img = edtAnhCS.getText().toString();

                int idtruyen= TruyenArrayList.get(position).getiD();

                Intent intent= getIntent();

                int id = intent.getIntExtra("Id",0);

                Truyen truyen= new Truyen(tentruyen,noidung,img,id);

                if(tentruyen.equals("")|| noidung.equals("")|| img.equals("")){
                    Toast.makeText(manAdmin.this, "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    Log.e("ERR","Nhập đầy đủ thông tin");

                }
                // nếu nhập đầy đủ thông tin thì thực hiện thêm dữ liệu
                else{
                    databasedoctruyen.Update(truyen, idtruyen);

                    // Chuyển qua màn hình admin và cập nhập lại dữ liệu
                }

                // Xóa dữ liệu


                // cập nhật lại activity
                Intent intent1= new Intent(manAdmin.this, manAdmin.class);

                finish();
                startActivity(intent1);
                Toast.makeText(manAdmin.this, "Cập nhật truyện thành công", Toast.LENGTH_SHORT).show();


            }
        });


        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // thực hiện đóng dialog
                dialog.cancel();
            }
        });
        // run dialog
        dialog.show();
    }

    // đã xóa hết dữ liệu truyên
    // ta thấy không còn truyện trong data
    // găn dữ liệu cho listview

    private void initList() {
        TruyenArrayList= new ArrayList<>();
        databasedoctruyen = new databasedoctruyen(this);

        Cursor cursor1= databasedoctruyen.getdataTruyen();

        while(cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArrayList.add(new Truyen(id, tentruyen,noidung,anh, id_tk));

            adapterTruyen= new adapterTruyen(getApplicationContext(), TruyenArrayList);

            listView.setAdapter(adapterTruyen);


        }
        cursor1.moveToFirst();
        cursor1.close();
    }


}