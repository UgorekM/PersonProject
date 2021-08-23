package ru.project;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.project.models.Person;
import ru.project.services.PersonService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProjectApplicationTestsService {

	PersonService personServiceMock = Mockito.mock(PersonService.class);

	@Test
	void testGetByFirstmane() {
		when(personServiceMock.getByFirstname("Igor")).thenReturn(new Person(1, "Igor", "Morozov"));
		assertEquals(personServiceMock.getByFirstname("Igor"), new Person(1, "Igor", "Morozov"));
		verify(personServiceMock).getByFirstname("Igor");

		doReturn(new Person(2, "Kate", "Liv")).when(personServiceMock).getByFirstname("Kate");
		assertEquals(personServiceMock.getByFirstname("Kate"), new Person(2, "Kate", "Liv"));
		verify(personServiceMock).getByFirstname("Kate");
	}

	@Test
	void testGetById() {
		when(personServiceMock.getById(1)).thenReturn(new Person(1, "Igor", "Morozov"));
		assertEquals(personServiceMock.getById(1), new Person(1, "Igor", "Morozov"));
		verify(personServiceMock, atLeastOnce()).getById(1);

		doReturn(new Person(2, "Kate", "Liv")).when(personServiceMock).getById(2);
		assertEquals(personServiceMock.getById(2), new Person(2, "Kate", "Liv"));
		assertEquals(personServiceMock.getById(2), new Person(2, "Kate", "Liv"));
		verify(personServiceMock, times(2)).getById(2);
	}
}
