package com.notificationbox.application;

import android.app.Application;

import com.notificationbox.application.db.CancelListDBHelper;

/**
 * Created by sunxinyang on 2016/6/16.
 *
 */
public class NBoxApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(BaseContact.cancellist == null){
            CancelListDBHelper cancelListDBHelper = new CancelListDBHelper(getApplicationContext());
            BaseContact.cancellist = cancelListDBHelper.queryCancelListDB();
        }
    }
}
