package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Stetho
        Stetho.initializeWithDefaults(this);

        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        /* Database */
        DBAdapter db= new DBAdapter(this);
        db.open();
        //db.insert("food", "food_id, food_name, food_manufactor_name", "NULL, 'Ham', 'Gilde'");

        //Count rows in food
        int numberRows = db.count("food");

        if(numberRows < 1 ){
            //Run Setup
            db.insert("food",
                    "food_id, food_name, food_manufactor_name, food_serving_size, food_serving_mesurment, food_energy_calculated",
                    "NULL, 'Egg, whole, cooked, hard-boiled', 'Prior', '136.0', 'g', '211'");

            db.insert("food",
                    "food_id, food_name, food_manufactor_name, food_serving_size, food_serving_mesurment, food_energy_calculated",
                    "NULL, 'Stake', 'Glide', '106.0', 'g', '232'");
        }

        //Close Database
        db.close();
    }
}
