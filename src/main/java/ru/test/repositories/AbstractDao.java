package ru.test.repositories;

public interface AbstractDao <K, E> {

    E getById(K id);

    E getByFirstname(String firstname);

    void changeSurname(K id, String newSurname);
}
