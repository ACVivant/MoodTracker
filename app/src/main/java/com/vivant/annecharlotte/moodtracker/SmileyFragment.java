package com.vivant.annecharlotte.moodtracker;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SmileyFragment extends android.support.v4.app.Fragment {

    public SmileyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_smiley, container, false);

        RelativeLayout colorBg = (RelativeLayout) v.findViewById(R.id.fragment_page_rootview);
        colorBg.setBackgroundResource(getArguments().getInt("color"));

        ImageButton imageSmiley = (ImageButton) v.findViewById(R.id.fragment_main_smiley_image_view);
        imageSmiley.setBackgroundResource(getArguments().getInt("image"));

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static SmileyFragment newInstance(SmileyEnum smiley) {

        SmileyFragment f = new SmileyFragment();
        Bundle bTransfert = new Bundle();
        int smileyColor;
        int smileyIndex;
        int smileyImage;

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
    }

}

