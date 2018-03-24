package com.example.csio;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class PrimeRun extends Activity implements  Runnable  {


        private static String IP;

        private static String portNumber ;

        private  int port;

        private static final String debugString = "debug";

        private Socket socket = null;

        private  Thread t = null;

        private String threadName;

        private Context context;

        public int dataToTransfer;

        private  boolean threadComplete;

        private  boolean connection;

        private boolean threadSuspended;



    BufferedWriter bw;

        public PrimeRun()
        {

        }

        public PrimeRun(String thName)
        {
            this.threadName = thName;
        }



    public void start()
        {

            if(t==null)
            {
                t = new Thread(this,threadName);
                t.start();
            }
        }


       @Override
        public void run() {

            String debugString = "debug";

            Socket socket = null;

            t= Thread.currentThread();

            try
            {

                t.sleep(2000);

                synchronized(this) {

                while (threadSuspended)
                    wait();
            }
        } catch (InterruptedException e){
    }



            if (t.toString().contains("sendData"))
            {
                  if(connection)
                  {
                      synchronized (t) {
                          t.setName("connect");
                          t.start();
                      }
                  }


                    if (getIP().equals("") && getPortNumber().equals("")) {
                        Toast.makeText(getApplicationContext(), "Your IP or Port Number has issues", Toast.LENGTH_LONG).show();
                    } else {
                        try {

                            Log.i(debugString, "Attempting to connect to server");

                            port = Integer.parseInt(portNumber);

                            socket = new Socket(IP, port);

                            connection = true;

                            Log.i(debugString, "Connection Establish");

                            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            if (dataToTransfer == 1) {
                                bw.write("HVAC On\t" + dataToTransfer +"\n");
                            } else if (dataToTransfer == 2) {
                                bw.write("HVAC OFF\t" + dataToTransfer+"\n");
                            } else if (dataToTransfer == 3) {
                                bw.write("Alarm On\t" + dataToTransfer+"\n");
                            } else if (dataToTransfer == 4) {
                                bw.write("Alarm Off\t" + dataToTransfer+"\n");
                            } else if (dataToTransfer == 5) {
                                bw.write("Door open\t" + dataToTransfer+"\n");
                            } else if (dataToTransfer == 6) {
                                bw.write("Door close\t" + dataToTransfer+"\n");
                            }


                            bw.newLine();
                            bw.flush();


                        } catch (IOException e) {
                            System.out.println(e);
                            connection = false;

                        } finally {
                            threadComplete = true;
                        }

                    }



            }
            else if (t.toString().contains("connect"))
            {

                if (connection) {

                    try {
                        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        if (dataToTransfer == 1) {
                            bw.write("HVAC On\t" + dataToTransfer +"\n");
                        } else if (dataToTransfer == 2) {
                            bw.write("HVAC OFF\t" + dataToTransfer+"\n");
                        } else if (dataToTransfer == 3) {
                            bw.write("Alarm On\t" + dataToTransfer+"\n");
                        } else if (dataToTransfer == 4) {
                            bw.write("Alarm Off\t" + dataToTransfer+"\n");
                        } else if (dataToTransfer == 5) {
                            bw.write("Door open\t" + dataToTransfer+"\n");
                        } else if (dataToTransfer == 6) {
                            bw.write("Door close\t" + dataToTransfer+"\n");
                        }


                        bw.newLine();
                        bw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                else
                    {
                    if (getIP().equals("") && getPortNumber().equals("")) {
                        Toast.makeText(getApplicationContext(), "Your IP or Port Number has issues", Toast.LENGTH_LONG).show();
                    } else {
                        try {


                            Log.i(debugString, "Attempting to connect to server");

                            //      Toast.makeText(null,"Attempting to connect",Toast.LENGTH_SHORT).show();
                            port = Integer.parseInt(portNumber);

                            socket = new Socket(IP, port);

                            connection = true;

                            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            bw.write("\t" + dataToTransfer);
                            bw.newLine();
                            bw.flush();


                        } catch (IOException ee) {
                            System.out.println(ee);
                            connection = false;

                        } finally {
                            threadComplete = true;
                        }


                    }

                    //send message to server


                }


            }

        }



    public String getThreadName() {
            return threadName;
        }



    public int getDataToTransfer() {
        return dataToTransfer;
    }

    public void setDataToTransfer(int dataToTransfer) {
        this.dataToTransfer = dataToTransfer;
    }

    public static String getIP() {
        return IP;
    }

    public static void setIp(String IP) {
        PrimeRun.IP = IP;
    }

    public static String getPortNumber() {
        return portNumber;
    }

    public static void setPortNumber(String portNumber) {
        PrimeRun.portNumber = portNumber;
    }
}



