package com.example.killua4564.hwapplication2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Collections;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private int count;
    private CheckBox[] checkBoxes;

    public Adapter(Context context, int count) {
        this.count = count;
        this.checkBoxes = new CheckBox[this.count];
        for (int i = 0; i < this.count; i++) {
            final int index = i;
            this.checkBoxes[index] = new CheckBox(context);
            this.checkBoxes[index].setText(String.valueOf(index + 1));
            this.checkBoxes[index].setChecked(false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.adapter, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int index) {
        viewHolder.bind(index);
    }

    @Override
    public int getItemCount() {
        return this.count;
    }

    public String getCheckedString() {
        ArrayList<String> list = new ArrayList<String>();
        for (CheckBox checkBox : this.checkBoxes) {
            if (checkBox.isChecked()) {
                list.add((String) checkBox.getText());
            }
        }
        if (list.size() > 0) {
            return "You select " + String.join(",", list);
        } else {
            return "You select nothing!";
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            this.checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        }

        void bind(final int index) {
            final String text = (String) checkBoxes[index].getText();
            this.checkBox.setText(checkBoxes[index].getText());
            this.checkBox.setChecked(checkBoxes[index].isChecked());
            this.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox checkBoxClick = (CheckBox) view;
                    checkBoxes[index].setChecked(checkBoxClick.isChecked());
                }
            });
        }
    }
}