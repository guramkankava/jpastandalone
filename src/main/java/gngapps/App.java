package gngapps;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import gngapps.entities.DeviceCheck;
import gngapps.entities.DeviceCheckProblems;
import gngapps.entities.DeviceProblem;

/**
 * Hello world!
 *
 */
public class App {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private static final String PERSISTANCE_UNIT_NAME_JPA_TRAINING = "JPATraining";

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME_JPA_TRAINING);
    }

    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    public static void main( String[] args ) {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<DeviceCheck> criteriaQuery = criteriaBuilder.createQuery(DeviceCheck.class);

        Root<DeviceCheck> from = criteriaQuery.from(DeviceCheck.class);

        Join<DeviceCheck, DeviceCheckProblems> deviceCheckProblems = from.join("problems");

        Join<DeviceCheckProblems, DeviceProblem> deviceProblem = deviceCheckProblems.join("problems");

        ParameterExpression<Long> parameter = criteriaBuilder.parameter(Long.class);

        criteriaQuery.select(from).where(criteriaBuilder.equal(from.get("id"), parameter));


        TypedQuery<DeviceCheck> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter(parameter, 1L);
        DeviceCheck resultList = typedQuery.getSingleResult();
        System.out.println(resultList);
    }

//    public static void main( String[] args ) {
//        EntityManager entityManager = getEntityManager();
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<DeviceCheck> criteriaQuery = criteriaBuilder.createQuery(DeviceCheck.class);
//
//        Root<DeviceCheck> from = criteriaQuery.from(DeviceCheck.class);
//
//        Join<Object, Object> deviceCheckProblems = from.join("problems");
//
//        Join<Object, Object> problemTypes = deviceCheckProblems.join("problems");
//
//        ParameterExpression<Long> parameter = criteriaBuilder.parameter(Long.class);
//
//        criteriaQuery.select(from).where(criteriaBuilder.equal(problemTypes.get("id"), parameter));
//
//        TypedQuery<DeviceCheck> typedQuery = entityManager.createQuery(criteriaQuery);
//        typedQuery.setParameter(parameter, 3L);
//        DeviceCheck resultList = typedQuery.getSingleResult();
//        System.out.println(resultList);
//    }
}
