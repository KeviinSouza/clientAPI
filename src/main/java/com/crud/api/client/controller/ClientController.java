package com.crud.api.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.api.client.model.Client;
import com.crud.api.client.repository.ClientRepository;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientRepository clientRepo;

    @GetMapping()	
    @ResponseBody
    public ResponseEntity<List<Client>> getAllClient(){
    	
    	List<Client> aClient = clientRepo.findAll();
    	
        return new ResponseEntity<List<Client>>(aClient, HttpStatus.OK);
    }

    @GetMapping("/id")
    @ResponseBody
    public ResponseEntity<?> getClientById(@RequestParam(name = "id") Long id){
    	
    	try {
    		Client client = clientRepo.findById(id).get();
        	
            return new ResponseEntity<Client>(client, HttpStatus.OK); 
		} catch (Exception e) {
			String ErrorMsg = "Error retrieving client";
			return new ResponseEntity<String>(ErrorMsg, HttpStatus.BAD_REQUEST); 
		}
    	
    }
    
    @GetMapping("/name")
    @ResponseBody
    public ResponseEntity<?> getClientByName(@RequestParam(name = "name") String name){
    	
    	if(name.isBlank()) {
			String notFound = "Name value required to fetch client";
			return new ResponseEntity<String>(notFound, HttpStatus.BAD_REQUEST);
		}
    	
    	try {
    		List<Client> clientList = clientRepo.findByName(name.trim().toUpperCase());
    		
    		if(clientList.isEmpty()) {
    			String notFound = "Client not found";
    			return new ResponseEntity<String>(notFound, HttpStatus.OK);
    		}
        	
            return new ResponseEntity<List<Client>>(clientList, HttpStatus.OK); 
		} catch (Exception e) {
			System.out.println(e);
			String errorMsg = "Error retrieving client list";
			return new ResponseEntity<String>(errorMsg, HttpStatus.BAD_REQUEST); 
		}
    	
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<Client> saveClient(@RequestBody Client aClient){
    	
    	Client client = clientRepo.save(aClient);
  
        return new ResponseEntity<Client>(client, HttpStatus.CREATED);
    }
    
    @PutMapping()
    @ResponseBody
    public ResponseEntity<?> updateClient(@RequestBody Client aClient){
    	
    	if(aClient.getId() == null) {
    		String errorMsg = "ID value required for client update";
            return new ResponseEntity<String>(errorMsg, HttpStatus.OK);
    	}
    	
    	Client client = clientRepo.saveAndFlush(aClient);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @DeleteMapping()
    @ResponseBody
    public ResponseEntity<String> deleteClient(@RequestParam(name = "id") Long id){
    		
    	try {
    		Client client = clientRepo.getById(id);
    		String name = client.getName();
            clientRepo.deleteById(id);
            
            String msg = "Client successfully deleted: " + name;
            return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			
			String msg = "Error deleting client";
            return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}
    }
}
