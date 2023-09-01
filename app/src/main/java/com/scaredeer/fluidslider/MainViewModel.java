package com.scaredeer.fluidslider;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

public class MainViewModel extends ViewModel {

    private static final List<String> ALPHABET = Arrays.asList(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    );
    private static final List<String> CLOCK = Arrays.asList(
            "00:00", "01:00", "02:00", "03:00", "04:00", "05:00",
            "06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
            "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
            "18:00", "19:00", "20:00", "21:00", "22:00", "23:00",
            "24:00"
    );
    private static final List<String> NUMBER = Arrays.asList(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    );

    private final MutableLiveData<List<String>> mList;
    
    private final MutableLiveData<String> mStart;
    public LiveData<String> getStart() {
        return mStart;
    }
    private final MutableLiveData<String> mEnd;
    public LiveData<String> getEnd() {
        return mEnd;
    }

    private final MutableLiveData<Float> mPosition;
    public void setPosition(float position) {
        mPosition.setValue(position);
    }

    private final MediatorLiveData<String> mCurrent;
    public LiveData<String> getCurrent() {
        return mCurrent;
    }

    private int count;

    private void changeList() {
        switch (count % 3) {
            case 1:
                mList.setValue(ALPHABET);
                break;
            case 2:
                mList.setValue(CLOCK);
                break;
            default:
                mList.setValue(NUMBER);
        }
        ++count;

        List<String> list = mList.getValue();
        mStart.setValue(list.get(0));
        mEnd.setValue(list.get(list.size() - 1));
    }

    public void onClick(View view) {
        changeList();
    }

    public MainViewModel() {
        mList = new MutableLiveData<>(NUMBER);
        mStart = new MutableLiveData<>();
        mEnd = new MutableLiveData<>();
        mPosition = new MutableLiveData<>(0.5f);
        count = 0;
        changeList();

        mCurrent = new MediatorLiveData<>();
        mCurrent.addSource(mList, list -> {
            mCurrent.setValue(list.get(Math.round(mPosition.getValue() * (list.size() - 1))));
        });
        mCurrent.addSource(mPosition, position -> {
            mCurrent.setValue(mList.getValue().get(Math.round(position * (mList.getValue().size() - 1))));
        });
    }
}