package by.gladyshev.util;

import by.gladyshev.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PixelsToFile { // Cохранение массива пикселей в картинку
    private static PixelsToFile instance;
    private PixelsToFile(){}
    public static PixelsToFile getInstance() {
        if(instance==null) {
            instance = new PixelsToFile();
        }
        return instance;
    }
    public void save(Color[] pixels, String path) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(Main.width, Main.height,
                BufferedImage.TYPE_INT_RGB);
        int c = 0;
        for (int x = 0; x < Main.width; x++) {// заполняем пустую картинку пикселями
            for (int y = 0; y < Main.height; y++) {
                bufferedImage.setRGB(x, y, pixels[c].getRGB());
                c++;
            }
        }
        File outputfile = new File(path);
        ImageIO.write(bufferedImage, "jpg", outputfile);//сохраняем заполненную картинку
    }
}
