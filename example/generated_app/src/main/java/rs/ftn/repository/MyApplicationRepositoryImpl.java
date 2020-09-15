/**
    ######################################## BEGIN ###############################################
     generated class: MyApplicationRepositoryImpl.java, template used : base_repository_impl.template
     author: Luka, version: 1, time: 2020-09-15 21:47:20.486259, grammar: entity.tx
    ##############################################################################################
*/
package rs.ftn.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import rs.ftn.repository.MyApplicationRepository;


public class MyApplicationRepositoryImpl<T> extends SimpleJpaRepository<T, Integer> implements MyApplicationRepository<T> {
    public MyApplicationRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}
}

/**
    ########################################  END ################################################
*/