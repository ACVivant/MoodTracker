package com.vivant.annecharlotte.moodtracker;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.pdf.PdfDocument;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import java.util.Date;
import java.util.Locale;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class MainActivity extends AppCompatActivity  {

    private String NOTE_KEY;
    private String SMILEY_KEY;
    private EditText smileyText;

    protected int responseIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VerticalViewPager pager = (VerticalViewPager) findViewById(R.id.activity_main_verticalviewpager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(3);
    }

    //----------------------------------------------------------------------------------------------
    // Pour l'affichage des smileys au démarrage
    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return  SmileyFragment.newInstance(SmileyEnum.SAD);
                case 1:
                    return  SmileyFragment.newInstance(SmileyEnum.DISAPPOINTED);
                case 2:
                    return  SmileyFragment.newInstance(SmileyEnum.NORMAL);
                case 3:
                    return  SmileyFragment.newInstance(SmileyEnum.HAPPY);
                case 4:
                    return  SmileyFragment.newInstance(SmileyEnum.SUPER_HAPPY);
                default:
                    return  SmileyFragment.newInstance(SmileyEnum.HAPPY);
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

}
