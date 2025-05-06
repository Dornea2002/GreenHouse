#include <ArduinoJson.h>
#include <Adafruit_Sensor.h>
#include <DHT.h>
#include <Wire.h>

#define SOIL_WET 500
#define SOIL_DRY 800
#define MOISTUREPIN A0

#define LIGHTPIN A1

#define BOTTOM_LEVELPIN A2
#define MIDDLE_LEVELPIN A3
#define TOP_LEVELPIN A4
#define WATER_LEVEL_TRESHOLD 480

#define DHTPIN 2
#define DHTTYPE DHT22
DHT dht(DHTPIN, DHTTYPE);

int counter = 0;
float sum_temperature = 0;
float sum_humidity = 0;
float sum_moisture = 0;
float sum_light = 0;
float water_level_state = 0;

bool bottomWet = true;
bool middleWet = true;
bool topWet = true;

void setup() {

  Serial.begin(9600);
  pinMode(MOISTUREPIN, INPUT);
  pinMode(LIGHTPIN, INPUT);
  pinMode(BOTTOM_LEVELPIN, INPUT);
  pinMode(MIDDLE_LEVELPIN, INPUT);
  pinMode(TOP_LEVELPIN, INPUT);
  dht.begin();
}

void loop() {

  getSensorsValues();
  generateJSON();

  counter++;
  delay(2000);
}

float calculateAvg(float value) {
  return value / counter;
}

void generateJSON() {
  if (counter == 1) {
    StaticJsonDocument<200> doc;
    doc["temperature"] = String(calculateAvg(sum_temperature));
    doc["humidity"] = String(calculateAvg(sum_humidity));
    doc["moisture"] = String(calculateAvg(sum_moisture));
    doc["light"] = String(calculateAvg(sum_light));

    String levelString = "Empty";
    if (water_level_state == 1) {
      levelString = "Low";
    } else if (water_level_state == 2) {
      levelString = "Middle";
    } else if (water_level_state == 3) {
      levelString = "High";
    }

    doc["waterLevel"] = levelString;

    String jsonString;
    serializeJson(doc, jsonString);
    Serial.println(jsonString);

    counter = 0;
    sum_humidity = 0;
    sum_temperature = 0;
    sum_moisture = 0;
    sum_light = 0;
    water_level_state = 0;
  }
}

void getSensorsValues() {
  sum_humidity += dht.readHumidity();
  sum_temperature += dht.readTemperature();

  sum_moisture += analogRead(MOISTUREPIN);

  int lightValue = analogRead(LIGHTPIN);
  float lux = lightValue * (5.0 / 1023.0);
  sum_light += (lux * 200.0);

  int valLow = analogRead(BOTTOM_LEVELPIN);
  int valMiddle = analogRead(MIDDLE_LEVELPIN);
  int valTop = analogRead(TOP_LEVELPIN);

  processWaterLevel(valLow, valMiddle, valTop);
}

void processWaterLevel(int valLow, int valMiddle, int valTop){
  if (valLow < WATER_LEVEL_TRESHOLD) bottomWet = false;
  if (valMiddle < WATER_LEVEL_TRESHOLD) middleWet = false;
  if (valTop < WATER_LEVEL_TRESHOLD) topWet = false;

  if (bottomWet && middleWet && topWet) water_level_state = 3;
  else if (bottomWet && middleWet) water_level_state = 2;
  else if (bottomWet) water_level_state = 1;
  else water_level_state = 0;
}