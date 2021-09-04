package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import android.view.View;
import android.widget.EditText;


import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.EventModel;

public class EditEventHolder extends RecyclerView.ViewHolder{
    private EditText editEventTitle, editEventTime, editEventDetails, editEventAlarmSettings, editEventSendingTo;
    FloatingActionButton add_event;

    public EditEventHolder(View itemView) {
        super(itemView);
        this.editEventTitle = itemView.findViewById(R.id.tv_edit_event_title);
        this.editEventTime = itemView.findViewById(R.id.tv_edit_event_time);
        this.editEventDetails = itemView.findViewById(R.id.tv_edit_event_details);
        this.editEventAlarmSettings = itemView.findViewById(R.id.tv_edit_event_alarm);
        this.editEventSendingTo = itemView.findViewById(R.id.tv_sending_to);
        this.add_event = itemView.findViewById(R.id.fab_add_event);


    }

    public void bindData(EventModel event) {
        this.editEventTitle.setText(event.getEventTitle());
        this.editEventTime.setText(event.getTime());
        this.editEventDetails.setText(event.getDetails());
        this.editEventAlarmSettings.setText(event.getNotificationType());
    }

    public void setAddEvent(View.OnClickListener onClickListener) {
        this.add_event.setOnClickListener(onClickListener);
    }
}
