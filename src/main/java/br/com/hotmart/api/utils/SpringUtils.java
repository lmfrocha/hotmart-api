package br.com.hotmart.api.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("singleton")
@Component
public class SpringUtils implements ApplicationContextAware{

	private static ApplicationContext CONTEXT;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		initializeApplicationContext(applicationContext);
	}

	private static void initializeApplicationContext(ApplicationContext applicationContext) {
		CONTEXT = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return CONTEXT;
	}

	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}
}
