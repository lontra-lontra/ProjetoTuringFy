package com.pacote.customFilter;

import java.util.List;

import com.pacote.customComparator.CustomComparatorAscending;

import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class customFilterMode implements customFilter {
	
	CustomComparatorAscending comparator = new CustomComparatorAscending();
	
	
	public void filter(String key, List<Track> musicaspesquisadas) {
		
				
	}

	@Override
	public void filter(int key, List<Track> musicaspesquisadas) {
		
	}

	@Override
	public void filter(int keyInit, int keyEnd, List<Track> musicaspesquisadas) {
		
	}

}
