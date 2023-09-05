package com.mastercoding.firestore_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Initialize firestore
        val db = Firebase.firestore
        val textv:TextView = findViewById(R.id.tttview)
        //creating collections for users
        val user_collection = db.collection("Users")
        //creating documents
        val user1 = hashMapOf(
            "name" to "Jack",
            "lastn" to "rechard",
            "born" to "1990"
        )
        val user2 = hashMapOf(
            "name" to "Tom",
            "lastn" to "Alex",
            "born" to "2000"
        )

//Adding documents to collections
        user_collection.document("user1").set(user1)
        user_collection.document("user2").set(user2)


//Read or receive from firestore
      val docRef  = db.collection("Users").document("user1")
        docRef.get().addOnSuccessListener { document ->
            if (document != null){
                //textv.text ="${document.data}"
                textv.text = "${document.data?.get("name")}"

                //Get all documents from a collection
                var allDocuments:String = ""
                db.collection("Users").get().addOnSuccessListener { result ->
                    for (document in result){
                        //textv.text ="${document.data}"
                        allDocuments += "${document.data}"
                        allDocuments +="/n"
                    }
                    textv.text = ""+ allDocuments

                }


        }


        }


    }
}