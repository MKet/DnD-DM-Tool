package dnd.tools.dnddmtools;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Locale;

import Models.Ability;
import Models.AbilityValueWrapper;
import Models.Campaign;
import Models.CampaignPlayer;
import Models.Skill;

/**
 * Created by maxhe on 22-3-2018.
 */

public class PlayerActivity extends AppCompatActivity {

    private CampaignPlayer player;
    private Campaign campaign;
    private List<Skill> skills;
    private int position;

    private TextView txtPlayerName;
    private Spinner spinnerAbility;
    private Button btnSave;

    private EditText abilityScore;

    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    private TextView txt5;

    private TextView skill1;
    private TextView skill2;
    private TextView skill3;
    private TextView skill4;
    private TextView skill5;

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_player);

        txtPlayerName = findViewById(R.id.txtPlayername);
        abilityScore = findViewById(R.id.AbilityScore);

        skill1 = findViewById(R.id.txtSkill1);
        skill2 = findViewById(R.id.txtSkill2);
        skill3 = findViewById(R.id.txtSkill3);
        skill4 = findViewById(R.id.txtSkill4);
        skill5 = findViewById(R.id.txtSkill5);

        txt1 = findViewById(R.id.textView1);
        txt2 = findViewById(R.id.textView2);
        txt3 = findViewById(R.id.textView3);
        txt4 = findViewById(R.id.textView4);
        txt5 = findViewById(R.id.textView5);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> savePlayer());

        Intent intent = getIntent();
        player = intent.getParcelableExtra(HomeActivity.CAMPAIGNPLAYER);
        campaign = intent.getParcelableExtra(HomeActivity.CAMPAIGN);
        skills = (List<Skill>) intent.getSerializableExtra(HomeActivity.SKILLSLIST);
        position = intent.getIntExtra(HomeActivity.POSITION,-1);
        player.setSkillList(skills);

        txtPlayerName.setText(player.getName());
        spinnerAbility = findViewById(R.id.spinnerAbility);

        ArrayAdapter<Ability> adapter = new ArrayAdapter<Ability>(this,android.R.layout.simple_list_item_1,Ability.values());
        adapter.notifyDataSetChanged();
        spinnerAbility.setAdapter(adapter);



        checkBox1.setOnCheckedChangeListener((cb, b) -> {
            for (Skill skill : skills)
                if (skill.getName().equals(txt1.getText())) {
                    skill.setProficiency(b);
                }
        });
        checkBox2.setOnCheckedChangeListener((cb, b) -> {
            for (Skill skill : skills)
                if (skill.getName().equals(txt2.getText())) {
                    skill.setProficiency(b);
                }
        });
        checkBox3.setOnCheckedChangeListener((cb, b) -> {
            for (Skill skill : skills)
                if (skill.getName().equals(txt3.getText())) {
                    skill.setProficiency(b);
                }
        });
        checkBox4.setOnCheckedChangeListener((cb, b) -> {
            for (Skill skill : skills)
                if (skill.getName().equals(txt4.getText())) {
                    skill.setProficiency(b);
                }
        });
        checkBox5.setOnCheckedChangeListener((cb, b) -> {
            for (Skill skill : skills)
                if (skill.getName().equals(txt4.getText())) {
                    skill.setProficiency(b);
                }
        });

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

        skill1.setVisibility(View.VISIBLE);
        skill2.setVisibility(View.VISIBLE);
        skill3.setVisibility(View.VISIBLE);
        skill4.setVisibility(View.VISIBLE);
        skill5.setVisibility(View.VISIBLE);

        checkBox2.setVisibility(View.VISIBLE);
        checkBox3.setVisibility(View.VISIBLE);
        checkBox4.setVisibility(View.VISIBLE);
        checkBox5.setVisibility(View.VISIBLE);

        switch (ability){
            case Strength : layoutAbilityStrength(); break;
            case Dexterity : layoutAbilityDexterity(); break;
            case Intelligence : layoutAbilityIntelligence(); break;
            case Wisdom : layoutAbilityWisdom(); break;
            case Charisma : layoutAbilityCharisma(); break;
        }
    }

    private int getAbilityScore(Ability ability) {
        for (AbilityValueWrapper abilityValueWrapper : player.getWrappers()) {
            if (abilityValueWrapper.getAbility().equals(ability))
                return abilityValueWrapper.getValue();
        }
        throw new IllegalArgumentException("Ability does not exist");
    }

    @SuppressLint("SetTextI18n")
    private void layoutAbilityStrength(){
        abilityScore.setText(Integer.toString(getAbilityScore(Ability.Strength)));
        txt1.setText("Athletics");

        try {
            for(Skill s : player.getSkillList()){
                if(s.getAbility().toString().equals("Strength")){
                    if(s.getName().equals("Athletics")){
                        skill1.setText(Integer.toString(s.getValue()));
                        checkBox1.setChecked(s.hasProficiency());
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

        skill2.setVisibility(View.GONE);
        skill3.setVisibility(View.GONE);
        skill4.setVisibility(View.GONE);
        skill5.setVisibility(View.GONE);

        checkBox2.setVisibility(View.GONE);
        checkBox3.setVisibility(View.GONE);
        checkBox4.setVisibility(View.GONE);
        checkBox5.setVisibility(View.GONE);
    }

    @SuppressLint("SetTextI18n")
    private void layoutAbilityDexterity(){
        abilityScore.setText(Integer.toString(getAbilityScore(Ability.Dexterity)));
        try {
            for(Skill s : player.getSkillList()){
                if(s.getAbility().toString().equals("Dexterity")){
                    if(s.getName().equals("Acrobatics")){
                        txt1.setText(s.getName());
                        skill1.setText(Integer.toString(s.getValue()));
                        checkBox1.setChecked(s.hasProficiency());
                    }

                    if(s.getName().equals("Sleight of Hand")){
                        txt2.setText(s.getName());
                        skill2.setText(Integer.toString(s.getValue()));
                        checkBox2.setChecked(s.hasProficiency());
                    }

                    if(s.getName().equals("Stealth")){
                        txt3.setText(s.getName());
                        skill3.setText(Integer.toString(s.getValue()));
                        checkBox3.setChecked(s.hasProficiency());
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        txt4.setVisibility(View.GONE);
        txt5.setVisibility(View.GONE);

        skill4.setVisibility(View.GONE);
        skill5.setVisibility(View.GONE);

        checkBox4.setVisibility(View.GONE);
        checkBox5.setVisibility(View.GONE);
    }

    @SuppressLint("SetTextI18n")
    private void layoutAbilityIntelligence(){
        abilityScore.setText(Integer.toString(getAbilityScore(Ability.Intelligence)));
        for(Skill s : player.getSkillList()){
            if(s.getAbility().toString().equals("Intelligence")){
                if(s.getName().equals("Arcana")){
                    txt1.setText(s.getName());
                    skill1.setText(Integer.toString(s.getValue()));
                    checkBox1.setChecked(s.hasProficiency());
                }else if(s.getName().equals("History")){
                    txt2.setText(s.getName());
                    skill2.setText(Integer.toString(s.getValue()));
                    checkBox2.setChecked(s.hasProficiency());
                }else if(s.getName().equals("Investigation")){
                    txt3.setText(s.getName());
                    skill3.setText(Integer.toString(s.getValue()));
                    checkBox3.setChecked(s.hasProficiency());
                }else if(s.getName().equals("Nature")){
                    txt4.setText(s.getName());
                    skill4.setText(Integer.toString(s.getValue()));
                    checkBox4.setChecked(s.hasProficiency());
                }else if(s.getName().equals("Religion")){
                    txt5.setText(s.getName());
                    skill5.setText(Integer.toString(s.getValue()));
                    checkBox5.setChecked(s.hasProficiency());
                }
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private void layoutAbilityWisdom(){
        abilityScore.setText(Integer.toString(getAbilityScore(Ability.Wisdom)));
        for(Skill s : player.getSkillList()){
            if(s.getAbility().toString().equals("Wisdom")){
                if(s.getName().equals("Animal Handling")){
                    txt1.setText(s.getName());
                    skill1.setText(Integer.toString(s.getValue()));
                    checkBox1.setChecked(s.hasProficiency());
                }else if(s.getName().equals("Insight")){
                    txt2.setText(s.getName());
                    skill2.setText(Integer.toString(s.getValue()));
                    checkBox2.setChecked(s.hasProficiency());
                }else if(s.getName().equals("Medicine")){
                    txt3.setText(s.getName());
                    skill3.setText(Integer.toString(s.getValue()));
                    checkBox3.setChecked(s.hasProficiency());
                }else  if(s.getName().equals("Perception")){
                    txt4.setText(s.getName());
                    skill4.setText(Integer.toString(s.getValue()));
                    checkBox4.setChecked(s.hasProficiency());
                }else if(s.getName().equals("Survival")){
                    txt5.setText(s.getName());
                    skill5.setText(Integer.toString(s.getValue()));
                    checkBox5.setChecked(s.hasProficiency());
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void layoutAbilityCharisma(){
        abilityScore.setText(Integer.toString(getAbilityScore(Ability.Charisma)));
        for(Skill s : player.getSkillList()){
            if(s.getAbility().toString().equals("Charisma")){
                if(s.getName().equals("Deception")){
                    txt1.setText(s.getName());
                    skill1.setText(Integer.toString(s.getValue()));

                }else if(s.getName().equals("Intimidation")){
                    txt2.setText(s.getName());
                    skill2.setText(Integer.toString(s.getValue()));
                    checkBox2.setChecked(s.hasProficiency());
                }else if(s.getName().equals("Performance")){
                    txt3.setText(s.getName());
                    skill3.setText(Integer.toString(s.getValue()));
                    checkBox3.setChecked(s.hasProficiency());
                }else if(s.getName().equals("Persuasion")){
                    txt4.setText(s.getName());
                    skill4.setText(Integer.toString(s.getValue()));
                    checkBox4.setChecked(s.hasProficiency());
                }
            }
        }

        checkBox5.setVisibility(View.GONE);
        skill5.setVisibility(View.GONE);
        txt5.setVisibility(View.GONE);
    }

    private void savePlayer(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        System.out.println(player.getId());
        DatabaseReference reference = firebaseDatabase.getReference("Campaign").child(campaign.getId()).child("players").child(String.valueOf(position));
        reference.removeValue();
        reference.setValue(player);

        for(Skill s : player.getSkillList()){
            System.out.println(s);
        }

    }

}
