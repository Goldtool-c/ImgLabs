package by.gladyshev.task;

import by.gladyshev.Main;
import by.gladyshev.entity.ColorBrightnessDecorator;

import java.awt.*;

public class HistLineariser {
    private static HistLineariser instance;
    private HistLineariser(){}
    public static HistLineariser getInstance() {
        if(instance==null) {
            instance = new HistLineariser();
        }
        return instance;
    }

    public Color[] linearise(Color[] pixels) // Выравниваем гистограмму
    {
        double p[] = new double[255]; // массив вероятнотей
        Color[] linearizedPixels = new Color[pixels.length];
        for (int i = 0; i < 255; i++) { // Вычисляем вероятность появления каждого целого
            //значения яркости на картинке в диапазоне [0, 255]
            p[i] = Main.ranges[i].getValues().size()/(double)(Main.height*Main.width);
        }
        double[] brightness = new double[255];
        brightness[0] = p[0];
        for (int i = 1; i < 255; i++) {//Находим яркость пикселей по формуле из методы
            // Hi = сумма (p jитое) j принадлежит от 0 до i
            double prob = p[0];
            for (int j = 0; j < i; j++) {
                prob = prob + p[j];
            }
            brightness[i] = prob;
        }
        for (int i = 0; i < pixels.length; i++) {
            ColorBrightnessDecorator CBD = new ColorBrightnessDecorator(pixels[i]);
            int grey = (int) (255 * brightness[(int) CBD.getBrightness()]); // представляем нашу яркость в фомате (0, 255)
            // ибо иначе она будет лежать в (0, 1)
            if(grey>255)
            {
                grey = 255;
            }
            linearizedPixels[i] = new Color(grey, grey, grey);//сохраняем новый пикслель
        }
        return linearizedPixels;
    }
}
