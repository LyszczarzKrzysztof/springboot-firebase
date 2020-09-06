package com.example.springbootfirebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseStart {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {


        FileInputStream serviceAccount =
                new FileInputStream("java-firebase-e128e-firebase-adminsdk-31jcn-64e867078c.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://java-firebase-e128e.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        //save data
       Firestore db = FirestoreClient.getFirestore();

        // Create a Map to store the data we want to set
        Map<String, Object> docData = new HashMap<>();
        docData.put("name", "Los Angeles");
        docData.put("state", "CA");
        docData.put("religion", "catholic");
        docData.put("country", "USA");
        docData.put("regions", Arrays.asList("west-coast","socal","near-mexico"));
// Add a new document (asynchronously) in collection "cities" with id "LA"
        ApiFuture<WriteResult> future = db.collection("cities").document("LA").set(docData);
// ...
// future.get() blocks on response
        System.out.println("Update time : " + future.get().getUpdateTime());

    }
}
