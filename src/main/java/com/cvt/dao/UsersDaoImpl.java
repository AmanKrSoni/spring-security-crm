package com.cvt.dao;

import com.cvt.model.Customer;
import com.cvt.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class UsersDaoImpl implements UsersDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Users> getAll() {
        //get current hibernate session
        Session currentSession=sessionFactory.getCurrentSession();

        //create the query
        CriteriaBuilder builder=currentSession.getCriteriaBuilder();
        CriteriaQuery<Users> criteriaQuery=builder.createQuery(Users.class);
        Root<Users> root=criteriaQuery.from(Users.class);
        Query<Users> query=currentSession.createQuery(criteriaQuery);

        //execute the query and get result
        List<Users> usersList=query.getResultList();


        //return list of customer

        return usersList;
    }

    @Override
    public int updateUsers(Users users) {
        Session session=sessionFactory.getCurrentSession();
        String username=users.getUsername();
        Users users1=session.byId(Users.class).load(users.getUsername());
        users1.setPassword(users.getPassword());
        users1.setEnabled(users.getEnabled());
        session.flush();

        return users1!=null ? 1:0;
    }

    @Override
    public Users getById(String username) {
        Session session=sessionFactory.getCurrentSession();
        return session.byId(Users.class).load(username);
    }

    @Override
    public int deleteUsers(String username) {
        Session session=sessionFactory.getCurrentSession();
        Users users=getById(username);
        session.delete(users);
        return users!=null?1:0;
    }

    @Override
    public int createUsers(Users users) {
        Session session=sessionFactory.getCurrentSession();
        users.setEnabled(1);
        session.save(users);
        return 1;
    }
}
