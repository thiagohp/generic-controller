// Copyright 2008 Thiago H. de Paula Figueiredo
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package br.com.arsmachina.controller.impl;

import java.io.Serializable;
import java.util.List;

import br.com.arsmachina.controller.Controller;
import br.com.arsmachina.dao.DAO;
import br.com.arsmachina.dao.SortCriterion;


/**
 * Abstract class that implements the {@link Controller} interface by delegating all method calls to
 * a {@link DAO} passed through its constructor.
 * 
 * @author Thiago H. de Paula Figueiredo (ThiagoHP)
 * @param <T> the entity class related to this controller.
 * @param <K> the type of the field that represents the entity class' primary key.
 */
public abstract class ControllerImpl<T, K extends Serializable> implements Controller<T, K> {

	private DAO<T, K> dao;

	/**
	 * Single constructor of this class.
	 * 
	 * @param dao a {@link DAO<T, K>}. It cannot be <code>null</code>.
	 */
	public ControllerImpl(DAO<T, K> dao) {

		if (dao == null) {
			throw new IllegalArgumentException("Parameter dao cannot be null");
		}

		this.dao = dao;

	}

	/**
	 * Invokes <code>delegate.countAll()<code>.
	 * @return
	 * @see br.com.arsmachina.dao.ReadableDAO#countAll()
	 */
	public int countAll() {
		return dao.countAll();
	}

	/**
	 * Invokes <code>dao.delete()<code>.
	 * @param id
	 * @see br.com.arsmachina.dao.WriteableDAO#delete(java.io.Serializable)
	 */
	public void delete(K id) {
		dao.delete(id);
	}

	/**
	 * Invokes <code>dao.delete()<code>.
	 * @param object
	 * @see br.com.arsmachina.dao.WriteableDAO#delete(java.lang.Object)
	 */
	public void delete(T object) {
		dao.delete(object);
	}

	/**
	 * Invokes <code>dao.evict()<code>.
	 * @param object
	 * @see br.com.arsmachina.dao.WriteableDAO#evict(java.lang.Object)
	 */
	public void evict(T object) {
		dao.evict(object);
	}

	/**
	 * Invokes <code>dao.findAll()<code>.
	 * @return
	 * @see br.com.arsmachina.dao.ReadableDAO#findAll()
	 */
	public List<T> findAll() {
		return dao.findAll();
	}

	/**
	 * Invokes <code>dao.findAll()<code>.
	 * @param firstResult
	 * @param maxResults
	 * @param sortCriteria
	 * @return
	 * @see br.com.arsmachina.dao.ReadableDAO#findAll(int, int, br.com.arsmachina.dao.SortConstraint[])
	 */
	public List<T> findAll(int firstResult, int maxResults, SortCriterion... sortCriteria) {
		return dao.findAll(firstResult, maxResults, sortCriteria);
	}

	/**
	 * Invokes <code>dao.findById()<code>.
	 * @param ids
	 * @return
	 * @see br.com.arsmachina.dao.ReadableDAO#findById(K[])
	 */
	public List<T> findByIds(K... ids) {
		return dao.findByIds(ids);
	}

	/**
	 * Invokes <code>dao.findById()<code>.
	 * @param id
	 * @return
	 * @see br.com.arsmachina.dao.ReadableDAO#findById(java.io.Serializable)
	 */
	public T findById(K id) {
		return dao.findById(id);
	}

	/**
	 * Invokes <code>dao.merge()<code>.
	 * @param object
	 * @return
	 * @see br.com.arsmachina.dao.WriteableDAO#merge(java.lang.Object)
	 */
	public T merge(T object) {
		return dao.merge(object);
	}

	/**
	 * Invokes <code>dao.save()<code>.
	 * @param object
	 * @see br.com.arsmachina.dao.WriteableDAO#save(java.lang.Object)
	 */
	public void save(T object) {
		dao.save(object);
	}

	/**
	 * Invokes <code>dao.save()<code> if the object is persistent and 
	 * <code>dao.save()<code> otherwise. The object is considered persistent
	 * if {@link #isPersistent(Object)} returns <code>true</code>. 
	 * @param object a <code>T</code>.
	 * @see br.com.arsmachina.dao.WriteableDAO#saveOrUpdate(java.lang.Object)
	 * @see br.com.arsmachina.controller.WriteableController
	 */
	public void saveOrUpdate(T object) {

		if (isPersistent(object)) {
			update(object);
		}
		else {
			save(object);
		}

	}

	/**
	 * Invokes <code>dao.update()<code>.
	 * @param object
	 * @see br.com.arsmachina.dao.WriteableDAO#update(java.lang.Object)
	 */
	public void update(T object) {
		dao.update(object);
	}

	/**
	 * Invokes <code>dao.isPersistent()<code>.
	 * @param object
	 * @return
	 * @see br.com.arsmachina.dao.WriteableDAO#isPersistent(java.lang.Object)
	 */
	public boolean isPersistent(T object) {
		return dao.isPersistent(object);
	}

	/**
	 * Invokes <code>delegate.refresh()<code>.
	 * @param object
	 * @see br.com.arsmachina.dao.ReadableDAO#refresh(java.lang.Object)
	 */
	public void refresh(T object) {
		dao.refresh(object);
	}

}
