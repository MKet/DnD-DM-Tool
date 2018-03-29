package dnd.tools.dnddmtools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Models.Ability;
import Models.Campaign;
import Models.CampaignPlayer;
import Models.DungeonMaster;
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
    private String DUNGEON_MASTER = "";
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_campaign_activity);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Button btnAddPlayer = (Button)findViewById(R.id.btnAddPlayer);
        Button btnAddCampaign = (Button)findViewById(R.id.btnNewCampaign);
        lstPlayers = (ListView)findViewById(R.id.lstPlayers);
        txtPlayername = (EditText)findViewById(R.id.txtPlayername);
        txtCampaignname = (EditText)findViewById(R.id.txtCampaignName);


        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayer();
            }
        });
        setListView();

        btnAddCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCampaign();
            }
        });
    }

    private void addCampaign(){
        campaign = new Campaign();
        campaign.setPlayers(players);

        Intent intent = getIntent();
        DungeonMaster dungeonMaster = (DungeonMaster) intent.getParcelableExtra(HomeActivity.DUNGEONMASTER);

        campaign.setDungeonMaster(dungeonMaster.getId());
        campaign.setName(txtCampaignname.getText().toString());

        String key = reference.push().getKey();
        reference.child("Campaign").child(key).child("dungeonMaster").setValue(campaign.getDungeonMaster());
        reference.child("Campaign").child(key).child("name").setValue(campaign.getName());
        reference.child("Campaign").child(key).child("players").setValue(campaign.getPlayers());
        Intent intentBack = new Intent(this,HomeActivity.class);
        intent.putExtra(DUNGEON_MASTER,dungeonMaster);
        startActivity(intentBack);
    }

    private void addPlayer(){
        CampaignPlayer player = new CampaignPlayer();

        player.setName(txtPlayername.getText().toString());

        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Athletics",10, Ability.Strength));

        skills.add(new Skill("Acrobatics",10, Ability.Dexterity));
        skills.add(new Skill("Sleight of Hand",10, Ability.Dexterity));
        skills.add(new Skill("Stealth",10, Ability.Dexterity));

        skills.add(new Skill("Arcana",10, Ability.Intelligence));
        skills.add(new Skill("History",10, Ability.Intelligence));
        skills.add(new Skill("Investigation",10, Ability.Intelligence));
        skills.add(new Skill("Nature",10, Ability.Intelligence));
        skills.add(new Skill("Religion",10, Ability.Intelligence));

        skills.add(new Skill("Animal Handling",10, Ability.Wisdom));
        skills.add(new Skill("Insight",10, Ability.Wisdom));
        skills.add(new Skill("Medicine",10, Ability.Wisdom));
        skills.add(new Skill("Perception",10, Ability.Wisdom));
        skills.add(new Skill("Survival",10, Ability.Wisdom));

        skills.add(new Skill("Deception",10, Ability.Charisma));
        skills.add(new Skill("Intimidation",10, Ability.Charisma));
        skills.add(new Skill("Performance",10, Ability.Charisma));
        skills.add(new Skill("Persuasion",10, Ability.Charisma));



        player.setSkillList(skills);
        String id = reference.push().getKey();
        player.setId(id);
        players.add(player);
        adapter.notifyDataSetChanged();
        txtPlayername.setText("");
    }

    private void setListView(){
        adapter = new ArrayAdapter<CampaignPlayer>(this,android.R.layout.simple_list_item_1,players);
        lstPlayers.setAdapter(adapter);
    }
}
