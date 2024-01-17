#define SCL_PIN 15
#define SDO_PIN 14
#include "SPI.h"
#include <LiquidCrystal.h>
// SPI library
#include "MFRC522.h" // RFID library (https://github.com/miguelbalboa/rfid)
const int pinRST = 9;
const int pinSDA = 10;
const int BUZZpin=8; 
double limit1=300000;
double limit2=30000;
double bill = 0;
int quant=0;
const int rs = 7, en = 6, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
const int S2=16;

const int S3=17;
const int S1=18;
const int led=19;
byte Key;
int P1=0;
int P2=0;
int P3=0;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);
MFRC522 mfrc522(pinSDA, pinRST);
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  SPI.begin(); // open SPI connection
  mfrc522.PCD_Init(); // Initialize Proximity Coupling Device (PCD) 
//  pinMode(LEDpin,OUTPUT);
  pinMode(BUZZpin,OUTPUT);
  pinMode(S1,OUTPUT);
  pinMode(S2,OUTPUT);  
  pinMode(S3,OUTPUT);
  pinMode(led,OUTPUT);
  pinMode(SCL_PIN, OUTPUT);  
  pinMode(SDO_PIN, INPUT); 
  digitalWrite(S1,HIGH);
  digitalWrite(S2,HIGH);
  digitalWrite(S3,HIGH);
  lcd.begin(20,4);
  lcd.display();
  lcd.setCursor(5,1);
  lcd.print("Welcome To");
  lcd.setCursor(6,2);  
  lcd.print("TechMart");
  delay(3000);
  Serial.println("Welcome To TechMart");
  lcd.noDisplay();
  lcd.display();
  lcd.begin(20,4);
  lcd.setCursor(0,0);
  lcd.print("Put your product");
  lcd.setCursor(0,1);
  lcd.print("near the sensor");
  lcd.setCursor(0,2);
  lcd.print("for billing");
  Serial.println("Put your product near the sensor for billing");
  delay(3000);
  lcd.noDisplay();
  }
void bills()
{
  String content= "";
  byte letter;
   if (mfrc522.PICC_IsNewCardPresent()) { // (true, if RFID tag/card is present ) PICC = Proximity Integrated Circuit Card
    if (mfrc522.PICC_ReadCardSerial()) { // true, if RFID tag/card was read
      Serial.print("RFID TAG ID:");
      for (byte i = 0; i < mfrc522.uid.size; i++)
      { // read id (in parts)
        Serial.print(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " ");
        Serial.print(mfrc522.uid.uidByte[i], HEX); // print id as hex values
        Serial.print(" ");
       content.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " "));
       content.concat(String(mfrc522.uid.uidByte[i], HEX));
      }
      Serial.println(); // Print out of id is complete.
      delay(2500);
      Serial.println();
    }
   }
  content.toUpperCase();
  if (content.substring(1) == "52 06 C6 44") //change here the UID of the card/cards that you want to give access
  {
    lcd.begin(20,4);
    lcd.display();
    lcd.setCursor(0,0);
    lcd.print("Samsung OLED TV ");
    lcd.setCursor(0,1);
    lcd.print("- Rs.84,000");
    tone(BUZZpin, 1000, 500);
    digitalWrite(S1,LOW);
    digitalWrite(led,HIGH);
    digitalWrite(S1,HIGH);
    delay(500);
    digitalWrite(led,LOW);
    bill=bill+84000;
    ++P1;
    Serial.print("Samsung ");
    Serial.println(P1);
    Serial.print("Quantity");
    Serial.println(++quant);
    Serial.print("Bill = ");
    Serial.println(bill);
    lcd.setCursor(0,2);
    lcd.print("Total Bill = ");
    lcd.setCursor(0,3);
    lcd.print("- Rs.");
    lcd.print(bill);
    delay(3000);
    lcd.noDisplay();
  }
 if (content.substring(1) == "B2 E9 D3 39") //change here the UID of the card/cards that you want to give access
  {
   lcd.begin(20,4);
    lcd.display();
    lcd.setCursor(0,0);
    lcd.print("Lenovo ideaPad s540");
    lcd.setCursor(0,1);
    lcd.print("- Rs.62,000");
    tone(BUZZpin, 1000, 500);
    digitalWrite(S2,LOW);
    digitalWrite(led,HIGH);
    digitalWrite(S2,HIGH);
    delay(500);
    digitalWrite(led,LOW);
    bill=bill+62000;
    ++P2;
    Serial.print("Lenovo ");
    Serial.println(P2);
    Serial.print("Quantity");
    Serial.println(++quant);
    Serial.print("Bill = ");
    Serial.println(bill);
    lcd.setCursor(0,2);
    lcd.print("Total Bill = ");
    lcd.setCursor(0,3);
    lcd.print("- Rs.");
    lcd.print(bill);
    delay(3000);
    lcd.noDisplay();
  }
 if (content.substring(1) == "AB D3 2B 83") //change here the UID of the card/cards that you want to give access
  {
    lcd.begin(20,4);
    lcd.display();
    lcd.setCursor(0,0);
    lcd.print("Oneplus 7 PRO");
    lcd.setCursor(0,1);  
    lcd.print("- Rs.46,000");
    tone(BUZZpin, 1000, 500);
    digitalWrite(S3,LOW);
    digitalWrite(led,HIGH);
    digitalWrite(S3,HIGH);
    delay(500);
    digitalWrite(led,LOW);
    bill=bill+46000;
    ++P3;
    Serial.print("OnePlus ");
    Serial.println(P3);
    Serial.print("Quantity");
    Serial.println(++quant);
    Serial.print("Bill = ");
    Serial.println(bill);
    lcd.setCursor(0,2);
    lcd.print("Total Bill = ");
    lcd.setCursor(0,3);
    lcd.print("- Rs.");
    lcd.print(bill);
    delay(3000);
    lcd.noDisplay();
  }
 if(content.substring(1) == "D7 88 6B B2" || content.substring(1) == "83 52 9B 1D")
  {
    Serial.println("CARD DETECTED");
    if (content.substring(1) == "83 52 9B 1D") //change here the UID of the card/cards that you want to give access
  {
    int s=0;
    int c=4;
    lcd.begin(20,4);
    lcd.display();
    lcd.setCursor(0,0);
    lcd.print("Enter your PIN :");
    Serial.println("Enter your PIN :");
    for(int i=1;i<=16;i++)
    {
    Key = Read_Keypad();
    delay(500);
    /* If a key has been pressed output it to the serial port */
    if (Key&&c>0)
    {
    Serial.print(Key);
    lcd.print(Key); 
    s=s*10+Key;
    //lcd.print(s);
    delay(500);
    c--;
    }
     }
     delay(2000);
     lcd.noDisplay();
     if(s!=2368)
     {  
      lcd.begin(20,4);
      lcd.display();
      lcd.setCursor(0,0);
      lcd.print("Wrong Pin Entered");
     }
     else
     {
    lcd.begin(20,4);
    lcd.display();
    lcd.setCursor(0,0);
    lcd.print("Total Bill = ");
    lcd.setCursor(0,1);
    lcd.print("- Rs.");
    lcd.print(bill);
    delay(2000);
    Serial.println();
    Serial.print("Total Bill = Rs.");
    Serial.println(bill);
    lcd.noDisplay();
    lcd.begin(20,4);
    lcd.display();
    lcd.setCursor(0,0);
    lcd.print("Previous Balance = ");
    lcd.setCursor(2,1);
    lcd.print(limit1);
    tone(BUZZpin, 500, 1000);
    Serial.print("Previous Balance = Rs.");
    Serial.println(limit1);
    if(limit1>bill)
    {
    limit1=limit1-bill;
    lcd.setCursor(0,2);
    lcd.print("Remaining Balance = ");
    lcd.setCursor(2,3);
    lcd.print(limit1);
    tone(BUZZpin, 500, 1000);
    Serial.print("Remaining Balance = Rs.");
    Serial.println(limit1);
    }
    else
    {
      lcd.setCursor(0,2);
      lcd.println("Insufficient Balance");
    }
  }
 }
  if (content.substring(1) == "D7 88 6B B2") //change here the UID of the card/cards that you want to give access
  {
    int s=0;
    int c=4;
    lcd.begin(20,4);
    lcd.display();
    lcd.setCursor(0,0);
    lcd.print("Enter your PIN :");
    Serial.print("Enter your PIN :");
    for(int i=1;i<=16;i++)
    {
    Key = Read_Keypad();
    delay(500);
    /* If a key has been pressed output it to the serial port */
    if (Key&&c>0)
    {
    Serial.print(Key);
    lcd.print(Key); 
    s=s*10+Key;
    //lcd.print(s);
    delay(500);
    c--;
    }
     }
     delay(2000);
     lcd.noDisplay();
     if(s!=2345)
     {  
      lcd.begin(20,4);
      lcd.display();
      lcd.setCursor(0,0);
      lcd.print("Wrong Pin Entered");
     }
     else
     {
    lcd.begin(20,4);
    lcd.display();
    lcd.setCursor(0,0);
    lcd.print("Total Bill = ");
    lcd.setCursor(0,1);
    lcd.print("- Rs.");
    lcd.print(bill);
    delay(2000);
    Serial.println();
    Serial.print("Total Bill = Rs.");
    Serial.println(bill);
    lcd.noDisplay();
    lcd.begin(20,4);
    lcd.display();
    lcd.setCursor(0,0);
    lcd.print("Previous Balance = ");
    lcd.setCursor(2,1);
    lcd.print(limit2);
    tone(BUZZpin, 500, 1000);
    Serial.print("Previous Balance = Rs.");
    Serial.println(limit2);
    if(limit2>bill)
    {
    limit2=limit2-bill;
    lcd.setCursor(0,2);
    lcd.print("Remaining Balance = ");
    lcd.setCursor(2,3);
    lcd.print(limit2);
    Serial.print("Remaining Balance = Rs.");
    Serial.println(limit2);
    }
    else
    {
      lcd.setCursor(0,2);
      lcd.println("Insufficient Balance");
    }
  }
  }
 }
}
void loop() {
  bills();
}
byte Read_Keypad(void)
{
 byte Count;
 byte Key_State = 0;
 /* Pulse the clock pin 16 times (one for each key of the keypad) 
    and read the state of the data pin on each pulse */
 for(Count = 1; Count <= 16; Count++)
 {
   digitalWrite(SCL_PIN, LOW); 
  /* If the data pin is low (active low mode) then store the 
      current key number */
   if (!digitalRead(SDO_PIN))
     Key_State = Count; 
     digitalWrite(SCL_PIN, HIGH);
 }  
 return Key_State; 
} 
