package com.raman.musicplayer;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rama.bean.Song;

public class SongAdapter extends ArrayAdapter<Song> {

	private Context context;
	private ArrayList<Song> songs;

	public SongAdapter(Context context, ArrayList<Song> songs) {
		super(context, R.layout.fragment_list);
		this.context = context;
		this.songs = songs;
	}

	@Override
	public int getCount() {
		return songs.size();
	}

	@Override
	public Song getItem(int position) {
		return songs.get(position);
	}

	static class ViewHolder {
		TextView name, album, artist;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.fragment_list, parent,
					false);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.txtname);
			holder.album = (TextView) convertView.findViewById(R.id.txtalbum);
			holder.artist = (TextView) convertView.findViewById(R.id.txtartist);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Song song = songs.get(position);
		if (song != null) {
			holder.name.setText(song.getName());
			holder.artist.setText(song.getArtist());
			holder.album.setText(song.getAlbum());
		}
		return convertView;
	}
}
