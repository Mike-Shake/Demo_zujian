package com.example.demo_zujian;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentDeeplink#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentDeeplink extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragmentDeeplink() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmentDeeplink.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmentDeeplink newInstance(String param1, String param2) {
        fragmentDeeplink fragment = new fragmentDeeplink();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deeplink, container, false);

        PendingIntent pendingIntent = new NavDeepLinkBuilder(getContext())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.fragmentA)
                .setArguments(savedInstanceState)
                .createPendingIntent();


        TextView textView = view.findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
//                    NotificationChannel()
                }

            }
        });
        return view;
    }

}