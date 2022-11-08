package by.gladyshev.entity;

import java.awt.*;
import java.util.ArrayList;

public class Range {
    private String range;
    private ArrayList<Color> values = new ArrayList<>();

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public ArrayList<Color> getValues() {
        return values;
    }

    public void setValues(ArrayList<Color> values) {
        this.values = values;
    }
}
