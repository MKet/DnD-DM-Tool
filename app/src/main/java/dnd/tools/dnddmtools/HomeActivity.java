package dnd.tools.dnddmtools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by maxhe on 15-3-2018.
 */

public class HomeActivity extends AppCompatActivity{

    public static String ACCOUNT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intentGetAccount = getIntent();
        final GoogleSignInAccount account = (GoogleSignInAccount) intentGetAccount.getParcelableExtra(MainActivity.ACCOUNT);
        Button btnNewCampaign = (Button)findViewById(R.id.btnNewCampaign);
        final Intent intent = new Intent(this,NewCampaignActivity.class);
        btnNewCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(ACCOUNT,account);
                startActivity(intent);
            }
        });
    }

}
