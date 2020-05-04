package com.example.experiment7_1.Factory;

import android.content.Context;

import com.example.experiment7_1.DAO.tab_service;

public class ServiceFactory {
    public static tab_service Service(Context context){
        return new tab_service(context);
    }
}
