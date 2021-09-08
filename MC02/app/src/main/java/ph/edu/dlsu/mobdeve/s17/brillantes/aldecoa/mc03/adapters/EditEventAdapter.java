package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.EditEventHolder;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.R;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.EventDAO;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.EventDAOSQLImpl;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.EventModel;

public class EditEventAdapter extends RecyclerView.Adapter<EditEventHolder>{
    private ArrayList<EventModel> data;
    private Context context;

    public EditEventAdapter(Context context, ArrayList<EventModel> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public EditEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.activity_edit_event, parent, false);
        EventDAO postDAO = new EventDAOSQLImpl(context);
        EditEventHolder holder = new EditEventHolder(itemView);



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditEventHolder holder, int position) {

        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<EventModel> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
