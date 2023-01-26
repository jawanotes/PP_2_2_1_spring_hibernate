package hiber.dao;

import hiber.model.User;
//import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserByModelAndSeries(String model, int series) {
      // 1
      //String hql = "FROM User AS u JOIN FETCH u.car AS car WHERE car.model = :pmodel AND car.series = :pseries";
      // 2
      String hql = "FROM User u WHERE u.car.model = :pmodel AND u.car.series = :pseries";

      //Session session = sessionFactory.getCurrentSession(); // debug
      TypedQuery<User> query= sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter("pmodel", model);
      query.setParameter("pseries", series);
      return query.getSingleResult();
   }

}
