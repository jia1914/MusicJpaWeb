	package music;

	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Map;
	import java.sql.PreparedStatement;
	import java.sql.Connection;
	import javax.persistence.*;

	import org.json.JSONException;
	import org.json.JSONObject;

//annotation structure based on MusicJPA code example
@Entity
@Table (name="artist")
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name="artist_id")
	private String artistID;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="band_name")
	private String bandName;
	
	@Column(name="bio")
	private String bio;
	
// USE GETTER AND SETTER
	
	public String getArtistID() {
		return artistID;
	}
	
	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getBandName() {
		return bandName;
	}
	
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	

	public String getBio() {
		return bio;
	}
	

	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public JSONObject toJSON(){
		JSONObject artistJson = new JSONObject();
		try {
			artistJson.put("artist_id", this.artistID);
			artistJson.put("first_name", this.firstName);
			artistJson.put("last_name", this.lastName);
			artistJson.put("band_name", this.bandName);
			artistJson.put("bio", this.bio);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return artistJson;
		
	}
	

}