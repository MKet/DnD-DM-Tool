package dnd.tools.dnddmtools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

import DndUtil.DndUtil;

import static DndUtil.DndUtil.RollD20;
import static DndUtil.DndUtil.RollToModifier;

public class InitiativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiative);

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

        InitiativeRecyclerAdapter adapter = new InitiativeRecyclerAdapter();
        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LinearLayoutManager);
        recyclerView.setAdapter(adapter);

        add.setOnClickListener((v) -> {
            if (creatureNameInput.getText().length() < 1)
                return;
            if (dexterityInput.getText().length() < 1)
                return;

            String name = creatureNameInput.getText().toString();
            int cr  = Integer.parseInt(challengeRatingInput.getText().toString());
            int amount;
            if (creatureNameInput.getText().length() < 1)
                amount = 1;
            else
                amount = Integer.parseInt(amountInput.getText().toString());
            int dexterity = Integer.parseInt(dexterityInput.getText().toString());
            int initiative;
            if (initiativeInput.getText().length() < 1)
                initiative = RollToModifier(dexterity);
            else
                initiative = Integer.parseInt(initiativeInput.getText().toString());

            int rollResult;
            if (roll.getText().length() < 1)
                rollResult = RollD20();
            else
                rollResult = Integer.parseInt((rollInput.getText().toString()));

            for(int i = 0; i < amount; i++) {
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

            rollInput.setText(rollResult);
        });

        endTurn.setOnClickListener((v) -> adapter.nextTurn());

        endCombat.setOnClickListener((v) -> adapter.clear());
    }
}
