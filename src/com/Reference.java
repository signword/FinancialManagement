package com;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Reference {
    public static ResultSet KEY;
    public static int USER_ID = 0;
    public static List<String> USER_ID_remain = new ArrayList<>();
    public static String AvailID_Find(){
        String AvailID;
        if(USER_ID_remain.isEmpty()){
            AvailID = String.format("%6d", Reference.USER_ID).replace(" ", "0");
        } else {
            AvailID = USER_ID_remain.get(0);
            USER_ID_remain.remove(0);
        }
        //id超过6位数，提示一下
        return AvailID;
    }
}
