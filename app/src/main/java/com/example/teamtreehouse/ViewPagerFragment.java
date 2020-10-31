package com.example.teamtreehouse;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.switchmaterial.SwitchMaterial;


public class ViewPagerFragment extends Fragment {
    public static final String KEY_RECIPE_INDEX = "recipe_index";
    Button btn_viewall, btn_add;
    EditText edt_name;
    SwitchMaterial sw_active;
    ListView lv_todos;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        int index = getArguments().getInt(KEY_RECIPE_INDEX);
        getActivity().setTitle(Recipes.names[index]);
        View view = inflater.inflate(R.layout.viewpager_fragment, container, false);


        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_viewall = (Button) view.findViewById(R.id.btn_viewAll);
        edt_name = (EditText) view.findViewById(R.id.et_name);
        sw_active = (SwitchMaterial) view.findViewById(R.id.sw_active);
        lv_todos = (ListView) view.findViewById(R.id.lv_todos);
        Todo todo = new Todo(-3, edt_name.getText().toString(), sw_active.isChecked());


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Todo todo = new Todo(-3, edt_name.getText().toString(), sw_active.isChecked());
                    Toast.makeText(getActivity(), todo.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(getActivity(), "Error Creating Todo", Toast.LENGTH_SHORT).show();

                }

            }
        });


        btn_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "hahaha", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
