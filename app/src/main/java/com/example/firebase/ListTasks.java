//package com.example.firebase;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.firebase.ui.database.FirebaseListAdapter;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.GenericTypeIndicator;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.List;
//
//public class ListTasks extends AppCompatActivity {
//
//    private FirebaseAuth mAuth;
//    private DatabaseReference myRef;
//
//    FirebaseUser user = mAuth.getInstance().getCurrentUser();
//
//    FirebaseListAdapter mAdapter;
//
//    EditText et_new_tasks;
//    Button btn_add;
//
//    ListView ListUserTasks;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_tasks);
//
//        ListUserTasks = findViewById(R.id.discr_for_task);
//
//        myRef = FirebaseDatabase.getInstance().getReference();
//
//        mAdapter = new FirebaseListAdapter<String>(this, String.class, android.R.layout.simple_list_item_1, myRef.child(user.getUid()).child("Tasks")) {
//            @Override
//            protected void populateView(View v, String s, int position) {
//                TextView text = v.findViewById(android.R.id.text1);
//                text.setText(s);
//            }
//        };
//        ListUserTasks.setAdapter(mAdapter);
//
//        btn_add = findViewById(R.id.btn_add);
//        et_new_tasks = findViewById(R.id.et_new_tasks);
//
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myRef.child(user.getUid()).child("Tasks").push().setValue(et_new_tasks.getText().toString());
//            }
//        });
//
////        FirebaseUser user = mAuth.getInstance().getCurrentUser();
////
////        myRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
////                DiscrTasks = dataSnapshot.child("Tasks").getValue(t);
////
////                updateUI();
////            }
////
////            @Override
////            public void onCancelled(DatabaseError error) {
////
////            }
////        });
//    }
////    private void updateUI(){
////        ArrayAdapter <String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, DiscrTasks);
////        ListUserTasks.setAdapter(adapter);
////    }
//}