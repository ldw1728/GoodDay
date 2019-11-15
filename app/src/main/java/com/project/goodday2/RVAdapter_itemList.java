package com.project.goodday2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RVAdapter_itemList extends RecyclerView.Adapter<RVAdapter_itemList.ItemHolder> {

    private ArrayList<ToDoItem> listData;
    private Context context;

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        context = viewGroup.getContext();
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder itemHolder, int i) {

        final ToDoItem item = listData.get(i);

        itemHolder.getTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailInfoActivity.class);
                intent.putExtra("seletedItem",item );
                context.startActivity(intent);
            }
        });
        itemHolder.onBInd(item);

        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        int today =  Integer.parseInt(sdf.format(new Date(System.currentTimeMillis())));
        if(today > Integer.parseInt(item.getDay())){
            itemHolder.title.setPaintFlags(itemHolder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            itemHolder.title.setBackgroundColor(Color.rgb(130,130,130));
            itemHolder.getItemView().findViewById(R.id.RV_CB).setEnabled(false);
        }

        isChecked(item, itemHolder);

        itemHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    itemHolder.title.setPaintFlags(itemHolder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    itemHolder.title.setBackgroundColor(Color.rgb(130,130,130));
                    item.setChecked(true);
                }else  {
                    itemHolder.title.setPaintFlags(0);
                    itemHolder.title.setBackgroundColor(Color.rgb(230,230,230));
                    item.setChecked(false);
                }
            }
        });
    }

    public void isChecked(ToDoItem item, ItemHolder holder){
        if(item.isChecked()){
            holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.title.setBackgroundColor(Color.rgb(130,130,130));
            holder.cb.setChecked(true);
        }
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }


    public void addItem(ArrayList<ToDoItem> items){
        this.listData = items;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private CheckBox cb;
        private TextView title;
        private View itemView;

        public ItemHolder(@NonNull final View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = itemView.findViewById(R.id.RV_txt_title);
            cb = itemView.findViewById(R.id.RV_CB);

        }
        public TextView getTitle(){
            return this.title;
        }
        public View getItemView() {return this.itemView;}

        void onBInd(ToDoItem item){
            title.setText(item.getTitle());
        }
    }
}
