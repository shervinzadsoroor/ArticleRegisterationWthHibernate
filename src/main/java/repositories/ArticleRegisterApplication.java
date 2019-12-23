package repositories;

import confighibernate.HibernateUtil;
import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class ArticleRegisterApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArticleRegisterApplication articleRegisterApplication =
                new ArticleRegisterApplication();
        String command = null;
        boolean isLogin = false;
        while (true) {
            if (!isLogin) {
                System.out.println("what do you want? (sign up | login | show articles): ");
                command = scanner.nextLine();
                if (command.equalsIgnoreCase("sign up")) {
                    articleRegisterApplication.signUp();
                }
                if (command.equalsIgnoreCase("login")) {
                    isLogin = articleRegisterApplication.login();
                    if (isLogin) {
                        System.out.println("    LOGIN SUCCESSFUL !!!");
                    } else {
                        System.out.println("    INVALID USERNAME OR PASSWORD !!!");
                    }
                }
                if (command.equalsIgnoreCase("show articles")) {
                    articleRegisterApplication.showAllArticles();
                }
            }
            if (isLogin) {
                System.out.println("what do you want? (show | edit | new | change pass | dashboard | logout): ");
                command = scanner.nextLine();
                if (command.equalsIgnoreCase("logout")) {
                    isLogin = false;
                }

            }

        }
    }

    public void signUp() {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================
        System.out.println("username: ");
        String username = scanner.nextLine();
        System.out.println("password: ");
        String password = scanner.nextLine();
        System.out.println("national code: ");
        String nationalCode = scanner.nextLine();
        System.out.println("birthday: ");
        String birthday = scanner.nextLine();
        User user = new User(username, nationalCode, password, birthday);
        Long id = (Long) session.save(user);
        System.out.println("sign up successfully done!!!\nyour id is:" + id);
        System.out.println(user);

        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();

    }

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        boolean isLogin = false;

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================

        System.out.println("username: ");
        String username = scanner.nextLine();

        System.out.println("password: ");
        String password = scanner.nextLine();

        List dbPassword = session.createQuery("select password from User where username = :username")
                .setParameter("username", username)
                .list();

        if (dbPassword.size() == 1 && password.equals(dbPassword.get(0))) {
            isLogin = true;
        }

        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
        return isLogin;
    }

    public void showAllArticles() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================

        //todo

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

        //todo

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

        //todo

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

        //todo
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

        //todo

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

        //todo
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

        //todo
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

        //todo
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
        //todo

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

        //todo
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

        //todo
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
        //todo

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
        //todo

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

        //todo
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

        //todo
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
        //todo

        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

}
