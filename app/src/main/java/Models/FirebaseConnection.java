package Models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by maxhe on 15-3-2018.
 */

public class FirebaseConnection {
    private FirebaseDatabase firebaseDatabase;

    public FirebaseConnection(){
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void newCampaign(Campaign campaign){
        String key = firebaseDatabase.getReference("Campaign").push().getKey();
        DatabaseReference reference = firebaseDatabase.getReference();
        reference.child("Campaign").child(key).setValue(campaign);
    }
}
