package dnd.tools.dnddmtools;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Models.Ability;
import Models.Campaign;
import Models.CampaignPlayer;
import Models.Skill;

import static Models.Ability.Charisma;
import static Models.Ability.Dexterity;
import static Models.Ability.Intelligence;
import static Models.Ability.Strength;
import static Models.Ability.Wisdom;

/**
 * Created by maxhe on 22-3-2018.
 */

public class PlayerActivity extends AppCompatActivity {

    private CampaignPlayer player;
    private Campaign campaign;
    private List<Skill> skills;

    private TextView txtPlayerName;
    private Spinner spinnerAbility;
    private Button btnSave;
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

        txtPlayerName = (TextView)findViewById(R.id.txtPlayername);

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

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                for(Skill skill : skills){
                    if(skill.getName().equals(txt1.getText())){
                        try {
                            skill.setValue(Integer.valueOf(editText1.getText().toString()));
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                for(Skill skill :skills){
                    if(skill.getName().equals(Integer.valueOf(editText2.getText().toString())));
                }
            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                for(Skill skill : skills){
                    if(skill.getName().equals(Integer.valueOf(editText3.getText().toString())));
                }
            }
        });
        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                for(Skill skill : skills){
                    if(skill.getName().equals(txt4.getText())){
                        skill.setValue(Integer.valueOf(editText4.getText().toString()));
                    }
                }
            }
        });
        editText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                for(Skill skill : skills){
                    if(skill.getName().equals(editText5.getText())){
                        skill.setValue(Integer.valueOf(txt5.getText().toString()));
                    }
                }
            }
        });

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePlayer();
            }
        });

        Intent intent = getIntent();
        player = intent.getParcelableExtra(HomeActivity.CAMPAIGNPLAYER);
        campaign = intent.getParcelableExtra(HomeActivity.CAMPAIGN);
        skills = (List<Skill>) intent.getSerializableExtra(HomeActivity.SKILLSLIST);
        player.setSkillList(skills);

        txtPlayerName.setText(player.getName());
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

    private void changeLayout(Ability ability){
        txt1.setVisibility(View.VISIBLE);
        txt2.setVisibility(View.VISIBLE);
        txt3.setVisibility(View.VISIBLE);
        txt4.setVisibility(View.VISIBLE);
        txt5.setVisibility(View.VISIBLE);

        editText2.setVisibility(View.VISIBLE);
        editText3.setVisibility(View.VISIBLE);
        editText4.setVisibility(View.VISIBLE);
        editText5.setVisibility(View.VISIBLE);


        switch (ability){
            case Strength : layoutAbilityStrength(); break;
            case Dexterity : layoutAbilityDexterity(); break;
            case Intelligence : layoutAbilityIntelligence(); break;
            case Wisdom : layoutAbilityWisdom(); break;
            case Charisma : layoutAbilityCharisma(); break;
        }
    }

    private void layoutAbilityStrength(){
        txt1.setText("Athletics");

        try {
            for(Skill s : player.getSkillList()){
                if(s.getAbility().toString().equals("Strength")){
                    if(s.getName().equals("Athletics")){
                        editText1.setText(Integer.toString(s.getValue()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        txt2.setVisibility(View.GONE);
        txt3.setVisibility(View.GONE);
        txt4.setVisibility(View.GONE);
        txt5.setVisibility(View.GONE);

        editText2.setVisibility(View.GONE);
        editText3.setVisibility(View.GONE);
        editText4.setVisibility(View.GONE);
        editText5.setVisibility(View.GONE);
    }

    private void layoutAbilityDexterity(){
        try {
            for(Skill s : player.getSkillList()){
                if(s.getAbility().toString().equals("Dexterity")){
                    if(s.getName().equals("Acrobatics")){
                        txt1.setText(s.getName());
                        editText1.setText(Integer.toString(s.getValue()));
                    }

                    if(s.getName().equals("Sleight of Hand")){
                        txt2.setText(s.getName());
                        editText2.setText(Integer.toString(s.getValue()));
                    }

                    if(s.getName().equals("Stealth")){
                        txt3.setText(s.getName());
                        editText3.setText(Integer.toString(s.getValue()));
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        txt4.setVisibility(View.GONE);
        txt5.setVisibility(View.GONE);

        editText4.setVisibility(View.GONE);
        editText5.setVisibility(View.GONE);
    }

    private void layoutAbilityIntelligence(){
        for(Skill s : player.getSkillList()){
            if(s.getAbility().toString().equals("Intelligence")){
                if(s.getName().equals("Arcana")){
                    txt1.setText(s.getName());
                    editText1.setText(Integer.toString(s.getValue()));
                }

                if(s.getName().equals("History")){
                    txt2.setText(s.getName());
                    editText2.setText(Integer.toString(s.getValue()));
                }

                if(s.getName().equals("Investigation")){
                    txt3.setText(s.getName());
                    editText3.setText(Integer.toString(s.getValue()));
                }

                if(s.getName().equals("Nature")){
                    txt4.setText(s.getName());
                    editText4.setText(Integer.toString(s.getValue()));
                }

                if(s.getName().equals("Religion")){
                    txt5.setText(s.getName());
                    editText5.setText(Integer.toString(s.getValue()));
                }
            }
        }

    }

    private void layoutAbilityWisdom(){
        for(Skill s : player.getSkillList()){
            if(s.getAbility().toString().equals("Wisdom")){
                if(s.getName().equals("Animal Handling")){
                    txt1.setText(s.getName());
                    editText1.setText(Integer.toString(s.getValue()));
                }

                if(s.getName().equals("Insight")){
                    txt2.setText(s.getName());
                    editText2.setText(Integer.toString(s.getValue()));
                }

                if(s.getName().equals("Medicine")){
                    txt3.setText(s.getName());
                    editText3.setText(Integer.toString(s.getValue()));
                }

                if(s.getName().equals("Perception")){
                    txt4.setText(s.getName());
                    editText4.setText(Integer.toString(s.getValue()));
                }

                if(s.getName().equals("Survival")){
                    txt5.setText(s.getName());
                    editText5.setText(Integer.toString(s.getValue()));
                }
            }
        }
    }

    private void layoutAbilityCharisma(){
        for(Skill s : player.getSkillList()){
            if(s.getAbility().equals("Charisma")){
                if(s.getName().equals("Deception")){
                    txt1.setText(s.getName());
                    editText1.setText(Integer.toString(s.getValue()));
                }

                if(s.getName().equals("Intimidation")){
                    txt2.setText(s.getName());
                    editText2.setText(Integer.toString(s.getValue()));
                }

                if(s.getName().equals("Performance")){
                    txt3.setText(s.getName());
                    editText3.setText(Integer.toString(s.getValue()));
                }

                if(s.getName().equals("Persuasion")){
                    txt4.setText(s.getName());
                    editText4.setText(Integer.toString(s.getValue()));
                }
            }
        }

        editText5.setVisibility(View.GONE);
        txt5.setVisibility(View.GONE);
    }

    private void savePlayer(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("Campaign").child(campaign.getId()).child("players").child(player.getId()).child("skillList");
        reference.removeValue();
        reference.setValue(skills);
    }

}
