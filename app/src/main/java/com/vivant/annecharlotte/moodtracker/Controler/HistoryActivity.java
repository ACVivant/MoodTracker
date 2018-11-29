package com.vivant.annecharlotte.moodtracker.Controler;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.vivant.annecharlotte.moodtracker.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * HistoryActivity manages what is related to the application history function.
 *
 * Members of this class are characterized by :
 * values of registered moods.
 * values of registered notes
 * date of 7 pasted days
 *
 * @author acvivant
 * @version 1.0
 */
public class HistoryActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     * none of these variables are modifiable
     */
    private int smiley7return, smiley6return, smiley5return, smiley4return, smiley3return, smiley2return, smiley1return, smiley0return;
    private String note7return, note6return, note5return, note4return, note3return, note2return, note1return, note0return;
    private String today, todayMinus1, todayMinus2, todayMinus3, todayMinus4, todayMinus5, todayMinus6, todayMinus7;

    /**
     * send data to the layout
     *
     * @param savedInstanceState
     *                  saved instance state
     * @see HistoryActivity#calculate7dates()
     * @see HistoryActivity#returnSavedSmiley()
     * @see HistoryActivity#returndSavedNotes()
     * @see HistoryActivity#designHistory(LinearLayout, TextView, int, Context)
     * @see HistoryActivity#displayNoteBtn(String, ImageButton)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView mDay7TextView, mDay6TextView, mDay5TextView, mDay4TextView, mDay3TextView, mDay2TextView, mDay1TextView;
        LinearLayout mDay7, mDay6, mDay5, mDay4, mDay3, mDay2, mDay1;
        ImageButton mDay7Btn, mDay6Btn, mDay5Btn, mDay4Btn, mDay3Btn, mDay2Btn, mDay1Btn;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mDay7TextView =  findViewById(R.id.activity_history_d7_textview);
        mDay6TextView =  findViewById(R.id.activity_history_d6_textview);
        mDay5TextView =  findViewById(R.id.activity_history_d5_textview);
        mDay4TextView =  findViewById(R.id.activity_history_d4_textview);
        mDay3TextView =  findViewById(R.id.activity_history_d3_textview);
        mDay2TextView =  findViewById(R.id.activity_history_d2_textview);
        mDay1TextView =  findViewById(R.id.activity_history_d1_textview);

        mDay7 = findViewById(R.id.activity_history_d7_linearlayout);
        mDay6 = findViewById(R.id.activity_history_d6_linearlayout);
        mDay5 = findViewById(R.id.activity_history_d5_linearlayout);
        mDay4 = findViewById(R.id.activity_history_d4_linearlayout);
        mDay3 = findViewById(R.id.activity_history_d3_linearlayout);
        mDay2 = findViewById(R.id.activity_history_d2_linearlayout);
        mDay1 = findViewById(R.id.activity_history_d1_linearlayout);

        mDay7Btn = findViewById(R.id.activity_history_d7_notebtn);
        mDay6Btn = findViewById(R.id.activity_history_d6_notebtn);
        mDay5Btn = findViewById(R.id.activity_history_d5_notebtn);
        mDay4Btn = findViewById(R.id.activity_history_d4_notebtn);
        mDay3Btn = findViewById(R.id.activity_history_d3_notebtn);
        mDay2Btn = findViewById(R.id.activity_history_d2_notebtn);
        mDay1Btn = findViewById(R.id.activity_history_d1_notebtn);

        mDay7Btn.setOnClickListener(this);
        mDay6Btn.setOnClickListener(this);
        mDay5Btn.setOnClickListener(this);
        mDay4Btn.setOnClickListener(this);
        mDay3Btn.setOnClickListener(this);
        mDay2Btn.setOnClickListener(this);
        mDay1Btn.setOnClickListener(this);

        calculate7dates();
        returnSavedSmiley();
        returndSavedNotes();
        cleanSharedPreferences();

        // send data to the layout
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
    /**
     * create keys for 7 last days
     */
    public void calculate7dates() {
        Date day = new Date();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        today = sdf.format(day);
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
    /**
     * return saved smileys
     */
    public void returnSavedSmiley() {
        smiley7return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus7, 3);
        smiley6return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus6, 3);
        smiley5return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus5, 3);
        smiley4return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus4, 3);
        smiley3return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus3, 3);
        smiley2return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus2, 3);
        smiley1return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus1, 3);
        smiley0return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + today, 3);
    }
    /**
     * return saved notes
     */
    public void returndSavedNotes() {
        note7return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus7, null);
        note6return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus6, null);
        note5return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus5, null);
        note4return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus4, null);
        note3return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus3, null);
        note2return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus2, null);
        note1return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + todayMinus1, null);
        note0return = getSharedPreferences("smiley", MODE_PRIVATE).getString("NOTE_KEY_" + today, null);
    }
    /**
     * clean the unused SharedPreferences
     */
    public void cleanSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("smiley", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        saveDaylyPreferences(today, smiley0return, note0return);
        saveDaylyPreferences(todayMinus1, smiley1return, note1return);
        saveDaylyPreferences(todayMinus2, smiley2return, note2return);
        saveDaylyPreferences(todayMinus3, smiley3return, note3return);
        saveDaylyPreferences(todayMinus4, smiley4return, note4return);
        saveDaylyPreferences(todayMinus5, smiley5return, note5return);
        saveDaylyPreferences(todayMinus6, smiley6return, note6return);
        saveDaylyPreferences(todayMinus7, smiley7return, note7return);
    }

    /**
     * save the SharedPreferences
     */
    public void saveDaylyPreferences(String day, int smiley, String note) {
        SharedPreferences preferences = getSharedPreferences("smiley", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (note != null) {
            editor.putString("NOTE_KEY_" + day , note);
        } else {
            editor.putString("NOTE_KEY_" + day, "");
        }
        editor.apply();

        editor.putInt("SMILEY_KEY_" + day ,smiley);
        editor.apply();

    }

    /**
     * adaptation of the layout (colors and sizes of each view)
     * @param mDay
     *            LinearLayout for each day in HistoryActivity / mDay?
     * @param mDayTV
     *            TextView with the text of the day / MDay?TextView
     * @param smiley
     *            Index of registered smiley of the day / smiley?return
     * @param context
     *            This context
     */
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
    /**
     * display or not the button
     * @param noteReturn
     *            note which was registerd in SavedSharedPreferences for a day
     * @param mDayBtn
     *            Button showing if there is a note or not for a day
     */
    public void displayNoteBtn(String noteReturn, ImageButton mDayBtn) {
        if (noteReturn==null || noteReturn.equals("")) {
            mDayBtn.setVisibility(View.INVISIBLE);
        } else {
            mDayBtn.setVisibility(View.VISIBLE);
        }
    }
    /**
     * show Toast if one note button is clicked
     * @param v
     *            view corresponding of the representation of a day,
     *            give us the reference of the day which vas choose when button was clicked
     */
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
}
