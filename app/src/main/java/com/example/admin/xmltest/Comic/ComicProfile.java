package com.example.admin.xmltest.Comic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.xmltest.R;
import com.example.admin.xmltest.models.Truyen;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ComicProfile extends AppCompatActivity {
    private ImageView imgThum;
    private TextView tvName, tvDescription;
    private ListView lvChapter;
    private DatabaseReference mDatabase;
    private String description;
    private List<String> listChapter;
    private Truyen truyen;
    private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_profile);
        addControls();
        addData();
        catchData();
        setView();
        getData();
        addEvents();
    }

    private void addControls(){
        imgThum = (ImageView) findViewById(R.id.imgThumbnailComic);
        tvName = (TextView) findViewById(R.id.tvNameComic_profile);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        lvChapter = (ListView) findViewById(R.id.lvChapter);
    }

    private void addData(){
        listChapter = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listChapter);
        lvChapter.setAdapter(arrayAdapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void catchData(){
        final Intent intent = getIntent();
        truyen = (Truyen) intent.getSerializableExtra("TRUYEN");
    }

    private void setView(){
        tvName.setText(truyen.getName());
        tvDescription.setText(truyen.getDescription());
        Picasso.with(ComicProfile.this).load(truyen.getThumbnail()).into(imgThum);
        tvDescription.setMovementMethod(new ScrollingMovementMethod());
    }

    private void getData(){
        mDatabase.child("Comic").child(truyen.getName()).child("description")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        description = dataSnapshot.getValue().toString();
                        tvDescription.setText(description);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        mDatabase.child("Comic").child(truyen.getName()).child("chuongs").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String idChapter = dataSnapshot.getKey().toString();
                String chapter = "Chương " + (Integer.parseInt(idChapter)+1);
                listChapter.add(chapter);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addEvents(){
        lvChapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(ComicProfile.this, ComicPlayer.class);
                intent1.putExtra("TRUYEN",truyen.getName());
                intent1.putExtra("CHUONG",position);
                startActivity(intent1);
            }
        });
    }
}
