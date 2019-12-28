package usecases.impl;

import confighibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.ShowListOfCategoriesUseCase;

import java.util.List;

public class ShowListOfCategoriesUseCaseImpl implements ShowListOfCategoriesUseCase {
    @Override
    public void show() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================

        List list = session.createQuery("from Category")
                .list();
        System.out.println("\nCATEGORY TITLES\n==========================================");
        for (Object title : list) {

            System.out.println(title.toString());
        }
        System.out.println("==========================================");

        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }
}
