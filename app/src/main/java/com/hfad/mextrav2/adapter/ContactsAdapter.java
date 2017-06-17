package com.hfad.mextrav2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfad.mextrav2.R;

/**
 * Created by energywin4 on 16/6/2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView main_image;
        public TextView title;
        public TextView status;
        public TextView timestamp;
        public TextView notification;
        public TextView silentstatus;
        public View stline;


        public ViewHolder(View itemView) {
            super(itemView);

            main_image = (ImageView) itemView.findViewById(R.id.main_image);
            title = (TextView) itemView.findViewById(R.id.title);
            status = (TextView) itemView.findViewById(R.id.status);
            timestamp = (TextView) itemView.findViewById(R.id.timestamp);
            notification = (TextView) itemView.findViewById(R.id.notification);
            silentstatus = (TextView) itemView.findViewById(R.id.silentstatus);
            stline = (View) itemView.findViewById(R.id.stline);
        }
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_screen_frag,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
