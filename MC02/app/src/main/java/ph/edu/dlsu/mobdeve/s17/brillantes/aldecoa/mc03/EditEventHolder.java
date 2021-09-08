package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;


import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.EventModel;

public class EditEventHolder extends RecyclerView.ViewHolder{
    private EditText editEventTitle, editEventTimeHr,editEventTimeMin, editEventDetails, editEventSendingTo;
    FloatingActionButton add_event;
    ToggleButton btn_alarm, btn_email;

    public EditEventHolder(View itemView) {
        super(itemView);
        this.editEventTitle = itemView.findViewById(R.id.et_edit_event_title);
        this.editEventTimeHr = itemView.findViewById(R.id.et_edit_event_time_hr);
        this.editEventTimeMin = itemView.findViewById(R.id.et_edit_event_time_min);
        this.editEventDetails = itemView.findViewById(R.id.et_edit_event_details);
        this.btn_alarm = itemView.findViewById(R.id.btn_edit_event_alarm);
        this.btn_email = itemView.findViewById(R.id.btn_edit_event_email);
        this.editEventSendingTo = itemView.findViewById(R.id.et_sending_to);
        this.add_event = itemView.findViewById(R.id.fab_add_event);


    }

    public void bindData(EventModel event) {
        this.editEventTitle.setText(event.getEventTitle());
        this.editEventTimeHr.setText(event.getTime().substring(0,2));
        this.editEventTimeMin.setText(event.getTime().substring(2,4));
        this.editEventDetails.setText(event.getDetails());
        switch (event.getNotificationType()) {
            case "alarmemail":
                btn_alarm.setChecked(true);
                btn_email.setChecked(true);
                break;
            case "alarm":
                btn_alarm.setChecked(true);
                btn_email.setChecked(false);
                break;
            case "email":
                btn_alarm.setChecked(false);
                btn_email.setChecked(true);
                break;
            default:
                btn_alarm.setChecked(false);
                btn_email.setChecked(false);
                break;
        }

    }

    public void setAddEvent(View.OnClickListener onClickListener) {
        this.add_event.setOnClickListener(onClickListener);
    }
}
