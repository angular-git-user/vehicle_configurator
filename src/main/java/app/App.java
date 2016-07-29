package app;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import app.connection.HibernateConnectionUtil;
import app.entityClasses.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class App 
{
	public static void main( String[] args )
	{
		SpringApplication.run(App.class, args);
		SessionFactory sf = HibernateConnectionUtil.getConnectionFactory();

		Session session = sf.openSession();
		session.beginTransaction();

		/*String sql = "select * from feature_types f where feature_name = :feature_name";
    	SQLQuery query = session.createSQLQuery(sql);
    	query.setString("feature_name", EFeatures.STANDARD_FEATURE.toString());
    	query.addEntity(FeatureTypes.class);
		features = (List<FeatureTypes>) query.list();*/
		
		//Model m = (Model)session.get(Model.class, 1);
		session.getTransaction().commit();
		sf.close();
	}

	@Bean
	public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2)  
				.select()                                  
				.apis(RequestHandlerSelectors.any())              
				.paths(PathSelectors.any())                          
				.build();                                           
	}
}
