package com.example.geometric;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    /*実機へのインストール方法
    * スマホの設定から開発者向けオプションを選択
    * USBデバックを選択
    * androidStudioから実行する
    * */


    TextView geometricSizing;
    double valueOfGeometricSizing;
    TextView mdf;
    double valueOfMdf;
    TextView finalCallingRange;
    double valueOfFinalCallingRange;

    EditText pot;
    double valueOfPot;
    EditText effectiveStack;
    double valueOfES;
    EditText nob;
    double valueOfnob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pot = findViewById(R.id.editPot);
        effectiveStack = findViewById(R.id.editES);
        nob = findViewById(R.id.editNoB);


    }

    public void setValueOfPot(){
        //入力された文字列（数字で）をdouble型に変換し、計算に使えるようにする
        valueOfPot = Double.parseDouble(pot.getText().toString());
    }

    public void setValueOfES(){
        valueOfES = Double.parseDouble(effectiveStack.getText().toString());
    }

    public void setValueOfnob(){
        valueOfnob = Double.parseDouble(nob.getText().toString());
    }



    public void setAll(){
        setValueOfPot();
        setValueOfES();
        setValueOfnob();
    }

    public void calc(){
        //ジオメトリックサイズの計算
        double term1;
        double term2;
        double term3;

        term1 = (valueOfPot + 2 * valueOfES) / valueOfPot;
        term2 = 1 / valueOfnob;
        term3 = Math.pow(term1, term2);

        valueOfGeometricSizing = 0.5 * ( term3 - 1);

        //mdfの計算
        valueOfMdf = 1 / (valueOfGeometricSizing + 1);

        //最終コールレンジ割合の計算
        valueOfFinalCallingRange = Math.pow(valueOfMdf,valueOfnob);

        //最後に全ての数値を100倍にして％表記に合わせたのち、四捨五入する

        valueOfGeometricSizing *= 100;
        valueOfGeometricSizing = Math.round(valueOfGeometricSizing*10)/10.0;

        valueOfMdf *= 100;
        valueOfMdf = Math.round(valueOfMdf*10)/10.0;

        valueOfFinalCallingRange *= 100;//最終コールレンジのみ先に
        valueOfFinalCallingRange = Math.round(valueOfFinalCallingRange*10)/10.0;

        setResult();
    }

    public void setResult(){

        geometricSizing = findViewById(R.id.geometricSizing);
        geometricSizing.setText( Double.toString(valueOfGeometricSizing) + "%");

        mdf = findViewById(R.id.mdf);
        mdf.setText( Double.toString(valueOfMdf) + "%");

        finalCallingRange = findViewById(R.id.finalCallingRange);
        finalCallingRange.setText(Double.toString(valueOfFinalCallingRange) + "%");

    }

    public void tapAC(View view){
        geometricSizing.setText(null);
        mdf.setText(null);
        finalCallingRange.setText(null);
        pot.setText(null);
        effectiveStack.setText(null);
        nob.setText(null);
    }

    public void tapOK(View view) {
        try{
            setAll();
            calc();
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "値を全て入力してください", Toast.LENGTH_LONG).show();
        }
    }


    }
