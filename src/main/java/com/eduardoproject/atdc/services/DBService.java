package com.eduardoproject.atdc.services;

import com.eduardoproject.atdc.entities.Category;
import com.eduardoproject.atdc.entities.Company;
import com.eduardoproject.atdc.entities.Product;
import com.eduardoproject.atdc.entities.User;
import com.eduardoproject.atdc.entities.enums.TypeUser;
import com.eduardoproject.atdc.entities.enums.UserProfile;
import com.eduardoproject.atdc.repositories.CategoryRepository;
import com.eduardoproject.atdc.repositories.CompanyRepository;
import com.eduardoproject.atdc.repositories.ProductRepository;
import com.eduardoproject.atdc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.Date;
import java.text.DateFormat;
import java.util.Set;

@Service
public class DBService {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CompanyRepository companyRepository;

    public void instantiateDataBase() throws ParseException {
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        Company c1 = new Company("Maria's SexShop", "Festa da Maria", "988888888");
        Company c2 = new Company("Clinica da Felicidade Ltda", "Clinica de aborto Feto Feliz", "977777777");
        Company c3 = new Company("Biqueira de Cajazeiras", "Ponto da Droga", "11022448000122");

        Category cat1 = new Category(null, "Electronics", 0.10, 10);
        Category cat2 = new Category(null, "Books", 0.3, 10);
        Category cat3 = new Category(null, "Computers", 0.2, 5);
        Category cat4 = new Category(null, "Vei. de Tração pessoal e animal", 0.1, 15);
        Category cat5 = new Category(null, "Eqp. Agricola", 0.1, 10);
        Category cat6 = new Category(null, "Mobiliario em Geral", 0.1, 10);
        Category cat7 = new Category(null, "Armamento", 0.1, 20);

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, sdf.parse("20190413"));
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, sdf.parse("20180513"));
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, sdf.parse("20171213"));
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, sdf.parse("20200415"));
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, sdf.parse("20200927"));
        Product p6 = new Product(null, "Colcha", "Colcha de retalhos feita pela vovó",200.00, sdf.parse("20190719"));
        Product p7 = new Product(null, "TV true color", "TV para a sala de reuniões",1200.00, sdf.parse("20171221"));
        Product p8 = new Product(null, "Roçadeira", "Roçadeira para jardinagem",800.00, sdf.parse("20130530"));
        Product p9 = new Product(null, "Abajour", "Abajour para a sala de reuniões",100.00,sdf.parse("20140804"));
        Product p10 = new Product(null, "Carroça", "Punição para o estagiário",18000.00, sdf.parse("20000923"));
        Product p11 = new Product(null, "Fuzil AR-15", "Equipamento para segurança",9000.00, sdf.parse("20150713"));

       /* cat1.getProducts().addAll(Arrays.asList(p2, p7));
        cat2.getProducts().addAll(Arrays.asList(p1, p5));
        cat3.getProducts().addAll(Arrays.asList(p3, p4));
        cat4.getProducts().addAll(Arrays.asList(p10));
        cat5.getProducts().addAll(Arrays.asList(p8));
        cat6.getProducts().addAll(Arrays.asList(p6, p9));
        cat7.getProducts().addAll(Arrays.asList(p11));*/

        p1.setCategory(cat2);
        p2.setCategory(cat1);
        p3.setCategory(cat3);
        p4.setCategory(cat3);
        p5.setCategory(cat2);
        p6.setCategory(cat6);
        p7.setCategory(cat1);
        p8.setCategory(cat5);
        p9.setCategory(cat6);
        p10.setCategory(cat4);
        p11.setCategory(cat7);

        p1.setCompany(c1);
        p2.setCompany(c1);
        p3.setCompany(c3);
        p4.setCompany(c2);
        p5.setCompany(c2);
        p6.setCompany(c1);
        p7.setCompany(c3);
        p8.setCompany(c1);
        p9.setCompany(c2);
        p10.setCompany(c2);
        p11.setCompany(c1);

        User user1 = new User("Maria Silva", "maria@gmail.com", TypeUser.USER,passwordEncoder.encode("123"));
        User user2 = new User("Jose Santos", "jose@gmail.com", TypeUser.ADMIN, passwordEncoder.encode("123"));
        User user3 = new User("Joao Maia", "joao@gmail.com", TypeUser.USER, passwordEncoder.encode("123"));

        user1.getTels().addAll(Arrays.asList("1234567","12323345"));
        user2.getTels().addAll(Arrays.asList("406441663","882132125"));

        user1.addProfile(UserProfile.USER);
        user2.addProfile(UserProfile.ADMIN);
        user3.addProfile(UserProfile.USER);











        companyRepository.saveAll(Arrays.asList(c1,c2,c3));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2,cat3,cat4,cat5,cat6,cat7));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
        userRepository.saveAll(Arrays.asList(user1,user2,user3));


    }

}
