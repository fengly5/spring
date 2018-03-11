/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.service;

//import com.google.gson.Gson;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.json.JsonObject;


import spring.validacion.RespuestaFCM;
/**
 * Clase para enviar notificaciones a través del servicio FCM
 * @author jcpm0
 */
public class PushNotificationHelper {
public final static String AUTH_KEY_FCM = "AAAAlCEF9UQ:APA91bHkOJCCqKA8Gx95bWdEfbADQEnmPh0jtS2DmjwEBNm-9tBa6D1tnCgU5MPm6wrco4apZmvv-R1CNJ0fFrsWn3wCo6ESy79o5m2AeA8VDD81n_s_hk2Nkpl0bRbd4e0iGU7lgmXy";
public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
/**
 * Método para enviar notificacion de cliente
 * @param deviceToken 
 * @param mensaje
 * @return
 * @throws IOException
 * @throws JSONException 
 */
public static String sendPushNotification(String deviceToken,String mensaje)
        throws IOException {
    String result = "fallo";
    URL url = new URL(API_URL_FCM);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 RespuestaFCM response = new RespuestaFCM();
    conn.setUseCaches(false);
    conn.setDoInput(true);
    conn.setDoOutput(true);

    conn.setRequestMethod("POST");
    conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
    conn.setRequestProperty("Content-Type", "application/json");

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    JsonNodeFactory factory = new JsonNodeFactory(false);
    JsonFactory jsonFactory = new JsonFactory();
    JsonGenerator generator = jsonFactory.createGenerator(outputStream);
    generator.writeStartObject();
    generator.writeObjectField("to", deviceToken.trim());
    generator.writeObjectFieldStart("notificacion");
    generator.writeObjectField("body", mensaje);
    generator.writeObjectField("title", "titulo");
    generator.writeEndObject();
    generator.writeEndObject();
    generator.close();

    try {
        OutputStreamWriter wr = new OutputStreamWriter(
                conn.getOutputStream());
        wr.write(outputStream.toString());
        wr.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
           
            
//            Gson resp = new Gson();
//            response = resp.fromJson(output, RespuestaFCM.class);
            System.out.println(response.toString());
        }
        if (response.getFailure() == 0){
        
          result = "exito";}
    } catch (IOException e) {
        e.printStackTrace();
        result = "fallo";
    }
    System.out.println("GCM Notification is sent successfully");

    return result;

}
public static String sendPushNotificationTopic(String topic,String message)
        throws IOException {
    String result = "";
    URL url = new URL(API_URL_FCM);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setUseCaches(false);
    conn.setDoInput(true);
    conn.setDoOutput(true);

    conn.setRequestMethod("POST");
    conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
    conn.setRequestProperty("Content-Type", "application/json");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    JsonNodeFactory factory = new JsonNodeFactory(false);
    JsonFactory jsonFactory = new JsonFactory();
    JsonGenerator generator = jsonFactory.createGenerator(outputStream);
    generator.writeStartObject();
    generator.writeObjectField("to", topic.trim());
    generator.writeObjectFieldStart("notificacion");
    generator.writeObjectField("message", message.trim());
    generator.writeObjectField("data", "info");
    generator.writeEndObject();
    generator.writeEndObject();
    generator.close();
 
    try {
        OutputStreamWriter wr = new OutputStreamWriter(
                conn.getOutputStream());
        wr.write(outputStream.toString());
        wr.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        result = "exito";
    } catch (IOException e) {
        e.printStackTrace();
        result = "fallo";
    }
    System.out.println("GCM Notification is sent successfully");

    return result;

}
}