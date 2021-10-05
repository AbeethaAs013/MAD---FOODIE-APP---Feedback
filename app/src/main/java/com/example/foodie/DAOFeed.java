package com.example.foodie;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAOFeed
{
    private DatabaseReference databaseReference;
    public DAOFeed()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Feed.class.getSimpleName());
    }

    public Task<Void> add(Feed feed)

    {
        return databaseReference.push().setValue(feed);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }

}

