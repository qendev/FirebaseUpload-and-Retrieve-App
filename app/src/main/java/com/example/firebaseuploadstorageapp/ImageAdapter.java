package com.example.firebaseuploadstorageapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    //Create two member variables;to have them in the class.

    private Context mContext;
    private List<Upload> mUploads;
    private OnItemClickListener mListener;

    //Inorder to get the values from outside into the Adapter;
    //Create a Constructer.

    public ImageAdapter(Context context,List<Upload> uploads){
        //pass the values being passed to the member variables
        mContext=context;
        mUploads=uploads;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Here the intention is to return a viewholder so do the folllowing...

       View v= LayoutInflater.from(mContext).inflate(R.layout.image_item,viewGroup,false);
       return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        //Inorder to get data out of the Upload items and into the senior Card item do the following...

        //First you need a reference to the Current Item;its an upload item.
        Upload uploadCurrent=mUploads.get(i);
        imageViewHolder.textViewName.setText(uploadCurrent.getName());

        //to load the images into the ImageView use picasso(makes loading images from the url easy)like this...

        Picasso.with(mContext)
                .load(uploadCurrent.getmImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()//resizes the image into the size of the imageview.
                .centerCrop()
                .into(imageViewHolder.imageView);


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView textViewName;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName=itemView.findViewById(R.id.text_view_name);
            imageView=itemView.findViewById(R.id.image_view_upload);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener!=null){
                int position=getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }
            }

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem doWhatEver=menu.add(Menu.NONE, 1, 1, "Do Whatever");
            MenuItem delete=menu.add(Menu.NONE,2, 2, "Delete");

            //Now to handle clicks for the menu items do the following.
            doWhatEver.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(mListener!=null){
                int position=getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION){

                    switch (item.getItemId()){
                        case 1:
                            mListener.onWhatEverClick(position);
                            return  true;
                        case 2:
                            mListener.onDelete(position);
                            return  true;
                    }
                }
            }
            return false;

        }
    }
    //Create an interface thta will foward clicks to the undrelying activities.

    public interface OnItemClickListener{
        //will define three methods.

        void onItemClick(int position); //will handle default clicks.

        void onWhatEverClick(int position); //for the other menu item.

        void onDelete(int position);
    }

    public void setonClickItemListener(OnItemClickListener listener){ //will use this mtd to set our activity as the listener for the interface.
        mListener=listener;


    }
}
