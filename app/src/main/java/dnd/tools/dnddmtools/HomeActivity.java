package dnd.tools.dnddmtools;

import android.content.Intent;
import android.os.Bundle;
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
        dungeonMaster = (DungeonMaster) intentGetAccount.getParcelableExtra(MainActivity.DUNGEON_MASTER);
        Button btnNewCampaign = (Button)findViewById(R.id.btnNewCampaign);
        final Intent intent = new Intent(this,NewCampaignActivity.class);
        btnNewCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(DUNGEONMASTER,dungeonMaster);
                startActivity(intent);
            }
        });
        setSpinnerCampaign();

        lstViewPlayers = (ListView) findViewById(R.id.lstPlayers);
        spinnerCampaign.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setLstViewPlayers((Campaign) campaigns.toArray()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSpinnerCampaign(){
        campaigns = new ArrayList<>();
        campaignArrayAdapter = new ArrayAdapter<Campaign>(this,android.R.layout.simple_list_item_1,campaigns);
        spinnerCampaign = (Spinner)findViewById(R.id.spinner);
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
        campaignPlayerArrayAdapter = new ArrayAdapter<CampaignPlayer>(this,android.R.layout.simple_list_item_1,campaignPlayers);
        lstViewPlayers.setAdapter(campaignPlayerArrayAdapter);

        campaignPlayers.addAll(campaign.getPlayers());
        campaignPlayerArrayAdapter.notifyDataSetChanged();
        lstViewPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToPlayerStats((CampaignPlayer) campaignPlayers.toArray()[position]);
            }
        });

    }

    private void goToPlayerStats(CampaignPlayer player){
        Intent intent = new Intent(this,PlayerActivity.class);
        intent.putExtra(CAMPAIGNPLAYER,player);
        startActivity(intent);
    }
}
