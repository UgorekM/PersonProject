package ru.project.dao;

import ru.project.models.Person;
import ru.project.repositories.AbstractDao;

import javax.transaction.Transactional;
import java.sql.*;

@Transactional
public class PersonDao implements AbstractDao<Integer, Person> {

    Connection connection;

    public PersonDao(){
    }

    public PersonDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public Person getById(Integer id) {
        Person person = null;

        if(connection == null){
            connection = ConnectorDatabase.getConnection();
        }
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM person WHERE id=?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String name = rs.getString(2);
                String surname = rs.getString(3);
                person = new Person(id, name, surname);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return person;
    }

    @Override
    public Person getByFirstname(String firstname) {
        Person person = null;

        if(connection == null){
            connection = ConnectorDatabase.getConnection();
        }
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM person WHERE firstname=?")) {
            statement.setString(1, firstname);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt(1);
                String surname = rs.getString(3);
                person = new Person(id, firstname, surname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public void changeSurname(Integer id, String newSurname) {

        if(connection == null){
            connection = ConnectorDatabase.getConnection();
        }
        try (PreparedStatement statement =
                     connection.prepareStatement("UPDATE person SET surname=? WHERE id=?;")) {
            statement.setString(1, newSurname);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

