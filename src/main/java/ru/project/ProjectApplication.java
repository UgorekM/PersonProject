package ru.project;

import ru.project.dao.PersonDao;

public class ProjectApplication {

	public static void main(String[] args) {
		PersonDao personDao = new PersonDao();
		System.out.println(personDao.getById(1).toString());
	}
}
