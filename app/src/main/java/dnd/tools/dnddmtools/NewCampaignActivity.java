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

import DndUtil.DndUtil;
import Models.Ability;
import Models.AbilityValueWrapper;
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

        Button btnAddPlayer = findViewById(R.id.btnAddPlayer);
        Button btnAddCampaign = findViewById(R.id.btnNewCampaign);
        lstPlayers = findViewById(R.id.lstPlayers);
        txtPlayername = findViewById(R.id.txtPlayername);
        txtCampaignname = findViewById(R.id.txtCampaignName);

        btnAddPlayer.setOnClickListener(v -> addPlayer());
        setListView();

        btnAddCampaign.setOnClickListener(v -> addCampaign());
    }

    private void addCampaign(){
        campaign = new Campaign();
        campaign.setPlayers(players);

        Intent intent = getIntent();
        DungeonMaster dungeonMaster = intent.getParcelableExtra(HomeActivity.DUNGEONMASTER);

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
        CampaignPlayer player = new CampaignPlayer(txtPlayername.getText().toString(), reference.push().getKey());
        players.add(player);
        adapter.notifyDataSetChanged();
        txtPlayername.setText("");
    }

    private void setListView(){
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,players);
        lstPlayers.setAdapter(adapter);
    }
}
