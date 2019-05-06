package com.example.witsup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class testListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_uploaded_files);
        mRecyclerView = (RecyclerView) findViewById(R.id.recylerview_filepathh);
        new FirebaseDataHelper().getFileNames(new FirebaseDataHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<FilePathh> filePathh, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, testListActivity.this, filePathh, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
