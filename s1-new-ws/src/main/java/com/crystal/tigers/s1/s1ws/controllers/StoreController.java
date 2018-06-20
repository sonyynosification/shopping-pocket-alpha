package com.crystal.tigers.s1.s1ws.controllers;

import com.crystal.tigers.s1.s1ws.common.objects.mapper.JSONRequestMapperObject;
import com.crystal.tigers.s1.s1ws.dbmodels.Store;
import com.crystal.tigers.s1.s1ws.services.IStoreService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stores")
public class StoreController {

	private final Logger LOG = LoggerFactory.getLogger(StoreController.class);

	@Autowired
	private IStoreService storeService;

	/**
	 * Simple search for some specific stores.
	 * @param jsonStr The JSON String which contains the search criteria
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public ResponseEntity<Map<String, List<Store>>> search(@RequestBody String jsonStr)
			throws JsonParseException, JsonMappingException, IOException {

		//TODO: move implementation into another business class / function
		ObjectMapper mapperObj = new ObjectMapper();

		Store store = mapperObj.readValue(jsonStr, Store.class);
		System.out.println("jsonStr" + jsonStr);


		//TODO: we should return the same HttpStatus.OK when no result. That would be helpful in some cases.
		if (store == null || jsonStr.isEmpty()) {
			return new ResponseEntity("no store found", HttpStatus.NOT_FOUND);
		}

		List<Store> storeList = storeService.searchStore(store);

		//TODO: handle the results in another class / function
		Map results = new HashMap();
		results.put("stores", storeList);
		LOG.info("results: ", results);
		return new ResponseEntity(results, HttpStatus.OK); 
	}

	/**
	 * return all Stores from database
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getAll() {
		LOG.info("getting all stores");
		List<Store> stores = storeService.getStores();
		if (stores == null || stores.isEmpty()) {
			LOG.info("no stores found");
			//TODO: No content is weird :( let's return an empty list
			//return new ResponseEntity<List<com.crystal.tigers.s1.s1ws.dbmodels.Store>>(HttpStatus.NO_CONTENT);
		}

		//TODO: I'm trying to modify the response this way: { "stores":[ { store1},...], "otherparams": {}}
        //TODO: As mentioned, it would need to go into some functions / class
        Map<String,Object> results = new HashMap<String,Object>();
        results.put("stores", stores);
        results.put("messages", "OK");

		return new ResponseEntity(results, HttpStatus.OK);
	}

	@RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> create(@RequestBody JSONRequestMapperObject<Store> newObj) {
		LOG.info("Creating a store:");
        //TODO: create new response entity and results || statusCode using Factory pattern
        Map<String, Object> results = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity;
        HttpStatus statusCode;

        //TODO: messages should be well formatted to provide level: info / errors / warning
        List<String> messages = new ArrayList<String>();

		Store newStore = newObj.getObject();

		if (storeService.exists(newStore)) {
		    messages.add("Store already exists");
		    results.put("messages",messages);
		    statusCode = HttpStatus.CONFLICT;
        } else {
		    storeService.saveStore(newStore);
		    messages.add("Store created successfully");
		    results.put("messages",messages);
		    statusCode = HttpStatus.CREATED;
        }

        responseEntity = new ResponseEntity<Map<String, Object>>(results, statusCode);
        return responseEntity;
	}

}
