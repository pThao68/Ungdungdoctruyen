package com.example.ungdungdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ungdungdoctruyen.R;
import com.example.ungdungdoctruyen.model.chuyenmuc;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterchuyenmuc extends BaseAdapter {

    private Context context;

    private List<chuyenmuc> chuyenmucList;

    public adapterchuyenmuc(Context context, List<chuyenmuc> chuyenmucList) {
        this.context = context;

        this.chuyenmucList = chuyenmucList;
    }

    @Override
    public int getCount() {
        return chuyenmucList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    // chuyeyern đổi layour thành java code

    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.chuyenmuc,null);

        ImageView img= (ImageView) view.findViewById(R.id.imgchuyenmuc);

        TextView txt= (TextView) view.findViewById(R.id.textviewTenchuyenmuc);

        chuyenmuc cm = new chuyenmuc();
//        txt.setText(cm.getTenchuyenmuc());
//        img.setImageResource(cm.getHinhanhchuyenmuc());
        txt.setText(chuyenmucList.get(i).getTenchuyenmuc());
        img.setImageResource(chuyenmucList.get(i).getHinhanhchuyenmuc());
//        Picasso.get().load(cm.getHinhanhchuyenmuc()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(img);

        return view;

    }
}
