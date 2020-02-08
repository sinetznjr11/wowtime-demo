package com.example.wowtimedemo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wowtimedemo.Model.ItemsModel;
import com.example.wowtimedemo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {
    ArrayList<ItemsModel> arrayList= new ArrayList<>();

    private OnItemClickListener mListener;


    public ItemsAdapter(ArrayList<ItemsModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        ItemsViewHolder itemsViewHolder=new ItemsViewHolder(view,mListener);
        return itemsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
    holder.title.setText(arrayList.get(position).getTitle());
    holder.channelName.setText(arrayList.get(position).getChannelName());
    holder.dateTime.setText(arrayList.get(position).getDateTime());
        Picasso.get()
                .load(arrayList.get(position).getThumbnailUrl())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ItemsViewHolder extends RecyclerView.ViewHolder {

        public TextView title,channelName, dateTime;
        public ImageView thumbnail;

        public ItemsViewHolder(@NonNull View itemView,  final OnItemClickListener listener) {
            super(itemView);
            title=itemView.findViewById(R.id.video_title);
            channelName=itemView.findViewById(R.id.channel_name);
            dateTime=itemView.findViewById(R.id.date_time);
            thumbnail=itemView.findViewById(R.id.thumbnail);

            //setting click listener for individual item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!= null){
                        int position=getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }
}
