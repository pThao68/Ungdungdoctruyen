package com.example.ungdungdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.ungdungdoctruyen.adapter.adapterTruyen;
import com.example.ungdungdoctruyen.adapter.adapterchuyenmuc;
import com.example.ungdungdoctruyen.adapter.adapterthongtin;
import com.example.ungdungdoctruyen.database.databasedoctruyen;
import com.example.ungdungdoctruyen.model.TaiKhoan;
import com.example.ungdungdoctruyen.model.Truyen;
import com.example.ungdungdoctruyen.model.chuyenmuc;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView,listViewNew, listViewThongtin;
    DrawerLayout drawerLayout;

    String email;
    String tentaikhoan;

    ArrayList<Truyen> TruyenArraylist;
    adapterTruyen adapterTruyen;

    ArrayList<chuyenmuc> chuyenmucArayList;

    ArrayList<TaiKhoan> taiKhoanArrayLis;



    databasedoctruyen databasedoctruyen;

    adapterchuyenmuc adapterchuyenmuc;
    adapterthongtin adapterthongtin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasedoctruyen = new databasedoctruyen(this);

        // nhận dữ liệu ở màn đăng nhập gửi
        Intent intentpq = getIntent();
        int ipq = intentpq.getIntExtra("phanq",0);
        int idd= intentpq.getIntExtra("id",0);
        email = intentpq.getStringExtra("email");
        tentaikhoan= intentpq.getStringExtra("tentaikhoan");


        AnhXa();
        ActionViewFliper();
        ActionBar();

        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MainNoiDung.class);

                String tent = TruyenArraylist.get(i).getTenTruyen();
                String noidungt= TruyenArraylist.get(i).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);

                startActivity(intent);

            }
        });

        // bắt click item cho list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // đăng bài
                if(i==0){
                    if(ipq==2){
                        Intent intent = new Intent(MainActivity.this, manAdmin.class);
                        // gửi id tài khoản qua màn admin
                        intent.putExtra("Id",idd);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Bạn không có quyền đăng bài", Toast.LENGTH_LONG ).show();
                        Log.e("Đăng bài","Bạn không có quyền");
                    }
                }
                // nếu vị ró ấn valf là thông tin thì sẽ chuyển qua màn thông tin app

                else if( i==1){

                    Intent intent = new Intent(MainActivity.this, MainThongtin.class);
                    startActivity(intent);
                }
                else if (i==2){
                    finish();
                }
            }
        });
    }

    // Thanh actionbar với toolbar
    private void ActionBar() {
        //  hàm hỗ trọ tool bar
        setSupportActionBar(toolbar);

        // set nút cho actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // tạo icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        // bắt sự kiện
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(GravityCompat.START);
            }
        });



    }

    // phương thức cho chạy quảng cáo với ViewFlipper
    private void ActionViewFliper() {
        // mảng chứa tấm ảnh cho quảng cáo
        ArrayList<String> mangquangcao= new ArrayList<>();
        // add ảnh vào mảng
        mangquangcao.add("https://toplist.vn/images/800px/rua-va-tho-230179.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/deo-chuong-cho-meo-230180.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/cu-cai-trang-230181.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg");

        // Thực hiện vòng lặp for gắn ảnh vào InmagrVIew rồi từ imageview lên app
        for (int i=0; i <mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            // sử dụng hàm thư viện Piscasso
            Picasso.get().load(mangquangcao.get(i)).into(imageView);

            // phương thức chính tán hình vừa khung quảng cáo
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            // thêm ảnh từ imageView cào viewFlipper
            viewFlipper.addView(imageView);

        }
            // thiết lập tự động chạy cho viewFlipper chạy trong  4 giây
            viewFlipper.setFlipInterval(3000);
            //run auto viewFlipter
            viewFlipper.setAutoStart(true);

            // Gọi animation cho vào và ra
            Animation animation_slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
            Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_night);

            // Gọi animation vào viewFLipper
            viewFlipper.setInAnimation(animation_slide_in);
            viewFlipper.setInAnimation(animation_slide_out);


    }

    // phương thức ánh xạ
    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listView= findViewById(R.id.listviewanhhinhchinh);
        listViewNew= findViewById(R.id.lisviewNew);
        listViewThongtin= findViewById(R.id.listviewthongtin);
        navigationView= findViewById(R.id.navigationView);
        drawerLayout= findViewById(R.id.drawerlayout);

        TruyenArraylist = new ArrayList<>();

        Cursor cursor1= databasedoctruyen.getDatal();// lấy 3 truyện mwois nhất
        while (cursor1.moveToNext()){// đến cuối thì trả về giá trị false
            int id= cursor1.getInt(0);
            String tentruyen= cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk= cursor1.getInt(4);

            TruyenArraylist.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            adapterTruyen= new adapterTruyen(getApplicationContext(),TruyenArraylist);
            listViewNew.setAdapter(adapterTruyen);// hiển thị list view set Adapter
        }
        cursor1.moveToFirst();
        cursor1.close();

        // thông tin
        taiKhoanArrayLis = new ArrayList<>();
        taiKhoanArrayLis.add(new TaiKhoan(tentaikhoan,email));

        adapterthongtin = new adapterthongtin(this,taiKhoanArrayLis);// ???????
        listViewThongtin.setAdapter(adapterthongtin);// thêm dữ liệu vào list view

        // chuyeen mucj
        chuyenmucArayList= new ArrayList<>();
        chuyenmucArayList.add(new chuyenmuc("Đăng bài", R.drawable.ic_baseline_post_add_24));
        chuyenmucArayList.add(new chuyenmuc("Thông tin", R.drawable.ic_baseline_face_24));
        chuyenmucArayList.add(new chuyenmuc("Đăng xuất",R.drawable.ic_baseline_login_24));

        adapterchuyenmuc = new adapterchuyenmuc(this,chuyenmucArayList);
        listView.setAdapter(adapterchuyenmuc);
    }


    // nhập một menu tìm kiếm cào Actionbar: Phương thức onCreateOptionsMenu() sẽ thực hiện các công việc khởi tạo menu cho đối tượng Activity,
    // ở đây chúng ta dùng phương thức inflate() của lớp android.view.MenuInflater để lấy dữ liệu của menu từ file options_menu.xml về sử dụng. Phương thức này nhận vào một đối tượng android.view.Menu.
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.mymenu, menu);
//
//        return true;
//    }
//
//    // nạp một tìm kiếm vào actionBar
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        // nếu click vào icon tìm kiếm sẽ chuyển qua màn hình tìm kiếm
//        switch (item.getItemId()){
//
//            case R.id.menu1:
//                Intent intent= new Intent(MainActivity.this, ManTimKiem.class);
//                startActivity(intent);
//                break;
//            default:
//                break;
//
//        }
//        return super.onOptionsItemSelected(item);
//    }

}