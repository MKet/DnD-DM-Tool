package dnd.tools.dnddmtools;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.Ability;
import Models.Campaign;
import Models.CampaignPlayer;
import Models.Skill;


/**
 * Created by maxhe on 15-3-2018.
 */

public class NewCampaignActivity extends AppCompatActivity {

    private List<CampaignPlayer> players = new ArrayList<>();
    private ListView lstPlayers;
    private EditText txtPlayername;
    private Campaign campaign;
    private EditText txtCampaignname;
    private ArrayAdapter<CampaignPlayer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_campaign_activity);

        Button btnAddPlayer = (Button)findViewById(R.id.btnAddPlayer);
        Button btnAddCampaign = (Button)findViewById(R.id.btnNewCampaign);
        lstPlayers = (ListView)findViewById(R.id.lstPlayers);
        txtPlayername = (EditText)findViewById(R.id.txtPlayername);
        txtCampaignname = (EditText)findViewById(R.id.txtCampaignName);

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayer(reference);
            }
        });
        setListView();

        btnAddCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCampaign(reference);
            }
        });
    }

    private void addCampaign(DatabaseReference reference){
        campaign = new Campaign();
        String key = reference.push().getKey();
        campaign.setId(key);
        campaign.setPlayers(players);
        campaign.setName(txtCampaignname.getText().toString());

        reference.child("Campaign").child(key).setValue(campaign);
    }

    private void addPlayer(DatabaseReference reference){
        CampaignPlayer player = new CampaignPlayer();

        player.setId(reference.push().getKey());
        player.setName(txtPlayername.getText().toString());

        List<Skill> skills = new ArrayList<>();
        List<Ability> abilities = new ArrayList<>();

        Ability strength = new Ability();
        strength.setValue(10);
        strength.setName("Strength");
        skills.add(new Skill("Athletics",10));
        strength.setSkills(skills);
        abilities.add(strength);
        skills.clear();

        Ability dexterity = new Ability();
        dexterity.setValue(10);
        dexterity.setName("Dexterity");
        skills.add(new Skill("Acrobatics",10));
        skills.add(new Skill("Sleight of Hand",10));
        skills.add(new Skill("Stealth",10));
        dexterity.setSkills(skills);
        abilities.add(dexterity);
        skills.clear();

        Ability intelligence = new Ability();
        intelligence.setValue(10);
        intelligence.setName("Intelligence");
        skills.add(new Skill("Arcana",10));
        skills.add(new Skill("History",10));
        skills.add(new Skill("Investigation",10));
        skills.add(new Skill("Nature",10));
        skills.add(new Skill("Religion",10));
        intelligence.setSkills(skills);
        abilities.add(intelligence);
        skills.clear();

        Ability wisdom = new Ability();
        wisdom.setValue(10);
        wisdom.setName("Wisdom");
        skills.add(new Skill("Animal Handling",10));
        skills.add(new Skill("Insight",10));
        skills.add(new Skill("Medicine",10));
        skills.add(new Skill("Perception",10));
        skills.add(new Skill("Survival",10));
        wisdom.setSkills(skills);
        abilities.add(wisdom);
        skills.clear();

        Ability charisma = new Ability();
        charisma.setValue(10);
        charisma.setName("Charisma");
        skills.add(new Skill("Deception",10));
        skills.add(new Skill("Intimidation",10));
        skills.add(new Skill("Performance",10));
        skills.add(new Skill("Persuasion",10));
        charisma.setSkills(skills);
        abilities.add(charisma);
        skills.clear();

        player.setAbilityList(abilities);
        players.add(player);
        adapter.notifyDataSetChanged();
    }

    private void setListView(){
        adapter = new ArrayAdapter<CampaignPlayer>(this,android.R.layout.simple_list_item_1,players);
        lstPlayers.setAdapter(adapter);
    }
}
