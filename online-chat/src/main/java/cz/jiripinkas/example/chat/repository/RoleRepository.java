package cz.jiripinkas.example.chat.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cz.jiripinkas.example.chat.entity.Role;
import cz.jiripinkas.example.chat.entity.User;
import cz.jiripinkas.example.chat.entity.UserRole;

@Repository
public class RoleRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void assignRole(User user, Role role) {
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		entityManager.persist(userRole);
	}

	public List<Role> findAll() {
		return entityManager.createNamedQuery(Role.FIND_ALL, Role.class)
				.getResultList();
	}

	public Role findOne(int id) {
		return entityManager.find(Role.class, id);
//		return entityManager.createQuery("select r from Role r where r.id = :id", Role.class).setParameter("id", id).getSingleResult();
	}

	public Role findByName(String name) {
		return entityManager.createNamedQuery(Role.FIND_BY_NAME, Role.class)
				.setParameter("name", name).getSingleResult();
	}
	
	public List<Role> findByUserName(String name) {
		return entityManager.createNamedQuery(Role.FIND_BY_USER_NAME, Role.class).setParameter("name", name).getResultList();
	}
	
	public long count() {
		return entityManager.createNamedQuery(Role.COUNT, Long.class).getSingleResult();
	}
}
