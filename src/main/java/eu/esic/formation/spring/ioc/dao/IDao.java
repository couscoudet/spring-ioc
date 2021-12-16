package eu.esic.formation.spring.ioc.dao;

public interface IDao {

    /**
     * Renvoie le salaire mensuel d'un Employe
     * 
     * @return
     */
    public double getSalaireMensuelEmploye();
    
    public static int NB_MOIS = 12;
}
