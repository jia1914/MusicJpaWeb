package music;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlbumManager {
	
	public JSONArray getAlbumList(String searchTerm, String searchType){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MusicJpaWeb");
		EntityManager em = emFactory.createEntityManager();
		
		// Note that you are querying the object grid, not the database!
		String qry = "SELECT l.albumID FROM Album l ";
		
		if(!searchTerm.equals("")){
			if(searchType.equalsIgnoreCase("equals")){
				qry += "WHERE l.title = '" + searchTerm + "'";
			}
			else if(searchType.equalsIgnoreCase("begin")){
				qry += "WHERE l.title LIKE '" + searchTerm + "%'";
			}
			else if(searchType.equalsIgnoreCase("ends")){
				qry += "WHERE l.title LIKE '%" + searchTerm + "'";
			}
			else{
				qry += "WHERE l.title LIKE '%" + searchTerm + "%'";
			}
		}
		
		
		List<String> albumIDs = em.createQuery(qry).getResultList();
		JSONArray albumListJSON = new JSONArray();
		for(String albumID : albumIDs){
			Album l = em.find(Album.class, albumID);
			albumListJSON.put(l.toJSON());
		}
		em.close();
		emFactory.close();
		
		return albumListJSON;
	}
	
	
	public void createAlbum(String albumID, String title, String releaseDate, String coverImagePath, String recordingCompanyName, int numberOfTracks, String pmrcRating, int length) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MusicJpaWeb");
		EntityManager em = emFactory.createEntityManager();
		
		em.getTransaction().begin();
		Album l = new Album();
		
		l.setAlbumID(java.util.UUID.randomUUID().toString());
		l.setTitle(title);
		l.setReleaseDate(releaseDate);
		l.setCoverImagePath(coverImagePath);
		l.setRecordingCompanyName(recordingCompanyName);
		l.setNumberOfTracks(numberOfTracks);
		l.setPMRCRating(pmrcRating);
		l.setLength(length);
		
		em.persist(l);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
	}
	

	public void updateAlbum(String albumID, String title, String releaseDate, String coverImagePath, String recordingCompanyName, int numberOfTracks, String pmrcRating, int length) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MusicJpaWeb");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album l = em.find(Album.class, albumID);
		 
		if (!title.equals("")) {
			l.setTitle(title);
		}
		
		if (!title.equals("")) {
			l.setTitle(title);
		}
		
		if (!releaseDate.equals("")) {
			l.setReleaseDate(releaseDate);
		}
		
		if (!coverImagePath.equals("")) {
			l.setCoverImagePath(coverImagePath);
		}
		
		if (!recordingCompanyName.equals("")) {
			l.setRecordingCompanyName(recordingCompanyName);
		}
		if(numberOfTracks > 0) {
			l.setNumberOfTracks(numberOfTracks);
		}
		if(!pmrcRating.equals("")) {
			l.setPMRCRating(pmrcRating);
		}	
		if (length > 0) {
				l.setLength(length);
			}
		
		em.persist(l);
		em.getTransaction().commit();		
		em.close();
		emFactory.close();
	}
	

	public void deleteAlbum(String albumID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MusicJpaWeb");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album l = em.find(Album.class, albumID);
		em.remove(l);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
	}
	

	public Album findAlbum(String albumID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MusicJpaWeb");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album l = em.find(Album.class, albumID);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
		return l;
	}


	public Album getAlbum(String albumID){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MusicJpaWeb");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		Album l = em.find(Album.class, albumID); 
		em.close();
		emFactory.close();
		return l;
	}


}

 