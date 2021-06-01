package com.example.bestyoutube.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestyoutube.R;
import com.example.bestyoutube.pojos.YoutubeVideo;

import java.util.List;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeVideoViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(YoutubeVideo youtubeVideo);
    }

    private List<YoutubeVideo> youtubeVideos;
    private OnItemClickListener listener;

    public YoutubeVideoAdapter(List<YoutubeVideo> youtubeVideos, OnItemClickListener listener) {
        this.youtubeVideos = youtubeVideos;
        this.listener = listener;
    }

    public class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvDescription;

        public YoutubeVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }

    @NonNull
    @Override
    public YoutubeVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mainrv_item, parent, false);

        return new YoutubeVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeVideoViewHolder holder, int position) {
        YoutubeVideo youtubeVideo = youtubeVideos.get(position);

        holder.tvTitle.setText(youtubeVideo.getTitle());
        holder.tvDescription.setText(youtubeVideo.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(youtubeVideo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return youtubeVideos.size();
    }


}
