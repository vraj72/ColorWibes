package com.example.colorwibe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorItemAdapter extends RecyclerView.Adapter<ColorItemAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<ColorItem> mExampleListFiltered;
    private OnItemClickedListener mListener;
    private Context context;
    public ArrayList<ColorItem> addFavList = new ArrayList<>();


    public interface OnItemClickedListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickedListener listener) {
        mListener = listener;
    }


    public ColorItemAdapter(Context context, ArrayList<ColorItem> ExampleList) {
        mContext = context;
        mExampleListFiltered = ExampleList;
        this.context=context;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.color_item, viewGroup, false);

        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder exampleViewHolder, final int i) {
        ColorItem currentItem = mExampleListFiltered.get(i);

        String hash = currentItem.getHash();
        String e = currentItem.getExtra1();



        exampleViewHolder.mTextHash.setText(mExampleListFiltered.get(i).getHash());
        exampleViewHolder.mTextE1.setText(mExampleListFiltered.get(i).getExtra1());
        exampleViewHolder.mTextE2.setText(mExampleListFiltered.get(i).getExtra2());
        exampleViewHolder.color_window.setBackgroundColor(Color.parseColor(hash));
        if(e.equals("0")){
            exampleViewHolder.favcheck.setVisibility(View.INVISIBLE);
        }

        exampleViewHolder.favcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    if(exampleViewHolder.favcheck.isChecked()) {
                        addFavList.add(mExampleListFiltered.get(i));

                    }


                }
            }
        });
       }

    @Override
    public int getItemCount() {
        return mExampleListFiltered.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextHash, mTextE1, mTextE2;
        LinearLayout color_window ;
        CardView container;
//        AppCompatImageButton favnull,favfill;
        public CheckBox favcheck;
        public ExampleViewHolder(@NonNull final View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.box);
            mTextHash = itemView.findViewById(R.id.text_hash);
            mTextE1 = itemView.findViewById(R.id.text_extra1);
            mTextE2 = itemView.findViewById(R.id.text_extra2);
            color_window=itemView.findViewById(R.id.color_window);
//            favfill=itemView.findViewById(R.id.favnull);
//            favnull=itemView.findViewById(R.id.favfill);
            favcheck=itemView.findViewById(R.id.favcheck);





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
