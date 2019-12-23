package repositories;

import confighibernate.HibernateUtil;
import entities.Article;
import entities.Category;
import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ArticleRegisterApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArticleRegisterApplication articleRegisterApplication =
                new ArticleRegisterApplication();
        String command = null;
        // boolean isLogin = false;
        User user = null;
        while (true) {
            if (user == null) {
                System.out.println("what do you want? ( sign up | login | show articles ): ");
                command = scanner.nextLine();
                if (command.equalsIgnoreCase("sign up")) {
                    articleRegisterApplication.signUp();
                }
                if (command.equalsIgnoreCase("login")) {
                    user = articleRegisterApplication.login();
                    if (user != null) {
                        System.out.println("    LOGIN SUCCESSFUL !!!");
                    } else {
                        System.out.println("    INVALID USERNAME OR PASSWORD !!!");
                    }
                }
                if (command.equalsIgnoreCase("show articles")) {
                    articleRegisterApplication.showAllArticles();
                }
            }
            if (user != null) {
                System.out.println("what do you want? ( show | edit | new | change pass | dashboard | logout ): ");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("show")) {

                }
                if (command.equalsIgnoreCase("edit")) {

                }
                if (command.equalsIgnoreCase("new")) {
                    articleRegisterApplication.showListOfCategories();
                    System.out.println("select category option ( new | existing ): ");
                    String catOption = scanner.nextLine();
                    if (catOption.equalsIgnoreCase("new")) {
                        articleRegisterApplication.creatNewCategory();
                    }
                    articleRegisterApplication.showListOfCategories();
                    articleRegisterApplication.createNewArticle(user);
                }
                if (command.equalsIgnoreCase("change pass")) {
                    articleRegisterApplication.changePassword(user.getUsername());
                }
                if (command.equalsIgnoreCase("dashboard")) {

                }
                if (command.equalsIgnoreCase("logout")) {
                    user = null;
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

        System.out.println("national code: ");
        String nationalCode = scanner.nextLine();
        System.out.println("birthday: ");
        String birthday = scanner.nextLine();
        User user = new User(username, nationalCode, nationalCode, birthday); // password is the national code for the first time
        Long id = (Long) session.save(user);
        System.out.println("sign up successfully done!!!\nyour id is:" + id);

        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();

    }

    public User login() {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        boolean isLogin = false;
        User user = null;

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
        List users = session.createQuery("from User where username= :username")
                .setParameter("username", username)
                .list();

        if (users.size() == 1 && password.equals(dbPassword.get(0))) {
            user = (User) users.get(0);
        }

        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
        //return isLogin;
        return user;
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

    public void createNewArticle(User user) {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerLong = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        boolean isCategoryExist = false;

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================

        System.out.println("category id: ");
        Long category_id = null;

        // validating the category id ====================================
        while (!isCategoryExist) {
            category_id = scannerLong.nextLong();
            List categoryIds = session.createQuery("select id from Category ")
                    .list();
            for (Object obj : categoryIds) {
                if (obj == category_id) {
                    isCategoryExist = true;
                    break;
                }
            }
            if (!isCategoryExist) {
                System.out.println("category not found, enter an existing category id: ");
            }
        }
        //end of validating ==============================================
        System.out.println(" article title: ");
        String title = scanner.nextLine();
        System.out.println("article brief: ");
        String brief = scanner.nextLine();
        System.out.println("article content: ");
        String content = scanner.nextLine();
        String createDate = currentDate();
        String isPublished = "no";
        String lastUpdateDate = currentDate();

        List<Category> categoryList = session.createQuery("from Category where id= :id")
                .setParameter("id", category_id)
                .list();
        Category category = categoryList.get(0);

        Article article = new Article(title, brief, content, createDate, lastUpdateDate,
                null, isPublished, user, category);

        session.save(article); // insert into article


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

    public void creatNewCategory() {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================

        System.out.println("category title: ");
        String title = scanner.nextLine();
        System.out.println("category description: ");
        String description = scanner.nextLine();

        Category category = new Category(title, description);
        session.save(category); // insert into category

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

    public void changePassword(String username) {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================
        System.out.println("enter new password(at least 4 characters): ");
        String newPassword = scanner.nextLine();
        if (newPassword.length() >= 4) {
            System.out.println("enter new password again: ");
            String newPasswordAgain = scanner.nextLine();

            if (newPassword.equals(newPasswordAgain)) {
                Query query = session.createQuery("update User set password = :password where username=:username");
                query.setParameter("password", newPassword);
                query.setParameter("username", username);
                query.executeUpdate();
                System.out.println("password changed successfully !!!");
            } else {
                System.out.println("invalid password !!!");
            }
        } else {
            System.out.println("new password is too short !!!");
        }
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

    public static String currentDate() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return date.format(now);
    }

}
