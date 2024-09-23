package web.DAO;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        List<User> usersList = new ArrayList<>();
        usersList = entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
        return usersList;
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser (User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser (int id, User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUser(id));
    }

}
