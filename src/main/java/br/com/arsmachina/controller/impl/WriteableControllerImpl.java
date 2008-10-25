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

import br.com.arsmachina.controller.WriteableController;
import br.com.arsmachina.dao.WriteableDAO;

/**
 * Abstract class that implements the {@link WriteableController} interface by delegating all method
 * calls to a {@link WriteableDAO} passed through its constructor.
 * 
 * @author Thiago H. de Paula Figueiredo
 * @param <T> the entity class related to this controller.
 * @param <K> the type of the field that represents the entity class' primary key.
 */
public abstract class WriteableControllerImpl<T, K extends Serializable> implements
		WriteableController<T, K> {

	private WriteableDAO<T, K> dao;

	/**
	 * Single constructor of this class.
	 * 
	 * @param dao a {@link WriteableDAO<T, K>}. It cannot be <code>null</code>.
	 */
	public WriteableControllerImpl(WriteableDAO<T, K> dao) {

		if (dao == null) {
			throw new IllegalArgumentException("Parameter dao cannot be null");
		}

		this.dao = dao;

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
	 * @see br.com.arsmachina.dao.WriteableDAO#update(java.lang.Object)
	 */
	public T update(T object) {
		return dao.update(object);
	}

	/**
	 * Invokes <code>dao.evict()<code>.
	 * @param object
	 * @see br.com.arsmachina.dao.WriteableDAO#evict(java.lang.Object)
	 */
	public void evict(T object) {
		dao.evict(object);
	}

}
