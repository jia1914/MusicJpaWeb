package music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.persistence.*;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

//annotation structure based on MusicJPA code example
@Entity
@Table (name="album")
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name="album_id")
	private String albumID;
	
	@Column(name="title")
	private String title;
	
	@Column(name="release_date")
	private String releaseDate;
	
	@Column(name="cover_image_path")
	private String coverImagePath;
	
	@Column(name="recording_company_name")
	private String recordingCompanyName;
	
	@Column(name="number_of_tracks")
	private int numberOfTracks;
	
	@Column(name="PMRC_rating")
	private String pmrcRating;
	
	@Column(name="length")
	private int length;
	
	private HashMap<String, Song> albumSongs;
	
	
	public Album() {
		 this.albumID = java.util.UUID.randomUUID().toString();
		 this.albumSongs = new HashMap<String, Song>();
	}
	
	//add song
	public void addSong(Song song) {
		this.albumSongs.put(song.getSongID(), song);
	}
	//delete song
	public void deleteSong(String songID) {
		this.albumSongs.remove(songID);
	}
	
	//delete song 
	public void deleteSong(Song song) {
		this.albumSongs.remove(song.getSongID());
	}
	
	
	//USE GETTER AND SETTER
	public String getAlbumID() {
		return albumID;
	}
	
	public void setAlbumID(String albumID) {
		this.albumID = albumID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}
	
	public String coverImagePath() {
		return coverImagePath;
	}
	
	public void setRecordingCompanyName(String recordingCompanyName) {
		this.recordingCompanyName = recordingCompanyName;
	}
	

	public String getRecordingCompanyName() {
		return recordingCompanyName;
	}
	

	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}
	
    public int getNumberOfTracks() {
		return numberOfTracks;
    	
    }
    
    public void setPMRCRating(String pmrcRating) {
    	this.pmrcRating = pmrcRating;
    }
    
    public String getPMRCRating() {
    	return pmrcRating;
    }
    public void setLength(int length) {
    	this.length = length;
    }
    
    public int length() {
    	return length;
    }
    
    public Map<String, Song>getAlbumSongs(){
    	return albumSongs;
    }
    
    
	public JSONObject toJSON(){
		JSONObject albumJson = new JSONObject();
		try {
			albumJson.put("album_id", this.albumID);
			albumJson.put("title", this.title);
			albumJson.put("release_date", this.releaseDate);
			albumJson.put("cover_image_path", this.coverImagePath);
			albumJson.put("recording_company_name", this.recordingCompanyName);
			albumJson.put("number_of_tracks", this.numberOfTracks);
			albumJson.put("PMRC_rating", this.pmrcRating);
			albumJson.put("length", this.length);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return albumJson;
		
	}
	

}