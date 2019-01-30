package com.cvt.dao;

import com.cvt.model.Customer;
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
public class CustomerDaoImpl implements CustomerDao {

    /*need to inject session facory*/
    @Autowired
    private SessionFactory sessionFactory;



    public List<Customer> getCustomers() {

        //get current hibernate session
        Session currentSession=sessionFactory.getCurrentSession();

        //create the query
        CriteriaBuilder builder=currentSession.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery=builder.createQuery(Customer.class);
        Root<Customer> root=criteriaQuery.from(Customer.class);
        Query<Customer> query=currentSession.createQuery(criteriaQuery);

        //execute the query and get result
        List<Customer> customers=query.getResultList();


        //return list of customer
        return customers;
    }

    public void saveCustomer(Customer customer) {
        Session session=sessionFactory.getCurrentSession();
        //saving or updating data based on p_key
        session.saveOrUpdate(customer);
    }

    public Customer getCustomer(int id) {
        //get current session
        Session session=sessionFactory.getCurrentSession();
        //now retrive data from database
        Customer customer=session.get(Customer.class,id);
        return customer;
    }

    public void deleteCustomer(int Id) {
        Session session=sessionFactory.getCurrentSession();
        Customer customer=getCustomer(Id);
        session.delete(customer);

    }
}
