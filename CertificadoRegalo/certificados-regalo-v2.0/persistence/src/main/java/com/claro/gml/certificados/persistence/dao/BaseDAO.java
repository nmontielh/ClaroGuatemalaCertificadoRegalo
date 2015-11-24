package com.claro.gml.certificados.persistence.dao;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see DAO generico para la capa de persistencia, Se encuentra incompleto
 * @author nmontielh
 * 
 */

@Repository
@Transactional("mayaTM")
public class BaseDAO {

	private Logger logger = LoggerFactory.getLogger(BaseDAO.class);

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @see Metodo para salvar un objeto en la base de datos y entregando el
	 *      objeto id resultante
	 * @param <T>
	 * @param entity
	 * @param entityClass
	 * @return
	 * @throws IOException
	 */
	public <T> Serializable save(T entity, Class<T> entityClass) throws IOException {
		logger.debug("saving {}", entity);
		return getSessionFactory().getCurrentSession().save(entity);
	}

	/**
	 * @see Salva o actualiza el objeto, en ocasiones hay problemas en guardar
	 *      correctamente el objeto, por lo tanto hacemos merge para realizar la
	 *      actualizacion
	 * @param <T>
	 * @param entity
	 * @param entityClass
	 * @throws IOException
	 */
	public <T> void saveOrUpdate(T entity, Class<T> entityClass) throws IOException {
		logger.debug("saveOrUpdate {}", entity);
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error("Error {}", e.getMessage());
			logger.debug("merging {}", entity);
			getSessionFactory().getCurrentSession().merge(entity);
		}
	}

	/**
	 * @see Metodo para actualizar una entidad
	 * @param <T>
	 * @param entity
	 * @param entityClass
	 * @throws IOException
	 */
	public <T> void update(T entity, Class<T> entityClass) throws IOException {
		logger.debug("update {}", entity);
		try {
			getSessionFactory().getCurrentSession().update(entity);
		} catch (Exception e) {
			logger.error("Error {}", e.getMessage());
			logger.debug("merging {}", entity);
			getSessionFactory().getCurrentSession().merge(entity);
		}
	}

	/**
	 * @see Borrar un objeto de la base de datos
	 * @param <T>
	 * @param entity
	 * @param entityClass
	 * @throws IOException
	 */
	public <T> void delete(T entity, Class<T> entityClass) throws IOException {
		logger.debug("delete {}", entity);
		getSessionFactory().getCurrentSession().delete(entity);
	}

	/**
	 * @see Metodo que nos trae un objeto con base a la consulta realizada
	 * @param queryName
	 * @param parameters
	 * @param types
	 * @param cl
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T findObjectByQuery(String queryName, Object[] parameters, Type[] types, Class<T> cl) {

		logger.debug("findObjectByQuery {}", queryName);

		Query query = getSessionFactory().getCurrentSession().getNamedQuery(queryName);

		query.setParameters(parameters, types);

		T resultado = null;

		List<T> lista = query.list();

		if (!lista.isEmpty())
			resultado = lista.get(0);

		return resultado;
	}

	/**
	 * @see Entrega una lista del resultado ejecutado por el query, pasandole
	 *      parametros
	 * @param queryName
	 * @param parameters
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public <T> List<T> findListByQuery(String queryName, Object[] parameters, Type[] types, Class<T> cl)
			throws IOException {

		logger.debug("findListByQuery {}", queryName);

		Query query = getSessionFactory().getCurrentSession().getNamedQuery(queryName);

		query.setParameters(parameters, types);

		try {

			return (List<T>) query.list();

		} catch (IndexOutOfBoundsException ioe) {
			logger.error("Error {}", ioe.getMessage());
			throw new IOException(ioe);
		}

	}

	/**
	 * @see Se ejecuta la consulta entregando una lista sin paso de parametros
	 * @param <T>
	 * @param queryName
	 * @param cl
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findListByQuery(String queryName, Class<T> cl) throws IOException {

		logger.debug("findListByQuery {}", queryName);

		Query query = getSessionFactory().getCurrentSession().getNamedQuery(queryName);

		try {
			return (List<T>) query.list();

		} catch (IndexOutOfBoundsException ioe) {
			logger.error("Error {}", ioe.getMessage());
			throw new IOException(ioe);
		}

	}

	/**
	 * @see Se ejecuta la consulta entregando una lista sin paso de parametros
	 * @param <T>
	 * @param queryName
	 * @param cl
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findListByQuery(String queryName, FlushMode flushMode, Class<T> cl) throws IOException {

		logger.debug("findListByQuery {}", queryName);

		Query query = getSessionFactory().getCurrentSession().getNamedQuery(queryName);
		query.setFlushMode(flushMode);
		try {
			return (List<T>) query.list();

		} catch (IndexOutOfBoundsException ioe) {
			logger.error("Error {}", ioe.getMessage());
			throw new IOException(ioe);
		}

	}

	/**
	 * @see lee una entidad de la base de datos por llave
	 * @param <T>
	 * @param key
	 * @param entityClass
	 * @return
	 * @throws IOException
	 */
	public <T> T read(Serializable key, Class<T> entityClass) throws IOException {

		logger.debug("read {}", key);
		return (T) getSessionFactory().getCurrentSession().get(entityClass, key);
	}

	public <T> T readForUpdate(Serializable key, Class<T> entityClass, LockMode lockMode) throws IOException {
		logger.debug("read {}", key);
		return (T) getSessionFactory().getCurrentSession().get(entityClass, key, new LockOptions(lockMode));
	}

	/**
	 * @see Metodo para desligar un objeto del cache de primer nivel
	 * @param obj
	 */
	public void evictObject(Object obj) {
		logger.debug("evictObject:{}", obj);
		this.getSessionFactory().getCurrentSession().evict(obj);
	}

	/**
	 * @author nmontielh
	 * @see servicio para ejectar un query batch en sql por ejemplo, insert,
	 *      update, delete
	 * @param queryName
	 *            : nombre del query en sql
	 * @param parameters
	 *            : Arreglo de objetos de los parametros de entrada
	 * @param types
	 *            : arreglo de los tipos de datos
	 * @return el número de registros eliminados.
	 */
	public int executeBatchByQuery(String queryName, Object[] parameters, Type[] types) {

		logger.debug("executeBatchByQuery:{}", queryName);

		int resultado = 0;

		Query query = this.getSessionFactory().getCurrentSession().getNamedQuery(queryName);

		int contador = 0;

		for (Type type : types) {

			if (type instanceof IntegerType)
				query.setInteger(contador, (Integer) parameters[contador]);
			if (type instanceof StringType)
				query.setString(contador, (String) parameters[contador]);

			contador++;

		}

		resultado = query.executeUpdate();

		return resultado;
	}

	public int executeBatchByQuery(String queryName, Object[] parameters, Type[] types, String inParamName,
			Collection inParameters) {
		return -1;
	}

	/**
	 * @author nmontielh
	 * @see Entrega un objeto con base a una consulta, util para mandar llamar
	 *      instrucciones simples de SQL
	 * @param queryName
	 * @param cl
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T findObjectByQuery(String queryName, Class<T> cl) {

		logger.debug("findObjectByQuery {}", queryName);

		Query query = getSessionFactory().getCurrentSession().getNamedQuery(queryName);

		T resultado = null;

		List<T> lista = query.list();

		if (!lista.isEmpty())
			resultado = lista.get(0);

		return resultado;
	}

	/**
	 * @see
	 * @param queryName
	 * @param cl
	 * @param mode
	 *            , FlushMode para sentencias que se encuentren dentro de un
	 *            interceptor de hibernate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public <T> List<T> findListByQuery(String queryName, Class<T> cl, FlushMode mode) {

		logger.debug("findObjectByQuery {}", queryName);

		Query query = getSessionFactory().getCurrentSession().getNamedQuery(queryName);
		query.setFlushMode(FlushMode.MANUAL);

		return (List<T>) query.list();

	}

	/**
	 * @see se agrega metodo flush para hacer comit explicito a la base de datos
	 *      (No es muy buena practica)
	 */
	public void flush() {
		this.getSessionFactory().getCurrentSession().flush();
	}

}
