package com.example.balzsmohr.logreg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private EditText Edit_Text_reg_fhnev, Edit_Text_reg_jelszo, Edit_Text_reg_jelszo_megerosites, Edit_Text_reg_telj_nev, Edit_Text_reg_telszam;
    private Button Button_reg_regisztracio;

    private Adatbazis db;

    boolean felvetel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();

        Button_reg_regisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adatFelvetel();
                if (felvetel)
                {
                    Intent bejelentkezes = new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(bejelentkezes);
                    finish();
                }


            }
        });

    }

    public void init()
    {
        Edit_Text_reg_fhnev = (EditText) findViewById(R.id.Edit_Text_reg_fhnev);
        Edit_Text_reg_jelszo = (EditText) findViewById(R.id.Edit_Text_reg_jelszo);
        Edit_Text_reg_jelszo_megerosites = (EditText) findViewById(R.id.Edit_Text_reg_jelszo_megerosites);
        Edit_Text_reg_telj_nev = (EditText) findViewById(R.id.Edit_Text_reg_telj_nev);
        Edit_Text_reg_telszam = (EditText) findViewById(R.id.Edit_Text_reg_telszam);
        Button_reg_regisztracio = (Button) findViewById(R.id.Button_reg_regisztracio);

        db = new Adatbazis(this);
    }

    public void adatFelvetel()
    {

        String felhasznalonev = Edit_Text_reg_fhnev.getText().toString();
        String jelszo = Edit_Text_reg_jelszo.getText().toString();
        String jelszomegerosites = Edit_Text_reg_jelszo_megerosites.getText().toString();
        String teljesnev = Edit_Text_reg_telj_nev.getText().toString();
        String telefonszam = Edit_Text_reg_telszam.getText().toString();

        if (felhasznalonev.equals("")  || jelszo.equals("") || jelszomegerosites.equals("") || teljesnev.equals("") || telefonszam.equals(""))
        {
            Toast.makeText(this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (jelszo.equals(jelszomegerosites))
            {
                boolean eredmeny = db.adatFelvetel(felhasznalonev, jelszo, teljesnev, telefonszam);
                felvetel = true;
                if (eredmeny){
                    Toast.makeText(this, "Adatrögzítés sikeres", Toast.LENGTH_SHORT).show();
                }
else
                {
                    Toast.makeText(this, "FElvétel nem sikerült", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "A jelszóknak egyezniük kell!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
