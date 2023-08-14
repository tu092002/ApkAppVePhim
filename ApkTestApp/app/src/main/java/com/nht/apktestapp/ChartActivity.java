package com.nht.apktestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nht.apktestapp.Dao.RapDao;
import com.nht.apktestapp.Dao.VeDaThanhToanDao;
import com.nht.apktestapp.Model.Rap;
import com.nht.apktestapp.Model.VeDaThanhToanByRap;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);


    //  Thống kê số vé đã đặt theo rạp bằng pieChart
        PieChart pieChart = findViewById(R.id.pieChart);
        VeDaThanhToanDao dao = new VeDaThanhToanDao();
        List<VeDaThanhToanByRap> listVe = dao.getVeDaThanhToanByRap();
        ArrayList<PieEntry> data = new ArrayList<>();
        for (int i = 0; i < listVe.size(); i++) {
            data.add(new PieEntry(listVe.get(i).getSoVeDaThanhToan(), listVe.get(i).getTenRap() ));
        }
        PieDataSet pieDataSet = new PieDataSet(data, "Rạp");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Số lượng vé được đặt cho từng rạp");
        pieChart.animate();


        //Thống kê theo barchart - đang dữ liệu ảo
        BarChart barChart = findViewById(R.id.chart);

        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(1, 420));
        visitors.add(new BarEntry(2, 450));
        visitors.add(new BarEntry(3, 520));
        visitors.add(new BarEntry(4, 620));
        visitors.add(new BarEntry(5, 820));
        visitors.add(new BarEntry(6, 320));
        visitors.add(new BarEntry(7, 220));
        visitors.add(new BarEntry(8, 250));
        visitors.add(new BarEntry(9, 250));
        visitors.add(new BarEntry(10, 250));
        visitors.add(new BarEntry(11, 250));
        visitors.add(new BarEntry(12, 250));

        BarDataSet barDataSet = new BarDataSet(visitors, "Visitors");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(2000);

        //Gọi từng chart
        Button btnChart1 = (Button) findViewById(R.id.btnChart1);
        Button btnChart2 = (Button) findViewById(R.id.btnChart2);
        Button btnChart3 = (Button) findViewById(R.id.btnChart3);
        btnChart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pieChart.setVisibility(View.GONE);
               barChart.setVisibility(View.VISIBLE);
            }
        });
        btnChart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pieChart.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.GONE);
            }
        });
    }
}