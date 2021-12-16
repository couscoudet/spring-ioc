package eu.esic.formation.spring.ioc.metier.impl;

import eu.esic.formation.spring.ioc.dao.IDao;
import eu.esic.formation.spring.ioc.metier.IMetier;

public class MetierImpl implements IMetier {

    private IDao dao;

    public double calcul() {
        double nb = dao.getSalaireMensuelEmploye();
        double valeurApresMetier = nb * IDao.NB_MOIS;
        return valeurApresMetier;
    }

    public IDao getDao() {
        return dao;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }

}
