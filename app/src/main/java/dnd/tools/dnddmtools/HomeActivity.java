package dnd.tools.dnddmtools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Models.Campaign;
import Models.CampaignPlayer;
import Models.DungeonMaster;
import Models.Skill;

/**
 * Created by maxhe on 15-3-2018.
 */

public class HomeActivity extends AppCompatActivity{

    public static final String DUNGEONMASTER = "";
    public static final String POSITION = "Position";
    public static final String NOTES = "Note";
    public static final int NOTE_RESULT = 20;
    private ListView lstViewPlayers;
    private Spinner spinnerCampaign;
    private ArrayAdapter<CampaignPlayer> campaignPlayerArrayAdapter;
    private ArrayAdapter<Campaign> campaignArrayAdapter;
    private List<CampaignPlayer> campaignPlayers;
    private List<Campaign> campaigns;
    private DungeonMaster dungeonMaster;
    public static final String CAMPAIGN = "Campaign";
    public static final String CAMPAIGNPLAYER = "";
    public static final String SKILLSLIST = "LIST";
    private Campaign campaign;
    private DatabaseReference reference;

    private Button combatButton;
    private Button btnNewCampaign;
    private Button btnNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intentGetAccount = getIntent();
        dungeonMaster = intentGetAccount.getParcelableExtra(MainActivity.DUNGEON_MASTER);

        btnNewCampaign = findViewById(R.id.btnNewCampaign);
        final Intent intent = new Intent(this,NewCampaignActivity.class);
        btnNewCampaign.setOnClickListener(v -> {
            intent.putExtra(DUNGEONMASTER, (Parcelable) dungeonMaster);
            startActivity(intent);
        });
        setSpinnerCampaign();


        btnNotes = findViewById(R.id.btnSeeNotes);
        Intent intentNotes = new Intent(this,NoteActivity.class);
        btnNotes.setOnClickListener(v -> {
            intentNotes.putExtra(NOTES, (Parcelable)campaign);
            startActivity(intentNotes);
            startActivityForResult(intentNotes, NOTE_RESULT);
        });

        lstViewPlayers = findViewById(R.id.lstPlayers);
        spinnerCampaign.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setLstViewPlayers((Campaign) campaigns.toArray()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        combatButton = findViewById(R.id.combatTracker);
        combatButton.setOnClickListener((v) -> {
            Intent combatIntent = new Intent(this, InitiativeActivity.class);
            combatIntent.putParcelableArrayListExtra("campaignPlayers", (ArrayList<CampaignPlayer>) campaignPlayers);
            startActivity(combatIntent);
        });

        CampaignButtonsEnabled(campaigns != null && !campaigns.isEmpty());
    }

    /**
     * disable buttons that require canpaigns to be loaded
     * @param enabled whether any campaigns are loaded in
     */
    private void CampaignButtonsEnabled(boolean enabled) {
        combatButton.setEnabled(enabled);
        btnNotes.setEnabled(enabled);
    }

    private void setSpinnerCampaign(){
        campaigns = new ArrayList<>();
        campaignArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,campaigns);
        spinnerCampaign = findViewById(R.id.spinner);
        spinnerCampaign.setAdapter(campaignArrayAdapter);

        reference = FirebaseDatabase.getInstance().getReference("Campaign");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Campaign campaign = snapshot.getValue(Campaign.class);
                    String id = snapshot.getKey();
                    campaign.setId(id);
                    campaigns.add(campaign);
                }
                campaignArrayAdapter.notifyDataSetChanged();
                CampaignButtonsEnabled(campaigns != null && !campaigns.isEmpty());
            }

            @Override public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private void setLstViewPlayers(Campaign campaign){
        this.campaign = campaign;
        campaignPlayers = new ArrayList<>();
        campaignPlayerArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,campaignPlayers);
        lstViewPlayers.setAdapter(campaignPlayerArrayAdapter);

        campaignPlayers.addAll(campaign.getPlayers());
        campaignPlayerArrayAdapter.notifyDataSetChanged();
        lstViewPlayers.setOnItemClickListener((parent, view, position, id) -> {
            goToPlayerStats((CampaignPlayer) campaignPlayers.toArray()[position],position);
        });
    }

    private void goToPlayerStats(CampaignPlayer player, int position){
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(CAMPAIGNPLAYER, (Parcelable) player);
        intent.putExtra(CAMPAIGN, (Parcelable) campaign);
        intent.putExtra(POSITION, position);

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (NOTE_RESULT) : {
                if (resultCode == Activity.RESULT_OK) {
                    String note = data.getStringExtra(NoteActivity.NOTE_RESULT_KEY);
                    campaign.setNote(note);
                }
                break;
            }
        }
    }
}
