package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE User SET password = :fPassword WHERE login = :fLogin")
                    .setParameter("fLogin", user.getLogin())
                    .setParameter("fPassword", user.getPassword())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE FROM User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        List<User> result;
        try {
            session.beginTransaction();
            result = session.createQuery("FROM User u ORDER BY u.id", User.class).list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        Session session = sf.openSession();
        User result = new User();
        try {
            session.beginTransaction();
            result = session.get(User.class, userId);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return Optional.ofNullable(result);
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        List<User> result;
        try {
            session.beginTransaction();
            result = session.createQuery("FROM User WHERE login LIKE :fKey", User.class)
                    .setParameter("fKey", "%" + key + "%")
                    .list();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        Optional<User> result = Optional.of(new User());
        try {
            session.beginTransaction();
            result = session.createQuery("FROM User WHERE login = :fLogin", User.class)
                    .setParameter("fLogin", login)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return result;
    }
}