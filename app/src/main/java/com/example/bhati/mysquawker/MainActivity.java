package com.example.bhati.mysquawker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bhati.mysquawker.following.FollowingActivity;
import com.example.bhati.mysquawker.provider.MySquawkContract;
import com.example.bhati.mysquawker.provider.MySquawkProvider;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    SquawkAdapter adapter;

    String[] projection={MySquawkContract.COLUMN_AUTHOR,
            MySquawkContract.COLUMN_AUTHOR_KEY,
            MySquawkContract.COLUMN_DATE,
            MySquawkContract.COLUMN_MESSAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG,"onCreate Main Activity");
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter=new SquawkAdapter(this);
        recyclerView.setAdapter(adapter);

        Log.v(TAG,"key="+FirebaseInstanceId.getInstance().getToken());

        getSupportLoaderManager().initLoader(5,null,this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:
                Intent i=new Intent(this, FollowingActivity.class);
                startActivity(i);
                return true;
            default:
                Log.v(TAG,"check the switch statement in onOptionSelected");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                MySquawkProvider.SquawkMessages.CONTENT_URI,
                projection,
                MySquawkContract.createSelectionForCurrentFollowers(PreferenceManager.getDefaultSharedPreferences(this)),
                null,
                MySquawkContract.COLUMN_DATE+" DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
