package com.gs.web.app;

import com.gslibrary.app.SAppLication;

/*********************************************
 ***                                       ***
 ***                                       ***
 ***       Created by HC on 2017/5/3.       ***
 *********************************************/

public class WebAppLication extends SAppLication {

    @Override
    public void onCreate() {
        super.onCreate();
        setXutilsDebug(true);

//        Bugsee.launch(this, "d58d8ee7-23b7-48a0-b7b3-adb293670acb");
    }
}
