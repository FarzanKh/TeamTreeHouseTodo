package com.example.teamtreehouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.LinearLayoutCompat;
import java.util.ArrayList;

// THIS FILE IS NOT BEING USED YET
public class ViewPagerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Todo> todos;

    public ViewPagerAdapter(Context context, ArrayList<Todo> todos) {
        this.context = context;
        this.todos = todos;
    }


    @Override
    public int getCount() {
        if (todos != null && todos.size() > 0) {
            return todos.size();
        } else {
            return 0;
        }
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return todos.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.viewpager_item, viewGroup, false);
        }

        LinearLayoutCompat mainTodoLayout = view.findViewById(R.id.mainTodoLayout);
        TextView txtTodo = view.findViewById(R.id.item_text);
        CheckBox checkBox = view.findViewById(R.id.item_checkbox);
        Button deleteButton = view.findViewById(R.id.buttonDelete);

        txtTodo.setText("Todo" + todos.get(position).getName());
        checkBox.setChecked(false);


        // When I tap on each, I want to change its color to represent that it's under work
        mainTodoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked" + todos.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
