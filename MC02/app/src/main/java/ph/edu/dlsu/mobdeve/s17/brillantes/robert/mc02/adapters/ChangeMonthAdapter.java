package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.R;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.util.StoragePreferences;

public class ChangeMonthAdapter
                extends RecyclerView.Adapter<ChangeMonthAdapter.ChangeMonthViewHolder>{

    private Context context;
    private ArrayList<String> months;
    private String currentMonth;
    private int lastCheckedPosition = -1;

    public ChangeMonthAdapter(Context context, ArrayList<String> months, String currentMonth) {
        this.context = context;
        this.months = months;
        this.currentMonth = currentMonth;

        for (int i = 0; i < this.months.size(); i++) {
            if (this.months.get(i).equals(currentMonth)) {
                this.lastCheckedPosition = i;
            }
        }
    }

    @Override
    public int getItemCount() { return this.months.size(); }

    @Override
    public ChangeMonthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_change_month, parent, false);

        ChangeMonthViewHolder changeMonthViewHolder = new ChangeMonthViewHolder(view);

        return changeMonthViewHolder;
    }

    @Override
    public void onBindViewHolder(ChangeMonthAdapter.ChangeMonthViewHolder holder, int position) {
        if (this.months.get(position).equals(currentMonth)) {
            holder.rb_monthOption.setChecked(true);
        }
        holder.rb_monthOption.setText(this.months.get(position));
    }

    protected class ChangeMonthViewHolder extends RecyclerView.ViewHolder {
        RadioButton rb_monthOption;

        public ChangeMonthViewHolder(View view) {
            super(view);
            this.rb_monthOption = view.findViewById(R.id.rb_monthOption);
        }
    }
}
