package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.R;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.EventModel;

public class SetAlarmsAdapter extends RecyclerView.Adapter<SetAlarmsAdapter.SetAlarmsViewHolder> {

    private ArrayList<EventModel> eventList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public SetAlarmsAdapter(Context context, ArrayList<EventModel> eventList, OnItemClickListener onItemClickListener) {
        this.eventList = eventList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() { return this.eventList.size(); }

    public void updateList(ArrayList<EventModel> newEventList) {
        this.eventList = newEventList;
        notifyDataSetChanged();
    }

    @Override
    public SetAlarmsAdapter.SetAlarmsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_set_alarm, parent, false);

        SetAlarmsViewHolder setAlarmsViewHolder = new SetAlarmsViewHolder(view, onItemClickListener);

        return setAlarmsViewHolder;
    }

    @Override
    public void onBindViewHolder(SetAlarmsAdapter.SetAlarmsViewHolder holder, int position) {
        String displayedMonth = "";

        holder.tv_dayNumber.setText(this.eventList.get(position).getDayNumber());

        switch (this.eventList.get(position).getMonthName()) {
            case "January":     displayedMonth = "JAN.";    break;
            case "February":    displayedMonth = "FEB.";    break;
            case "March":       displayedMonth = "MAR.";    break;
            case "April":       displayedMonth = "APR.";    break;
            case "August":      displayedMonth = "AUG.";    break;
            case "September":   displayedMonth = "SEPT.";   break;
            case "October":     displayedMonth = "OCT.";    break;
            case "November":    displayedMonth = "NOV.";    break;
            case "December":    displayedMonth = "DEC.";    break;
        }

        holder.tv_monthName.setText(displayedMonth);
        holder.tv_yearNumber.setText(this.eventList.get(position).getYearNumber());
        holder.tv_eventTitle.setText(this.eventList.get(position).getEventTitle());
        holder.tv_time.setText(this.eventList.get(position).getTime());
    }

    protected class SetAlarmsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_dayNumber;
        private TextView tv_monthName;
        private TextView tv_yearNumber;
        private TextView tv_eventTitle;
        private TextView tv_time;
        private LinearLayout ll_eventClickable;
        OnItemClickListener onItemClickListener;

        public SetAlarmsViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);

            tv_dayNumber = view.findViewById(R.id.tv_dayNumber);
            tv_monthName = view.findViewById(R.id.tv_monthName);
            tv_yearNumber = view.findViewById(R.id.tv_yearNumber);
            tv_eventTitle = view.findViewById(R.id.tv_eventTitle);
            tv_time = view.findViewById(R.id.tv_time);
            ll_eventClickable = view.findViewById(R.id.ll_eventClickable);
            this.onItemClickListener = onItemClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
