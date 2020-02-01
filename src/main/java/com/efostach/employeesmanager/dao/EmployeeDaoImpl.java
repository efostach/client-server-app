//package com.efostach.employeesmanager.dao;
//
//import com.efostach.employeesmanager.model.Employee;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class EmployeeDaoImpl implements EmployeeDao {
//    private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);
//
//    private SessionFactory sessionFactory;
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    public void add(Employee employee) {
//        Session session = this.sessionFactory.getCurrentSession();
//        session.persist(employee);
//        logger.info("Employee successfully added: " + employee);
//    }
//
//    @Override
//    public void update(Employee employee) {
//        Session session = this.sessionFactory.getCurrentSession();
//        session.update(employee);
//        logger.info("Employee successfully updated: " + employee);
//    }
//
//    @Override
//    public void remove(Integer id) {
//        Session session = this.sessionFactory.getCurrentSession();
//
//        Employee employee = (Employee) session.load(Employee.class, new Integer(id));
//
//        if (employee != null)
//            session.delete(employee);
//
//        logger.info("Employee successfully removed: " + employee);
//    }
//
//    @Override
//    public Employee getById(Integer id) {
//        Session session = this.sessionFactory.getCurrentSession();
//
//        Employee employee = (Employee) session.load(Employee.class, new Integer(id));
//
//        logger.info("Employee successfully loaded: " + employee);
//
//        return employee;
//    }
//
//    @Override
//    public List<Employee> listAll() {
//        Session session = this.sessionFactory.getCurrentSession();
//
//        List<Employee> listEmployees = session.createQuery("from Employee").list();
//
//        for (Employee employee : listEmployees) {
//            logger.info("Employees list: " + employee);
//        }
//        return listEmployees;
//    }
//}