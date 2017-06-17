package com.hfad.mextrav2.homescreen;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.provider.ContactsContract;

/**
 * Created by energywin4 on 15/6/2017.
 */

public class HomeScreenFragment extends Fragment implements HomeScreenContract.View{

    private HomeScreenContract.Presenter mPresenter;

    public HomeScreenFragment(){

        //Empty constructor for the fragment

    }

    @Override
    public void setPresenter(HomeScreenContract.Presenter presenter) {

        mPresenter = presenter;


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showNotifications() {

    }

    @Override
    public void showImage() {

    }

    @Override
    public void showStatus() {

    }

    @Override
    public void showLastStatusTimeStamp() {

    }

    @Override
    public void getTitle() {

    }

    @Override
    public void isSilent() {

    }


}

