package com.four.factory;

import java.util.ResourceBundle;

public class DaoFactory {
    private static ResourceBundle bundle;
    static{
        bundle=ResourceBundle.getBundle("instance");
    }
    /**
     * 泛型方法
     */
    public static <T>T getInstance(String key) {
        Class cls= null;
        T t=null;
        try {
            cls = Class.forName(bundle.getString(key));
            t= (T) cls.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return t;
    }
}
