package com.greenhouse.greenhouseapp.helpers;

import static com.greenhouse.greenhouseapp.helpers.Constants.BROKER_URL;
import static com.greenhouse.greenhouseapp.helpers.Constants.CLIENT_ID;
import static com.greenhouse.greenhouseapp.helpers.Constants.PASSWORD;
import static com.greenhouse.greenhouseapp.helpers.Constants.TOPIC;
import static com.greenhouse.greenhouseapp.helpers.Constants.USERNAME;

import android.util.Log;

import com.greenhouse.greenhouseapp.HomeFragment;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttHelper {

    private MqttClient mqttClient;
    private HomeFragment homeFragment;

    public MqttHelper(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    public void connect() {
        try {
            mqttClient = new MqttClient(BROKER_URL, CLIENT_ID, new MemoryPersistence());

            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(USERNAME);
            options.setPassword(PASSWORD.toCharArray());
            options.setCleanSession(true);
            options.setAutomaticReconnect(true);

            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.d("mqtt", "Conexiune pierdută: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Log.d("mqtt", "Mesaj primit: " + new String(message.getPayload()));
                    if(homeFragment!=null){
                        homeFragment.updateUI_whenMessageReceived(new String(message.getPayload()));
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.d("mqtt", "Mesaj trimis cu succes!");
                }
            });

            mqttClient.connect(options);
            Log.d("mqtt", "Conectat la RabbitMQ!");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe() {
        try {
            mqttClient.subscribe(TOPIC, 1);
            Log.d("mqtt", "Abonat la topic: " + TOPIC);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(1);
            mqttClient.publish(TOPIC, mqttMessage);
            Log.d("mqtt", "Mesaj trimis: " + message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (mqttClient != null && mqttClient.isConnected()) {
                mqttClient.disconnect();
                Log.d("mqtt", "Deconectat de la RabbitMQ.");
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
