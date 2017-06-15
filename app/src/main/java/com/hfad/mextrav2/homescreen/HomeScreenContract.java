package com.hfad.mextrav2.homescreen;

import com.hfad.mextrav2.util.BasePresenter;
import com.hfad.mextrav2.util.BaseView;

/**
 * Created by energywin4 on 15/6/2017.
 */

public interface HomeScreenContract {

    interface View extends BaseView<Presenter> {

        void showNotifications();

        void showImage();

        void showStatus();

        void showLastStatusTimeStamp();

        void getTitle();

        void isSilent();

    }

    interface Presenter extends BasePresenter {

        void fetchContacts();

        void syncDb();

        boolean isDataMissing();

        void fetchStatus();

        void makeSilent();


    }
}