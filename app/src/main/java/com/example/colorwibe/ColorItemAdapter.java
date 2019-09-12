package com.example.colorwibe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorItemAdapter extends RecyclerView.Adapter<ColorItemAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<ColorItem> mExampleList;
    private ArrayList<ColorItem> mExampleListFiltered;
    private OnItemClickedListener mListener;

    public interface OnItemClickedListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickedListener listener) {
        mListener = listener;
    }

    public ColorItemAdapter(Context context, ArrayList<ColorItem> ExampleList) {
        mContext = context;
        mExampleList = ExampleList;
        this.mExampleListFiltered = mExampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.color_item, viewGroup, false);

        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        ColorItem currentItem = mExampleList.get(i);

        String hash = currentItem.getHash();



        exampleViewHolder.mTextHash.setText(mExampleListFiltered.get(i).getHash());
        exampleViewHolder.mTextE1.setText(mExampleListFiltered.get(i).getExtra1());
        exampleViewHolder.mTextE2.setText(mExampleListFiltered.get(i).getExtra2());
        exampleViewHolder.color_window.setBackgroundColor(Color.parseColor(hash));
       }

    @Override
    public int getItemCount() {
        return mExampleListFiltered.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextHash, mTextE1, mTextE2;
        LinearLayout color_window ;
        CardView container;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.box);
            mTextHash = itemView.findViewById(R.id.text_hash);
            mTextE1 = itemView.findViewById(R.id.text_extra1);
            mTextE2 = itemView.findViewById(R.id.text_extra2);
            color_window=itemView.findViewById(R.id.color_window);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
