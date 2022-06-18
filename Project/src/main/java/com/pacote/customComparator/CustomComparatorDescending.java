package com.pacote.customComparator;

import java.util.Comparator;
import java.util.List;

import com.pacote.controllers.EditorDePlaylists;

import se.michaelthelin.spotify.enums.Modality;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class CustomComparatorDescending {
	
	private static EditorDePlaylists editor = new EditorDePlaylists();
	final public String[] types = {"Acousticness", "Danceability", "Energy", "Instrumentalness", "Key", "Liveness", "Loudness", "Speechiness", "Tempo", "Valence", "Mode"};
	public List<Comparator<Track>> compara;
	
	public CustomComparatorDescending() {
		compara.add(comparaPorAcousticness);
		compara.add(comparaPorDanceability);
		compara.add(comparaPorEnergy);
		compara.add(comparaPorInstrumentalness);
		compara.add(comparaPorKey);
		compara.add(comparaPorLiveness);
		compara.add(comparaPorLoudness);
		compara.add(comparaPorSpeechiness);
		compara.add(comparaPorTempo);
		compara.add(comparaPorValence);
		compara.add(comparaPorMode);
	}
	
	private static Comparator<Track> comparaPorAcousticness = new Comparator<>(){
		public int compare(Track mus1, Track mus2) {
			String[] ids = {mus1.getId(), mus2.getId()}; 
			List<AudioFeatures> listaDeFt = editor.getAudioFeatures(ids);
			return listaDeFt.get(1).getAcousticness().compareTo(listaDeFt.get(0).getAcousticness());		
		}	
	};
	
	private static Comparator<Track> comparaPorDanceability = new Comparator<>() {
		public int compare(Track mus1, Track mus2) {
			String[] ids = {mus1.getId(), mus2.getId()}; 
			List<AudioFeatures> listaDeFt = editor.getAudioFeatures(ids);
			return listaDeFt.get(1).getDanceability().compareTo(listaDeFt.get(0).getDanceability());		
		}		
	};
	
	private static Comparator<Track> comparaPorEnergy = new Comparator<>() {
		public int compare(Track mus1, Track mus2) {
			String[] ids = {mus1.getId(), mus2.getId()}; 
			List<AudioFeatures> listaDeFt = editor.getAudioFeatures(ids);
			return listaDeFt.get(1).getEnergy().compareTo(listaDeFt.get(0).getEnergy());		
		}		
	};
		
	private static Comparator<Track> comparaPorInstrumentalness  = new Comparator<>() {
		public int compare(Track mus1, Track mus2) {
			String[] ids = {mus1.getId(), mus2.getId()}; 
			List<AudioFeatures> listaDeFt = editor.getAudioFeatures(ids);
			return listaDeFt.get(1).getInstrumentalness().compareTo(listaDeFt.get(0).getInstrumentalness());		
		}		
	};
	
	private static Comparator<Track> comparaPorKey  = new Comparator<>() {
		public int compare(Track mus1, Track mus2) {
			String[] ids = {mus1.getId(), mus2.getId()}; 
			List<AudioFeatures> listaDeFt = editor.getAudioFeatures(ids);
			return listaDeFt.get(1).getKey().compareTo(listaDeFt.get(0).getKey());		
		}		
	};
	
	private static Comparator<Track> comparaPorLiveness  = new Comparator<>() {
		public int compare(Track mus1, Track mus2) {
			String[] ids = {mus1.getId(), mus2.getId()}; 
			List<AudioFeatures> listaDeFt = editor.getAudioFeatures(ids);
			return listaDeFt.get(1).getLiveness().compareTo(listaDeFt.get(0).getLiveness());		
		}		
	};
	
	private static Comparator<Track> comparaPorLoudness  = new Comparator<>() {
		public int compare(Track mus1, Track mus2) {
			String[] ids = {mus1.getId(), mus2.getId()}; 
			List<AudioFeatures> listaDeFt = editor.getAudioFeatures(ids);
			return listaDeFt.get(1).getLoudness().compareTo(listaDeFt.get(0).getLoudness());		
		}		
	};
	
	private static Comparator<Track> comparaPorSpeechiness  = new Comparator<>() {
		public int compare(Track mus1, Track mus2) {
			String[] ids = {mus1.getId(), mus2.getId()}; 
			List<AudioFeatures> listaDeFt = editor.getAudioFeatures(ids);
			return listaDeFt.get(1).getSpeechiness().compareTo(listaDeFt.get(0).getSpeechiness());		
		}		
	};
	
	private static Comparator<Track> comparaPorTempo  = new Comparator<>() {
		public int compare(Track mus1, Track mus2) {
			String[] ids = {mus1.getId(), mus2.getId()}; 
			List<AudioFeatures> listaDeFt = editor.getAudioFeatures(ids);
			return listaDeFt.get(1).getTempo().compareTo(listaDeFt.get(0).getTempo());		
		}		
	};
	
	private static Comparator<Track> comparaPorValence  = new Comparator<>() {
		public int compare(Track mus1, Track mus2) {
			String[] ids = {mus1.getId(), mus2.getId()}; 
			List<AudioFeatures> listaDeFt = editor.getAudioFeatures(ids);
			return listaDeFt.get(1).getValence().compareTo(listaDeFt.get(0).getValence());		
		}		
	};
	
	private static Comparator<Track> comparaPorMode  = new Comparator<>() {
		public int compare(Track mus1, Track mus2) {
			String[] ids = {mus1.getId(), mus2.getId()};
			List<AudioFeatures> listaDeFt = editor.getAudioFeatures(ids);
			if(listaDeFt.get(0).getMode().equals(listaDeFt.get(1).getMode()))
				return 0;
			else if(listaDeFt.get(1).getMode().equals(Modality.MAJOR))
				return 1;
			else
				return -1;	
		}		
	};

}
