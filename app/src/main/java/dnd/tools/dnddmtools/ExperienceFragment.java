package dnd.tools.dnddmtools;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Marco on 3/29/2018.
 */

public class ExperienceFragment extends DialogFragment {

    public static ExperienceFragment newInstance(int exp) {
        ExperienceFragment f = new ExperienceFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("exp", exp);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int exp = getArguments().getInt("exp");
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.exp_result));
        builder.setMessage(getString(R.string.experience_message, exp));
        builder.setPositiveButton("OK", (dialog, id) -> {
            // You don't have to do anything here if you just
            // want it dismissed when clicked
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
