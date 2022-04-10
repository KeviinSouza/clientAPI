package com.crud.api.client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crud.api.client.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{
	
	@Query("select c from Client c where upper(trim(c.name)) like %?1%")
	List<Client> findByName(String name);
    
}
