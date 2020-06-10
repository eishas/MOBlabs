package com.example.lab3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DbActivity extends AppCompatActivity
{
    private DbContext dbContext;
    private TextView getAllTextView;
    private TextView emptyDBTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        dbContext = new DbContext(this);

        getAllTextView = this.findViewById(R.id.allRows);
        emptyDBTextView = this.findViewById(R.id.emptyDB);
        getAllRows();
    }

    private void getAllRows() {
        SQLiteDatabase database = dbContext.getWritableDatabase();
        Cursor cursor = database.query(DbContext.TABLE_NAME, null, null, null, null, null, null);
        //database.delete(DbContext.TABLE_NAME, null, null);
        String allRows = "";
        if (cursor.moveToFirst()) {
            emptyDBTextView.setText("DataBase");
            int idIndex = cursor.getColumnIndex(DbContext.KEY_ID);
            int dateIndex = cursor.getColumnIndex(DbContext.KEY_DATE);
            int resultIndex = cursor.getColumnIndex(DbContext.KEY_RESULT);

            do {
                allRows += String.format("%-3d %-30s %-40s\n",
                        cursor.getInt(idIndex),
                        "Date:\n" + cursor.getString(dateIndex),
                        "\nPassword :" + cursor.getString(resultIndex));
//                System.out.println(allRows);

            } while (cursor.moveToNext());
            getAllTextView.setText(allRows);
        } else {
            emptyDBTextView.setText("DB is empty");
        }
        cursor.close();

        dbContext.close();
    }
}
