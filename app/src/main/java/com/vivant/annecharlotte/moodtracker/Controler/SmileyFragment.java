package com.vivant.annecharlotte.moodtracker.Controler;

import android.content.Context;
import android.os.Bundle;
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
     * Send informations for adaptation of image and background and put listeners on all the buttons
     *
     * @see PieActivity#calculate7dates()
     * @see PieActivity#createTab()
     * @see PieActivity#setupPieChart()
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_smiley, container, false);

        RelativeLayout colorBg = v.findViewById(R.id.fragment_page_rootview);
        colorBg.setBackgroundResource(getArguments().getInt("color"));

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
        int smileyColor, smileyIndex, smileyImage;

        // link between moods and their graphical characteristics and index
        switch (smiley) {
            case SAD:
                smileyColor = SmileyEnum.SAD.getColor();
                bTransfert.putInt("color", smileyColor);
                smileyImage = SmileyEnum.SAD.getImage();
                bTransfert.putInt("image", smileyImage);
                smileyIndex = SmileyEnum.SAD.getIndex();
                bTransfert.putInt("index", smileyIndex);
                break;

            case DISAPPOINTED:
                smileyColor = SmileyEnum.DISAPPOINTED.getColor();
                bTransfert.putInt("color", smileyColor);
                smileyImage = SmileyEnum.DISAPPOINTED.getImage();
                bTransfert.putInt("image", smileyImage);
                smileyIndex = SmileyEnum.DISAPPOINTED.getIndex();
                bTransfert.putInt("index", smileyIndex);
                break;

            case NORMAL:
                smileyColor = SmileyEnum.NORMAL.getColor();
                bTransfert.putInt("color", smileyColor);
                smileyImage = SmileyEnum.NORMAL.getImage();
                bTransfert.putInt("image", smileyImage);
                smileyIndex = SmileyEnum.NORMAL.getIndex();
                bTransfert.putInt("index", smileyIndex);
                break;

            case HAPPY:
                smileyColor = SmileyEnum.HAPPY.getColor();
                bTransfert.putInt("color", smileyColor);
                smileyImage = SmileyEnum.HAPPY.getImage();
                bTransfert.putInt("image", smileyImage);
                smileyIndex = SmileyEnum.HAPPY.getIndex();
                bTransfert.putInt("index", smileyIndex);
                break;

            case SUPER_HAPPY:
                smileyColor = SmileyEnum.SUPER_HAPPY.getColor();
                bTransfert.putInt("color", smileyColor);
                smileyImage = SmileyEnum.SUPER_HAPPY.getImage();
                bTransfert.putInt("image", smileyImage);
                smileyIndex = SmileyEnum.SUPER_HAPPY.getIndex();
                bTransfert.putInt("index", smileyIndex);
                break;

            default:
                smileyColor = SmileyEnum.HAPPY.getColor();
                bTransfert.putInt("color", smileyColor);
                smileyImage = SmileyEnum.HAPPY.getImage();
                bTransfert.putInt("image", smileyImage);
                smileyIndex = SmileyEnum.HAPPY.getIndex();
                bTransfert.putInt("index", smileyIndex);
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

