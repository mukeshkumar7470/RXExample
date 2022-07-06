package com.mukeshkpdeveloper.rxexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String greeting = "Hello RX Java";
    private Observable<String> myObservable;
    private Observer<String> myObserver;
    private TextView tvGreeting;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initRXJava();
    }

    private void initViews() {
        tvGreeting = findViewById(R.id.tv_greeting);
    }

    private void initRXJava() {
        myObservable = Observable.just(greeting);
        myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "onSubscribe: invoked");
                disposable = d;
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "onNext: invoked");
                tvGreeting.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "onError: invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: invoked");
            }
        };
        myObservable.subscribe(myObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}