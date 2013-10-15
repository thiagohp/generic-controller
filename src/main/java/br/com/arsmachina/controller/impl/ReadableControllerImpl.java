// Copyright 2008-2013 Thiago H. de Paula Figueiredo
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

import br.com.arsmachina.controller.ReadableController;
import br.com.arsmachina.dao.ReadableDAO;
import br.com.arsmachina.dao.SortCriterion;

/**
 * Abstract class that implements the {@link ReadableController} interface by delegating all method
 * calls to a {@link ReadableDAO} passed through its constructor.
 * 
 * @author Thiago H. de Paula Figueiredo
 * @param <T> the entity class related to this controller.
 * @param <K> the type of the field that represents the entity class' primary key.
 */
public abstract class ReadableControllerImpl<T, K extends Serializable> implements ReadableController<T, K> {

	private ReadableDAO<T, K> dao;

	/**
	 * Single constructor of this class.
	 * 
	 * @param dao a {@link DAO<T, K>}. It cannot be <code>null</code>.
	 */
	public ReadableControllerImpl(ReadableDAO<T, K> dao) {

		if (dao == null) {
			throw new IllegalArgumentException("Parameter dao cannot be null");
		}

		this.dao = dao;

	}

	/**
	 * Invokes <code>dao.countAll()<code>.
	 * @return
	 * @see br.com.arsmachina.dao.ReadableDAO#countAll()
	 */
	public long countAll() {
		return dao.countAll();
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
	 * Invokes <code>dao.findByExample()<code>.
	 * @param example
	 * @return
	 * @see br.com.arsmachina.dao.ReadableDAO#findByExample(java.lang.Object)
	 */
	public List<T> findByExample(T example) {
		return dao.findByExample(example);
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
	 * Invokes <code>dao.refresh()<code>.
	 * @param object
	 * @see br.com.arsmachina.dao.ReadableDAO#refresh(java.lang.Object)
	 */
	public T refresh(T object) {
		return dao.refresh(object);
	}

	/**
	 * Invokes <code>dao.reattach()<code>.
	 * @param object
	 * @return
	 * @see br.com.arsmachina.dao.ReadableDAO#reattach(java.lang.Object)
	 */
	public T reattach(T object) {
		return dao.reattach(object);
	}

}
