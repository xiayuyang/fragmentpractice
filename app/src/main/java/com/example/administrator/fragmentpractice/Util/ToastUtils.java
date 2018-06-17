package com.example.administrator.fragmentpractice.Util;


import android.widget.Toast;

import com.example.administrator.fragmentpractice.Util.Application;

public class ToastUtils {
    public static void showError(String error){
        Toast.makeText(Application.getContext(),error,
                Toast.LENGTH_LONG).show();
    }

    public static void showResponse(String info){
        Toast.makeText(Application.getContext(),info,
                Toast.LENGTH_SHORT).show();
    }
}
