package dnd.tools.dnddmtools;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Marco on 3/22/2018.
 */
public class InitiativeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SortedList<CreatureTurnItem> Dataset;

    public InitiativeRecyclerAdapter() {

        Dataset = new SortedList<>(CreatureTurnItem.class, new SortedListAdapterCallback<CreatureTurnItem>(this) {
            @Override
            public int compare(CreatureTurnItem o1, CreatureTurnItem o2) {
                return o1.compareTo(o2);
            }

            @Override
            public boolean areContentsTheSame(CreatureTurnItem oldItem, CreatureTurnItem newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(CreatureTurnItem item1, CreatureTurnItem item2) {
                return item1 == item2;
            }
        });
    }

    public void add(CreatureTurnItem o) {
        Dataset.add(o);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.initiative_item_view, parent, false);
        return new InitiativeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        InitiativeViewHolder initiativeViewHolder = (InitiativeViewHolder) holder;
        CreatureTurnItem item = Dataset.get(position);
        initiativeViewHolder.CR.setText(String.format(Locale.US,"%d", item.getCR()));
        initiativeViewHolder.Initiative.setText(String.format(Locale.US,"%d", item.getInitiative()));
        initiativeViewHolder.name.setText(item.getName());
        initiativeViewHolder.Dexterity.setText(String.format(Locale.US,"%d", item.getDexterity()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return Dataset.size();
    }

    public void clear() {
        Dataset.clear();
    }

    public void nextTurn() {
        CreatureTurnItem creatureTurnItem = Dataset.get(0);
        creatureTurnItem.endTurn();
        Dataset.recalculatePositionOfItemAt(0);
    }

    private class InitiativeViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView CR;
        private TextView Initiative;
        private TextView Dexterity;

        InitiativeViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            CR = itemView.findViewById(R.id.CR);
            Initiative = itemView.findViewById(R.id.Initiative);
            Dexterity = itemView.findViewById(R.id.Dexterity);
        }
    }
}