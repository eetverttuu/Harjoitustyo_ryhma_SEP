package com.example.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

//This class controls the battle feature of the game.
public class BattleFieldActivity extends AppCompatActivity {
    private TextView battleText;
    private LinearLayout attacksLayout;
    private ArrayList<String> selectedSuperheroes = new ArrayList<>();
    private int battlerOneId;
    private int battlerTwoId;
    private int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_field);

        battleText = findViewById(R.id.txtBattle);
        attacksLayout = findViewById(R.id.attacksLayout);
        LinearLayout linearLayout = findViewById(R.id.linearLayout);

        //This loop creates checkboxes of all of the existing superheroes.
        for (int i = 0; i < Storage.getInstance().getSize(); i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(Storage.getInstance().getSuperheroById(i).getName() + " (" + Storage.getInstance().getSuperheroById(i).getType() + ")");
            linearLayout.addView(checkBox);
        }

        Button battleButton = findViewById(R.id.btnBattle);
        battleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //This method first empties the TextView if it isn't already.
            //It then starts the battling process.
            public void onClick(View v) {
                if (attacksLayout.getChildCount() > 0) {
                    attacksLayout.removeAllViews();
                }
                selectedSuperheroes.clear();
                LinearLayout layout = findViewById(R.id.linearLayout);
                //This loop adds the selected superheroes to a list.
                for (int i = 0; i < layout.getChildCount(); i++) {
                    View view = layout.getChildAt(i);
                    if (view instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) view;
                        if (checkBox.isChecked()) {
                            int id = checkBox.getId();
                            String name = Storage.getInstance().getSuperheroById(id).getName();
                            selectedSuperheroes.add(name);
                        }
                    }
                }
                //The battle can only be started if exactly two superheroes are selected.
                //Both superheroes need to also have more than 0 health.
                if (selectedSuperheroes.size() == 2) {
                    battleText.setText("Taistelussa: " + selectedSuperheroes.get(0) + " vs " + selectedSuperheroes.get(1));
                    getBattlerIds();
                    Superhero attacker = Storage.getInstance().getSuperheroById(battlerOneId);
                    Superhero defender = Storage.getInstance().getSuperheroById(battlerTwoId);
                    if (attacker.getHealth() <= 0) {
                        battleText.setText(attacker.getName() + " on kuollut! Käy sairaalassa ennen taistelua.");
                    } else if (defender.getHealth() <= 0) {
                        battleText.setText(defender.getName() + " on kuollut! Käy sairaalassa ennen taistelua");
                    } else if ((attacker.getHealth() <= 0) && (defender.getHealth() <= 0)) {
                        battleText.setText("Molemmat taistelijat ovat kuolleet :(, käytä heidät sairaalassa");
                    } else {
                        //This while-loop is the actual battle.
                        //Superheroes (attacker and defender) take turns attacking.
                        //First to reach 0 health loses and the winner gets 1 experience.
                        while ((attacker.getHealth() > 0) && (defender.getHealth() > 0)) {
                            if (j % 2 == 1) {
                                defender.defend(attacker.getAttack());
                                addAttackTextView(defender.getName(), attacker.getName(), defender.getAttack(), 1);
                                addAttackTextView(attacker.getName(), "", 0, 2);
                            } else {
                                attacker.defend(defender.getAttack());
                                addAttackTextView(attacker.getName(), defender.getName(), attacker.getAttack(), 1);
                                addAttackTextView(defender.getName(), "", 0, 2);
                            }
                            if (attacker.getHealth() <= 0) {
                                addAttackTextView(attacker.getName(), defender.getName(), 0, 3);
                                defender.giveExperience();
                                attacker.giveLoss();
                                defender.giveWin();
                            }
                            if (defender.getHealth() <= 0) {
                                addAttackTextView(defender.getName(), attacker.getName(), 0, 3);
                                attacker.giveExperience();
                                defender.giveLoss();
                                attacker.giveWin();
                            }
                            j++;
                        }
                        if (attacker.getHealth() <= 0) {
                            attacker.setHealth(0);
                        }
                        if (defender.getHealth() <= 0) {
                            defender.setHealth(0);
                        }
                    }
                } else {
                    battleText.setText("Valitse tasan kaksi sankaria.");
                }
            }
        });
    }

    //This method is used to get the ids of two selected battlers.
    private void getBattlerIds() {
        battlerOneId = -1;
        battlerTwoId = -1;
        LinearLayout layout = findViewById(R.id.linearLayout);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View view = layout.getChildAt(i);
            if (view instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked()) {
                    if (battlerOneId == -1) {
                        battlerOneId = checkBox.getId();
                    } else {
                        battlerTwoId = checkBox.getId();
                        break;
                    }
                }
            }
        }
    }

    //This method is used to display attacks, defenses and who won.
    //int type is used to control which prompt is displayed in the UI.
    private void addAttackTextView(String attackerName, String defenderName, int damage, int type) {
        TextView attackText = new TextView(this);
        if (type == 1) {
            attackText.setText(attackerName + " attacked " + defenderName + " for " + damage + " damage.");
        } else if (type == 2) {
            attackText.setText(attackerName + " took the damage but survived.");
        } else if (type == 3) {
            attackText.setText(attackerName + " died in battle and " + defenderName + " won!");
        }
        attacksLayout.addView(attackText);
    }
}
