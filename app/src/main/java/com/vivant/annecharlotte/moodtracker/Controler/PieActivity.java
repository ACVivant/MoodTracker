package com.vivant.annecharlotte.moodtracker.Controler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.vivant.annecharlotte.moodtracker.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class PieActivity extends AppCompatActivity {

    private int smiley7return, smiley6return, smiley5return, smiley4return, smiley3return, smiley2return, smiley1return;
    private String todayMinus1, todayMinus2, todayMinus3, todayMinus4, todayMinus5, todayMinus6, todayMinus7;


    HistoryActivity mHistoryActivity = new HistoryActivity();

    private int smileyTab[]= {0,0,0,0,0};
    private String smileyName[] = {"Dur, dur", "Peut mieux faire", "Pas si mal", "Sympa!", "Trop fort!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("bibi", "onCreate: début");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        calculate7dates();
        createTab();
        setupPieChart();
    }

    public void  setupPieChart() {
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i=0; i<smileyName.length; i++) {
            pieEntries.add(new PieEntry(smileyTab[i], smileyName[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "mes humeurs");   // Pourquoi est-ce que je ne peux pas mettre R.strings.pie_title comme référence pour le titre?
        PieData data = new PieData(dataSet);

        PieChart chart = (PieChart) findViewById(R.id.activity_pie_piechart);
        chart.setData(data);
        chart.invalidate();
    }

    public void createTab() {

        smiley7return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus7, 3);
        smiley6return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus6, 3);
        smiley5return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus5, 3);
        smiley4return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus4, 3);
        smiley3return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus3, 3);
        smiley2return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus2, 3);
        smiley1return = getSharedPreferences("smiley", MODE_PRIVATE).getInt("SMILEY_KEY_" + todayMinus1, 3);

        int smiley7days[] = {smiley1return, smiley2return, smiley3return, smiley4return, smiley5return, smiley6return, smiley7return};

        for (int i=0; i<5; i++) {   // boucle sur les 5 valeurs de smiley
            int counter =0;
            for (int j=0; j<7; j++) {  // boucle sur les 7 jours de la semaine
                if (smiley7days[j] ==i) {
                    counter+=1;
                    smileyTab[i] = counter;
                }
            }
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
}

