package usecases.impl;

import confighibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import usecases.usecase.ChangePasswordUseCase;

import java.util.Scanner;

public class ChangePasswordUseCaseImpl implements ChangePasswordUseCase {
    @Override
    public void changePass(String username) {

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
}
