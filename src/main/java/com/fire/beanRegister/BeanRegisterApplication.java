package com.fire.beanRegister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

record Person(String name,String id){}


@Configuration
@ConfigurationProperties(prefix = "property")
class MyConfig {

	private List<Person> persons;

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}


@Component
class RegisterBean{

	@Autowired
	MyConfig myConfig;

//	@Bean(name = "person1")
//	public Person person1(){
//		var p = myConfig.getPersons().get(0);
//		return new Person(p.name(),p.id());
//	}
//
//	@Bean(name = "person2")
//	public Person person2(){
//		var p = myConfig.getPersons().get(1);
//		return new Person(p.name(),p.id());
//	}
	
	public void registerBean(){
		myConfig.getPersons().forEach(person -> {
			//TODO 在这里注入 person2 和 person2
		});
	}
}

@Service
class BeanService{
	@Autowired
	Person person1;

	public void doSomeThing(){
		System.out.println(person1.name());
	}
}

@Component
class Runner implements CommandLineRunner {

	@Autowired
	BeanService beanService;

	@Override
	public void run(String... args) throws Exception {
		beanService.doSomeThing();
	}
}



@SpringBootApplication
@EnableConfigurationProperties(MyConfig.class)
public class BeanRegisterApplication {
	public static void main(String[] args) {
		SpringApplication.run(BeanRegisterApplication.class, args);
	}

}
