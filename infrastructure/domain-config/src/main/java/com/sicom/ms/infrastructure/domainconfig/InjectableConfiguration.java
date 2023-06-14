package com.sicom.ms.infrastructure.domainconfig;

<<<<<<< HEAD

=======
>>>>>>> release
import com.sicom.ms.domain.model.di.Injectable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.sicom.ms.domain",
        includeFilters = {
                @ComponentScan.Filter(Injectable.class)
        })
public class InjectableConfiguration {

}
