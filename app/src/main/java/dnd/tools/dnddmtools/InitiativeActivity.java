package dnd.tools.dnddmtools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

import static DndUtil.DndUtil.RollToModifier;

public class InitiativeActivity extends AppCompatActivity {

    private Random random;

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
        RecyclerView recyclerView = findViewById(R.id.recycler);

        InitiativeRecyclerAdapter adapter = new InitiativeRecyclerAdapter();
        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LinearLayoutManager);
        recyclerView.setAdapter(adapter);

        add.setOnClickListener((v) -> {
            String name = creatureNameInput.getText().toString();
            int cr  = Integer.parseInt(challengeRatingInput.getText().toString());
            int amount = Integer.parseInt(amountInput.getText().toString());
            int dexterity = Integer.parseInt(dexterityInput.getText().toString());
            int initiative = Integer.parseInt(initiativeInput.getText().toString());
            int rollResult = Integer.parseInt((rollInput.getText().toString()));

            CreatureTurnItem item = new CreatureTurnItem(
                                        name,
                                        cr,
                                        dexterity,
                                rollResult + initiative);
            adapter.add(item);
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
            int nextInt = random.nextInt(20)+1;

            rollInput.setText(nextInt);
        });

        endTurn.setOnClickListener((v) -> adapter.nextTurn());

        endCombat.setOnClickListener((v) -> adapter.clear());
    }
}
