package com.example.porus.cybertreasure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
private Button addvalue;
    private Button getvalue;
    private EditText locationname;
    private EditText couponno;
    private EditText enterlocation;
    private Firebase f;
    private TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        addvalue = (Button) findViewById(R.id.addstring);
        getvalue = (Button) findViewById(R.id.gv);
        locationname = (EditText) findViewById(R.id.ln);
        couponno = (EditText) findViewById(R.id.cn);
        enterlocation= (EditText) findViewById(R.id.el);
        t=(TextView)findViewById(R.id.tv);


        addvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = locationname.getText().toString();
                String s2 = couponno.getText().toString();
                String s3 = DateFormat.getDateTimeInstance().format(new Date());
                Firebase rootchild = f.child(s1);
                Firebase couponchild = rootchild.child("coupon no");
                Firebase currentdatetimechild = rootchild.child("date-time of addition");
                couponchild.setValue(s2);
                currentdatetimechild.setValue(s3);

            }
        });



        getvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s1=enterlocation.getText().toString();
                String sdash="https://cybertreasure-3f578.firebaseio.com/"+s1+"/coupon no";
                Firebase fdash = new Firebase(sdash);

                fdash.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        String value = dataSnapshot.getValue(String.class);
                        if(value==null)
                        {
                            t.setText("no coupon available at this location");
                        }
                        else

                        {
                            t.setText(value);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });



    }
}
