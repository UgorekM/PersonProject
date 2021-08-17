package ru.project;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.project.dao.PersonDao;
import ru.project.models.Person;

import java.sql.*;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectApplicationTests {

	static Connection connection;

	@BeforeAll
	public static void setup() throws ClassNotFoundException, SQLException {
		ResourceBundle resource = ResourceBundle.getBundle("databaseTest");
		Class.forName("org.h2.Driver");
		String url = resource.getString("db.url");
		String user = resource.getString("db.user");
		String pass = resource.getString("db.password");

		connection = DriverManager.getConnection(url,user,pass);
		Statement statement = connection.createStatement();
		String sql =  "CREATE TABLE person (\n" +
				"    id int PRIMARY key not null,\n" +
				"    firstname varchar(40) NOT null unique,\n" +
				"    surname   varchar(40) NOT null\n" +
				");";
		statement.executeUpdate(sql);
		sql = "insert into person (id, firstname, surname) values (1, 'I', 'M')";
		statement.executeUpdate(sql);
	}

	@Test
	void changeSurname() {
		PersonDao personDao = new PersonDao(connection);
		personDao.changeSurname(1, "Petrov");
		Person p = personDao.getById(1);
		assertTrue(p.toString().equals("Person {id=1, firstname='I', surname='Petrov'}"));
	}

	@Test
	void testGetByFirstmane() {
		PersonDao personDao = new PersonDao(connection);
		Person p = personDao.getByFirstname("I");
		assertTrue(p.toString().equals("Person {id=1, firstname='I', surname='M'}"));
	}

	@Test
	void testGetById() {
		PersonDao personDao = new PersonDao(connection);
		Person p = personDao.getById(1);
		assertEquals("Person {id=1, firstname='I', surname='M'}", p.toString());
	}

	@AfterAll
	public static void tearDown() throws SQLException {
		connection.close();
	}
}
