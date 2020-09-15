/**
    ######################################## BEGIN ###############################################
     generated class: MyApplicationApplication.java, template used : main.template
     author: Luka, version: 1, time: 2020-09-15 21:47:20.486259, grammar: entity.tx
    ##############################################################################################
*/
package rs.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import rs.ftn.repository.MyApplicationRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "rs.ftn",
repositoryBaseClass = MyApplicationRepositoryImpl.class)
public class MyApplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyApplicationApplication.class, args);
	}

}

/**
    ########################################  END ################################################
*/