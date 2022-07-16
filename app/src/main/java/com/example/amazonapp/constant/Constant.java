package com.example.amazonapp.constant;

import com.example.amazonapp.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static final List<Integer> QUANTITY_LIST= new ArrayList<>();

    static {
    for(int i=1;i<11;i++)
    {
        QUANTITY_LIST.add(i);

    }
      }

      public static final Product PRODUCT1=new Product(1,"Redmi Note 11T", BigDecimal.valueOf(16999),
              "3.5mm Jack, IR Blaster,\n X-Axis Linear Vibration Motor,\n Corning gorilla glass 3 protection,\n" +
                      " Dual Stereo Speakers for immersive audio experience.\n Hi-Res certified audio, Splash, Water and" +
                      " Dust Resistant- IP53","redminote");

    public static final Product PRODUCT2=new Product(2,"\n" +
            "OnePlus Nord 2T",BigDecimal.valueOf(24999),"Display: 6.43 Inches; 90 Hz AMOLED \nDisplay with Corning Gorilla Glass 5\n Resolution: 2400 X 1080 pixels \nHDR 10+ Certified\n" +
            "Display Features: Ambient Display,\n AI colour enhancement and Dark mode","oneplusnord");

    public static final Product PRODUCT3=new Product(3,"\n" +
            "Samsung Galaxy M53",BigDecimal.valueOf(17899),"6.7-inch Super AMOLED Plus Display,\n FHD+ resolution, 1080x2400 pixels with 120Hz Refresh Rate\n" +
            "MTK D900 Octa Core 2.4GHz 6nm Processor\n with 4x4 Mimo Band support for a HyperFast 5G experience","samsunggalaxy");

    public static final List<Product> PRODUCT_LIST= new ArrayList<>();

    static {
        PRODUCT_LIST.add(PRODUCT1);
        PRODUCT_LIST.add(PRODUCT2);
        PRODUCT_LIST.add(PRODUCT3);
    }

    public static final String CURRENCY="₹";
}
