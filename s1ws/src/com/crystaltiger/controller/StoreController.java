package com.crystaltiger.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crystaltiger.model.Store;
import com.crystaltiger.service.StoreService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/stores")
public class StoreController {

	private final Logger LOG = LoggerFactory.getLogger(StoreController.class);

	@Autowired
	private StoreService storeService;

	/*Search Stores*/
	

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public ResponseEntity<Map<String, List<Store>>> search(@RequestBody String jsonStr)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapperObj = new ObjectMapper();

		Store store = mapperObj.readValue(jsonStr, Store.class);
		System.out.println("jsonStr" + jsonStr);

		if (store == null || "".equals(jsonStr)) {
			return new ResponseEntity("no store found", HttpStatus.NOT_FOUND);
		}

		List<Store> storeList = storeService.searchStore(store);
		Map results = new HashMap();
		results.put("users", storeList);
		LOG.info("results: ", results);
		return new ResponseEntity(results, HttpStatus.OK); 
	}

	/* Get All Stores */

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Store>> getAll() {
		LOG.info("getting all users");
		List<Store> stores = storeService.getStores();
		if (stores == null || stores.isEmpty()) {
			LOG.info("no stores found");
			return new ResponseEntity<List<Store>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Store>>(stores, HttpStatus.OK);
	}

}
