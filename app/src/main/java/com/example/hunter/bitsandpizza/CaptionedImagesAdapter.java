package com.example.hunter.bitsandpizza;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hunter on 9/4/17.
 */

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>{
    //Provide a reference to the views used in RecylerView
   private String[] captions;
    private int[] imageIDs;
private Listener listener;

        public static interface Listener{
            public void onClick(int position);

        }
    public void setListener(Listener listener)
    {
        this.listener=listener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        //Define the view holder
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;

        }
    }
        public CaptionedImagesAdapter(String[] captions,int[] imageIDs)
        {
            this.captions=captions;

            this.imageIDs=imageIDs;
        }

    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
CardView cv=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        //Set the values inside the given view
        CardView cardView=holder.cardView;
        ImageView imageView=(ImageView)cardView.findViewById(R.id.info_image);
        Drawable drawable=cardView.getResources().getDrawable(imageIDs[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView=(TextView)cardView.findViewById(R.id.info_text);
        textView.setText(captions[position]);
        cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(listener!=null)
                {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        //Return the number of items in the data set
        return captions.length;

    }
}