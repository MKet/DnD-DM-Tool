package dnd.tools.dnddmtools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import Models.Campaign;

/**
 * Created by maxhe on 29-3-2018.
 */

public class NoteActivity extends AppCompatActivity {

    Campaign campaign;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_notes);

        Intent intent = getIntent();
        campaign = (Campaign) intent.getParcelableExtra(HomeActivity.NOTES);

        TextView txtCampaign = (TextView)findViewById(R.id.txtCampaign);
        txtCampaign.setText(campaign.getName());

        editText = (EditText)findViewById(R.id.txtNotes);

        try {
            editText.setText(campaign.getNote());
        } catch (Exception e) {
            e.printStackTrace();
        }


        Button btnSave = (Button)findViewById(R.id.btnSaveNote);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

    }

    private void saveNote(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Campaign").child(campaign.getId());
        reference.child("note").removeValue();
        reference.child("note").setValue(editText.getText().toString());
    }
}