package ru.test;

import ru.test.dao.PersonDao;

public class TestApplication {

	public static void main(String[] args) {
		PersonDao personDao = new PersonDao();
		System.out.println(personDao.getById(1).toString());
	}
}
