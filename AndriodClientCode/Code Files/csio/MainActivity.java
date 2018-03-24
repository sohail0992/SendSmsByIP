package com.example.csio;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private  boolean isHVACOn = false;

    private  boolean isAlarmOn = false;

    private  boolean isDoorOn = false;

    private boolean isStart = false;


    //buttons

    Button HVAC;

    Button alarm;

    Button door;

    Button ipButton;

    //variable

    private int icon;

    private String text;


    //text view


    EditText ipEditText;

    EditText portEditText;

    //instance to client

   PrimeRun ad;

    private int valueToPass;

    //ip validator

    private static final Pattern IP_ADDRESS =
            Pattern.compile("^((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])\\.){0,3}"+
                    "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])){0,1}$");

/*
    private TextView HVACView;

    private  TextView alarmView;

    private  TextView doorView;

    private  TextView  lightView;

*/

    public MainActivity()
    {
       isHVACOn = isDoorOn = isAlarmOn = false;

         icon = 0;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        //assign id and initialize button

        HVAC = (Button) findViewById(R.id.HVAC);

        door = (Button) findViewById(R.id.door);

        alarm = (Button) findViewById(R.id.alarm);

        ipButton = (Button) findViewById(R.id.ipButton);

        ipEditText = (EditText) findViewById(R.id.ip);

        portEditText = (EditText) findViewById(R.id.port);




        /*
        //assign id and initialize textView

        lightView = (TextView) findViewById(R.id.light);

        HVACView = (TextView) findViewById(R.id.HVAC);

        doorView = (TextView) findViewById(R.id.door);

        alarmView = (TextView) findViewById(R.id.alarm);

        */




        ipEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


            @Override
            public void beforeTextChanged(CharSequence s,int start,int count,int after) {

            }

            private String mPreviousText = "";
            @Override
            public void afterTextChanged(Editable s) {
                if(IP_ADDRESS.matcher(s).matches()) {
                    mPreviousText = s.toString();
                } else {
                    Toast.makeText(getApplicationContext(),"Your IP is InCorrect, Try adding . e.g 192.168.1.14",Toast.LENGTH_SHORT).show();

                    s.replace(0, s.length(), mPreviousText);
                }
            }
        });


        ipButton.setOnClickListener(new View.OnClickListener()
                                    {
                                        public void onClick(View v)
                                        {
                                            Matcher matcher = IP_ADDRESS.matcher(ipEditText.getText());


                                                String ip = ipEditText.getText().toString();

                                                String portNum = portEditText.getText().toString();


                                                PrimeRun con = new PrimeRun("connect");






                                            if(ip.length() == 0)
                                            {
                                                Toast.makeText(getApplicationContext(),"Assign IP First",Toast.LENGTH_SHORT).show();
                                                isStart=false;
                                            }
                                            else
                                            {
                                                 if(matcher.matches()) {
                                                     PrimeRun.setIp(ip);
                                                     Toast.makeText(getApplicationContext(),"Your Ip \t"+ip+"\t is Assigned",Toast.LENGTH_SHORT).show();
                                                     isStart=true;

                                                     if(portNum.length()==0) {
                                                         PrimeRun.setPortNumber("4444");
                                                         isStart=true;
                                                     }
                                                     else
                                                     {
                                                         PrimeRun.setPortNumber(portNum);
                                                         Toast.makeText(getApplicationContext(),"Your Port Number \t"+portNum+"\t is Assigned",Toast.LENGTH_SHORT).show();
                                                          isStart = true;
                                                     }

                                                 }
                                                 else
                                                 {
                                                     Toast.makeText(getApplicationContext(),"Your IP is InCorrect, Try adding . e.g 192.168.1.14",Toast.LENGTH_LONG).show();
                                                     isStart =false;
                                                 }


                                                 if(isStart)
                                                 {


                                                 }




                                            }


                                        }
                                    }

        );




    }

    public void  HVACEvent(View v)
    {
        //for HVAC on the value will be 1 and for HVAC off the value will be 2
        if(ipEditText.getText().length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Assign IP First",Toast.LENGTH_LONG).show();

        }
        else {
            if (isHVACOn) {
                isHVACOn = false;

                icon = R.drawable.fan_on;

                text = "HVAC On";

                valueToPass=1;

            } else {
                isHVACOn = true;

                icon = R.drawable.fan_off;

                text = "HVAC Off";

                valueToPass = 2;

            }

            Drawable top = getResources().getDrawable(icon);
            HVAC.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
            ad = new PrimeRun("sendData");
            ad.setDataToTransfer(valueToPass);
            ad.start();
            //send data to server

            HVAC.setText(text);

        }
    }



    public void  alarmEvent(View v)
    {
        //for alaram on the value will be 3 and for alarm off the value will be 4

        if(ipEditText.getText().length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Assign IP First",Toast.LENGTH_LONG).show();

        }
        else {
            if (isAlarmOn) {
                isAlarmOn = false;

                text = "Alarm On";

                icon = R.drawable.alarm_on;

                valueToPass = 3;

            } else {
                isAlarmOn = true;

                text = "Alarm Off";

                icon = R.drawable.alarm_off;

                valueToPass = 4;

            }


            Drawable top = getResources().getDrawable(icon);
            alarm.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
            ad = new PrimeRun("sendData");
            ad.setDataToTransfer(getValueToPass());
            ad.start();
            //send data to server

            alarm.setText(text);


        }

    }


    public void  doorEvent(View v)
    {

        //for door open the value will be 5 and for close the value will be 6
        if(ipEditText.getText().length() == 0)
        {
            Toast.makeText(getApplicationContext(),"Assign IP First",Toast.LENGTH_LONG).show();

        }
        else {
            if (isDoorOn) {
                isDoorOn = false;

                text = "Door Open";

                icon = R.drawable.door_open;

                valueToPass = 5;

            } else {
                isDoorOn = true;

                text = "Door Close";

                icon = R.drawable.door_close;

                valueToPass =6;

            }


            Drawable top = getResources().getDrawable(icon);
            door.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
            ad = new PrimeRun("sendData");
            ad.setDataToTransfer(getValueToPass());
            ad.start();
            //send data to server

            door.setText(text);

        }
    }

    public void changePort(View v)
    {


    }

    public int getValueToPass() {
        return valueToPass;
    }

    public void setValueToPass(int valueToPass) {
        this.valueToPass = valueToPass;
    }
}
