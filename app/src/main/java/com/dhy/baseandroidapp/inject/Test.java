package com.dhy.baseandroidapp.inject;

/**
 * Created by dhy on 2018/1/19.
 */
public class Test {
    public static final int PARA1 = 1;
    public static final int PARA2 = 2;
    public static final int PARA3 = 3;
    int text;

//    @IntDef({PARA1,PARA2,PARA3})
//    public @interface Param{
//
//    }
//
//    @Param
//    public int getTest() {
//        return text;
//    }

    public void setText( int flavour) {
        this.text = flavour;
    }
}
