package dnd.tools.dnddmtools;

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

/**
 * Created by maxhe on 15-3-2018.
 */

public class HomeActivity extends AppCompatActivity{

    public static String DUNGEONMASTER = "";
    private ListView lstViewPlayers;
    private Spinner spinnerCampaign;
    private ArrayAdapter<CampaignPlayer> campaignPlayerArrayAdapter;
    private ArrayAdapter<Campaign> campaignArrayAdapter;
    private List<CampaignPlayer> campaignPlayers;
    private List<Campaign> campaigns;
    private DungeonMaster dungeonMaster;
    public static final String CAMPAIGNPLAYER = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intentGetAccount = getIntent();
        dungeonMaster = intentGetAccount.getParcelableExtra(MainActivity.DUNGEON_MASTER);
        Button btnNewCampaign = findViewById(R.id.btnNewCampaign);
        final Intent intent = new Intent(this,NewCampaignActivity.class);
        btnNewCampaign.setOnClickListener(v -> {
            intent.putExtra(DUNGEONMASTER,dungeonMaster);
            startActivity(intent);
        });
        setSpinnerCampaign();

        lstViewPlayers = findViewById(R.id.lstPlayers);
        spinnerCampaign.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setLstViewPlayers((Campaign) campaigns.toArray()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button combatButton = findViewById(R.id.combatTracker);

        combatButton.setOnClickListener((v) -> {
            Intent combatIntent = new Intent(this, InitiativeActivity.class);
            combatIntent.putParcelableArrayListExtra("campaignPlayers", (ArrayList<CampaignPlayer>) campaignPlayers);
            startActivity(combatIntent);
        });
    }

    private void setSpinnerCampaign(){
        campaigns = new ArrayList<>();
        campaignArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,campaigns);
        spinnerCampaign = findViewById(R.id.spinner);
        spinnerCampaign.setAdapter(campaignArrayAdapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Campaign");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Campaign campaign = snapshot.getValue(Campaign.class);
                    campaigns.add(campaign);
                }
                campaignArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setLstViewPlayers(Campaign campaign){
        campaignPlayers = new ArrayList<>();
        campaignPlayerArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,campaignPlayers);
        lstViewPlayers.setAdapter(campaignPlayerArrayAdapter);

        campaignPlayers.addAll(campaign.getPlayers());
        campaignPlayerArrayAdapter.notifyDataSetChanged();
        lstViewPlayers.setOnItemClickListener((parent, view, position, id) -> {
            goToPlayerStats((CampaignPlayer) campaignPlayers.toArray()[position]);
        });

    }

    private void goToPlayerStats(CampaignPlayer player){
        Intent intent = new Intent(this,PlayerActivity.class);
        intent.putExtra(CAMPAIGNPLAYER,player);
        startActivity(intent);
    }
}
