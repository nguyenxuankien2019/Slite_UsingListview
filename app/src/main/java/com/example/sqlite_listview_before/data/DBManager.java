package com.example.sqlite_listview_before.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;

import com.example.sqlite_listview_before.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    private static final String database_name = "student_management";
    private static final String table_name = "studentKids";

    private Context context;

    public DBManager(Context context) {
        super(context, database_name, null, 1);
        this.context = context;
        Log.d("kien", "DBManager: ");
    }

    private String SQLQuery = "CREATE TABLE " + table_name + "(id integer primary key, name TEXT, phone TEXT, address TEXT, email TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        Log.d("kien", "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("kien", "onUpgrade: ");
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", student.getmName());
        values.put("phone", student.getMphoneNumber());
        values.put("address", student.getmAddress());
        values.put("email", student.getmEmail());

        db.insert(table_name, null, values);
        db.close();
        Log.d("kien", "addStudent: ");

    }

    public List<Student> getAllstudent() {

        List<Student> studentList = new ArrayList<>();
        String selecQuery = "SELECT * FROM " + table_name;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selecQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setmID(cursor.getInt(0));
                student.setmName(cursor.getString(1));
                student.setMphoneNumber(cursor.getString(2));
                student.setmAddress(cursor.getString(3));
                student.setmEmail(cursor.getString(4));
                studentList.add(student);
            } while (cursor.moveToNext());
        }
//            cursor.close();
        db.close();
        return studentList;

    }

    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", student.getmName());
        values.put("phone", student.getMphoneNumber());
        values.put("address", student.getmAddress());
        values.put("email", student.getmEmail());
        return db.update(table_name, values, "id=?", new String[]{String.valueOf(student.getmID())});

    }

    public int deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(table_name, "id=?", new String[]{String.valueOf(id)});
    }
}
