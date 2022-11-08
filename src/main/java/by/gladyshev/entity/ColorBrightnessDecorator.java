package by.gladyshev.entity;

import java.awt.*;

public class ColorBrightnessDecorator { // Обертка стандартного класса color
    // нужна чтобы расширить функционал класса color, дабы
    // иметь возможность вычислять ее серую яркость
    private Color pixel;
    public ColorBrightnessDecorator(Color pixel) {
        this.pixel = pixel;
    }

    public Color getPixel() {
        return pixel;
    }

    public void setPixel(Color pixel) {
        this.pixel = pixel;
    }
    public double getBrightness()
    {
        //Считаем яркость из RGB пикселя
        //https://translated.turbopages.org/proxy_u/en-ru.ru.c44e60b7-6365627e-7854031f-74722d776562/https/stackoverflow.com/questions/596216/formula-to-determine-perceived-brightness-of-rgb-color

        return ((0.2126*pixel.getRed()) + (0.7152*pixel.getGreen()) + (0.0722*pixel.getBlue()));
    }
}
