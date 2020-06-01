package com.javaLive.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.javaLive.entity.Group;
import com.javaLive.entity.User;

/**
 * A program that demonstrates using JPA annotations to map
 * a bidirectional many-to-many association in Hibernate framework.
 * @author www.JavaLive.com
 *
 */
public class UsersManager {

	public static void main(String[] args) {
		// loads configuration and mappings
		Configuration configuration = new Configuration().configure();
		ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
		registry.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = registry.buildServiceRegistry();

		// builds a session factory from the service registry
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);

		// obtains the session
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Group groupAdmin = new Group("Administrator Group");
		Group groupGuest = new Group("Guest Group");
		
		User user1 = new User("Tom", "tomcat", "tom@JavaLive.com");
		User user2 = new User("Mary", "mary", "mary@JavaLive.com");
		
		groupAdmin.addUser(user1);
		groupAdmin.addUser(user2);
		
		groupGuest.addUser(user1);
		
		user1.addGroup(groupAdmin);
		user2.addGroup(groupAdmin);
		user1.addGroup(groupGuest);
		
		session.save(groupAdmin);
		session.save(groupGuest);
		
		session.getTransaction().commit();
		session.close();		
	}

}