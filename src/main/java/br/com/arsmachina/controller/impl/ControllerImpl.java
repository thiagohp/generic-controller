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
 * @author Thiago H. de Paula Figueiredo
 * @param <T> the entity class related to this controller.
 * @param <K> the type of the field that represents the entity class' primary key.
 */
public abstract class ControllerImpl<T, K extends Serializable> implements Controller<T, K> {

	private ReadableControllerImpl<T, K> readableController;

	private WriteableControllerImpl<T, K> writeableController;

	/**
	 * Single constructor of this class.
	 * 
	 * @param dao a {@link DAO<T, K>}. It cannot be <code>null</code>.
	 */
	public ControllerImpl(DAO<T, K> dao) {

		if (dao == null) {
			throw new IllegalArgumentException("Parameter dao cannot be null");
		}

		readableController = new InternalReadableController(dao);
		writeableController = new InternalWriteableController(dao);

	}

	/**
	 * Invokes <code>dao.countAll()<code>.
	 * @return
	 * @see br.com.arsmachina.controller.impl.ReadableControllerImpl#countAll()
	 */
	public int countAll() {
		return readableController.countAll();
	}

	/**
	 * Invokes <code>dao.findAll()<code>.
	 * @return
	 * @see br.com.arsmachina.controller.impl.ReadableControllerImpl#findAll()
	 */
	public List<T> findAll() {
		return readableController.findAll();
	}

	/**
	 * Invokes <code>dao.findAll()<code>.
	 * @param firstResult
	 * @param maxResults
	 * @param sortCriteria
	 * @return
	 * @see br.com.arsmachina.controller.impl.ReadableControllerImpl#findAll(int, int, br.com.arsmachina.dao.SortCriterion[])
	 */
	public List<T> findAll(int firstResult, int maxResults, SortCriterion... sortCriteria) {
		return readableController.findAll(firstResult, maxResults, sortCriteria);
	}

	/**
	 * Invokes <code>dao.findByExample()<code>.
	 * @param example
	 * @return
	 * @see br.com.arsmachina.controller.impl.ReadableControllerImpl#findByExample(java.lang.Object)
	 */
	public List<T> findByExample(T example) {
		return readableController.findByExample(example);
	}

	/**
	 * Invokes <code>dao.findById()<code>.
	 * @param id
	 * @return
	 * @see br.com.arsmachina.controller.impl.ReadableControllerImpl#findById(java.io.Serializable)
	 */
	public T findById(K id) {
		return readableController.findById(id);
	}

	/**
	 * Invokes <code>dao.findByIds()<code>.
	 * @param ids
	 * @return
	 * @see br.com.arsmachina.controller.impl.ReadableControllerImpl#findByIds(K[])
	 */
	public List<T> findByIds(K... ids) {
		return readableController.findByIds(ids);
	}

	/**
	 * Invokes <code>dao.refresh()<code>.
	 * @param object
	 * @see br.com.arsmachina.controller.impl.ReadableControllerImpl#refresh(java.lang.Object)
	 */
	public void refresh(T object) {
		readableController.refresh(object);
	}

	/**
	 * Invokes <code>dao.delete()<code>.
	 * @param id
	 * @see br.com.arsmachina.controller.impl.WriteableControllerImpl#delete(java.io.Serializable)
	 */
	public void delete(K id) {
		writeableController.delete(id);
	}

	/**
	 * Invokes <code>dao.delete()<code>.
	 * @param object
	 * @see br.com.arsmachina.controller.impl.WriteableControllerImpl#delete(java.lang.Object)
	 */
	public void delete(T object) {
		writeableController.delete(object);
	}

	/**
	 * Invokes <code>dao.evict()<code>.
	 * @param object
	 * @see br.com.arsmachina.controller.impl.WriteableControllerImpl#evict(java.lang.Object)
	 */
	public void evict(T object) {
		writeableController.evict(object);
	}

	/**
	 * Invokes <code>dao.isPersistent()<code>.
	 * @param object
	 * @return
	 * @see br.com.arsmachina.controller.impl.WriteableControllerImpl#isPersistent(java.lang.Object)
	 */
	public boolean isPersistent(T object) {
		return writeableController.isPersistent(object);
	}

	/**
	 * Invokes <code>dao.save()<code>.
	 * @param object
	 * @see br.com.arsmachina.controller.impl.WriteableControllerImpl#save(java.lang.Object)
	 */
	public void save(T object) {
		writeableController.save(object);
	}

	/**
	 * Invokes <code>dao.saveOrUpdate()<code>.
	 * @param object
	 * @see br.com.arsmachina.controller.impl.WriteableControllerImpl#saveOrUpdate(java.lang.Object)
	 */
	public T saveOrUpdate(T object) {

		if (isPersistent(object)) {
			return update(object);
		}
		else {
			save(object);
			return object;
		}
		
	}

	/**
	 * Invokes <code>dao.update()<code>.
	 * @param object
	 * @see br.com.arsmachina.controller.impl.WriteableControllerImpl#update(java.lang.Object)
	 */
	public T update(T object) {
		return writeableController.update(object);
	}
	
	/**
	 * Invokes <code>dao.reattach()<code>.
	 * @param object
	 * @return
	 * @see br.com.arsmachina.controller.ReadableController#reattach(java.lang.Object)
	 */
	public T reattach(T object) {
		return readableController.reattach(object);
	}

	/**
	 * Concrete {@link ReadableControllerImpl} subclass.
	 * 
	 * @author Thiago H. de Paula Figueiredo
	 */
	private final class InternalReadableController extends ReadableControllerImpl<T, K> {

		/**
		 * @param dao
		 */
		public InternalReadableController(DAO<T, K> dao) {
			super(dao);
		}

	}

	/**
	 * Concrete {@link WriteableControllerImpl} subclass.
	 * 
	 * @author Thiago H. de Paula Figueiredo
	 */
	private final class InternalWriteableController extends WriteableControllerImpl<T, K> {

		/**
		 * @param dao
		 */
		public InternalWriteableController(DAO<T, K> dao) {
			super(dao);
		}

	}

}
