package by.gladyshev.util;

import by.gladyshev.entity.ColorBrightnessDecorator;

import java.awt.*;

public class RGBtoGreyConverter {
    private static RGBtoGreyConverter instance;

    private RGBtoGreyConverter(){}
    public static RGBtoGreyConverter getInstance() {
        if(instance==null) {
            instance = new RGBtoGreyConverter();
        }
        return instance;
    }
    public Color[] convert(Color[] pixels) // Преобразование картинки в чб
    {
        Color[] greyPixels = new Color[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            int grey = (int) new ColorBrightnessDecorator(pixels[i]).getBrightness();
            greyPixels[i] = new Color(grey, grey, grey);
        }
        return greyPixels;
    }
}
