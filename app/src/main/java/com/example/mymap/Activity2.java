package com.example.mymap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Activity2 extends AppCompatActivity implements SensorEventListener {
    private FirebaseFirestore db;
    private Button button;
    SensorManager DeviceSensorManager;
    Sensor lightsensor;
    TextView text1;
    EditText perigrafh;
    EditText color;
    int count=0;
    double lat,lot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        DeviceSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        text1 = findViewById(R.id.textView);
        color=findViewById(R.id.editText2);
        perigrafh=findViewById(R.id.editText);
        Intent intent = getIntent();
        db = FirebaseFirestore.getInstance();
        lat=intent.getDoubleExtra("Lat",0);
        lot=intent.getDoubleExtra("Lot",0);
        button = (Button) findViewById(R.id.button1);
        System.out.println(lat+" "+lot);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //metrhsh kenvwn
                String entertext = perigrafh.getText().toString();
                String[] words = entertext.split(" ");
                System.out.println(words);



                String txt=color.getText().toString();
                int num=0;
                if(color.getText().toString().equals("") ){
                    //elegxos gia to xrwma an einai keno to pedio
                    Toast.makeText(Activity2.this, "δωσε χρωμα ",Toast.LENGTH_LONG).show();

                }
                else{

                    if (txt.matches("[a-zA-Z]+")){
                        //elegxos an einai grama sto pedio color
                        Toast.makeText(Activity2.this, "δωσε αριθμο οχι χαρακτηρες ",Toast.LENGTH_LONG).show();
                    }


                    else{
                        num = Integer.parseInt(color.getText().toString());
                        if (num > 360 || num < 0) {
                            //elegxos gia to diasthma ton arithmon
                            Toast.makeText(Activity2.this, "δωσε αριθμο απο 0-360 ",Toast.LENGTH_LONG).show();
                        }
                        else {
                            if(words.length>10){
                                //elegxos an einai perossoteres apo 10 lekseis
                                Toast.makeText(Activity2.this, "Κειμενο μεχρι 10 λεξεις ",Toast.LENGTH_LONG).show();
                            }

                            else{
                                //ginete h eisagwgh twn dedomenvn poy dvsame sth bash
                                Marker m = new Marker(lat, lot, perigrafh.getText().toString(), color.getText().toString(), text1.getText().toString());
                                db.collection("Marks").add(m).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(Activity2.this, "ολα καλα", Toast.LENGTH_SHORT).show();
                                }
                            });
                                //kleinei to fragment
                            Activity2.this.finish();
                        }}


                    }}
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        DeviceSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lightsensor = DeviceSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightsensor != null) {

            DeviceSensorManager.registerListener(this, lightsensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "not this sensor", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        switch (event.sensor.getType()) {
            case Sensor.TYPE_LIGHT:
                text1.setText("light: " + Float.toString(event.values[0]));

                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
