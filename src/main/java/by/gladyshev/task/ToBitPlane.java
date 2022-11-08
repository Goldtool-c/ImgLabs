package by.gladyshev.task;

import by.gladyshev.entity.ColorBrightnessDecorator;

import java.awt.*;

public class ToBitPlane {
    private static ToBitPlane instance;
    private String[] brightness;//Cтроковое представление байтового значения яркости
    //тк эта функция вызывается 8 раз для 8 разных битов одного и того же байта (значение яркости пикселя),
    //а набор пикселей все 8 раз один и тот же, можно заполнить этот массив только 1 раз
    //и этот массив будет общим для всех вызовов этой функции
    private ToBitPlane(){}
    public static ToBitPlane getInstance() {
        if(instance==null) {
            instance = new ToBitPlane();
        }
        return instance;
    }
    //НЕ ЗАБЫВАЕМ ЧИСТИТЬ КОМЕНТЫ ПЕРЕД СДАЧЕЙ!!!
    //НЕ ЗАБЫВАЕМ ЧИСТИТЬ КОМЕНТЫ ПЕРЕД СДАЧЕЙ!!!
    //НЕ ЗАБЫВАЕМ ЧИСТИТЬ КОМЕНТЫ ПЕРЕД СДАЧЕЙ!!!
    public Color[] toBitPlane(Color[] pixels, int id) {
        //хитровыдуманный механизм оптимизации
        if (brightness == null) {// Проверяем, не заполняли ли мы массив раньше
            int[] temp = new int[pixels.length];
            brightness = new String[pixels.length];
            for (int i = 0; i < pixels.length; i++) {
                temp[i] = (int) new ColorBrightnessDecorator(pixels[i]).getBrightness();// Вычисляем серую яркость пикселя
                //преобразуем ее в строковое представление ее байта
                brightness[i] = String.format("%8s", Integer.toBinaryString((byte) temp[i] & 0xFF)).replace(' ', '0');
            }
        }
        Color[] res = new Color[pixels.length];
        for (int i = 0; i < res.length; i++) {
            if(brightness[i].charAt(7-id)=='0')// если бит в выбранной плоскости =1, то делаем пиксель белым, иначе -- черным
            {
                res[i] = new Color(0,0,0);
            } else {
                res[i] = new Color(255, 255, 255);
            }
        }
        return res;
    }
}
