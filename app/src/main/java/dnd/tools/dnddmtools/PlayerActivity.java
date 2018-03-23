package dnd.tools.dnddmtools;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Type;

import Models.Ability;
import Models.CampaignPlayer;
import Models.Skill;

/**
 * Created by maxhe on 22-3-2018.
 */

public class PlayerActivity extends AppCompatActivity {

    private CampaignPlayer player;

    private TextView txtPlayerName;
    private Spinner spinnerAbility;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    private TextView txt5;

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_player);

        Intent intent = getIntent();
        player = intent.getParcelableExtra(HomeActivity.CAMPAIGNPLAYER);

        txtPlayerName = (TextView)findViewById(R.id.txtPlayername);
        txtPlayerName.setText(player.getName());

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);

        txt1 = (TextView)findViewById(R.id.textView1);
        txt2 = (TextView)findViewById(R.id.textView2);
        txt3 = (TextView)findViewById(R.id.textView3);
        txt4 = (TextView)findViewById(R.id.textView4);
        txt5 = (TextView)findViewById(R.id.textView5);

        spinnerAbility = (Spinner) findViewById(R.id.spinnerAbility);

        ArrayAdapter<Ability> adapter = new ArrayAdapter<Ability>(this,android.R.layout.simple_list_item_1,Ability.values());
        adapter.notifyDataSetChanged();
        spinnerAbility.setAdapter(adapter);

        spinnerAbility.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeLayout(Ability.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void changeLayout(Enum ability){
        switch (ability.name()){
            case "Strength" : layoutAbilityStrength(); break;
            case "Dexterity" : layoutAbilityDexterity(); break;
            case "Intelligence" : layoutAbilityIntelligence(); break;
            case "Wisdom" : layoutAbilityWisdom(); break;
            case "Charisma" : layoutAbilityCharisma(); break;
        }
    }

    private void layoutAbilityStrength(){
        txt1.setText("Athleticts");

        for(Skill s : player.getSkillList()){
            if(s.getName().equals("Strength")){
                editText1.setText(s.getValue());
            }
        }
    }

    private void layoutAbilityDexterity(){

    }

    private void layoutAbilityIntelligence(){

    }

    private void layoutAbilityWisdom(){

    }

    private void layoutAbilityCharisma(){

    }
}
