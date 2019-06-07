package com.example.firebaseuploadstorageapp;

import com.google.firebase.database.Exclude;

public class Upload {
    private String mName;
    private String mImageUrl;
    private String mKey;

    //so as to work with the firebase database create an emety constructor.
    public Upload(){
        //emty constructor needed.

    }
    //create another constructor that will take the image url and the image name.
    public Upload(String name,String imageUrl){
        if(name.trim().equals("")){
            name="No Name";
        }

        mName=name;
        mImageUrl=imageUrl;

    }
    public String getName(){
        return mName;
    }
    public void setName(String name){

        mName=name;
    }
    public String getmImageUrl(){

        return mImageUrl;
    }
    public void  setmImageUrl(String imageUrl){
        mImageUrl=imageUrl;
    }

    @Exclude
    public String getKey(){
        return mKey;
    }
    @Exclude
    public void setKey(String key){
        mKey=key;
    }

}
