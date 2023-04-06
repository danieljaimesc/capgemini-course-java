package com.example.ioc;

import com.example.exceptions.InvalidDataException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Primary
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE) 
public class StringRepositoryImpl implements StringRepository {
	private String ultimo = "";
	
	@Override
	public String load() {
		return "Soy el StringRepositoryImpl";
	}

	@Override
	public void save(String item) throws InvalidDataException {
		if(item == "")
			throw new InvalidDataException("La cadena no puede estar vacia");
//		throw new ArgumentoInvalidoException();
		System.out.println("Anterior: " + ultimo);
		this.ultimo = item;
		System.out.println("Guardo el item: " + item);
	}

}
