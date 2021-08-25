package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.R;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.DayModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.EventModel;

public class EventsMainAdapter
                extends RecyclerView.Adapter<EventsMainAdapter.EventsMainViewHolder>{

    private Context context;
    private ArrayList<DayModel> currentMonthDays;

    public EventsMainAdapter(Context context, ArrayList<DayModel> currentMonthDays) {
        this.context = context;
        this.currentMonthDays = currentMonthDays;
    }

    @Override
    public int getItemCount() {
        return this.currentMonthDays.size();
    }

    @Override
    public EventsMainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_main, parent, false);

        EventsMainViewHolder eventsMainViewHolder = new EventsMainViewHolder(view);

        return eventsMainViewHolder;
    }

    @Override
    public void onBindViewHolder(EventsMainAdapter.EventsMainViewHolder holder, int position) {
        holder.tv_dayNumber.setText(this.currentMonthDays.get(position).getDayNumber());
        holder.tv_monthName.setText(this.currentMonthDays.get(position).getMonthName());

        if (this.currentMonthDays.get(position).getEvents().size() == 1
            && this.currentMonthDays.get(position).getEvents().get(0).getEventTitle().equals("No events")) {
            holder.tv_eventTitle.setText("-- No events --");
            holder.tv_eventTitle.setTextColor(ContextCompat.getColor(context, R.color.gray));
        } else if (this.currentMonthDays.get(position).getEvents().size() == 1) {
            holder.tv_eventTitle.setText(this.currentMonthDays.get(position).getEvents().get(0).getEventTitle());
            holder.tv_eventTitle.setTextColor(ContextCompat.getColor(context, R.color.black));
        } else {
            String temp = this.currentMonthDays.get(position).getEvents().get(0).getEventTitle() + " ... +" +
                    (this.currentMonthDays.get(position).getEvents().size() - 1) + " more";
            holder.tv_eventTitle.setText(temp);
            holder.tv_eventTitle.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
    }

    protected class EventsMainViewHolder extends RecyclerView.ViewHolder {
        TextView tv_dayNumber;
        TextView tv_monthName;
        TextView tv_eventTitle;

        public EventsMainViewHolder(View view) {
            super(view);
            tv_dayNumber = view.findViewById(R.id.tv_dayNumber);
            tv_monthName = view.findViewById(R.id.tv_monthName);
            tv_eventTitle = view.findViewById(R.id.tv_eventTitle);
        }
    }
}
