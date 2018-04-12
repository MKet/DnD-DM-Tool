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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

import Models.Abilities;
import Models.Campaign;
import Models.CampaignPlayer;
import Models.PlayerAbility;
import Models.Skill;
import Models.Skills;

/**
 * Created by maxhe on 22-3-2018.
 */
public class PlayerActivity extends AppCompatActivity {

    private CampaignPlayer player;
    private Campaign campaign;
    private int position;

    private TextView txtPlayerName;
    private Spinner spinnerAbility;
    private Button btnSave;

    private EditText abilityScore;

    private TextView[] txt;
    private TextView[] skillView;
    private CheckBox[] checkBox;
    private Skills[] skill;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_player);

        txtPlayerName = findViewById(R.id.txtPlayername);
        abilityScore = findViewById(R.id.AbilityScore);

        skill = new Skills[5];
        skillView = new TextView[5];
        skillView[0] = findViewById(R.id.txtSkill1);
        skillView[1] = findViewById(R.id.txtSkill2);
        skillView[2] = findViewById(R.id.txtSkill3);
        skillView[3] = findViewById(R.id.txtSkill4);
        skillView[4] = findViewById(R.id.txtSkill5);

        txt = new TextView[5];
        txt[0] = findViewById(R.id.textView1);
        txt[1] = findViewById(R.id.textView2);
        txt[2] = findViewById(R.id.textView3);
        txt[3] = findViewById(R.id.textView4);
        txt[4] = findViewById(R.id.textView5);

        checkBox = new CheckBox[5];
        checkBox[0] = findViewById(R.id.checkBox1);
        checkBox[1] = findViewById(R.id.checkBox2);
        checkBox[2] = findViewById(R.id.checkBox3);
        checkBox[3] = findViewById(R.id.checkBox4);
        checkBox[4] = findViewById(R.id.checkBox5);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> savePlayer());

        Intent intent = getIntent();
        player = intent.getParcelableExtra(HomeActivity.CAMPAIGNPLAYER);
        campaign = intent.getParcelableExtra(HomeActivity.CAMPAIGN);
        position = intent.getIntExtra(HomeActivity.POSITION, -1);

        txtPlayerName.setText(player.getName());
        spinnerAbility = findViewById(R.id.spinnerAbility);

        ArrayAdapter<Abilities> adapter = new ArrayAdapter<Abilities>(this, android.R.layout.simple_list_item_1, Abilities.values());
        adapter.notifyDataSetChanged();
        spinnerAbility.setAdapter(adapter);

        for (int i = 0; i < 5; i++) {
            int index = i; // variables used in lambda must be effectively final
            checkBox[index].setOnCheckedChangeListener((cb, b) -> {
                setProficiency(skill[index], b);
                skillView[index].setText(String.format(Locale.US, "%s", player.calculateSkillValue(skill[index])));
            });
        }

        spinnerAbility.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeLayout(Abilities.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        changeLayout(Abilities.values()[position]);

        abilityScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();

                if (!text.isEmpty()) {
                    int textParse = Integer.parseInt(text);
                    for (int i = 0; i < 5; i++) {
                        if (skill[i] == null)
                            break;
                        Abilities abilities = skill[i].getAbilities();
                        PlayerAbility playerAbility = player.getAbilities().get(abilities.name());
                        playerAbility.setValue(textParse);

                        skillView[i].setText(String.format(Locale.US, "%s", player.calculateSkillValue(skill[i])));
                    }
                }
            }
        });
    }

    private void changeLayout(Abilities abilities) {
        for (TextView txt : txt)
            txt.setVisibility(View.VISIBLE);

        for (TextView txt : skillView)
            txt.setVisibility(View.VISIBLE);

        for (CheckBox cb : checkBox)
            cb.setVisibility(View.VISIBLE);

        switch (abilities) {
            case Strength:
                layoutAbilityStrength();
                break;
            case Dexterity:
                layoutAbilityDexterity();
                break;
            case Intelligence:
                layoutAbilityIntelligence();
                break;
            case Wisdom:
                layoutAbilityWisdom();
                break;
            case Charisma:
                layoutAbilityCharisma();
                break;
            case Constitution: {
                for (TextView txt : txt)
                    txt.setVisibility(View.GONE);
                for (TextView txt : skillView)
                    txt.setVisibility(View.GONE);
                for (CheckBox cb : checkBox)
                    cb.setVisibility(View.GONE);
                break;
            }
        }
    }

    private void setProficiency(Skills skill, boolean proficient) {
        Skill playerSkill = player.getAbilities().get(skill.getAbilities().name()).getSkills().get(skill.name());
        playerSkill.setProficienct(proficient);
    }

    private void layoutAbilityStrength() {
        PlayerAbility playerAbility = player.getAbilities().get(Abilities.Strength.name());
        abilityScore.setText(String.format(Locale.US, "%d", playerAbility.getValue()));

        setSkillView(Skills.Athletics, playerAbility, 0);

        for (int i = 1; i < 5; i++)
            txt[i].setVisibility(View.GONE);
        for (int i = 1; i < 5; i++)
            skillView[i].setVisibility(View.GONE);
        for (int i = 1; i < 5; i++)
            checkBox[i].setVisibility(View.GONE);
    }

    @SuppressLint("SetTextI18n")
    private void layoutAbilityDexterity() {
        PlayerAbility playerAbility = player.getAbilities().get(Abilities.Dexterity.name());
        abilityScore.setText(Integer.toString(playerAbility.getValue()));

        setSkillView(Skills.Acrobatics, playerAbility, 0);
        setSkillView(Skills.SleightOfHand, playerAbility, 1);
        setSkillView(Skills.Stealth, playerAbility, 2);

        for (int i = 3; i < 5; i++)
            txt[i].setVisibility(View.GONE);
        for (int i = 3; i < 5; i++)
            skillView[i].setVisibility(View.GONE);
        for (int i = 3; i < 5; i++)
            checkBox[i].setVisibility(View.GONE);
    }

    private void layoutAbilityIntelligence() {
        PlayerAbility playerAbility = player.getAbilities().get(Abilities.Intelligence.name());
        abilityScore.setText(String.format(Locale.US, "%d", playerAbility.getValue()));

        setSkillView(Skills.Arcana, playerAbility, 0);
        setSkillView(Skills.History, playerAbility, 1);
        setSkillView(Skills.Investigation, playerAbility, 2);
        setSkillView(Skills.Nature, playerAbility, 3);
        setSkillView(Skills.Religion, playerAbility, 4);
    }

    private void layoutAbilityWisdom() {
        PlayerAbility playerAbility = player.getAbilities().get(Abilities.Wisdom.name());
        abilityScore.setText(String.format(Locale.US, "%d", playerAbility.getValue()));
        setSkillView(Skills.AnimalHandling, playerAbility, 0);
        setSkillView(Skills.Insight, playerAbility, 1);
        setSkillView(Skills.Medicine, playerAbility, 2);
        setSkillView(Skills.Perception, playerAbility, 3);
        setSkillView(Skills.Survival, playerAbility, 4);
    }

    private void layoutAbilityCharisma() {
        PlayerAbility playerAbility = player.getAbilities().get(Abilities.Charisma.name());
        abilityScore.setText(String.format(Locale.US, "%d", playerAbility.getValue()));

        setSkillView(Skills.Deception, playerAbility, 0);
        setSkillView(Skills.Intimidation, playerAbility, 1);
        setSkillView(Skills.Performance, playerAbility, 2);
        setSkillView(Skills.Persuasion, playerAbility, 3);

        checkBox[4].setVisibility(View.GONE);
        skillView[4].setVisibility(View.GONE);
        txt[4].setVisibility(View.GONE);
    }

    private void setSkillView(Skills skills, PlayerAbility playerAbility, int index) {
        Skill playerSkill = playerAbility.getSkills().get(skills.name());

        txt[index].setText(playerSkill.getName().name());
        skillView[index].setText(String.format(Locale.US, "%s", player.calculateSkillValue(skills)));
        checkBox[index].setChecked(playerSkill.isProficienct());
        skill[index] = skills;
    }

    private void savePlayer() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        System.out.println(player.getId());
        DatabaseReference reference = firebaseDatabase.getReference("Campaign").child(campaign.getId()).child("players").child(String.valueOf(position));
        reference.removeValue();
        reference.setValue(player);
    }


}
