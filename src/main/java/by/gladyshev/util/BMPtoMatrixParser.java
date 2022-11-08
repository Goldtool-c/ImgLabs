package by.gladyshev.util;


import by.gladyshev.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BMPtoMatrixParser {
    private static BMPtoMatrixParser instance;

    private BMPtoMatrixParser(){}
    public static BMPtoMatrixParser getInstance() {
        if(instance==null) {
            instance = new BMPtoMatrixParser();
        }
        return instance;
    }
    public Color[] parse(File bmpImage) throws IOException {
        BufferedImage image = ImageIO.read(bmpImage);
        Main.width = image.getWidth();
        Main.height = image.getHeight();
        Color[] result = new Color[image.getWidth()*image.getHeight()];
        int c = 0;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                result[c] = new Color(image.getRGB(i, j));
                c++;
            }
        }
        return result;
    }

}
