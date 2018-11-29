package com.vivant.annecharlotte.moodtracker.Controler;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.vivant.annecharlotte.moodtracker.R;
import com.vivant.annecharlotte.moodtracker.Model.SmileyEnum;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class MainActivity extends AppCompatActivity implements SmileyFragment.OnButtonClickedListener {
    private String noteKey;
    private String smileyKey;
    private EditText smileyText;
    private String smsTextLong;
    private EditText phone;


    protected int responseIndex;

    private int SMS_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VerticalViewPager pager = findViewById(R.id.activity_main_verticalviewpager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(3);
    }
    /**
     * start HistoryActivity when user clicks on button historic button
     * @see HistoryActivity
     */
    @Override
    public void onHistoryClicked() {
        Intent historyActivity = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(historyActivity);
    }
    /**
     * start PieActivity when user clicks on piechart button
     * @see PieActivity
     */
    @Override
    public void onPieClicked() {
        Intent pieActivity = new Intent(MainActivity.this, PieActivity.class);
        startActivity(pieActivity);
    }

    /**
     * generate AlertDialog when user clicks on note button
     * @param position
     *          index of the smiley which was clicked
     * @see MainActivity#playMusique()
     * @see MainActivity#createKeys()
     * @see MainActivity#saveToday(String)
     * @see MainActivity#askForPermission()
     */
    @Override
    public void onCommentClicked(int position) {
        responseIndex = position;

        playMusique();

        View view; // creation of the Dialog View
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.alertdialog_notes, null);
        smileyText = view.findViewById(R.id.frg_my_dialog_note_text_input);
        builder.setView(view);
        builder.setPositiveButton(R.string.validate_alertbtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createKeys();
                saveToday(smileyText.getText().toString());
            }
        });

        builder.setNeutralButton(R.string.dismiss_alertbtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createKeys();
                saveToday("");
            }
        });
        builder.setNegativeButton(R.string.sms_alertbtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createKeys();
                saveToday(smileyText.getText().toString());

                askForPermission();
            }
        });
        builder.create().show();
    }
    //-----------------------------------------------------------------------------------------------------------------
    // All about SMS
    //----------------------------------------------------------------------------------------------------------------
    /**
     * launch method for permission to send SMS if not already given
     * @see MainActivity#createSms
     * @see MainActivity#requestSMSPermission()
     */
    private void askForPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            createTextSms();
        } else {
            requestSMSPermission();
        }
    }
    /**
     * inform user that we need permission to send SMS
     */
    private void requestSMSPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.permission_title)
                    .setMessage(R.string.permission_message)
                    .setPositiveButton(R.string.permission_ok_btn, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_SMS}, SMS_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton(R.string.permission_no_btn, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_SMS}, SMS_PERMISSION_CODE);
        }
    }

    /**
     * generate Toast showing if user gave the permission to send SMS
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.permission_ok_toast, Toast.LENGTH_SHORT).show();
                createTextSms();
            } else {
                Toast.makeText(this, R.string.permission_no_toast, Toast.LENGTH_SHORT).show();
            }
        }
    }
    /**
     * generate text for the SMS
     */
    private void createTextSms() {
        String smsMood;
        switch (responseIndex) {
            case 0:
                smsMood = getResources().getString(R.string.sms_1);
                break;
            case 1:
                smsMood = getResources().getString(R.string.sms_2);
                break;
            case 2:
                smsMood = getResources().getString(R.string.sms_3);
                break;
            case 3:
                smsMood = getResources().getString(R.string.sms_4);
                break;
            case 4:
                smsMood = getResources().getString(R.string.sms_5);
                break;
            default:
                smsMood = getResources().getString(R.string.sms_4);
                break;
        }

        smsTextLong = getResources().getString(R.string.sms_text1) + smsMood + ". \n" + smileyText.getText().toString();
        createSms();
    }
    /**
     * generate AlertDialog with SMS Text
     */
    private void createSms() {
            View view;
        EditText smsText;

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            view = getLayoutInflater().inflate(R.layout.alertdialog_sms, null);
            phone = view.findViewById(R.id.frg_sms_phonenumer_edittext);
            smsText = view.findViewById(R.id.frg_sms_message_edittext);
            smsText.setText(smsTextLong);
            builder.setView(view);
            builder.setPositiveButton(R.string.validate_alertbtn, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sentMessage();
                }
            });

            builder.setNeutralButton(R.string.dismiss_alertbtn, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.create().show();
    }
    /**
     * verify that the phone number as the good format and sent the SMS
     */
    private void sentMessage() {
        String phoneMessage = phone.getText().toString();
        if (phoneMessage.length() == 10) {
            SmsManager.getDefault().sendTextMessage(phoneMessage, null, smsTextLong, null, null);
        } else {
            Toast.makeText(this, R.string.permission_wrong, Toast.LENGTH_SHORT).show();
            createSms();
        }
    }
    //-----------------------------------------------------------------------------------------------------------------
    // All about saving data
    //----------------------------------------------------------------------------------------------------------------
    /**
     * create keys to save datas in SavedSharedPreferences
     */
    public void createKeys() { // create the key of the day for theSavesPreferences
        Date day = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd", Locale.FRENCH);
        String s = f.format(day);
        noteKey = "NOTE_KEY_" + s;
        smileyKey = "SMILEY_KEY_" + s;
    }
    /**
     * save mood and note of the day
     * @param text
     *          note that the user may have entered
     */
    public void saveToday(String text) {
        SharedPreferences preferences = getSharedPreferences("smiley", MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        if (text != null) {
            editor.putString(noteKey , text);
        } else {
            editor.putString(noteKey, "");
        }
        editor.apply();
        //preferences.getString(noteKey, "défaut");

        editor.putInt(smileyKey, responseIndex);
        editor.apply();
    }

    //-----------------------------------------------------------------------------------------------------------------
    // All about sound
    //----------------------------------------------------------------------------------------------------------------
    /**
     * play a sound when user saves a mood
     * each mood is linked with a particular sound
     */
    public void playMusique() {
        MediaPlayer mediaPlayer;

        switch (responseIndex) {
            case 0:
                mediaPlayer = MediaPlayer.create(this, R.raw.dur);
                break;
            case 1:
                mediaPlayer = MediaPlayer.create(this, R.raw.bof);
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(this, R.raw.normal);
                break;
            case 3:
                mediaPlayer = MediaPlayer.create(this, R.raw.happy);
                break;
            case 4:
                mediaPlayer = MediaPlayer.create(this, R.raw.superhappy);
                break;
            default:
                mediaPlayer = MediaPlayer.create(this, R.raw.happy);
                break;
        }
        mediaPlayer.start();
    }
    //-----------------------------------------------------------------------------------------------------------------
    // All about ViewPager
    //----------------------------------------------------------------------------------------------------------------
    /**
     * link between position in ViewPager and Mood
     */
    private class MyPagerAdapter extends FragmentPagerAdapter {
        private MyPagerAdapter(FragmentManager fm) {
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
