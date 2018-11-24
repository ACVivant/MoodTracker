package com.vivant.annecharlotte.moodtracker;

import android.content.Context;
import android.content.res.Resources;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout mDay7, mDay6, mDay5, mDay4, mDay3, mDay2, mDay1;

    private TextView mDay7TextView, mDay6TextView, mDay5TextView, mDay4TextView, mDay3TextView, mDay2TextView, mDay1TextView;

    private ImageButton mDay7Btn, mDay6Btn, mDay5Btn, mDay4Btn, mDay3Btn, mDay2Btn, mDay1Btn;

    private int smiley7return, smiley6return, smiley5return, smiley4return, smiley3return, smiley2return, smiley1return;

    private String note7return, note6return, note5return, note4return, note3return, note2return, note1return;

    private String todayMinus1, todayMinus2, todayMinus3, todayMinus4, todayMinus5, todayMinus6, todayMinus7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mDay7TextView = (TextView) findViewById(R.id.activity_history_d7_textview);
        mDay6TextView = (TextView) findViewById(R.id.activity_history_d6_textview);
        mDay5TextView = (TextView) findViewById(R.id.activity_history_d5_textview);
        mDay4TextView = (TextView) findViewById(R.id.activity_history_d4_textview);
        mDay3TextView = (TextView) findViewById(R.id.activity_history_d3_textview);
        mDay2TextView = (TextView) findViewById(R.id.activity_history_d2_textview);
        mDay1TextView = (TextView) findViewById(R.id.activity_history_d1_textview);

        mDay7 = (LinearLayout) findViewById(R.id.activity_history_d7_linearlayout);
        mDay6 = (LinearLayout) findViewById(R.id.activity_history_d6_linearlayout);
        mDay5 = (LinearLayout) findViewById(R.id.activity_history_d5_linearlayout);
        mDay4 = (LinearLayout) findViewById(R.id.activity_history_d4_linearlayout);
        mDay3 = (LinearLayout) findViewById(R.id.activity_history_d3_linearlayout);
        mDay2 = (LinearLayout) findViewById(R.id.activity_history_d2_linearlayout);
        mDay1 = (LinearLayout) findViewById(R.id.activity_history_d1_linearlayout);

        mDay7Btn = (ImageButton) findViewById(R.id.activity_history_d7_notebtn);
        mDay6Btn = (ImageButton) findViewById(R.id.activity_history_d6_notebtn);
        mDay5Btn = (ImageButton) findViewById(R.id.activity_history_d5_notebtn);
        mDay4Btn = (ImageButton) findViewById(R.id.activity_history_d4_notebtn);
        mDay3Btn = (ImageButton) findViewById(R.id.activity_history_d3_notebtn);
        mDay2Btn = (ImageButton) findViewById(R.id.activity_history_d2_notebtn);
        mDay1Btn = (ImageButton) findViewById(R.id.activity_history_d1_notebtn);

        mDay7Btn.setOnClickListener(this);
        mDay6Btn.setOnClickListener(this);
        mDay5Btn.setOnClickListener(this);
        mDay4Btn.setOnClickListener(this);
        mDay3Btn.setOnClickListener(this);
        mDay2Btn.setOnClickListener(this);
        mDay1Btn.setOnClickListener(this);

        // récupérer les infos des 7 derniers jours et les stocker dans les variables smiley?return et note?return

        calculate7dates();

        Map<String, ?> allEntries = getSharedPreferences("smiley", MODE_PRIVATE).getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {     Log.d("Pref", "ce qu'on veut " + entry.getKey() + ": " + entry.getValue().toString()); }

        returnSavedSmiley();
        /*smiley7return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus7, 3);
        smiley6return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus6, 3);
        smiley5return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus5, 3);
        smiley4return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus4, 3);
        smiley3return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus3, 3);
        smiley2return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus2, 3);
        smiley1return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus1, 3);

        note7return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus7, null);
        note6return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus6, null);
        note5return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus5, null);
        note4return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus4, null);
        note3return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus3, null);
        note2return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus2, null);
        note1return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus1, null);*/

        // envoi des données au layout (couleur, longueur, affichage ou non du bouton) pour chaque jour de la semaine

        designHistory(mDay1, mDay1TextView, smiley1return, this);
        designHistory(mDay2, mDay2TextView, smiley2return, this);
        designHistory(mDay3, mDay3TextView, smiley3return, this);
        designHistory(mDay4, mDay4TextView, smiley4return, this);
        designHistory(mDay5, mDay5TextView, smiley5return, this);
        designHistory(mDay6, mDay6TextView, smiley6return, this);
        designHistory(mDay7, mDay7TextView, smiley7return, this);

        displayNoteBtn(note1return, mDay1Btn);
        displayNoteBtn(note2return, mDay2Btn);
        displayNoteBtn(note3return, mDay3Btn);
        displayNoteBtn(note4return, mDay4Btn);
        displayNoteBtn(note5return, mDay5Btn);
        displayNoteBtn(note6return, mDay6Btn);
        displayNoteBtn(note7return, mDay7Btn);
    }

    // on fixe la couleur et la taille
    public void designHistory(LinearLayout mDay, TextView mDayTV, int smiley, Context context) {

        LinearLayout.LayoutParams loparams = (LinearLayout.LayoutParams) mDayTV.getLayoutParams();
        switch (smiley)
        {
            case 0: mDay.setBackgroundColor(ContextCompat.getColor(context, R.color.faded_red));
                loparams.weight = 0.2f;
                break;
            case 1: mDay.setBackgroundColor(ContextCompat.getColor(context,R.color.warm_grey));
                loparams.weight = 0.35f;
                break;
            case 2: mDay.setBackgroundColor(ContextCompat.getColor(context,R.color.cornflower_blue_65));
                loparams.weight = 0.5f;
                break;
            case 3: mDay.setBackgroundColor(ContextCompat.getColor(context,R.color.light_sage));
                loparams.weight = 0.65f;
                break;
            case 4: mDay.setBackgroundColor(ContextCompat.getColor(context,R.color.banana_yellow));
                loparams.weight = 0.8f;
                break;
        }
        mDayTV.setLayoutParams(loparams);
    }

    // On affiche ou non le bouton commentaire sur le graph
    public void displayNoteBtn(String noteReturn, ImageButton mDayBtn) {
        if (noteReturn==null || noteReturn=="") {
            mDayBtn.setVisibility(View.INVISIBLE);
        } else {
            mDayBtn.setVisibility(View.VISIBLE);
        }
    }

    // créer les clés correspondants aux dates des 7 derniers jours
    public void calculate7dates() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        calendar.add(Calendar.DATE, -1);
        todayMinus1 = sdf.format(calendar.getTime());
        calendar.add(Calendar.DATE, -1);
        todayMinus2 = sdf.format(calendar.getTime());
        calendar.add(Calendar.DATE, -1);
        todayMinus3 = sdf.format(calendar.getTime());
        calendar.add(Calendar.DATE, -1);
        todayMinus4 = sdf.format(calendar.getTime());
        calendar.add(Calendar.DATE, -1);
        todayMinus5 = sdf.format(calendar.getTime());
        calendar.add(Calendar.DATE, -1);
        todayMinus6 = sdf.format(calendar.getTime());
        calendar.add(Calendar.DATE, -1);
        todayMinus7 = sdf.format(calendar.getTime());
    }

    // affichage du Toast avec le commentaire enregistré

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_history_d1_notebtn:
                Toast.makeText(this, note1return, Toast.LENGTH_LONG).show();
                break;

            case R.id.activity_history_d2_notebtn:
                Toast.makeText(this, note2return, Toast.LENGTH_LONG).show();
                break;

            case R.id.activity_history_d3_notebtn:
                Toast.makeText(this, note3return, Toast.LENGTH_LONG).show();
                break;

            case R.id.activity_history_d4_notebtn:
                Toast.makeText(this, note4return, Toast.LENGTH_LONG).show();
                break;

            case R.id.activity_history_d5_notebtn:
                Toast.makeText(this, note5return, Toast.LENGTH_LONG).show();
                break;

            case R.id.activity_history_d6_notebtn:
                Toast.makeText(this, note6return, Toast.LENGTH_LONG).show();
                break;

            case R.id.activity_history_d7_notebtn:
                Toast.makeText(this, note7return, Toast.LENGTH_LONG).show();
                break;

        }
    }

    public void returnSavedSmiley() {
        smiley7return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus7, 3);
        smiley6return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus6, 3);
        smiley5return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus5, 3);
        smiley4return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus4, 3);
        smiley3return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus3, 3);
        smiley2return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus2, 3);
        smiley1return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus1, 3);

        note7return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus7, null);
        note6return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus6, null);
        note5return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus5, null);
        note4return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus4, null);
        note3return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus3, null);
        note2return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus2, null);
        note1return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus1, null);
    }
}
