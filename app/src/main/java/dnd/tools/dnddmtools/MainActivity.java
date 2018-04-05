package dnd.tools.dnddmtools;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.FirebaseDatabase;

import Models.DungeonMaster;

public class MainActivity extends AppCompatActivity {

    private SignInButton signIn;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 9001;
    public static String DUNGEON_MASTER = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
         googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, (v) -> {
             Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_LONG).show();
         }).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();
        signIn = findViewById(R.id.btnLogin);
        signIn.setOnClickListener((v) -> {
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityForResult(intent,RC_SIGN_IN);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result){
        Log.d("TAG","handleSignInResult: " + result + " status: " + result.getStatus());

        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            DungeonMaster dungeonMaster = new DungeonMaster(account);
            Intent intent = new Intent(this,HomeActivity.class);
            intent.putExtra(DUNGEON_MASTER,dungeonMaster);
            this.startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
