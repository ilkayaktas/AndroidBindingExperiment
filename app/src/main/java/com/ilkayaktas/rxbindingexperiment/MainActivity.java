package com.ilkayaktas.rxbindingexperiment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button) Button button;
    @BindView(R.id.editText) EditText editText;
    @BindView(R.id.textView) EditText textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        RxView.clicks(button)
                //.debounce(100, TimeUnit.MILLISECONDS) // this line prevents multi squent,al button clicked event
                .subscribe(aVoid -> Toast.makeText(this, "Clicked! " + new Random().nextInt(), Toast.LENGTH_SHORT).show(),
                        System.err::println);

        RxTextView.textChanges(editText)
                .map(charSequence -> charSequence + "_")
                .subscribe(charSequence -> textView.setText(charSequence),
                        throwable -> System.err.println(throwable.getMessage()));

    }
}
