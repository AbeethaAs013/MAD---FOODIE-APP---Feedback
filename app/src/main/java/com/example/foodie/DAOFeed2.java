package com.example.foodie;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOFeed2
{
    private DatabaseReference databaseReference;
    public DAOFeed2()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Feed2.class.getSimpleName());
    }

    public Task<Void> add(Feed2 feed2)
    {
        return databaseReference.push().setValue(feed2);
    }
}
