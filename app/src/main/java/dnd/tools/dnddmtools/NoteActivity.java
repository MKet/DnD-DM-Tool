package dnd.tools.dnddmtools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Models.Campaign;

/**
 * Created by maxhe on 29-3-2018.
 */

public class NoteActivity extends AppCompatActivity {

    public static String NOTE_RESULT_KEY = "NoteResult";
    private Campaign campaign;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_notes);

        Intent intent = getIntent();
        campaign = intent.getParcelableExtra(HomeActivity.NOTES);

        TextView txtCampaign = findViewById(R.id.txtCampaign);
        txtCampaign.setText(campaign.getName());

        editText = findViewById(R.id.txtNotes);

        try {
            editText.setText(campaign.getNote());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button btnSave = findViewById(R.id.btnSaveNote);
        btnSave.setOnClickListener(v -> saveNote());

    }

    private void saveNote() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Campaign").child(campaign.getId());
        reference.child("note").removeValue();
        reference.child("note").setValue(editText.getText().toString());

        Intent resultIntent = new Intent();
        resultIntent.putExtra(NOTE_RESULT_KEY, editText.getText().toString());
        setResult(Activity.RESULT_OK, resultIntent);
    }
}
