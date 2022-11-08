package by.gladyshev;

import by.gladyshev.entity.Range;
import by.gladyshev.task.HistLineariser;
import by.gladyshev.task.LinearContrast;
import by.gladyshev.task.ToBitPlane;
import by.gladyshev.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main extends Application {
    public static int width; //ширина картинки в пикселях
    public static int height; // высота картинки в пикселях
    public static Range[] ranges;
    public void start(Stage stage) throws IOException {
        //НЕ ЗАБЫВАЕМ ЧИСТИТЬ КОМЕНТЫ ПЕРЕД СДАЧЕЙ!!!
        //НЕ ЗАБЫВАЕМ ЧИСТИТЬ КОМЕНТЫ ПЕРЕД СДАЧЕЙ!!!
        //НЕ ЗАБЫВАЕМ ЧИСТИТЬ КОМЕНТЫ ПЕРЕД СДАЧЕЙ!!!
        File file = new File("Arch.png");
        Color[] pixels = new Color[0];
        try {
            pixels = BMPtoMatrixParser.getInstance().parse(file);// заполняем Массив пикселей из файла
        } catch (IOException e) {
            e.printStackTrace();
        }
        PixelsToFile.getInstance().save(RGBtoGreyConverter.getInstance().convert(pixels), "chernobeloe.jpg");//Cохраняем
        // чб вариант изображения
        assembleStage(pixels, "original.jpg");
        //Выравнивание гистограммы - это метод обработки изображений,
        //который регулирует контраст изображения с помощью его гистограммы.
        //Чтобы повысить контраст изображения, он расширяет наиболее частые значения
        //яркости пикселей или расширяет диапазон яркости изображения.
        Color[] linearizedPixels = HistLineariser.getInstance().linearise(pixels);
        assembleStage(linearizedPixels, "linearized.jpg");
        //Линейное контрастирование изображения (линейная коррекция).
        //Задача контрастирования связана с улучшением согласования динамического диапазона
        //изображения и экрана, на котором выполняется визуализация.
        Color[] lContrastedPixels = LinearContrast.getInstance().convert(pixels);
        assembleStage(lContrastedPixels, "linearContrast.jpg");
        Color[] bits;
        for (int i = 0; i < 8; i++) { // режем изображение на битовые плоскости
            bits = ToBitPlane.getInstance().toBitPlane(pixels, i);
            PixelsToFile.getInstance().save(bits, "bits"+i+".jpg");
        }

    }

    public static void main(String... args)
    {
        launch(args);
    }
    private void assembleStage(Color[] pixels, String path) throws IOException { // Функция построения окна с гистограммой
        Stage stage1 = new Stage();
        final CategoryAxis xAxis1 = new CategoryAxis();
        final NumberAxis yAxis1 = new NumberAxis();
        final BarChart<String,Number> bc1 = new BarChart<>(xAxis1,yAxis1);
        stage1.setTitle(path);
        bc1.setTitle("Image hist");
        xAxis1.setLabel("Brightness");
        yAxis1.setLabel("H(i)");
        XYChart.Series series1 = HistBuilder.getInstance().build(pixels, 255);// строим гистограмму
        Scene scene1  = new Scene(bc1,800,600);
        bc1.getData().addAll(series1);// помещаем ее в окно
        stage1.setScene(scene1);
        stage1.show();// показываем окно
        PixelsToFile.getInstance().save(pixels, path);// сохраняем рисунок
    }


}
