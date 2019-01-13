package com.example.balzsmohr.logreg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Edit_Text_fhnev, Edit_Text_jelszo;
    private Button Button_belepes, Button_regisztracio;

    private Adatbazis db;

    String kulsofelhasznalonev = "";
    String kulsojelszo = "";
    String belsofhnev = "";
    String belsojelszo = "";

    boolean belepes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Button_belepes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginLekeres();


                final SharedPreferences sh = getSharedPreferences("adatok", Context.MODE_PRIVATE);
                String adat = belsofhnev;
                SharedPreferences.Editor editor = sh.edit();
                editor.putString("adat", adat);
                editor.apply();

                if (belepes)
                {
                    Intent belepes = new Intent(MainActivity.this, Main3Activity.class);
                    startActivity(belepes);
                    finish();

                }

            }
        });

        Button_regisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent regisztracio = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(regisztracio);
                finish();

            }
        });

    }

    public void init()
    {
        Edit_Text_fhnev = (EditText) findViewById(R.id.Edit_Text_fhnev);
        Edit_Text_jelszo = (EditText) findViewById(R.id.Edit_Text_jelszo);
        Button_belepes = (Button) findViewById(R.id.Button_belepes);
        Button_regisztracio = (Button) findViewById(R.id.Button_regisztracio);

        db = new Adatbazis(this);

    }


    public void loginLekeres()
    {
        kulsofelhasznalonev = Edit_Text_fhnev.getText().toString();
        kulsojelszo = Edit_Text_jelszo.getText().toString();


        if (kulsofelhasznalonev.equals("") || kulsojelszo.equals(""))
        {
            Toast.makeText(this, "Minden sort ki kell tölteni!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Cursor eredmeny = db.loginLekeres(Edit_Text_fhnev.getText().toString());

            if (eredmeny != null && eredmeny.getCount() > 0)
            {

                while (eredmeny.moveToNext())
                {
                    belsofhnev = eredmeny.getString(0);
                    belsojelszo = eredmeny.getString(1);
                }

                if (kulsofelhasznalonev.equals(belsofhnev) && kulsojelszo.equals(belsojelszo))
                {
                    belepes = true;
                    Toast.makeText(this, "Adat sikeresen lekérve", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Hibás felhasználónév vagy jelszó!", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Nincs ilyen felhasználó!", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
