package by.gladyshev.task;

import by.gladyshev.entity.ColorBrightnessDecorator;

import java.awt.*;

public class LinearContrast {
    private static LinearContrast instance;
    private LinearContrast(){}
    public static LinearContrast getInstance() {
        if(instance==null) {
            instance = new LinearContrast();
        }
        return instance;
    }
    public Color[] convert(Color[] pixels)
    {
        //f' = (255/(fmax - fmin))*(f - fmin)
        double min = new ColorBrightnessDecorator(pixels[0]).getBrightness();
        double max = 0.0;
        for (int i = 0; i < pixels.length; i++) {// вычисляем f max и f min
            double temp = new ColorBrightnessDecorator(pixels[i]).getBrightness();
            if(temp>=max)
            {
                max = temp;
            }
            if(temp<=min)
            {
                min = temp;
            }
        }
        Color[] res = new Color[pixels.length];
        int f;
        for (int i = 0; i < res.length; i++) {// заполняем новый массив пикселей по формуле сверху
            double brightness = new ColorBrightnessDecorator(pixels[i]).getBrightness();
            f = (int) ((255/(max-min))*(brightness-min));
            res[i] = new Color(f, f, f);
        }
        return res;
    }
}
