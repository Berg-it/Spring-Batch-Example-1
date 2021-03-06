package tn.berg.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tn.berg.bean.Personne;

@Transactional (propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PersonJdbcWriter implements ItemWriter<Personne>{

	
	//@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 private static final String REQUEST_INSERT_PERSONNE = "insert into PERSONNE (id,nom,prenom,civilite) values (?,?,?,?)";
	 private static final String REQUEST_UPDATE_PERSONNE = "update PERSONNE set nom=?, prenom=?, civilite=? where id=?";	
	
	public void write(List<? extends Personne> items) throws Exception {
		 
			 for (Personne personne : items) {
			// final Object object [] = {personne.getNom(),personne.getPrenom(), personne.getCivilite(),personne.getId()};
			 
			 //on tente un update
			 //int nbLigne = jdbcTemplate.update(REQUEST_UPDATE_PERSONNE, object);
			 
			 //si le nombre de ligne mise a jour vaut 0, on fait un insert
			 //if (nbLigne == 0) {
			 final Object object2 [] = {personne.getId(),personne.getNom(),personne.getPrenom(), personne.getCivilite()};
			 System.out.println("object2 "+object2[0]);
			 jdbcTemplate.update(REQUEST_INSERT_PERSONNE, object2);
			 //} 
			 /*else 
			 {
			 
			 }
			 */
			 }
		
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

}
