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
    private ArrayList<String> list = new ArrayList<String>();

    public Adapter(Context context, int count) {
        this.count = count;
        String formatString = String.format("%%0%dd", (int) Math.log10(this.count - 1) + 1);
        this.checkBoxes = new CheckBox[this.count];
        for (int i = 0; i < this.count; i++) {
            final int index = i;
            this.checkBoxes[index] = new CheckBox(context);
            this.checkBoxes[index].setText(String.format(formatString, index));
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

    public boolean isListEmpty() {
        return this.list.size() == 0;
    }

    public String getList() {
        Collections.sort(this.list);
        return String.join(",", this.list);
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
                    if (checkBoxClick.isChecked()) {
                        list.add(text);
                    } else {
                        list.remove(text);
                    }
                }
            });
        }
    }
}