/**
    ######################################## BEGIN ###############################################
     generated class: MyApplicationRepository.java, template used : base_repository.template
     author: Luka, version: 1, time: 2020-09-15 21:47:20.486259, grammar: entity.tx
    ##############################################################################################
*/
package rs.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface MyApplicationRepository<T> extends JpaRepository<T, Integer> {

}

/**
    ########################################  END ################################################
*/