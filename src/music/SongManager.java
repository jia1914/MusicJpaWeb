package music;

import java.util.List;
import javax.persistence.*;
import org.json.JSONArray;
public class SongManager {
	
	public JSONArray getSongList(String searchTerm, String searchType){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Music");
		EntityManager em = emFactory.createEntityManager();
		
		// Note that you are querying the object grid, not the database!
		String qry = "SELECT s.songID FROM Song s ";
		
		if(!searchTerm.equals("")){
			if(searchType.equalsIgnoreCase("equals")){
				qry += "WHERE s.title = '" + searchTerm + "'";
			}
			else if(searchType.equalsIgnoreCase("begin")){
				qry += "WHERE s.title LIKE '" + searchTerm + "%'";
			}
			else if(searchType.equalsIgnoreCase("ends")){
				qry += "WHERE s.title LIKE '%" + searchTerm + "'";
			}
			else{
				qry += "WHERE s.title LIKE '%" + searchTerm + "%'";
			}
		}
		
		
		List<String> songIDs = em.createQuery(qry).getResultList();
		JSONArray songListJSON = new JSONArray();
		for(String songID : songIDs){
			Song s = em.find(Song.class, songID);
			songListJSON.put(s.toJSON());
		}
		em.close();
		emFactory.close();
		
		return songListJSON;
	}
	
	
	public void createSong(String songID, String title, int length, String releaseDate, String recordDate, String filePath) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Music");
		EntityManager em = emFactory.createEntityManager();
		
		em.getTransaction().begin();
		Song s = new Song();
		
        s.setSongID(java.util.UUID.randomUUID().toString());	
		s.setTitle(title);
		s.setLength(length);
		s.setReleaseDate(releaseDate);
		s.setRecordDate(recordDate);
		s.setFilePath(filePath);
		
		em.persist(s);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
	}
	

	public void updateSong(String songID, String title, int length, String releaseDate, String recordDate, String filePath) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		
		if (!title.equals("")) {
			s.setTitle(title);
		}
		
		if (length > 0) {
			s.setLength(length);
		}
		
		if (!releaseDate.equals("")) {
			s.setReleaseDate(releaseDate);
		}
		
		if (!recordDate.equals("")) {
			s.setRecordDate(recordDate);
		}
		
		if (!filePath.equals("")) {
			s.setFilePath(filePath);
		}
		
		em.persist(s);
		em.getTransaction().commit();		
		em.close();
		emFactory.close();
	}
	

	public void deleteSong(String songID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		em.remove(s);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
	}
	

	public Song findSong(String songID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Song s = em.find(Song.class, songID);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
		return s;
	}


	public Song getSong(String songID){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		Song s = em.find(Song.class, songID);
		em.close();
		emFactory.close();
		return s;
	}


}

 
	
	
