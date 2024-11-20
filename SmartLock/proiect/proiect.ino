#include <SPI.h>
#include <MFRC522.h>
#include <Servo.h> 
#include "SoftwareSerial.h"

#define SS_PIN 10  //pinii pentru RFID Reader
#define RST_PIN 9
MFRC522 mfrc522(SS_PIN, RST_PIN);   // creare MFRC522 pentru RFID Reader.
 
const int PIN_RED   = 5; //pinii pentru led rgb
const int PIN_GREEN = 6;
const int PIN_BLUE  = 7;

Servo myservo;  // creare obiect de tip servo pentru controlul servo motor-ului 
int pos = 0;    // variabila in care stocam pozitia

const int buzzer = 4;

const int soundsensor=A5;  //variabila prin care legam senzorul de zgomot de pin-ul A5 
const int noise = 600;  //variabila cu care vom face comparatii cu zgomotul actual
boolean is_on = false; //variabila prin care determinam daca led-ul este deja aprins sau nu

const byte rxPin = 0;  //pinii pentru bluetooth
const byte txPin = 1;
SoftwareSerial BTSerial(rxPin, txPin); // RX TX

void setup() 
{
  Serial.begin(9600);   // Initiate a serial communication
  
  pinMode(PIN_RED,   OUTPUT);
  pinMode(PIN_GREEN, OUTPUT);
  pinMode(PIN_BLUE,  OUTPUT);

  pinMode(soundsensor,INPUT);

  pinMode(buzzer, OUTPUT);
  
  myservo.attach(3);  // atasam obiectul creat la pinul 3 de pe placuta

  SPI.begin();      // Initiate  SPI bus
  mfrc522.PCD_Init();   // Initiate MFRC522
  Serial.println("Approximate your card to the reader...");
  
  BTSerial.begin(115200);
  delay(1000);
  pinMode(rxPin, INPUT);
  pinMode(txPin, OUTPUT);

  BTSerial.println("Someone tried to enter in ur house."); //test pentru Bluetooth
  
}

void loop() 
{

  int soundsensitivity = analogRead(soundsensor);



  if (soundsensitivity == 1) { //daca detectam sunet
    if (is_on == true) { // daca ledul este deja activat , il stingem
      analogWrite(PIN_RED,   0);
      analogWrite(PIN_GREEN, 0);
      analogWrite(PIN_BLUE,  0);
      delay(1000);
      digitalWrite(buzzer,HIGH);
      delay(5000);; //alarma
      is_on = false;
    }
    else { // altfel, il activam
      analogWrite(PIN_RED,  255);
      analogWrite(PIN_GREEN, 0);
      analogWrite(PIN_BLUE,  0);
      delay(1000);

      // digitalWrite(buzzer,HIGH); //alarma
      // delay(200);
      // for(int i=0 ; i<=30;i++){
      // digitalWrite(buzzer,LOW);
      // delay(100);
      // digitalWrite(buzzer,HIGH);
      // delay(100);

      is_on = true;
    }
  }

// for(int i=0;i<5;i++){
//   if (soundsensitivity >= noise) {
//     analogWrite(PIN_RED,   255);
//     analogWrite(PIN_GREEN, 0);
//     analogWrite(PIN_BLUE,  0);
//     delay(100);
//     analogWrite(PIN_RED,   0);
//     analogWrite(PIN_GREEN, 0);
//     analogWrite(PIN_BLUE,  0);
//   } else {
//     analogWrite(PIN_RED,   0);
//     analogWrite(PIN_GREEN, 0);
//     analogWrite(PIN_BLUE,  0);
//     delay(100);
//   }
// }

  digitalWrite(buzzer,HIGH);
  delay(1000);
 
  // scanare pentru carduri noi
  if ( ! mfrc522.PICC_IsNewCardPresent()) 
  {
    return;
  }
  
  // citiri unul dintre carduri
  if ( ! mfrc522.PICC_ReadCardSerial()) 
  {
    return;
  }
   
  
  Serial.print("UID tag :");
  
  //formatarea ID-urilor pentru RFID tag
  
  String content= "";
  byte letter;
  for (byte i = 0; i < mfrc522.uid.size; i++) 
  {
     Serial.print(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " ");
     Serial.print(mfrc522.uid.uidByte[i], HEX);
     content.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " "));
     content.concat(String(mfrc522.uid.uidByte[i], HEX));
  }

  
  Serial.println();
  Serial.print("Message : ");
  
  content.toUpperCase();
  if (content.substring(1) == "89 C5 FF 62") //acesta este UID-ul tag-ului corect
  {
  digitalWrite(buzzer,HIGH);
  delay(100);
   
 
    
    delay(10); // delay aditional ca sa ne asiguram ca buzzer-ul este off
    analogWrite(PIN_RED,   52);
    analogWrite(PIN_GREEN, 168);
    analogWrite(PIN_BLUE,  83);
    
    Serial.println("Authorized access");
   
    myservo.write(0);

    analogWrite(buzzer,LOW);
    
    for (pos = 0; pos <= 180; pos += 1) { // Step motorul de la 0 grade la 180 grade
        myservo.write(pos);       //scrie pozitia si-i spune sa mearga la pozitie
        delay(15);                //asteapta un timp mic pentru a realiza asta
            if(pos==180){
              analogWrite(buzzer,HIGH);
            }
    }
    analogWrite(buzzer,HIGH);
    delay(7000);
   
    for(pos= 180 ; pos >= 0 ; pos -=1) { //de la 180 grade se intoarce in pozitia initiala
      myservo.write(pos);
      delay(15);
    }

    analogWrite(PIN_RED,   0);
    analogWrite(PIN_GREEN, 0);
    analogWrite(PIN_BLUE,  0);
    
  }
 
 else   {  //daca este alt TAG
    analogWrite(PIN_RED,   255);
    analogWrite(PIN_GREEN, 0);
    analogWrite(PIN_BLUE,  0);

    Serial.println(" Access denied");
    BTSerial.println(" Someone tried to enter in your house.");

    digitalWrite(buzzer,HIGH);
    delay(200);
    for(int i=0 ; i<=30;i++){
    digitalWrite(buzzer,LOW);
    delay(100);
    digitalWrite(buzzer,HIGH);
    delay(100);
  }

    analogWrite(PIN_RED,   0);
    analogWrite(PIN_GREEN, 0);
    analogWrite(PIN_BLUE,  0);

 }


}



