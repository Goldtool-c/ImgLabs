package by.gladyshev.util;

import by.gladyshev.entity.ColorBrightnessDecorator;

import java.awt.*;

public class RangeFinder { // юзелесс, удаляйте файл
    private static RangeFinder instance;

    private RangeFinder(){}
    public static RangeFinder getInstance() {
        if(instance==null) {
            instance = new RangeFinder();
        }
        return instance;
    }

    public int[] findRange(Color[][] pixels)
    {
        double maxBrightness = 0.0;
        double minBrightness = new ColorBrightnessDecorator(pixels[0][0]).getBrightness();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                ColorBrightnessDecorator temp = new ColorBrightnessDecorator(pixels[i][j]);
                if(temp.getBrightness()>=maxBrightness)
                {
                    maxBrightness = temp.getBrightness();
                }
                if(temp.getBrightness()<=minBrightness)
                {
                    minBrightness = temp.getBrightness();
                }
            }
        }
        return new int[]{(int) minBrightness, (int) (maxBrightness+1)};
    }
}
