package repositories;

import confighibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ArticleRegisterApplication {

    public static void main(String[] args) {
        ArticleRegisterApplication articleRegisterApplication =
                new ArticleRegisterApplication();
    }

    public void signUp() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================



        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();

    }

    public void login() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void showAllArticles() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void showSpecificArticle() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void showUserArticlesAfterLogin() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void createNewArticle() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void showListOfCategories() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void creatNewCategory() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void edit() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void editArticle() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void changePassword() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void deleteArticle() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void publishArticle() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void updateLastUpdateDate() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void updatePublishDate() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void dashboard() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void countOfAllArticles() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void countOfPublishedArticles() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================


        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

}
