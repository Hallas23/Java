package app;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

public class Callback implements MqttCallback{
	int status = 0;	
	double temp;
	double hum;
	 public void connectionLost(Throwable throwable) {
		    System.out.println("Connection to MQTT broker lost!");
		  }

		  public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
			String res= new String(mqttMessage.getPayload()); 
			JSONObject jo = new JSONObject(res);
			JSONObject jo2 = jo.getJSONObject("AM2301");
			
			 temp = jo2.getDouble("Temperature");
			 hum = jo2.getDouble("Humidity");
			System.out.println("Temp: " + temp + "  Hum: " + hum);
			System.out.println(s);
			if (hum > 60) {
				Opgave7.BlowerControl("1");
			}
			else {
				Opgave7.BlowerControl("0");
			}
		  }

		  public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		    // not used in this example
		  }		  
} 