package contoller;


//Sheri Westhoff
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import model.Team;

public class TeamHelper {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BowlingLeagueWesthoff");

	public void insertTeam(Team toAdd) {
		// TODO Auto-generated method stub

		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(toAdd);
		em.getTransaction().commit();
		em.close();

	}
	
	
	
	public List<Team> showAllTeams() {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		// creates the query but does not execute it.
		TypedQuery<Team> allResults = em.createQuery("Select list_item from Team list_item", Team.class);
		// Selects the info
		List<Team> allTeams = allResults.getResultList();
		// need to close the entity manager
		em.close();

		return allTeams;
	}

	
	public void deleteTeam(Team toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Team> typedQuery = em.createQuery(
				"select li from Team li where li.id = :selectedId",
				Team.class);
		typedQuery.setParameter("selectedId", toDelete.getTeamId());
		typedQuery.setMaxResults(1);
		Team result = typedQuery.getSingleResult(); 
		em.remove(result);
		em.getTransaction().commit();
		em.close();

	}
	
	
	public Team searchForTeamById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		Team foundTeam = em.find(Team.class, idToEdit);
		em.close(); // close
		// return the results of the query
		return foundTeam;
	}
	
}
