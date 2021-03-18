package com.example.cloudchat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG1" ;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://cloudchat-43fb4-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("message");
    Button button;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn);
        editText = findViewById(R.id.txt_message);
        ArrayList<String> list = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycler);
        AdapterForMyRecycler adapter = new AdapterForMyRecycler(this, list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                if (msg.equals("")) {
                    Toast.makeText(MainActivity.this, "Сообщение пусто", Toast.LENGTH_LONG).show();
                    return;
                }
                if (msg.length() > 150) {
                    Toast.makeText(MainActivity.this,
                            "Сообщение не может быть длиннее 150 символов",
                            Toast.LENGTH_LONG).show();
                }
                myRef.push().setValue(msg);
                editText.setText("");
            }
        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String msg = dataSnapshot.getValue(String.class);
                list.add(msg);
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(list.size());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}