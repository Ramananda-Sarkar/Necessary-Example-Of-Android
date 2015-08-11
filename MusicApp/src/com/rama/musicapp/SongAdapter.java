package com.rama.musicapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SongAdapter extends BaseAdapter {

	private ArrayList<Song> songs;
	private LayoutInflater songInf;

	public SongAdapter(Context c, ArrayList<Song> songs) {
		this.songs = songs;
		this.songInf = LayoutInflater.from(c);
	}

	@Override
	public int getCount() {
		return songs.size();
	}

	@Override
	public Song getItem(int position) {
		return songs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public class ViewHolder {
		TextView songs, artist;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = songInf.inflate(R.layout.song_list, null);
			holder.songs = (TextView) convertView.findViewById(R.id.song_title);
			holder.artist = (TextView) convertView
					.findViewById(R.id.song_artist);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.songs.setText(songs.get(position).getTitle());
		holder.artist.setText(songs.get(position).getArtist());

		return convertView;
	}

}
