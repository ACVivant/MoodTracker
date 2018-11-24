package com.vivant.annecharlotte.moodtracker;

/**
 * Created by Anne-Charlotte Vivant on 24/11/2018.
 */
public enum SmileyEnum {

    // association des humeurs avec leur couleur de fond et leur position
    SAD(0, R.color.faded_red, R.drawable.smiley_disappointed),
    DISAPPOINTED(1, R.color.warm_grey, R.drawable.smiley_sad),
    NORMAL(2, R.color.cornflower_blue_65, R.drawable.smiley_normal),
    HAPPY(3, R.color.light_sage, R.drawable.smiley_happy),
    SUPER_HAPPY(4, R.color.banana_yellow, R.drawable.smiley_super_happy),
    UNKNOWN(-1, R.color.faded_red, R.drawable.smiley_disappointed);

    SmileyEnum(int index, int color, int image) {
        this.index = index;
        this.color = color;
        this.image = image;
    }

    private int index = 0;
    private int color;
    private int image;

    public int getIndex(){
        return index;
    }

    public int getColor(){
        return color;
    }

    public int getImage(){
        return image;
    }

}
