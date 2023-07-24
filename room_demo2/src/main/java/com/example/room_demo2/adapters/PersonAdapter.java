package com.example.room_demo2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_demo2.EditActivity;
import com.example.room_demo2.MainActivity;
import com.example.room_demo2.R;
import com.example.room_demo2.contants.Constants;
import com.example.room_demo2.database.AppDatabase;
import com.example.room_demo2.model.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    private Context context;
    private List<Person> mPersonList;

    public PersonAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PersonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.person_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.MyViewHolder holder, int position) {
        holder.name.setText(mPersonList.get(position).getName());
        holder.email.setText(mPersonList.get(position).getEmail());
        holder.number.setText(mPersonList.get(position).getNumber());
        holder.pincode.setText(mPersonList.get(position).getPincode());
        holder.city.setText(mPersonList.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        if (mPersonList == null) {
            return 0;
        }
        return mPersonList.size();
    }

    public void setTasks(List<Person> personList) {
        mPersonList = personList;
        notifyDataSetChanged();
    }

    public List<Person> getTasks() {

        return mPersonList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, pincode, number, city;
        ImageView editImage;
        AppDatabase mDb;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);
            name = itemView.findViewById(R.id.person_name);
            email = itemView.findViewById(R.id.person_email);
            pincode = itemView.findViewById(R.id.person_pincode);
            number = itemView.findViewById(R.id.person_number);
            city = itemView.findViewById(R.id.person_city);
            editImage = itemView.findViewById(R.id.image);
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int elementId = mPersonList.get(getAdapterPosition()).getId();
                    Intent i = new Intent(context, EditActivity.class);
                    i.putExtra(Constants.UPDATE_Person_Id, elementId);
                    context.startActivity(i);
                }
            });
        }
    }
}
