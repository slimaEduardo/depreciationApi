package com.eduardoproject.atdc.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

import com.eduardoproject.atdc.entities.User;
import com.eduardoproject.atdc.entities.enums.TypeUser;
import com.eduardoproject.atdc.entities.enums.UserProfile;
import com.eduardoproject.atdc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.eduardoproject.atdc.entities.Category;
import com.eduardoproject.atdc.entities.Company;
import com.eduardoproject.atdc.entities.Product;
import com.eduardoproject.atdc.repositories.CategoryRepository;
import com.eduardoproject.atdc.repositories.CompanyRepository;
import com.eduardoproject.atdc.repositories.ProductRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception{
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		Company c1 = new Company("Maria's SexShop", "Festa da Maria", "988888888");
		Company c2 = new Company("Clinica da Felicidade Ltda", "Clinica de aborto Feto Feliz", "977777777");

		User user1 = new User("Maria Silva", "maria@gmail.com", TypeUser.USER,passwordEncoder.encode("123"));
		User user2 = new User("Jose Santos", "jose@gmail.com", TypeUser.ADMIN, passwordEncoder.encode("123"));
		user1.getTels().addAll(Arrays.asList("1234567","12323345"));
		user2.getTels().addAll(Arrays.asList("406441663","882132125"));
		user1.addProfile(UserProfile.USER);
		user2.addProfile(UserProfile.ADMIN);

		Category cat1 = new Category(null, "Electronics", 0.15, 6);
		Category cat2 = new Category(null, "Books", 0.3, 10);
		Category cat3 = new Category(null, "Computers", 0.2, 5);
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, simpleDateFormat.parse("20190413"));
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, simpleDateFormat.parse("20180513"));
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, simpleDateFormat.parse("20171213"));
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, simpleDateFormat.parse("20200415"));
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, simpleDateFormat.parse("20200927"));

		companyRepository.saveAll(Arrays.asList(c1,c2));
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		userRepository.saveAll(Arrays.asList(user1,user2));
		
		p1.setCategory(cat2);
		p2.setCategory(cat1);
		p3.setCategory(cat3);
		p4.setCategory(cat3);
		p5.setCategory(cat2);
		
		p1.setCompany(c1);
		p2.setCompany(c1);
		p3.setCompany(c1);
		p4.setCompany(c2);
		p5.setCompany(c2);



		userRepository.saveAll(Arrays.asList(user1,user2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
	}
}
