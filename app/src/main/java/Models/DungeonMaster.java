package Models;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.List;

/**
 * Created by maxhe on 15-3-2018.
 */

public class DungeonMaster {
    private List<Campaign> campaigns;
    private GoogleSignInAccount account;

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public GoogleSignInAccount getAccount() {
        return account;
    }

    public void setAccount(GoogleSignInAccount account) {
        this.account = account;
    }
}
