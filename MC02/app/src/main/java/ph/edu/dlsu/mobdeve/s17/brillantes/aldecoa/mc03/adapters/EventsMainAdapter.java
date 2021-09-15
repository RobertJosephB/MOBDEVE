package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.R;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.DayModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.EventModel;

public class EventsMainAdapter
                extends RecyclerView.Adapter<EventsMainAdapter.EventsMainViewHolder>{

    private Context context;
    private ArrayList<DayModel> currentMonthDays;

    private ArrayList<EventModel> data = new ArrayList<>();
    private ViewClickListener listener;




    public EventsMainAdapter(Context context, ArrayList<DayModel> currentMonthDays,ViewClickListener listener) {
        this.context = context;
        this.currentMonthDays = currentMonthDays;
        this.listener = listener;





    }

    public void updateList (ArrayList<DayModel> newMonthDays) {
        this.currentMonthDays = newMonthDays;
        ArrayList<EventModel> temp = new ArrayList<>();
        EventModel noEvents = new EventModel();
        noEvents.setEventTitle("No events");
        temp.add(noEvents);

        for(int i = 0; i < currentMonthDays.size();i++){
            System.out.println(currentMonthDays.get(0).getEvents().size());
            if(currentMonthDays.get(i).getEvents().size()==0)
                currentMonthDays.get(i).setEvents(temp);

        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return this.currentMonthDays.size();
    }

    public interface ViewClickListener{
        void onClick(View v,int position);
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

    protected class EventsMainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_dayNumber;
        TextView tv_monthName;
        TextView tv_eventTitle;
        LinearLayout ll_clickable;

        public EventsMainViewHolder(View view) {
            super(view);
            tv_dayNumber = view.findViewById(R.id.tv_dayNumber);
            tv_monthName = view.findViewById(R.id.tv_monthName);
            tv_eventTitle = view.findViewById(R.id.tv_eventTitle);
            ll_clickable = view.findViewById(R.id.ll_eventClickable);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());

        }
    }

}
