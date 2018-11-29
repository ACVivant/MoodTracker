package com.vivant.annecharlotte.moodtracker.Controler;

import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.vivant.annecharlotte.moodtracker.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 * PieActivity manages what is related to the statistic function.
 *
 * Members of this class are characterized by :
 * values of keys used in SavedSharedPreferences
 * strings for the legend
 * array with number of occurences of each mood for the 7 last days
 *
 * @author acvivant
 * @version 1.0
 */
public class PieActivity extends AppCompatActivity {
    private String todayMinus1, todayMinus2, todayMinus3, todayMinus4, todayMinus5, todayMinus6, todayMinus7;

    private int smileyTab[]= {0,0,0,0,0};
  /*  String leg1 = getResources().getString(R.string.sms_1);  // Ca fait planter l'appli! Pourquoi?
    String leg2 = getResources().getString(R.string.sms_2);
    String leg3 = getResources().getString(R.string.sms_3);
    String leg4 = getResources().getString(R.string.sms_4);
    String leg5 = getResources().getString(R.string.sms_5);*/

 //   private String smileyName[] = {leg1, leg2, leg3, leg4, leg5};
    private String smileyName[] = {"affreuse", "mauvaise", "normale", "bonne", "très bonne"};

    /**
     * generate a pie chart with statistic about moods of 7 lasted days
     *
     * @param savedInstanceState
     *                  saved instance state
     * @see PieActivity#calculate7dates()
     * @see PieActivity#createTab()
     * @see PieActivity#setupPieChart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        calculate7dates();
        createTab();
        setupPieChart();
    }
    /**
     * create keys for 7 late days
     */
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
    /**
     * create array  with statistics
     */
    public void createTab() {
        int smiley7return, smiley6return, smiley5return, smiley4return, smiley3return, smiley2return, smiley1return;

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
    /**
     * customize the pie chart
     */
    public void  setupPieChart() {
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i=0; i<smileyName.length; i++) {
            pieEntries.add(new PieEntry(smileyTab[i], smileyName[i]));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, getResources().getString(R.string.pie_legend_title));
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(15f);
        dataSet.setColors(new int[] {getResources().getColor(R.color.faded_red), getResources().getColor(R.color.warm_grey), getResources().getColor(R.color.cornflower_blue_65), getResources().getColor(R.color.light_sage), getResources().getColor(R.color.banana_yellow)}, 1000);
        PieData data = new PieData(dataSet);
        data.setDrawValues(false);

        PieChart chart = findViewById(R.id.activity_pie_piechart);
        chart.setData(data);
        chart.animateY(1000);
        chart.setHoleRadius(45f);
        chart.setCenterText(getResources().getString(R.string.pie_title));
        chart.setCenterTextSize(18f);
        chart.invalidate();

        Description description = new Description();
        description.setText("");
        chart.setDescription(description);
        Legend l = chart.getLegend();
        l.setFormSize(20f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setTextSize(16f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(3f);
        l.setWordWrapEnabled(true);
    }
}

