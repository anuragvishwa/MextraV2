package com.hfad.mextrav2.homescreen;

import android.support.v4.app.Fragment;

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

