package com.claro.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.claro.transfer.certificados.constants.ErrorCatalog;
import com.claro.transfer.certificados.exception.BussinesException;

public class ExceptionUtils {

	private static Logger log = LoggerFactory.getLogger(ExceptionUtils.class);

	public static void propagateBussinesException(BussinesException e) throws ClaroServiceException {
		ErrorCatalog error = e.getError();

		log.error("type [{}] code [{}] description []", error, error.getCode(), error.getMessage());

		ClaroServiceFault faultInfo = new ClaroServiceFault(error);
		throw new ClaroServiceException(ErrorCatalog.BUSSINES_ERROR.toString(), faultInfo);
	}

	public static void propagateUncheckedException(Throwable e) throws ClaroServiceException {

		log.error("type [{}] message [{}] ", ErrorCatalog.UNEXPECTED_ERROR.toString(), e.getMessage(), e);

		ClaroServiceFault faultInfo = new ClaroServiceFault(ErrorCatalog.UNEXPECTED_ERROR.getCode(), e.getMessage());
		throw new ClaroServiceException(ErrorCatalog.UNEXPECTED_ERROR.toString(), faultInfo);
	}

}
