package com.productmanagement.service;

import com.productmanagement.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class ProductService implements IProductService {
    private static EntityManager entityManager;
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        String queryStr = "SELECT c FROM Product AS c";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        return query.getResultList();
    }

    @Override
    public boolean save(Product product) {
        executeInTransaction(session -> session.saveOrUpdate(product));
        return true;
    }

    @Override
    public Product findById(int id) {
        String queryStr = "SELECT c FROM Product AS c WHERE c.id = :id";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public boolean update(int id, Product product) {
        product.setId(id);
        save(product);
        return true;
    }

    @Override
    public boolean remove(int id) {
        executeInTransaction(session -> {
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.remove(product);
            }
        });
        return true;
    }

    @Override
    public List<Product> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return findAll();
        }
        String queryStr = "SELECT c FROM Product AS c WHERE LOWER(c.name) LIKE LOWER(:name)";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        query.setParameter("name", "%" + name.trim() + "%");
        return query.getResultList();
    }

    private void executeInTransaction(Consumer<Session> action) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            action.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
