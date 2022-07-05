package com.pacote.customFilter;

import java.util.List;

import se.michaelthelin.spotify.model_objects.specification.Track;

public interface customFilter {
	
	public void filter(String key, List<Track> musicaspesquisadas);
	public void filter(int key, List<Track> musicaspesquisadas);
	public void filter(int keyInit, int keyEnd, List<Track> musicaspesquisadas);

}
