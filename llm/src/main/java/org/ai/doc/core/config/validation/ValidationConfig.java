package org.ai.doc.core.config.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
class ValidationConfig {

  @Bean
  LocalValidatorFactoryBean getValidator() {

    var bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource());

    return bean;
  }

  @Bean
  MessageSource messageSource() {

    var messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");

    return messageSource;
  }
}
