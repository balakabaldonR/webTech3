package com.es.core.dao.impl;

import com.es.core.entity.phone.Phone;
import com.es.core.entity.phone.sort.SortField;
import com.es.core.entity.phone.sort.SortOrder;
import com.es.core.dao.PhoneDao;
import com.es.core.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component
public class PhoneDaoImpl implements PhoneDao {
    private final SessionFactory sessionFactory;

    public PhoneDaoImpl() {
        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public Optional<Phone> get(Long key) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Phone.class, key));
        }
    }

    @Override
    public List<Phone> findAll(int offset, int limit, SortField sortField, SortOrder sortOrder, String query) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Phone> criteriaQuery = criteriaBuilder.createQuery(Phone.class);
            Root<Phone> root = criteriaQuery.from(Phone.class);

            criteriaQuery.select(root);
            if (query != null && !query.isEmpty()) {
                criteriaQuery.where(criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + query.toLowerCase() + "%"));
            }
            if (sortField != null && sortOrder != null) {
                Order order = sortOrder == SortOrder.ASC ? criteriaBuilder.asc(root.get(sortField.name().toLowerCase()))
                        : criteriaBuilder.desc(root.get(sortField.name().toLowerCase()));
                criteriaQuery.orderBy(order);
            }

            Query<Phone> queryResult = session.createQuery(criteriaQuery);
            queryResult.setFirstResult(offset);
            queryResult.setMaxResults(limit);

            return queryResult.getResultList();
        }
    }

    @Override
    public Long numberByQuery(String query) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
            Root<Phone> root = countQuery.from(Phone.class);

            countQuery.select(criteriaBuilder.count(root));
            if (query != null && !query.isEmpty()) {
                countQuery.where(criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + query.toLowerCase() + "%"));
            }

            return session.createQuery(countQuery).getSingleResult();
        }
    }
}
