package com.example.sqlite_listview_before.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sqlite_listview_before.R;
import com.example.sqlite_listview_before.model.Student;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resourse;
    private List<Student> studentList;

    public CustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resourse = resource;
        this.studentList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_students, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvId = (TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);
            viewHolder.tvAdress = (TextView) convertView.findViewById(R.id.tv_address);
            viewHolder.tvEmail = (TextView) convertView.findViewById(R.id.tv_email);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        Student student = studentList.get(position);
        viewHolder.tvId.setText(String.valueOf(student.getmID()));
        viewHolder.tvName.setText(student.getmName());
        viewHolder.tvPhone.setText(student.getMphoneNumber());
        viewHolder.tvAdress.setText(student.getmAddress());
        viewHolder.tvEmail.setText(student.getmEmail());
        return convertView;
    }

    public class ViewHolder {
        private TextView tvId;
        private TextView tvName;
        private TextView tvPhone;
        private TextView tvAdress;
        private TextView tvEmail;
    }
}
