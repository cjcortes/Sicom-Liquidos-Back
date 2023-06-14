package com.sicom.ms.infrastructure.domainconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

<<<<<<< HEAD

=======
>>>>>>> release
@Configuration
@ComponentScan(basePackages = "com.sicom.ms.domain.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfiguration {

}
