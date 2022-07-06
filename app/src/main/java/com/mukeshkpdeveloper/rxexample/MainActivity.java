package com.mukeshkpdeveloper.rxexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import io.reactivex.rxjava3.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String greeting = "Hello RX Java";
    private Observable<String> myObservable;
    private DisposableObserver<String> myObserver;
    private TextView tvGreeting;

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

        myObserver = new DisposableObserver<String>() {
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
        myObserver.dispose();
    }
}