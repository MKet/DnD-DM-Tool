package dnd.tools.dnddmtools;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Locale;

import Models.Abilities;
import Models.PlayerAbility;
import Models.Campaign;
import Models.CampaignPlayer;
import Models.Skill;
import Models.Skills;

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

    private TextView skill1View;
    private TextView skill2View;
    private TextView skill3View;
    private TextView skill4View;
    private TextView skill5View;

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;

    private Skills skill1;
    private Skills skill2;
    private Skills skill3;
    private Skills skill4;
    private Skills skill5;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_player);

        txtPlayerName = findViewById(R.id.txtPlayername);
        abilityScore = findViewById(R.id.AbilityScore);

        skill1View = findViewById(R.id.txtSkill1);
        skill2View = findViewById(R.id.txtSkill2);
        skill3View = findViewById(R.id.txtSkill3);
        skill4View = findViewById(R.id.txtSkill4);
        skill5View = findViewById(R.id.txtSkill5);

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

        txtPlayerName.setText(player.getName());
        spinnerAbility = findViewById(R.id.spinnerAbility);

        ArrayAdapter<Abilities> adapter = new ArrayAdapter<Abilities>(this,android.R.layout.simple_list_item_1, Abilities.values());
        adapter.notifyDataSetChanged();
        spinnerAbility.setAdapter(adapter);

        checkBox1.setOnCheckedChangeListener((cb, b) -> setProficiency(skill1, b));
        checkBox2.setOnCheckedChangeListener((cb, b) -> setProficiency(skill2, b));
        checkBox3.setOnCheckedChangeListener((cb, b) -> setProficiency(skill3, b));
        checkBox4.setOnCheckedChangeListener((cb, b) -> setProficiency(skill4, b));
        checkBox5.setOnCheckedChangeListener((cb, b) -> setProficiency(skill5, b));

        spinnerAbility.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeLayout(Abilities.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void changeLayout(Abilities abilities){
        txt1.setVisibility(View.VISIBLE);
        txt2.setVisibility(View.VISIBLE);
        txt3.setVisibility(View.VISIBLE);
        txt4.setVisibility(View.VISIBLE);
        txt5.setVisibility(View.VISIBLE);

        skill1View.setVisibility(View.VISIBLE);
        skill2View.setVisibility(View.VISIBLE);
        skill3View.setVisibility(View.VISIBLE);
        skill4View.setVisibility(View.VISIBLE);
        skill5View.setVisibility(View.VISIBLE);

        checkBox2.setVisibility(View.VISIBLE);
        checkBox3.setVisibility(View.VISIBLE);
        checkBox4.setVisibility(View.VISIBLE);
        checkBox5.setVisibility(View.VISIBLE);

        switch (abilities){
            case Strength : layoutAbilityStrength(); break;
            case Dexterity : layoutAbilityDexterity(); break;
            case Intelligence : layoutAbilityIntelligence(); break;
            case Wisdom : layoutAbilityWisdom(); break;
            case Charisma : layoutAbilityCharisma(); break;
        }
    }

    private void setProficiency(Skills skill, boolean proficient) {
        Skill playerSkill = player.getAbilities().get(skill.getAbilities()).getSkills().get(skill);
        playerSkill.setProficienct(proficient);
    }

    private void layoutAbilityStrength() {
        PlayerAbility playerAbility = player.getAbilities().get(Abilities.Strength);
        abilityScore.setText(String.format(Locale.US, "%d" , playerAbility.getValue()));;

        setSkill1View(Skills.Athletics, playerAbility);

        txt2.setVisibility(View.GONE);
        txt3.setVisibility(View.GONE);
        txt4.setVisibility(View.GONE);
        txt5.setVisibility(View.GONE);

        skill2View.setVisibility(View.GONE);
        skill3View.setVisibility(View.GONE);
        skill4View.setVisibility(View.GONE);
        skill5View.setVisibility(View.GONE);

        checkBox2.setVisibility(View.GONE);
        checkBox3.setVisibility(View.GONE);
        checkBox4.setVisibility(View.GONE);
        checkBox5.setVisibility(View.GONE);
    }

    @SuppressLint("SetTextI18n")
    private void layoutAbilityDexterity(){
        PlayerAbility playerAbility = player.getAbilities().get(Abilities.Dexterity);
        abilityScore.setText(Integer.toString(playerAbility.getValue()));

        setSkill1View(Skills.Acrobatics, playerAbility);
        setSkill2View(Skills.SleightOfHand, playerAbility);
        setSkill3View(Skills.Stealth, playerAbility);

        txt4.setVisibility(View.GONE);
        txt5.setVisibility(View.GONE);

        skill4View.setVisibility(View.GONE);
        skill5View.setVisibility(View.GONE);

        checkBox4.setVisibility(View.GONE);
        checkBox5.setVisibility(View.GONE);
    }

    private void layoutAbilityIntelligence(){
        PlayerAbility playerAbility = player.getAbilities().get(Abilities.Intelligence);
        abilityScore.setText(String.format(Locale.US, "%d" , playerAbility.getValue()));

        setSkill1View(Skills.Arcana, playerAbility);
        setSkill2View(Skills.History, playerAbility);
        setSkill3View(Skills.Investigation, playerAbility);
        setSkill4View(Skills.Nature, playerAbility);
        setSkill5View(Skills.Religion, playerAbility);
    }

    private void layoutAbilityWisdom(){
        PlayerAbility playerAbility = player.getAbilities().get(Abilities.Wisdom);
        abilityScore.setText(String.format(Locale.US, "%d" , playerAbility.getValue()));
        setSkill1View(Skills.AnimalHandling, playerAbility);
        setSkill2View(Skills.Insight, playerAbility);
        setSkill3View(Skills.Medicine, playerAbility);
        setSkill4View(Skills.Perception, playerAbility);
        setSkill5View(Skills.Survival, playerAbility);
    }

    private void layoutAbilityCharisma(){
        PlayerAbility playerAbility = player.getAbilities().get(Abilities.Charisma);
        abilityScore.setText(String.format(Locale.US, "%d" , playerAbility.getValue()));

        setSkill1View(Skills.Deception, playerAbility);
        setSkill2View(Skills.Intimidation, playerAbility);
        setSkill3View(Skills.Performance, playerAbility);
        setSkill4View(Skills.Persuasion, playerAbility);

        checkBox5.setVisibility(View.GONE);
        skill5View.setVisibility(View.GONE);
        txt5.setVisibility(View.GONE);
    }

    private void setSkill1View(Skills skills, PlayerAbility playerAbility) {
        Skill skill = playerAbility.getSkills().get(skills);

        txt1.setText(skill.getName().name());
        skill1View.setText(player.calculateSkillValue(skills));
        checkBox1.setChecked(skill.isProficienct());

        skill1 = skills;
    }

    private void setSkill2View(Skills skills, PlayerAbility playerAbility) {
        Skill skill = playerAbility.getSkills().get(skills);

        txt2.setText(skill.getName().name());
        skill2View.setText(player.calculateSkillValue(skills));
        checkBox2.setChecked(skill.isProficienct());

        skill2 = skills;
    }


    private void setSkill3View(Skills skills, PlayerAbility playerAbility) {
        Skill skill = playerAbility.getSkills().get(skills);

        txt3.setText(skill.getName().name());
        skill3View.setText(player.calculateSkillValue(skills));
        checkBox3.setChecked(skill.isProficienct());

        skill3 = skills;
    }

    private void setSkill4View(Skills skills, PlayerAbility playerAbility) {
        Skill skill = playerAbility.getSkills().get(skills);

        txt4.setText(skill.getName().name());
        skill4View.setText(player.calculateSkillValue(skills));
        checkBox4.setChecked(skill.isProficienct());

        skill4 = skills;
    }

    private void setSkill5View(Skills skills, PlayerAbility playerAbility) {
        Skill skill = playerAbility.getSkills().get(skills);

        txt5.setText(skill.getName().name());
        skill5View.setText(player.calculateSkillValue(skills));
        checkBox5.setChecked(skill.isProficienct());

        skill5 = skills;
    }

    private void savePlayer(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        System.out.println(player.getId());
        DatabaseReference reference = firebaseDatabase.getReference("Campaign").child(campaign.getId()).child("players").child(String.valueOf(position));
        reference.removeValue();
        reference.setValue(player);

    }



}
