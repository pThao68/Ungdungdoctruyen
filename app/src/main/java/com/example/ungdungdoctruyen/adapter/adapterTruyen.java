package com.example.ungdungdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ungdungdoctruyen.R;
import com.example.ungdungdoctruyen.model.Truyen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapterTruyen extends BaseAdapter {

    private Context context;
    private ArrayList<Truyen> listTruyen;

    public adapterTruyen(Context context, ArrayList<Truyen> listTruyen) {
        this.context = context;
        this.listTruyen = listTruyen;
    }

    @Override
    public int getCount() {
        return listTruyen.size();
    }

    @Override
    public Object getItem(int position) {
        return listTruyen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void filterList(ArrayList<Truyen> filteredList) {
        listTruyen = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder{
        TextView txtTenTruyen;
        ImageView imgtruyen;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.newtruyen, null);

        viewHolder.txtTenTruyen= view.findViewById(R.id.textviewTentruyenNew);
        viewHolder.imgtruyen= view.findViewById(R.id.imgNewTruyen);
        view.setTag(viewHolder);

        Truyen truyen= (Truyen) getItem(i);
        viewHolder.txtTenTruyen.setText(truyen.getTenTruyen());

        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(viewHolder.imgtruyen);

        return view;
    }
}
