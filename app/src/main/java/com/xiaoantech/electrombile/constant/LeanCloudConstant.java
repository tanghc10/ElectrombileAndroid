package com.xiaoantech.electrombile.constant;

/**
 * Created by yangxu on 2016/10/31.
 */

public class LeanCloudConstant {
    public static final String UserTable = "_User";
    public static final String BindTable = "Bindings";
    public static final String DIDTable = "DID";

    public static final String IMEI = "IMEI";


    //User
    public static final String defaultPassword = "123456";
    public static final String UserName = "username";


    //Bindings
    public static final String User = "user";
    public static final String CreatedAt = "createdAt";

    //DID
    public static final String Itinerary = "itinerary";
    public static final String CarName = "name";
    public static final String Image = "image";

    public enum leanCloudOptionType {
        LEAN_CLOUD_OPTION_TYPE_TOTAL_ITINERARY,
        LEAN_CLOUD_OPTION_TYPE_IMEI_LIST
    }

}