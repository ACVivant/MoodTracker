package com.vivant.annecharlotte.moodtracker.Controler;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.vivant.annecharlotte.moodtracker.Model.SmileyEnum;
import com.vivant.annecharlotte.moodtracker.R;

public class SmileyFragment extends android.support.v4.app.Fragment implements View.OnClickListener{

    private OnButtonClickedListener mBtnClic;

    public interface OnButtonClickedListener {
        void onHistoryClicked();
        void onCommentClicked(int position);
        void onPieClicked();
    }
    public SmileyFragment() {
    }
    /**
     * Send information for adaptation of image and background and put listeners on all the buttons
     *
     * @see PieActivity#calculate7dates()
     * @see PieActivity#createTab()
     * @see PieActivity#setupPieChart()
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_smiley, container, false);
        RelativeLayout colorBg = v.findViewById(R.id.fragment_page_rootview);
        if (getArguments() != null) {
            colorBg.setBackgroundResource(getArguments().getInt("color"));
        }
        ImageButton imageSmiley = v.findViewById(R.id.fragment_main_smiley_image_view);
        imageSmiley.setBackgroundResource(getArguments().getInt("image"));

        v.findViewById(R.id.activity_main_note_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnClic.onCommentClicked(getArguments().getInt("index"));
            }
        });
        v.findViewById(R.id.fragment_main_smiley_image_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnClic.onCommentClicked(getArguments().getInt("index"));
            }
        });
        v.findViewById(R.id.activity_main_history_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnClic.onHistoryClicked();
            }
        });
        v.findViewById(R.id.activity_main_pie_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnClic.onPieClicked();
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    /**
     * give the characteristics of each view according to mood
     */
    public static SmileyFragment newInstance(SmileyEnum smiley) {
        SmileyFragment f = new SmileyFragment();
        Bundle bTransfert = new Bundle();

        switch (smiley) {
            case SAD:
                bTransfert.putInt("color", SmileyEnum.SAD.getColor());
                bTransfert.putInt("image", SmileyEnum.SAD.getImage());
                bTransfert.putInt("index", SmileyEnum.SAD.getIndex());
                break;
            case DISAPPOINTED:
                bTransfert.putInt("color",SmileyEnum.DISAPPOINTED.getColor());
                bTransfert.putInt("image", SmileyEnum.DISAPPOINTED.getImage());
                bTransfert.putInt("index", SmileyEnum.DISAPPOINTED.getIndex());
                break;
            case NORMAL:
                bTransfert.putInt("color", SmileyEnum.NORMAL.getColor());
                bTransfert.putInt("image",SmileyEnum.NORMAL.getImage());
                bTransfert.putInt("index", SmileyEnum.NORMAL.getIndex());
                break;
            case HAPPY:
                bTransfert.putInt("color", SmileyEnum.HAPPY.getColor());
                bTransfert.putInt("image", SmileyEnum.HAPPY.getImage());
                bTransfert.putInt("index", SmileyEnum.HAPPY.getIndex());
                break;
            case SUPER_HAPPY:
                bTransfert.putInt("color", SmileyEnum.SUPER_HAPPY.getColor());
                bTransfert.putInt("image", SmileyEnum.SUPER_HAPPY.getImage());
                bTransfert.putInt("index",  SmileyEnum.SUPER_HAPPY.getIndex());
                break;
                }
        f.setArguments(bTransfert);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            //Parent activity will automatically subscribe to callback
            mBtnClic = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");
        }
    }

    @Override
    public void onClick(View view) {
    }
}

