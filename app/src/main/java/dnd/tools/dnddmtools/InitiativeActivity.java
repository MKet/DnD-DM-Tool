package dnd.tools.dnddmtools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Models.CampaignPlayer;
import Models.CreatureTurnItem;

import static DndUtil.DndUtil.ExpMap;
import static DndUtil.DndUtil.RollD20;
import static DndUtil.DndUtil.ScoreToModifier;

public class InitiativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiative);

        Bundle bundle = getIntent().getExtras();

        List<CampaignPlayer> players;
        {
            List<CampaignPlayer> temp = null;
            if (bundle != null)
                temp = bundle.getParcelableArrayList("campaignPlayers");

            if (temp == null)
                temp = new ArrayList<>();

            players = temp;
        }
        EditText creatureNameInput = findViewById(R.id.creatureName);
        EditText challengeRatingInput = findViewById(R.id.ChallengeRatingInput);
        EditText amountInput = findViewById(R.id.AmountInput);
        EditText dexterityInput = findViewById(R.id.DexterityInput);
        EditText initiativeInput = findViewById(R.id.InitiativeInput);
        EditText rollInput = findViewById(R.id.RollInput);
        Button clear = findViewById(R.id.ClearButton);
        Button roll = findViewById(R.id.RollButton);
        Button add = findViewById(R.id.AddButton);
        Button endTurn = findViewById(R.id.EndTurnButton);
        Button endCombat = findViewById(R.id.EndCombat);
        RecyclerView recyclerView = findViewById(R.id.recycler);

        InitiativeRecyclerAdapter adapter = new InitiativeRecyclerAdapter(this);
        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LinearLayoutManager);
        recyclerView.setAdapter(adapter);

        for (CampaignPlayer player : players) {
            adapter.add(new CreatureTurnItem(player));
        }

        add.setOnClickListener((v) -> {
            if (creatureNameInput.getText().toString().trim().length() == 0) {
                Toast.makeText(this, getString(R.string.name_not_filled), Toast.LENGTH_LONG).show();
                return;
            }
            if (dexterityInput.getText().toString().trim().length() == 0) {
                Toast.makeText(this, getString(R.string.dexterity_not_filled), Toast.LENGTH_LONG).show();
                return;
            }
            if (challengeRatingInput.getText().toString().trim().length() == 0) {
                Toast.makeText(this, getString(R.string.CR_not_filled), Toast.LENGTH_LONG).show();
                return;
            }

            String name = creatureNameInput.getText().toString();
            int dexterity = Integer.parseInt(dexterityInput.getText().toString());
            int cr = Integer.parseInt(challengeRatingInput.getText().toString());

            int amount;
            if (amountInput.getText().toString().trim().length() == 0)
                amount = 1;
            else
                amount = Integer.parseInt(amountInput.getText().toString());

            int initiative;
            if (initiativeInput.getText().toString().trim().length() == 0)
                initiative = ScoreToModifier(dexterity);
            else
                initiative = Integer.parseInt(initiativeInput.getText().toString());

            int rollResult;
            if (rollInput.getText().toString().trim().length() == 0)
                rollResult = RollD20();
            else
                rollResult = Integer.parseInt((rollInput.getText().toString()));

            for (int i = 0; i < amount; i++) {
                CreatureTurnItem item = new CreatureTurnItem(
                        name,
                        cr,
                        dexterity,
                        rollResult + initiative);
                adapter.add(item);
            }
        });

        clear.setOnClickListener((v) -> {
            creatureNameInput.getText().clear();
            challengeRatingInput.getText().clear();
            amountInput.getText().clear();
            dexterityInput.getText().clear();
            initiativeInput.getText().clear();
            rollInput.getText().clear();
        });
        roll.setOnClickListener((v) -> {
            int rollResult = RollD20();

            rollInput.setText(String.format(Locale.US, "%d", rollResult));
        });

        endTurn.setOnClickListener((v) -> adapter.nextTurn());

        endCombat.setOnClickListener((View v) -> {
            int experience = calculateExperience(adapter.getList(), players.size());

            Toast.makeText(this, getString(R.string.experience_message, experience), Toast.LENGTH_LONG).show();
        });
    }

    private int calculateExperience(Iterable<CreatureTurnItem> CRList, int playerAmount) {
        int totalExperience = 0;
        for (CreatureTurnItem item : CRList) {
            if (item.isFriendly())
                continue;
            totalExperience += calculateExperience(item.getCR());
        }
        return totalExperience / playerAmount;
    }

    private int calculateExperience(Iterable<CreatureTurnItem> CRList) {
        return calculateExperience(CRList, 1);
    }

    private int calculateExperience(float CR) {
        if (!ExpMap.containsKey(CR))
            throw new IllegalArgumentException("Challenge rating does not exist");

        return ExpMap.get(CR);
    }
}

