package com.setico.builditbigger;

import android.app.Activity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by setico on 19/02/2016.
 */
public class Admob{

    private Activity mRoot;
    private static InterstitialAd mInterstitial;
    private static final String DEVICE_HASH= AdRequest.DEVICE_ID_EMULATOR;


    public Admob(Activity activity) {
        mRoot=activity;
        mInterstitial = new InterstitialAd(activity);
        mInterstitial.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });
        requestNewInterstitial();

    }




    public void showAd(){

                    if (mInterstitial.isLoaded()) {
                        mInterstitial.show();
                    }
        }

        private void requestNewInterstitial() {
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(DEVICE_HASH)
                    .build();

            mInterstitial.loadAd(adRequest);
        }


    }


