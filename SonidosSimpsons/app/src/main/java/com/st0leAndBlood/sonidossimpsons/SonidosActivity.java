package com.st0leAndBlood.sonidossimpsons;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SonidosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonidos);
        final Button button = (Button) findViewById(R.id.primerLboton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ("Boton Nuevecico" == button.getText()){
                    button.setText("Rabote");
                }else{
                    button.setText("Boton Nuevecico");
                }
            }
        });
    }
}
