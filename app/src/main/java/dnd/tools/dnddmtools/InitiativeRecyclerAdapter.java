package dnd.tools.dnddmtools;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
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
        Dataset = new SortedList<>(CreatureTurnItem.class, new SortedList.Callback<CreatureTurnItem>() {
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

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
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
        InitiativeViewHolder pageViewHolder = (InitiativeViewHolder) holder;
        CreatureTurnItem item = Dataset.get(position);
        pageViewHolder.name.setText(String.format("name %s", item.getName()));
        pageViewHolder.CR.setText(String.format(Locale.US,"CR %d", item.getCR()));
        pageViewHolder.Initiative.setText(String.format(Locale.US,"Initiative %d", item.getInitiative()));
        pageViewHolder.Dexterity.setText(String.format(Locale.US,"Dexterity %d", item.getDexterity()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return Dataset.size();
    }

    private class InitiativeViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView CR;
        TextView Initiative;
        TextView Dexterity;

        InitiativeViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);

            CR = itemView.findViewById(R.id.CR);

            Initiative = itemView.findViewById(R.id.Initiative);

            Dexterity = itemView.findViewById(R.id.Dexterity);

        }
    }
}