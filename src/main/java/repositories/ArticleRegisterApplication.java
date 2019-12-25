package repositories;

import confighibernate.HibernateUtil;
import entities.Article;
import entities.Category;
import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;

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

        User user = null;
        while (true) {
            if (user == null) {
                System.out.println("what do you want? ( sign up | login | show articles | show one ): ");
                command = scanner.nextLine();
                //----------------------------------------------------------

                if (command.equalsIgnoreCase("sign up")) {
                    articleRegisterApplication.signUp();
                }

                //----------------------------------------------------------

                else if (command.equalsIgnoreCase("login")) {
                    user = articleRegisterApplication.login();
                    if (user != null) {
                        System.out.println("    LOGIN SUCCESSFUL !!!");
                    } else {
                        System.out.println("    INVALID USERNAME OR PASSWORD !!!");
                    }
                }

                //----------------------------------------------------------

                else if (command.equalsIgnoreCase("show articles")) {
                    articleRegisterApplication.showAllArticles();
                }

                //----------------------------------------------------------

                else if (command.equalsIgnoreCase("show one")) {
                    System.out.println("enter article id :");
                    Long id = Long.parseLong(scanner.nextLine());
                    articleRegisterApplication.showSpecificArticle(id);
                } else {
                    System.out.println("wrong command !!!");
                }

                //----------------------------------------------------------
            }
            if (user != null) {
                System.out.println("what do you want? ( show | edit | new | change pass | dashboard | logout ): ");
                command = scanner.nextLine();

                //----------------------------------------------------------

                if (command.equalsIgnoreCase("show")) {
                    articleRegisterApplication.showUserArticlesAfterLogin(user.getId());
                }

                //----------------------------------------------------------


                else if (command.equalsIgnoreCase("edit") &&
                        articleRegisterApplication.isUserHasArticle(user.getId())) {
                    System.out.println("choose an option: ( edit article | publish | delete )");
                    String articleCommand = scanner.nextLine();
                    Long id;
                    if (articleCommand.equalsIgnoreCase("edit article")) {
                        System.out.println("enter article id: ");
                        id = Long.parseLong(scanner.nextLine());
                        articleRegisterApplication.editArticle(id, user);
                    }
                    if (articleCommand.equalsIgnoreCase("publish")) {
                        System.out.println("enter article id: ");
                        id = Long.parseLong(scanner.nextLine());
                        articleRegisterApplication.publishArticle(id, user);
                    }
                    if (articleCommand.equalsIgnoreCase("delete")) {
                        System.out.println("enter article id: ");
                        id = Long.parseLong(scanner.nextLine());
                        articleRegisterApplication.deleteArticle(id, user);
                    }

                }

                //----------------------------------------------------------

                else if (command.equalsIgnoreCase("new")) {
                    articleRegisterApplication.showListOfCategories();
                    System.out.println("select category option ( new | existing ): ");
                    String catOption = scanner.nextLine();
                    if (catOption.equalsIgnoreCase("new")) {
                        articleRegisterApplication.creatNewCategory();
                        articleRegisterApplication.showListOfCategories();
                        articleRegisterApplication.createNewArticle(user);
                    } else if (catOption.equalsIgnoreCase("existing")) {
                        articleRegisterApplication.createNewArticle(user);
                    } else {
                        System.out.println("wrong command !!!");
                    }

                }

                //----------------------------------------------------------

                else if (command.equalsIgnoreCase("change pass")) {
                    articleRegisterApplication.changePassword(user.getUsername());
                }

                //----------------------------------------------------------

                else if (command.equalsIgnoreCase("dashboard")) {
                    System.out.println("quantity of your articles: " +
                            articleRegisterApplication.countOfAllArticlesOfUser(user));

                    System.out.println("quantity of your published articles: " +
                            articleRegisterApplication.countOfPublishedArticles(user));
                }

                //----------------------------------------------------------

                else if (command.equalsIgnoreCase("logout")) {
                    user = null;
                } else {
                    System.out.println("wrong command !!!");
                }

                //----------------------------------------------------------
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

        Query query = session.createQuery("from Article ");
        List<Article> articleList = query.list();
        System.out.printf("\n%-5s%-20s%-30s\n%s\n", "id", "title", "brief",
                "==================================================================");
        for (Article article : articleList) {
            article.printBrief();
        }
        System.out.println("==================================================================");

        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void showSpecificArticle(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================

        List list = session.createQuery("from Article where id=:id")
                .setParameter("id", id)
                .list();
        if (list.size() > 0) {
            System.out.println("\n" +
                    " Article\n==========================================");

            System.out.println(list.get(0).toString());

            System.out.println("==========================================");
        } else {
            System.out.println("id not found !!!");
        }
        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void showUserArticlesAfterLogin(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================

        List list = session.createQuery("from Article where user.id=:id")
                .setParameter("id", id)
                .list();
        if (list.size() > 0) {
            System.out.println("\n" +
                    " Article\n==========================================");
            for (Object article : list) {
                System.out.println(article.toString());
            }
            System.out.println("==========================================");
        } else {
            System.out.println("NO ARTICLES FOUND");
        }

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
        System.out.println("article title: ");
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

    public void editArticle(Long id, User user) {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================

        boolean isDone = false;
        boolean isIdExist = false;

        List<Long> idList = session.createQuery("select id from Article ")
                .list();
        for (Long articleId : idList) {
            if (id == articleId) {
                isIdExist = true;
            }
        }
        //checking the validation of the article's id
        Article article = session.load(Article.class, id);
        if (isIdExist) {
            if (article.getUser().getId() == user.getId()) {

                System.out.println("enter column's name ( title | brief | content ):");
                String columnName = scanner.nextLine();

                if (columnName.equalsIgnoreCase("title")) {
                    System.out.println("enter new title: ");
                    String newTitle = scanner.nextLine();
                    article.setTitle(newTitle);
                    isDone = true;

                } else if (columnName.equalsIgnoreCase("brief")) {
                    System.out.println("enter new brief: ");
                    String newBrief = scanner.nextLine();
                    article.setBrief(newBrief);
                    isDone = true;
                } else if (columnName.equalsIgnoreCase("content")) {
                    System.out.println("enter new content: ");
                    String newContent = scanner.nextLine();
                    article.setContent(newContent);
                    isDone = true;
                } else {
                    System.out.println("invalid column name !!!");
                }
                if (isDone) {
                    article.setLastUpdateDate(currentDate());
                    session.update(article);
                }
            } else {
                System.out.println("THE ARTICLE IS NOT YOURS !!!");
            }
        } else {
            System.out.println("ID NOT FOUND !!!");
        }

        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void deleteArticle(Long id, User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================
        boolean isIdExist = false;

        List<Long> idList = session.createQuery("select id from Article ")
                .list();
        for (Long articleId : idList) {
            if (id == articleId) {
                isIdExist = true;
            }
        }
        Article article = session.load(Article.class, id);
        if (isIdExist) {
            if (article.getUser().getId() == user.getId()) {
                session.remove(article);
            } else {
                System.out.println("THE ARTICLE IS NOT YOURS !!!");
            }
        } else {
            System.out.println("ID NOT FOUND !!!");
        }

        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
    }

    public void publishArticle(Long id, User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================
        boolean isIdExist = false;

        List<Long> idList = session.createQuery("select id from Article ")
                .list();
        for (Long articleId : idList) {
            if (id == articleId) {
                isIdExist = true;
            }
        }
        Article article = session.load(Article.class, id);
        if (isIdExist) {
            if (article.getUser().getId() == user.getId()) {

                article.setPublishDate(currentDate());
                article.setLastUpdateDate(currentDate());
                article.setPublished("yes");

                session.update(article);
            } else {
                System.out.println("THE ARTICLE IS NOT YOURS !!!");
            }
        } else {
            System.out.println("ID NOT FOUND !!!");
        }
        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();

    }

    public Long countOfAllArticlesOfUser(User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================

        Query query = session.createQuery("select count(id) from Article  where user.id=:id")
                .setParameter("id", user.getId());
        Long count = (Long) query.uniqueResult();

        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
        return count;
    }

    public Long countOfPublishedArticles(User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================

        Query query = session.createQuery("select count(id) from Article  where user.id=:id and isPublished='yes'")
                .setParameter("id", user.getId());
        Long count = (Long) query.uniqueResult();

        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
        return count;
    }

    public boolean isUserHasArticle(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //get session
        Session session = sessionFactory.openSession();
        //transaction start
        session.beginTransaction();
        //====================================
        boolean bool = false;

        List list = session.createQuery("from Article where user.id=:id")
                .setParameter("id", id)
                .list();
        if (list.size() > 0) {
            bool = true;
        } else {
            System.out.println("NO ARTICLES FOUND TO EDIT !!!");
        }

        //====================================
        //transaction commit
        session.getTransaction().commit();
        session.close();
        return bool;
    }

    public static String currentDate() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return date.format(now);
    }

}
