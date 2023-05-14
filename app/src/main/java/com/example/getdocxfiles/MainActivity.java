package com.example.getdocxfiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

import java.util.ArrayList;
import java.util.Currency;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FIles_Adapter(this,getdocfiles()));

    }

    // Method To get DOCX files

    private ArrayList<String>getdocfiles(){
        ContentResolver contentResolver = getContentResolver();

        String mime = MediaStore.Files.FileColumns.MIME_TYPE +"=?";
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("docx");
        String[] args = new String[]{mimeType};
        String[] proj = {MediaStore.Files.FileColumns.DATA,MediaStore.Files.FileColumns.DISPLAY_NAME};
        String sortingorder = MediaStore.Files.FileColumns.DATE_ADDED + " DESC";

        Cursor cursor = contentResolver.query(MediaStore.Files.getContentUri("external")
                ,proj,mime,args,sortingorder);
        ArrayList<String> docFiles = new ArrayList<>();

        if (cursor != null){
            while (cursor.moveToNext()){
                int index = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);

                String path = cursor.getString(index);
                docFiles.add(path);

            }
            cursor.close();
        }
        return docFiles;
    }
}