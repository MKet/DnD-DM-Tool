package dnd.tools.dnddmtools;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Marco on 3/22/2018.
 */
public class InitiativeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SortedList<CreatureTurnItem> Dataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

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
        pageViewHolder.textView.setText(item.toString());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return Dataset.size();
    }

    class InitiativeViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public InitiativeViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}