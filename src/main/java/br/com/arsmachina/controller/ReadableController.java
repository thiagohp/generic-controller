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

package br.com.arsmachina.controller;

import java.io.Serializable;
import java.util.List;

import br.com.arsmachina.dao.SortCriterion;


/**
 * Interface that defines a read-only, generic and generified controller for a given entity class.
 * 
 * @author Thiago H. de Paula Figueiredo
 * @param <T> the entity class related to this controller.
 * @param <K> the type of the field that represents the entity class' primary key.
 */

public interface ReadableController<T, K extends Serializable> {
	
	/**
	 * Returns the total number of objects of this class.
	 * @return an <code>int</code>.
	 */
	int countAll();

	/**
	 * Returns the object with a given primary key value.
	 * 
	 * @param id a <code>K</code>.
	 * @return a <code>T</code>.
	 */
	T findById(K id);

	/**
	 * Returns the all instances of the related entity class.
	 * 
	 * @return a {@link List} of <code>T</code>.
	 */
	List<T> findAll();

	/**
	 * Returns the objects with some given primary key values.
	 * 
	 * @param ids a <code>K</code> array.
	 * @return a {@link List} of <code>T</code>.
	 */
	List<T> findByIds(K... ids);
	
	/**
	 * Executes a query by example.
	 * 
	 * @param example a <code>T</code>.
	 * @return a {@link List} of <code>T</code>.
	 */
	List<T> findByExample(T example);

	/**
	 * Returns the all instances of the related entity class, but in a paginated fashion.
	 * 
	 * @param firstResult an <code>int</code> with the index of the first object to be returned.
	 * The first object has index 0.
	 * @param maxResults an <code>int</code> with the maximum number of objects to be returned.
	 * @param sortConstraints an {@link SortConstraint} array used to define how the returned list
	 * will be sorted.
	 * @return a {@link List} of <code>T</code>.
	 */
	List<T> findAll(int firstResult, int maxResults, SortCriterion... sortConstraints);
	
	/**
	 * Reattaches an object to the persistence context, if there is one. The object store
	 * must not be changed. The attached object, which not necessarily is the one passed
	 * as a parameter, is returned.
	 * 
	 * @param object a <code>T</code>.
	 * @return object a <code>T</code>.
	 */
	T reattach(T object);

}
