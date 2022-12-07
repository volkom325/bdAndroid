package com.example.myapplication1231;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS izdanie (id INTEGER,avtor TEXT, name TEXT, obyem INTEGER, language TEXT)");
        db.execSQL("INSERT OR IGNORE INTO izdanie(id,avtor, name, obyem, language) VALUES (1,'Олег','Как копать',220, 'Русский')");
        Cursor query = db.rawQuery("SELECT * FROM izdanie", null);
        TextView textView = findViewById(R.id.textView);
        textView.setText("");
        while (query.moveToNext()){
            int id = query.getInt(0);
            String avtor = query.getString(1);
            String name = query.getString(2);
            int obyem = query.getInt(3);
            String language = query.getString(4);
            textView.append("id: "+id+ "| "+ "Автор: " + avtor+ "| " + "Название книги: " + name+ "| " + "Кол-во страниц: " + obyem + "| "+ "Язык: " + language+ "\n");
            if (id ==5){
                break;
            }
        }
        query.close();
        db.close();
    }
}