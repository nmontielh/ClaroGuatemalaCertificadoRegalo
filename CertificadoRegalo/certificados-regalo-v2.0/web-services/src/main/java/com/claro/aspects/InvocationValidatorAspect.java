package com.claro.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InvocationValidatorAspect {

	private static Logger log = LoggerFactory.getLogger(InvocationValidatorAspect.class);

	@Before("execution(* com.claro.ws.service.impl.CertificadosEndpoint.*(..)) ")
	public void validateRequest(JoinPoint joinPoint) {

		log.info("printing args");

		Object[] args = joinPoint.getArgs();

		for (Object arg : args) {
			log.info("arg [{}]", arg);
		}

	}

}
