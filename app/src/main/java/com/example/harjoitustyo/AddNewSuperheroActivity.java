package com.example.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

//This class is used to add new superheroes.
public class AddNewSuperheroActivity extends AppCompatActivity {
    private EditText name;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_superhero);

        name = findViewById(R.id.txtSuperheroName);
        context = this;
    }

    //This method is used to create new superheroes.
    //There are five different types of superheroes, each one with their own picture.
    //The superheroes are also saved to a file so that they can be retrieved on restart.
    public void addSuperhero(View view){
        RadioGroup rgSuperheroType = findViewById(R.id.rgSuperheroType);
        int picture = 0;
        switch (rgSuperheroType.getCheckedRadioButtonId()){
            case R.id.rbCatWoman:
                picture=R.drawable.catwoman;
                Storage.getInstance().addSuperhero(new CatWoman(name.getText().toString(), picture));
                break;
            case R.id.rbHulk:
                picture=R.drawable.hulk;
                Storage.getInstance().addSuperhero(new Hulk(name.getText().toString(), picture));
                break;
            case R.id.rbSpiderman:
                picture=R.drawable.spiderman;
                Storage.getInstance().addSuperhero(new Spiderman(name.getText().toString(), picture));
                break;
            case R.id.rbBatman:
                picture=R.drawable.batman;
                Storage.getInstance().addSuperhero(new Batman(name.getText().toString(), picture));
                break;
            case R.id.rbWonderWoman:
                picture=R.drawable.wonderwoman;
                Storage.getInstance().addSuperhero(new WonderWoman(name.getText().toString(), picture));
                break;
        }
    }

}