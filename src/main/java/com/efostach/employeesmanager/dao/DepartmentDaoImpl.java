package com.efostach.employeesmanager.dao;

import com.efostach.employeesmanager.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{
    private static final Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class);

    private SessionFactory sessionFactory;

    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Department department) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(department);
        logger.info("Department successfully added: " + department);
    }

    @Override
    public void update(Department department) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(department);
        logger.info("Department successfully updated: " + department);
    }

    @Override
    public void remove(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();

        Department department = (Department) session.load(Department.class, new Integer(id));

        if (department != null)
            session.delete(department);

        logger.info("Department successfully removed: " + department);
    }

    @Override
    public Department getById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();

        Department departmnet = (Department) session.load(Department.class, new Integer(id));

        logger.info("Department successfully loaded: " + departmnet);
        return departmnet;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Department> listAll() {
        Session session = this.sessionFactory.getCurrentSession();

        List<Department> listDepartments = session.createQuery("from Department").list();

        for (Department department : listDepartments) {
            logger.info("Departments list: " + department);
        }
        return listDepartments;
    }
}