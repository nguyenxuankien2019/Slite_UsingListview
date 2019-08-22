package com.example.sqlite_listview_before;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlite_listview_before.adapter.CustomAdapter;
import com.example.sqlite_listview_before.data.DBManager;
import com.example.sqlite_listview_before.model.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtName, edtPhone, edtAdress, edtEmail, edtId;
    private Button btnSave, btnUpdate, btnShowStudentList,btnClear;
    private ListView lvStudent;
    private DBManager dbManager;
    private CustomAdapter customAdapter;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);

        studentList = dbManager.getAllstudent();
        initWidget();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = createStudent();
                if (student != null) {
                    dbManager.addStudent(student);
                }
                studentList.clear();
                studentList.addAll(dbManager.getAllstudent());
                setAdapter();
                clearEditText();
                Toast.makeText(MainActivity.this, "you clicked button save!", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setmID(Integer.parseInt(String.valueOf(edtId.getText())));
                student.setmName(edtName.getText() + "");
                student.setMphoneNumber(edtPhone.getText() + "");
                student.setmAddress(edtAdress.getText() + "");
                student.setmEmail(edtEmail.getText() + "");

                int result = dbManager.updateStudent(student);
                if (result > 0) {
                    studentList.clear();
                    studentList.addAll(dbManager.getAllstudent());
                    customAdapter.notifyDataSetChanged();
                }
                btnSave.setEnabled(true);
                btnUpdate.setEnabled(true);
                btnShowStudentList.setEnabled(true);
                clearEditText();
            }

        });
        btnShowStudentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentList.clear();
                studentList.addAll(dbManager.getAllstudent());
                setAdapter();
            }
        });
        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Student student = studentList.get(position);
                dbManager.deleteStudent(student.getmID());
                int result = dbManager.deleteStudent(student.getmID());
                if (result > 0) {
                    Toast.makeText(MainActivity.this, "Xoa that bai", Toast.LENGTH_SHORT).show();
                } else {
                    studentList.clear();
                    studentList.addAll(dbManager.getAllstudent());
                    customAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = studentList.get(position);
                edtId.setText(String.valueOf(student.getmID()));
                edtName.setText(student.getmName());
                edtPhone.setText(student.getMphoneNumber());
                edtAdress.setText(student.getmAddress());
                edtEmail.setText(student.getmEmail());
                btnSave.setEnabled(false);
                btnShowStudentList.setEnabled(false);
                btnUpdate.setEnabled(true);

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEditText();
                btnSave.setEnabled(true);
                btnShowStudentList.setEnabled(true);
            }
        });

    }

    public void clearEditText() {
        edtId.setText("");
        edtName.setText("");
        edtPhone.setText("");
        edtAdress.setText("");
        edtEmail.setText("");
    }

    public void initWidget() {
        edtId = (EditText) findViewById(R.id.edtId);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtAdress = (EditText) findViewById(R.id.edtAddress);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnShowStudentList = (Button) findViewById(R.id.btnShowStudentlist);
        btnClear = (Button) findViewById(R.id.btnClear);

        lvStudent = (ListView) findViewById(R.id.listview);
    }

    private Student createStudent() {
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();
        String address = edtAdress.getText().toString();
        String email = edtEmail.getText().toString();
        Student student = new Student(name, phone, address, email);
        return student;
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.item_list_students, studentList);
            lvStudent.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            lvStudent.setSelection(customAdapter.getCount() - 1);
        }

    }
}
