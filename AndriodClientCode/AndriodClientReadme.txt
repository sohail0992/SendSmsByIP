

Note. I provided the apk of the project you can just simple send it to your mobile and install it



Create Andriod project in eclips

MainActivity.java

Inside the code Files folder and then in csio,

Copy the code of the MainActivity.java in your Main Activity

Create a class PrimeRun.java

then copy the code of PrimeRun.java int your class

Make sure to register your PrimeRun in AndriodManifast.xml by writing

this under Activity closing tag.

 <activity android:name=".PrimeRun"></activity>

Also on top of application openning tag give permission for the internet

 <uses-permission android:name="android.permission.INTERNET"></uses-permission>

copy the interface xml code into your activity_main. I provided the values folder 
which contains strings , colors, values and dimens . so you have to copy this
into your values directory or you can edit your values according to mine.

Running

build clean and run the project. Now an interface will appear with edit text on 
top asking your ip address. 

A screen will appear with edit text for ip and port . Port is by default 4444 no need to change.
Put  ipv4 ip in ip edittext.


Now after assigning ip you can send message to the servre by clicking the button
like HVAC ,  door and alarm on /off


NOte: by default port is 4444 but you can change it, remember if you change port in app you have to change
it on java server also. Which means both must be same
