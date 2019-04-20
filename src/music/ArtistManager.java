package music;

import java.util.List;

import javax.persistence.*;

import org.json.JSONArray;

public class ArtistManager {
	
	public JSONArray getArtistList(String searchTerm, String searchType){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MusicJpaWeb");
		EntityManager em = emFactory.createEntityManager();
		
		// Note that you are querying the object grid, not the database!
		Query q = em.createQuery("SELECT a.artistID FROM Artist a WHERE a.bandName LIKE ?1");
		if(!searchTerm.equals("")) {
			if(searchType.equalsIgnoreCase("equals")) {
				q.setParameter(1, searchTerm);
			} 
			else if(searchType.equalsIgnoreCase("begins")) {
				q.setParameter(1, searchTerm + "%");				
			} 
			else if(searchType.equalsIgnoreCase("ends")) {
				q.setParameter(1, "%" + searchTerm);					
			} 
			else if (searchType.equalsIgnoreCase("contains")){
				q.setParameter(1, "%" + searchTerm + "%");									
			} 
			else {
				q.setParameter(1, "%");
			}
		} 
		else {
			q.setParameter(1, "%");
		}
		
		String qry = "SELECT a.artistID FROM Artist a ";
		
		if(!searchTerm.equals("")){
			if(searchType.equalsIgnoreCase("equals")){
				qry += "WHERE a.bandName = '" + searchTerm + "'";
			}
			else if(searchType.equalsIgnoreCase("begin")){
				qry += "WHERE a.bandName LIKE '" + searchTerm + "%'";
			}
			else if(searchType.equalsIgnoreCase("ends")){
				qry += "WHERE a.bandName LIKE '%" + searchTerm + "'";
			}
			else{
				qry += "WHERE a.bandName LIKE '%" + searchTerm + "%'";
			}
		}
		
		
		List<String> artistIDs = em.createQuery(qry).getResultList();
		JSONArray artistListJSON = new JSONArray();
		
		for(String artistID : artistIDs){
			Artist a = em.find(Artist.class, artistID);
			artistListJSON.put(a.toJSON());
		}
		
		em.close();
		emFactory.close();
		
		return artistListJSON;
	}

	
	public void createArtist(String artistID, String firstName, String lastName, String bandName, String bio) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MusicJpaWeb");
		EntityManager em = emFactory.createEntityManager();
		
		em.getTransaction().begin();
		Artist a = new Artist();
		
		a.setArtistID(java.util.UUID.randomUUID().toString());
		a.setFirstName(firstName);
		a.setLastName(lastName);
		a.setBandName(bandName);
		a.setBio(bio);
		
		em.persist(a);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
	}
	

	public void updateArtist(String artistID, String firstName, String lastName, String bandName, String bio) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MusicJpaWeb");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);
		
		if (!firstName.equals("")) {
			a.setFirstName(firstName);
		}
		
		if (!lastName.equals("")) {
			a.setLastName(lastName);
		}
		
		if (!bandName.equals("")) {
			a.setBandName(bandName);
		}
		
		if (!bio.equals("")) {
			a.setBio(bio);
		}
		
		em.persist(a);
		em.getTransaction().commit();		
		em.close();
		emFactory.close();
	}
	

	public void deleteArtist(String artistID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MusicJpaWeb");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);
		em.remove(a);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
	}
	

	public Artist findArtist(String artistID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MusicJpaWeb");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);
		em.getTransaction().commit();
		em.close();
		emFactory.close();
		return a;
	}


	public Artist getArtist(String artistID){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MusicJpaWeb");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		Artist a = em.find(Artist.class, artistID);
		em.close();
		emFactory.close();
		return a;
	}



}

 
	
	
