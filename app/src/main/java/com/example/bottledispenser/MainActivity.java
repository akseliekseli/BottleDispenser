package com.example.bottledispenser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Context context = null;
    private TextView moneyView;
    private SeekBar seekBar;
    private TextView bottleView;
    private TextView boughtView;
    private TextView returnView;
    private Spinner spinner;

    BottleDispenser bd=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bd = BottleDispenser.getInstance();
        context = MainActivity.this;

        moneyView = (TextView) findViewById(R.id.moneyView);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        bottleView = (TextView) findViewById(R.id.bottleView);
        boughtView = (TextView) findViewById(R.id.boughtView);
        returnView = (TextView) findViewById(R.id.returnView);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, bd.getBottles());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                moneyView.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void buyClick(View v){
        int btl_index = spinner.getSelectedItemPosition();
        String print_text = bd.buyBottle(btl_index);
        boughtView.setText(print_text);
    }
    public void addMoneyClick(View v){
        int more_money = seekBar.getProgress();
        bd.addMoney(more_money);
    }
    public void returnMoneyClick(View v){

        double change = bd.returnMoney();
        returnView.setText("Your change: "+Double.toString(change)+"€");
    }
    public void printClick(View v){
        String printline = bd.printList();
        bottleView.setText(printline);
    }

    public void writeFile(View v) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("kuitti.txt", Context.MODE_PRIVATE));

            osw.write(bd.getReceipt());
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("KIRJOITETTU");
        }
    }
}