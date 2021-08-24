package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventsMainAdapter
                extends RecyclerView.Adapter<EventsMainAdapter.EventsMainViewHolder>{

    @Override
    public EventsMainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(EventsMainAdapter.EventsMainViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected class EventsMainViewHolder extends RecyclerView.ViewHolder {
        public EventsMainViewHolder(View itemView) {
            super(itemView);
        }
    }
}
