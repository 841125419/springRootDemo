package com.hand.hmall.util;/**
 * Created by Administrator on 2017/6/8.
 */

import com.hand.hmall.dto.hmallTable.HmallTable;

/**
 * @author ChenMingjian
 * @create 2017-06-08 17:54
 **/
public class Util {
    private static String str_def = "";
    public static boolean isEmpty(Object o){
        if (o == null || o.toString().equals("")){
            return true;
        }
        return false;
    }
    public static int check2Int(Object o,int def){
        if(o == null){
            return def;
        }
        return Integer.parseInt(o.toString());
    }

    public static String check2Str(Object o) {
        if(o == null){
            return str_def;
        }
        return o.toString();
    }
}
