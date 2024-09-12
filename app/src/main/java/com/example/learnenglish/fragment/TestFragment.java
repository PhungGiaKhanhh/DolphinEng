package com.example.learnenglish.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.learnenglish.activity.ScreenSlidePagerActivity;
import com.example.learnenglish.R;

public class TestFragment extends Fragment {
    private ListView lv_test;
    private String[] list_test= {"Unit 1: Thành phố ", "Unit 2: Trái cây", "Unit 3: Động vật", "Unit 4: Rau củ", "Unit 5: Đồ uống", "Unit 6: Quần áo", "Unit 7: Thân thể", "Unit 8: Thời tiết"};

    public TestFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_test, container, false);
        lv_test= (ListView) view.findViewById(R.id.lisstview_test);
        ArrayAdapter adapter=new ArrayAdapter(getActivity(), R.layout.custom_dongbaitest, list_test);
        lv_test.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), list_test[position], Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), ScreenSlidePagerActivity.class);
                intent.putExtra("viTri", position);
                startActivity(intent);
            }
        });
    }
}
