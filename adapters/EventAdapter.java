package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.R;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.EventModel;

public class EventAdapter
                extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context context;
    private ArrayList<EventModel> eventList;

    public EventAdapter(Context context, ArrayList<EventModel> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_dayspecific, parent, false);

        EventViewHolder eventViewHolder = new EventViewHolder(view);

        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(EventAdapter.EventViewHolder holder, int position) {
        holder.tv_dayNumber.setText(eventList.get(position).getDayNumber());
        holder.tv_monthName.setText(eventList.get(position).getMonthName());
        holder.tv_eventTitle.setText(eventList.get(position).getEventTitle());
        holder.tv_time.setText(eventList.get(position).getTime());

        holder.ll_eventClickable.setOnClickListener( v -> {
            Toast.makeText(context, "Specific Event Opened.", Toast.LENGTH_SHORT).show();
        });

        holder.tv_delete.setOnClickListener( v -> {
            Toast.makeText(context, "Delete attempted.", Toast.LENGTH_SHORT).show();
        });
    }

    protected class EventViewHolder extends RecyclerView.ViewHolder{
        TextView tv_dayNumber;
        TextView tv_monthName;
        TextView tv_eventTitle;
        TextView tv_time;
        TextView tv_delete;
        LinearLayout ll_eventClickable;

        public EventViewHolder(View view) {
            super(view);
            tv_dayNumber = view.findViewById(R.id.tv_dayNumber);
            tv_monthName = view.findViewById(R.id.tv_monthName);
            tv_eventTitle = view.findViewById(R.id.tv_eventTitle);
            tv_time = view.findViewById(R.id.tv_time);
            tv_delete = view.findViewById(R.id.tv_delete);
            ll_eventClickable = view.findViewById(R.id.ll_eventClickable);
        }
    }
}
