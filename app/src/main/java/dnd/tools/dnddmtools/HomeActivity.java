package dnd.tools.dnddmtools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    }

    private void setSpinnerCampaign(){
        campaigns = new ArrayList<>();
        campaignArrayAdapter = new ArrayAdapter<Campaign>(this,android.R.layout.simple_list_item_1,campaigns);
        spinnerCampaign = (Spinner)findViewById(R.id.spinner);
        spinnerCampaign.setAdapter(campaignArrayAdapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Campaign");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Campaign campaign = new Campaign();
                    campaign.setName((String)snapshot.child("name").getValue());
                    campaign.setDungeonMaster((String)snapshot.child("dungeonMaster").getValue());
                    campaign.setPlayers((List<CampaignPlayer>)snapshot.child("players").getValue());
                    campaigns.add(campaign);
                }
                campaignArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
