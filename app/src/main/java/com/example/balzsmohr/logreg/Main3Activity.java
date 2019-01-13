package com.example.balzsmohr.logreg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private  TextView Text_View_udvozles;
    private Button Button_kijelentkezes;

    String adat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        init();
        final SharedPreferences sh = getSharedPreferences("adatok", Context.MODE_PRIVATE);
        adat = sh.getString("adat", null);
        Text_View_udvozles.setText("Üdvözöllek " + adat);

        Button_kijelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent kilepes = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(kilepes);
                finish();

            }
        });

    }

    public void init()
    {
        Text_View_udvozles = (TextView) findViewById(R.id.Text_View_udvozles);
        Button_kijelentkezes = (Button) findViewById(R.id.Button_kijelentkezes);
    }

}
