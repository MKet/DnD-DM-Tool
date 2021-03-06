package dnd.tools.dnddmtools;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Models.Campaign;
import Models.CampaignPlayer;
import Models.DungeonMaster;


/**
 * Created by maxhe on 15-3-2018.
 */

public class NewCampaignActivity extends AppCompatActivity {

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private ListView lstPlayers;
    private EditText txtPlayername;
    private Campaign campaign;
    private EditText txtCampaignname;
    private ArrayAdapter<CampaignPlayer> adapter;
    private String DUNGEON_MASTER = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        campaign = new Campaign();
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

    private void addCampaign() {
        Intent intent = getIntent();
        DungeonMaster dungeonMaster = intent.getParcelableExtra(HomeActivity.DUNGEONMASTER);

        campaign.setDungeonMaster(dungeonMaster.getId());
        campaign.setName(txtCampaignname.getText().toString());

        String key = reference.push().getKey();
        reference.child("Campaign").child(key).child("dungeonMaster").setValue(campaign.getDungeonMaster());
        reference.child("Campaign").child(key).child("name").setValue(campaign.getName());
        reference.child("Campaign").child(key).child("players").setValue(campaign.getPlayers());
        Intent intentBack = new Intent(this, HomeActivity.class);
        intent.putExtra(DUNGEON_MASTER, (Parcelable) dungeonMaster);
        startActivity(intentBack);
        campaign = new Campaign();
        setListView();
    }

    private void addPlayer() {
        CampaignPlayer player = new CampaignPlayer(txtPlayername.getText().toString(), reference.push().getKey());

        campaign.getPlayers().add(player);
        adapter.notifyDataSetChanged();
        txtPlayername.setText("");
    }

    private void setListView() {
        adapter = new ArrayAdapter<>(this, R.layout.list_item, campaign.getPlayers());
        lstPlayers.setAdapter(adapter);
    }
}
