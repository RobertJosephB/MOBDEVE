/*package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.EventModel;

public class EventViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_dayNumber;
    private TextView tv_monthName;
    private TextView tv_eventTitle;
    private TextView tv_time;
    private TextView tv_delete;


    public EventViewHolder(View itemView) {
        super(itemView);

        this.tv_dayNumber = itemView.findViewById(R.id.tv_dayNumber);
        this.tv_monthName = itemView.findViewById(R.id.tv_monthName);
        this.tv_eventTitle = itemView.findViewById(R.id.tv_eventTitle);
        this.tv_time = itemView.findViewById(R.id.tv_time);
        this.tv_delete = itemView.findViewById(R.id.tv_delete);

    }

    public void bindData(EventModel event) {
        this.tv_dayNumber.setText(event.getDayNumber());
        this.tv_monthName.setText(event.getMonthName());
        this.tv_eventTitle.setText(event.getEventTitle());
        this.tv_time.setText(event.getTime());

    }

    public void setDelBtnOnClickListener(View.OnClickListener onClickListener) {
        this.tv_delete.setOnClickListener(onClickListener);
    }
}

 */