package com.example.room_demo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.room_demo2.adapters.PersonAdapter;
import com.example.room_demo2.database.AppDatabase;
import com.example.room_demo2.database.AppExecutors;
import com.example.room_demo2.model.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    private RecyclerView mRecyclerView;
    private PersonAdapter mAdapter;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.addFAB);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,EditActivity.class));
            }
        });

        mRecyclerView = findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PersonAdapter(MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        mDb = AppDatabase.getInstance(getApplicationContext());
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<Person> tasks=mAdapter.getTasks();
                        mDb.personDao().deletePerson(tasks.get(position));
                        retrieveTasks();
                    }
                });
            }


        }).attachToRecyclerView(mRecyclerView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        retrieveTasks();
    }
    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Person> persons = mDb.personDao().loadAllPersons();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter.setTasks(persons);
                    }
                });
            }
        });
    }


}