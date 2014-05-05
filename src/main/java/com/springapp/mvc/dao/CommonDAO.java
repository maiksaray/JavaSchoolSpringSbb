package com.springapp.mvc.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

@Repository
public abstract class CommonDAO<T extends Serializable> {

    @Autowired
    protected SessionFactory sf;

    private Class<T> entityClass;
    protected Session currentSession;

    public CommonDAO(){};

    public CommonDAO(Class<T> entityClass) {
        this.entityClass = entityClass;

    }

    @PostConstruct
    private void PostConstruct(){
//        currentSession = sf.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    protected List<T> doGetAll(Class arg) {
        return sf.getCurrentSession().createQuery("FROM " + entityClass.getSimpleName()).list();
    }

    public abstract List<T> getAll();

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void persist(T entity) {
        sf.getCurrentSession().persist(entity);
    }

    @SuppressWarnings("unchecked")
    public T find(String entityId){
        return null;
    }

    public void merge(T entity) {
        if(entity!=null){
            sf.getCurrentSession().merge(entity);
        }
    }

    public void remove(T entity) {
        if (entity != null) {
            sf.getCurrentSession().delete(entity);
        }
    }
}
