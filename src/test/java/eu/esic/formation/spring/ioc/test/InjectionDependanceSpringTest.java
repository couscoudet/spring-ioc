package eu.esic.formation.spring.ioc.test;

import eu.esic.formation.spring.ioc.dao.IDao;
import eu.esic.formation.spring.ioc.dao.impl.DaoImpl;
import eu.esic.formation.spring.ioc.metier.IMetier;
import eu.esic.formation.spring.ioc.metier.impl.MetierImpl;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Christophe.ZOME@i-carre.net
 * 
 */
public class InjectionDependanceSpringTest {

    @Test
    public void testCalculParInjectionStatique() {
        IDao dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);
        double nbCalcule = metier.calcul();
        Assert.assertTrue(nbCalcule == 5000);
        System.out.println(metier.calcul());
    }

    @Test
    public void testCalculParInjectionDynamiqueReflexive() {
        try {
            Scanner scanner = new Scanner(new File("src/test/resources/ClasseConfig.txt"));
            String daoClassname = scanner.next();
            String metierClassName = scanner.next();
            Class<?> cdao = Class.forName(daoClassname);
            IDao dao = (IDao) cdao.getDeclaredConstructor().newInstance();
            Class<?> cmetier = Class.forName(metierClassName);
            IMetier metier = (IMetier) cmetier.getDeclaredConstructor().newInstance();
            Method meth = cmetier.getMethod("setDao", new Class[] { IDao.class });
            meth.invoke(metier, new Object[] { dao });
            double nbCalcule = metier.calcul();
            Assert.assertTrue(nbCalcule == 5000);
            System.out.println(nbCalcule);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCalculParInjectionDependanceFichierConfXMLStandard() {
        ClassPathXmlApplicationContext contextSpring = new ClassPathXmlApplicationContext(new String[] { "spring-ioc.xml" });
        IMetier metier = (IMetier) contextSpring.getBean("metierBean");
        double nbCalcule = metier.calcul();
        Assert.assertTrue(nbCalcule == 5000);
        System.out.println(nbCalcule);
        contextSpring.close();
    }

    @Test
    public void testCalculSalaireAnnuel() {
        /**
         * TODO : Completer le code ci dessous en : 
         * 1-Recuperant le contexte Spring 
         * 2-Recuperant les beans metiers declares dans le fichier de configuration "spring-ioc.xml"
         * 3-Assurez vous que les salaires annuels du Developpeur et Commercial sont diff�rents
         */
    }
}
