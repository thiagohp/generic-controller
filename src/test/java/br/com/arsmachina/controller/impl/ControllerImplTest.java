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

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.arsmachina.dao.DAO;

/**
 * Test class for {@link ControllerImpl}.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class ControllerImplTest {
	
	final static String OBJECT = "persistent";
	final static String OTHER_OBJECT = "other persistent";
	final static Integer ID = 1;

	private DAO<String, Integer> dao;

	private ControllerImpl<String, Integer> controller;

	@SuppressWarnings( { "unused", "unchecked" })
	@BeforeMethod
	private void setUp() {

		dao = EasyMock.createMock(DAO.class);
		controller = new DummyGenericController(dao);

	}
	
	/**
	 * Tests {@link ControllerImpl#ControllerImpl(DAO)}.
	 */
	@Test
	public void constructor() {
		
		boolean ok = false;
		
		try {
			new DummyGenericController(null);
		}
		catch (IllegalArgumentException e) {
			ok = true;
		}
		
		assert ok;
		
		new DummyGenericController(dao);
		
	}
	
	/**
	 * Tests {@link ControllerImpl#save(Object)}.
	 */
	@Test
	public void save() {

		dao.save(OBJECT);
		EasyMock.replay(dao);
		
		controller.save(OBJECT);
		EasyMock.verify(dao);
		
	}
	
	/**
	 * Tests {@link ControllerImpl#save(Object)}.
	 */
	@Test
	public void update() {

		final String OTHER_OBJECT = "sfasdf";
		
		EasyMock.expect(dao.update(OBJECT)).andReturn(OTHER_OBJECT);
		EasyMock.replay(dao);
		
		final String returned = controller.update(OBJECT);
		EasyMock.verify(dao);
		
		assert OTHER_OBJECT == returned;
		
	}
	
	/**
	 * Tests {@link ControllerImpl#delete(<K>))}.
	 */
	@Test
	public void delete_id() {

		dao.delete(ID);
		EasyMock.replay(dao);
		
		controller.delete(ID);
		EasyMock.verify(dao);
		
	}
	
	/**
	 * Tests {@link ControllerImpl#delete(<T>))}.
	 */
	@Test
	public void delete() {

		dao.delete(OBJECT);
		EasyMock.replay(dao);
		
		controller.delete(OBJECT);
		EasyMock.verify(dao);
		
	}
	
	/**
	 * Tests {@link ControllerImpl#findAll())}.
	 */
	@Test
	public void findAll() {
		
		List<String> list = new ArrayList<String>();
		EasyMock.expect(dao.findAll()).andReturn(list);
		EasyMock.replay(dao);
		
		final List<String> returned = controller.findAll();
		EasyMock.verify(dao);
		
		assert list == returned;
		
	}
	
	/**
	 * Tests {@link ControllerImpl#evict(<T>))}.
	 */
	@Test
	public void evict() {

		dao.evict(OBJECT);
		EasyMock.replay(dao);
		
		controller.evict(OBJECT);
		EasyMock.verify(dao);
		
	}
	
	/**
	 * Tests {@link ControllerImpl#reattach(Object)}.
	 */
	@Test
	public void reattach() {

		EasyMock.expect(dao.reattach(OBJECT)).andReturn(OTHER_OBJECT);
		EasyMock.replay(dao);
		
		assert OTHER_OBJECT == controller.reattach(OBJECT);
		EasyMock.verify(dao);
		
	}

	/**
	 * Tests {@link ControllerImpl#refresh(Object)}.
	 */
	@Test
	public void refresh() {

		EasyMock.expect(dao.refresh(OBJECT)).andReturn(OTHER_OBJECT);
		EasyMock.replay(dao);
		
		assert OTHER_OBJECT == controller.refresh(OBJECT);
		EasyMock.verify(dao);
		
	}

	/**
	 * Tests {@link ControllerImpl#findById(<K>))}.
	 */
	@Test
	public void findById() {

		EasyMock.expect(dao.findById(ID)).andReturn(OBJECT);
		EasyMock.replay(dao);
		
		final String result = controller.findById(ID);
		EasyMock.verify(dao);
		
		assert result == OBJECT;
		
	}
	
	/**
	 * Tests {@link ControllerImpl#findByIds(Object[])}.
	 */
	@Test
	public void findByIds() {

		final ArrayList<String> list = new ArrayList<String>();
		EasyMock.expect(dao.findByIds(ID)).andReturn(list);
		EasyMock.replay(dao);
		
		final List<String> result = controller.findByIds(ID);
		EasyMock.verify(dao);
		
		assert result == list;
		
	}
	
	/**
	 * Tests {@link ControllerImpl#findAll(int, int, String, boolean)))}.
	 */
	@Test
	public void findAll_paginated() {

		List<String> list = new ArrayList<String>();
		EasyMock.expect(dao.findAll(1, 1)).andReturn(list);
		EasyMock.replay(dao);
		
		final List<String> returned = controller.findAll(1, 1);
		EasyMock.verify(dao);
		
		assert list == returned;
		
	}
	
	/**
	 * Tests {@link ControllerImpl#saveOrUpdate(Object)}.
	 */
	@Test
	public void saveOrUpdate() {
		
		final String OTHER_OBJECT = "kkkkk";

		// at first, we test with a persistent object
		EasyMock.expect(dao.isPersistent(OBJECT)).andReturn(true);
		EasyMock.expect(dao.update(OBJECT)).andReturn(OTHER_OBJECT);
		EasyMock.replay(dao);
		
		String returned = controller.saveOrUpdate(OBJECT);
		EasyMock.verify(dao);
		
		assert returned == OTHER_OBJECT;
		
		// at last, we test with a non-persistent object
		EasyMock.reset(dao);

		EasyMock.expect(dao.isPersistent(OBJECT)).andReturn(false);
		dao.save(OBJECT);
		EasyMock.replay(dao);
		
		returned = controller.saveOrUpdate(OBJECT);
		EasyMock.verify(dao);
		
		assert returned == OBJECT;

	}

	final private static class DummyGenericController extends
			ControllerImpl<String, Integer> {

		/**
		 * @param dao
		 */
		public DummyGenericController(DAO<String, Integer> dao) {
			super(dao);
		}

	}

}
