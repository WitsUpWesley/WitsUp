package com.example.witsup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private FilePathhAdapter mFilePathhAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<FilePathh> filePathh, List<String> keys){


        mContext=context;
        mFilePathhAdapter= new FilePathhAdapter(filePathh, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mFilePathhAdapter);
    }


    class FileItemView extends RecyclerView.ViewHolder{
        private TextView tvFileName, tvFileLink;
        private String key;


        public FileItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.filename_item, parent, false));

            tvFileName= (TextView) itemView.findViewById(R.id.textViewFileName);

            tvFileLink= (TextView) itemView.findViewById(R.id.textViewLink);
            tvFileLink.setHeight(tvFileName.getHeight());
           // System.out.println("fucking did it budddy^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+tvFileName.getText());

        }
        public void bind(FilePathh fp, String key){
            tvFileName.setText(fp.filePathhName);
            tvFileLink.setText(fp.filePathhURL);
            //Linkify.addLinks(tvFileLink, Linkify.WEB_URLS);
            this.key=key;
            System.out.println("fucking did it budddy^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+tvFileName.getText());
            tvFileName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadbtn();
                }
            });

        }

        public void downloadbtn(){
            System.out.println("do downlaod here$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+tvFileLink.getText());
            Uri uri = Uri.parse(tvFileLink.getText().toString()); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(intent);

        }

    }
    class FilePathhAdapter extends  RecyclerView.Adapter<FileItemView>{
        private List<FilePathh> mFilePathList;
        private List<String> mKeys;

        public FilePathhAdapter(List<FilePathh> mFilePathList, List<String> mKeys) {
            this.mFilePathList = mFilePathList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public FileItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new FileItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull FileItemView holder, int position) {
                holder.bind(mFilePathList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mFilePathList.size();
        }
    }
}
