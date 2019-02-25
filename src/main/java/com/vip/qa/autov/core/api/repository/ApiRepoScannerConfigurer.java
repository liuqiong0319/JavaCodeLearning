package com.vip.qa.autov.core.api.repository;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import com.vip.qa.autov.core.repository.BasicRepoScannerConfigurer;

public class ApiRepoScannerConfigurer extends BasicRepoScannerConfigurer {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		processPropertyPlaceHolders();
		ClassPathApiRepoScanner scanner = new ClassPathApiRepoScanner(registry);
		scanner.setResourceLoader(this.applicationContext);
		scanner.setRequesterPackageMap(requesterPackageMap);
		scanner.setRequesterClassMap(requesterClassMap);
		scanner.registerFilters();
		scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage,
				ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
	}

}
