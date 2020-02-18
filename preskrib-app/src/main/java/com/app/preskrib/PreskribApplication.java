package com.app.preskrib;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;



@SpringBootApplication
@PropertySource("classpath:/preskrib-app.properties")
@ComponentScan({"com.techtrail.commons.db","com.techtrail.commons.auth",
	"com.preskrib.drugmgr","com.preskrib.customer","com.preskrib.order","com.techtrail.commons.notification"
	,"com.preskrib.invoice","com.preskrib.billing"})
public class PreskribApplication extends SpringBootServletInitializer implements CommandLineRunner{

	
	public static void main(String[] args) {
		SpringApplication.run(PreskribApplication.class, args);
	}
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(PreskribApplication.class);
	    }

	@Override
	public void run(String... args) throws Exception {
	}

}
