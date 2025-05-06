#include <WiFi.h>
#include <WiFiClientSecure.h>
#include <PubSubClient.h>
#include <WiFiManager.h>

#define RXD2 16  
#define TXD2 17  

const char* mqtt_server = "kangaroo.rmq.cloudamqp.com";
const int mqtt_port = 8883;
const char* mqtt_user = "mnjkmljf:mnjkmljf";
const char* mqtt_password = "DUDMCFZWJwlOWonUerM_gFwKS6NYMLfG";

const char* test_topic = "greenhouse/topic";

WiFiClientSecure espClient;
PubSubClient client(espClient);

String data = "hello";
String passwordPrefix = "#GreenHouse";

void reconnect() {
    while (!client.connected()) {
        Serial.print("🔄 Connect to RabbitMQ...");
        if (client.connect("ESP32Client", mqtt_user, mqtt_password)) {
            Serial.println("\n✅ Connected to RabbitMQ!");
            client.publish(test_topic, "ESP32 connected to RabbitMQ!");
            Serial.println("📤 Mesage sent: ESP32 connected to RabbitMQ!");
        } else {
            Serial.print("❌ Error, rc=");
            Serial.print(client.state());
            Serial.println(" Retry in 5 seconds...");
            delay(5000);
        }
    }
}

void setup() {
    Serial.begin(115200);
    Serial2.begin(9600, SERIAL_8N1, RXD2, TXD2);

    delay(1000);
    randomSeed(esp_random());

    int randomPasswordNumber = random(100000, 999999);
    String apPassword = passwordPrefix + String(randomPasswordNumber);

    Serial.println("📶 ESP32 in Access Point mode:");
    Serial.println("   ➤ SSID: GreenHouse");
    Serial.print("   ➤ Password: ");
    Serial.println(apPassword);

    WiFiManager wifiManager;

    wifiManager.resetSettings();

    wifiManager.setConnectTimeout(30);
    wifiManager.autoConnect("GreenHouse", apPassword.c_str());

    Serial.println("✅ Connected to WiFi!");

    espClient.setInsecure();

    client.setServer(mqtt_server, mqtt_port);

    reconnect();
}

void loop() {
    data = Serial2.readString();

    if (!client.connected()) {
        reconnect();
    }

    client.loop();

    if (data.length() > 0) {
        Serial.print("📩 Received data from UART: ");
        Serial.println(data);
        client.publish(test_topic, data.c_str());
    }

    delay(200);
}
