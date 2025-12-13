package demandlane.com.booklending.configuration;

import java.io.IOException;
import java.io.InputStream;
import org.kie.api.builder.Message;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import demandlane.com.booklending.utility.BookLoanUtility;


@Configuration

public class DroolsConfig {

    @Bean
    public KieContainer kieContainer() {
        //InputStream inputStream = new ClassPathResource("drools/BookLoanRule.xlsx").getInputStream();
        //String rulesFile = BookLoanUtility.convertInputStreamToString(inputStream);
        final String rules_file = "drools/BookLoanRule.xlsx";
        
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        
        // Load the Excel file from the classpath
        kfs.write(ResourceFactory.newClassPathResource(rules_file));
        
        KieBuilder kb = ks.newKieBuilder(kfs).buildAll();
        
        // Check for errors during compilation
        Results results = kb.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Drools compilation errors:\n" + results.toString());
        }
        
        return ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
    }

  

}
