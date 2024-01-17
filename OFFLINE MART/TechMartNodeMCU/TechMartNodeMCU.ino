/* Controlling LED using Firebase console by CircuitDigest(www.circuitdigest.com) */
#include <ESP8266WiFi.h>                                                // esp8266 library
#include <FirebaseArduino.h>                                             // firebase library
#define FIREBASE_HOST "techmart-67117.firebaseio.com"                         // the project name address from firebase id
#define FIREBASE_AUTH "soNtRp9cigEkkx1OJjDMBPLU1qBcwORWsDbvQc4f"                    // the secret key generated from firebase
#define WIFI_SSID "AKASH"                                          // input your home or public wifi name 
#define WIFI_PASSWORD "28739540"                                    //password of wifi ssid
int Lapcount=0;
String fireStatus = "";                                                     // led status received from firebase
int led = D3;  
const int R1=D0;
const int R2=D1;
const int R3=D2;
int val1=0;
int val2=0;
int val3=0;

int p1,p2,p3,pr1,pr2,pr3;

String Product1;
String Product2;
String Product3;
String Product01;
String Product02;
String Product03;
int data=0;// for external led
void setup() {
  Serial.begin(9600);
  delay(1000);
  pinMode(LED_BUILTIN, OUTPUT);      
  pinMode(led, OUTPUT);
  pinMode(R1,INPUT);
  pinMode(R2,INPUT);
  pinMode(R3,INPUT);                 
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);                                      //try to connect with wifi
  Serial.print("Connecting to ");
  Serial.print(WIFI_SSID);
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("Connected to ");
  Serial.println(WIFI_SSID);
  Serial.print("IP Address is : ");
  Serial.println(WiFi.localIP());                                                      //print local IP address
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);                                        //connect to firebase
  
  Firebase.setString("/Products/22/left","50");
  Firebase.setString("/Products/45/left","60"); 
  Firebase.setString("/Products/33/left","70");

  Firebase.setString("/Products/22/sold","50");
  Firebase.setString("/Products/45/sold","40");
  Firebase.setString("/Products/33/sold","30");

  
  Product1=Firebase.getString("/Products/22/left");
  Product01=Firebase.getString("/Products/22/sold");
  p1=Product1.toInt();
  pr1=Product01.toInt();

  Product2=Firebase.getString("/Products/45/left");
  Product02=Firebase.getString("/Products/45/sold");
  p2=Product2.toInt();
  pr2=Product02.toInt();

  Product3=Firebase.getString("/Products/33/left");
  Product03=Firebase.getString("/Products/33/sold");
  p3=Product3.toInt();
  pr3=Product03.toInt();

}
void loop() 
{
  val1=digitalRead(R1);
  if(val1==LOW)
  {
    
  p1=p1-1;
  pr1=pr1+1;
  Serial.print("Lenovo Ideapad s540 = ");
  Serial.print(p1);
  Serial.println(pr1);

  String P1 = String(p1);
  String Po1 = String(pr1);
  
  //Product1=Firebase.getInt("/Products/22/left");
  //Product01=Firebase.getInt("/Products/22/sold");
  
  Firebase.setString("/Products/22/left",P1);
  Firebase.setString("/Products/22/sold",Po1);
  
  }
  val2=digitalRead(R2);
  if(val2==LOW)
  {
    
  p2=p2-1;
  pr2=pr2+1;
  Serial.print("Samsung TV = ");
  Serial.print(p2);
  Serial.println(pr2);

  //Product2=Firebase.getInt("/Products/45/left");
  //Product02=Firebase.getInt("/Products/45/sold");
  
  String P2 = String(p2);
  String Po2 = String(pr2);
  
  Firebase.setString("/Products/45/left",P2);
  Firebase.setString("/Products/45/sold",Po2);
  
  }
  val3=digitalRead(R3);
  if(val3==LOW)
  {
    
  p3=p3-1;
  pr3=pr3+1;
  
  Serial.print("OnePlus 7 PRO = ");
  Serial.print(p3);
  Serial.println(pr3);

  //Product3=Firebase.getInt("/Products/33/left");
  //Product03=Firebase.getInt("/Products/33/sold");
  
  String P3 = String(p3);
  String Po3 = String(pr3);
  
  Firebase.setString("/Products/33/left",P3);
  Firebase.setString("/Products/33/sold",Po3);
  
  }
}
