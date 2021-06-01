package employee.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import employee.entity.Employee;

@Repository
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Employee add(Employee employee) {
        return this.entityManager.merge(employee);
    }

    @Transactional
    public void update(Employee employee) {
        this.entityManager.persist(employee);
    }

    public List<Employee> findAll() {
        Query query = entityManager.createQuery("SELECT c FROM Employee c");
        return (List<Employee>) query.getResultList();
    }

    public Employee findById(Long id) {
        return this.entityManager.find(Employee.class, id);
    }

    @Transactional
    public void delete(Long id) {
        Employee employee = this.findById(id);
        this.entityManager.remove(employee);
    }
}
