package com.example.cloudchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import java.util.zip.Inflater;

public class AdapterForMyRecycler  extends RecyclerView.Adapter<AdapterForMyRecycler.MyHolder> {
    Context context;
    ArrayList<String> msgList;

      AdapterForMyRecycler(Context _context, ArrayList<String> _list){
        context = _context;
        msgList = _list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.message_layout, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
         holder.Bind(msgList.get(position));

    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void Bind(String s){
            TextView textView = itemView.findViewById(R.id.msg_item);
            textView.setText(s);

        }
    }
}
