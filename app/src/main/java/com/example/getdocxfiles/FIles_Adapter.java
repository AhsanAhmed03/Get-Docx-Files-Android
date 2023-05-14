package com.example.getdocxfiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class FIles_Adapter extends RecyclerView.Adapter<FIles_Adapter.Files_ViewHolder> {

    Context context;
    List<String> docxFiles;

    public FIles_Adapter (Context context, List<String> docxFiles){
        this.context = context;
        this.docxFiles = docxFiles;
    }

    @NonNull
    @Override
    public Files_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.display_files_layout,parent,false);
        return new Files_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Files_ViewHolder holder, int position) {
        String path = docxFiles.get(position);
        File docfile = new File(path);
        String filename = docfile.getName();
        String filepath = docfile.getPath();

        holder.fileName.setText(filename);
        holder.filepath.setText(filepath);

    }

    @Override
    public int getItemCount() {
        return docxFiles.size();
    }

    static class Files_ViewHolder extends RecyclerView.ViewHolder{

        TextView fileName,filepath;


        public Files_ViewHolder(@NonNull View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.file_name);
            filepath = itemView.findViewById(R.id.file_path);
        }
    }
}
