package by.gladyshev.util;

import by.gladyshev.Main;
import by.gladyshev.entity.ColorBrightnessDecorator;
import by.gladyshev.entity.Range;
import javafx.scene.chart.XYChart;

import java.awt.*;
import java.util.ArrayList;

public class HistBuilder {
    private static HistBuilder instance;

    private HistBuilder(){}
    public static HistBuilder getInstance() {
        if(instance==null) {
            instance = new HistBuilder();
        }
        return instance;
    }
    public XYChart.Series build(Color[] pixels, int precision)// Строим гистограмму на основе пикселей
            //precision -- количество разбиений в гистограмме
    {
        XYChart.Series series = new XYChart.Series();
        Range[] ranges = findRanges(precision, pixels);
        for (int i = 0; i < ranges.length; i++) {
            series.getData().add(new XYChart.Data(ranges[i].getRange(), ranges[i].getValues().size()));
        }
        Main.ranges = ranges;
        return series;
    }
    private Range[] findRanges(int precision, Color[] pixels)
            // вычисляем диапазоны значений по количеству разбиений
    {
        double delta = 255.0/precision;
        double sum = 0.0;
        Range[] res = new Range[precision];
        String[] h_i = new String[precision];
        for (int i = 0; i < h_i.length; i++) {
            h_i[i] = "" + (sum + (delta/2));
            Range temp = new Range();
            temp.setRange(h_i[i]);
            for (int j = 0; j < pixels.length; j++) {
                ColorBrightnessDecorator tempCBD = new ColorBrightnessDecorator(pixels[j]);
                if(tempCBD.getBrightness()>=sum&&tempCBD.getBrightness()<sum+delta)
                {
                    temp.getValues().add(pixels[j]);
                }
            }
            res[i] = temp;
            sum = sum + delta;
        }
        return res;
    }

}
