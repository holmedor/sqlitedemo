package com.example.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         */
        try {
            SQLiteDatabase bd = openOrCreateDatabase("BDdemo", MODE_PRIVATE, null);

            //ELIMINAR LA TABLA
            bd.execSQL("DROP TABLE producto");

            //CREAR LA TABLA
//            String sql = "CREATE TABLE IF NOT EXISTS producto(nombre VARCHAR,stock INTEGER)";
            String sql = "CREATE TABLE IF NOT EXISTS producto(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR,stock INTEGER)";
            bd.execSQL(sql);
            //INSERTAR DATOS EN LA TABLA
            bd.execSQL("INSERT INTO producto(nombre, stock) VALUES('iPad',10)");
            bd.execSQL("INSERT INTO producto(nombre, stock) VALUES('iPhone',15)");
            bd.execSQL("INSERT INTO producto(nombre, stock) VALUES('Galaxy Tab 10',20)");
            bd.execSQL("INSERT INTO producto(nombre, stock) VALUES('iMac',5)");
//            bd.execSQL("UPDATE producto set nombre = 'Tablet Windows 10' WHERE nombre = 'iPad'");
//          bd.execSQL("DELETE from producto WHERE stock= 20");

            bd.execSQL("UPDATE producto set stock = 30 WHERE id = 1");
            bd.execSQL("DELETE from producto WHERE id= 3");

            //RECORRER LA TABLA
//            Cursor cursor = bd.rawQuery("SELECT nombre, stock FROM producto", null);
            Cursor cursor = bd.rawQuery("SELECT id, nombre, stock FROM producto", null);
            if (cursor.moveToFirst()) {
                String datos = "";
                while (cursor != null) {
//                    datos = "Producto: " + cursor.getString(0) + " Stock:" + cursor.getInt(1);
                    datos = "ID: " + cursor.getInt(0)+ " Producto: " + cursor.getString(1) + " Stock:" + cursor.getInt(2);
                    Log.i("SQLiteDemo", datos);
                    cursor.moveToNext();
                }
                ;

            } else {
                Toast.makeText(this, "No existen registros de productos", Toast.LENGTH_SHORT).show();
            }
        } catch(Exception e){
            e.printStackTrace();
            }
    }
}