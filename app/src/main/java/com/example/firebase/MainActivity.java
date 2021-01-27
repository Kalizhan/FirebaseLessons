package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    private EditText editText, etd;
    private Button button;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;
//    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;
//
//    private EditText ETemail, ETpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.et);
        etd = findViewById(R.id.etd);
        button = findViewById(R.id.btn);
        recyclerView = findViewById(R.id.list);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Tasks").push();
                Map<String, Object> map = new HashMap<>();
                map.put("id", databaseReference.getKey());
                map.put("title", editText.getText().toString());
                map.put("desc", etd.getText().toString());

                databaseReference.setValue(map);
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();

//        mAuth = FirebaseAuth.getInstance();
//
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null){
//                    Intent intent = new Intent(MainActivity.this, ListTasks.class);
//                    startActivity(intent);
//                }else{
//
//                }
//            }
//        };
//        ETemail = findViewById(R.id.et_email);
//        ETpassword = findViewById(R.id.et_password);
//
//        findViewById(R.id.btn_sign_in).setOnClickListener(this);
//        findViewById(R.id.btn_registration).setOnClickListener(this);
//
//
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user != null) {
//            Intent intent = new Intent(MainActivity.this, ListTasks.class);
//            startActivity(intent);
//        }
    }

    private void fetch() {
        Query query = FirebaseDatabase.getInstance().getReference().child("Tasks");

        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(query, new SnapshotParser<Model>() {
            @NonNull
            @Override
            public Model parseSnapshot(@NonNull DataSnapshot snapshot) {
                return new Model(snapshot.child("id").getValue().toString(),
                        snapshot.child("title").getValue().toString(),
                        snapshot.child("desc").getValue().toString());
            }
        }).build();

        adapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model model) {
                holder .setTxtTitle(model.getmTitle());
                holder .setTxtDesc(model.getmDesc());

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout root;
        public TextView txtTitle, txtDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtTitle = itemView.findViewById(R.id.list_title);
            txtDesc = itemView.findViewById(R.id.list_desc);
        }

        public void setTxtTitle(String string) {
            txtTitle.setText(string);
        }


        public void setTxtDesc(String string) {
            txtDesc.setText(string);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.btn_sign_in){
//            signing(ETemail.getText().toString(), ETpassword.getText().toString());
//        }else if (v.getId() == R.id.btn_registration){
//            registration(ETemail.getText().toString(), ETpassword.getText().toString());
//        }
//    }
//
//    public void signing(String email, String password){
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(MainActivity.this, "Sign in is complete!", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(MainActivity.this, "Sign in is not complete!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//
//    public void registration(String email, String password){
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(MainActivity.this, "Registration is complete!", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(MainActivity.this, "Registration is not complete!", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });
//    }
}