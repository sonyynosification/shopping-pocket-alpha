package com.crystal.tigers.s1.s1ws.controllers;

import com.crystal.tigers.s1.s1ws.common.objects.mapper.JSONRequestMapperObject;
import com.crystal.tigers.s1.s1ws.dbmodels.Mall;
import com.crystal.tigers.s1.s1ws.services.MallService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/malls")
public class MallController {

	private final Logger LOG = LoggerFactory.getLogger(MallController.class);

	@Autowired
	private MallService newMallSvc;

	/**
	 * Simple search for some specific malls.
	 * 
	 * @param jsonStr
	 *            The JSON String which contains the search criteria
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public ResponseEntity<Map<String, List<Mall>>> search(@RequestBody String jsonStr)
			throws JsonParseException, JsonMappingException, IOException {

		// TODO: move implementation into another business class / function
		ObjectMapper mapperObj = new ObjectMapper();

		Mall mall = mapperObj.readValue(jsonStr, Mall.class);
		System.out.println("jsonStr" + jsonStr);

		List<Mall> mallList = newMallSvc.searchByModel(mall);
		// TODO: we should return the same HttpStatus.OK when no result. That
		// would be helpful in some cases.
		if (mallList == null || jsonStr.isEmpty()) {
			return new ResponseEntity("no mall found", HttpStatus.NOT_FOUND);
		}

		// TODO: handle the results in another class / function
		
		Map results = new HashMap();
		results.put("malls", mallList);
		LOG.info("results: ", results);
		return new ResponseEntity(results, HttpStatus.OK);
	}

	/**
	 * return all Malls from database
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAll() {
		LOG.info("getting all malls");
		List<Mall> malls = newMallSvc.findAllMalls();
		if (malls == null || malls.isEmpty()) {
			LOG.info("no malls found");
			// TODO: No content is weird :( let's return an empty list
			// return new ResponseEntity<List<com.crystal.tigers.s1.s1ws.dbmodels.Mall>>(HttpStatus.NO_CONTENT);
		}

		// TODO: I'm trying to modify the response this way: { "malls":[ {
		// mall1},...], "otherparams": {}}
		// TODO: As mentioned, it would need to go into some functions / class
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("malls", malls);
		results.put("messages", "OK");

		return new ResponseEntity(results, HttpStatus.OK);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> create(@RequestBody JSONRequestMapperObject<Mall> newObj) {
		LOG.info("Creating a mall:");
		// TODO: create new response entity and results || statusCode using
		// Factory pattern
		Map<String, Object> results = new HashMap<String, Object>();
		ResponseEntity<Map<String, Object>> responseEntity;
		HttpStatus statusCode;

		// TODO: messages should be well formatted to provide level: info /
		// errors / warning
		List<String> messages = new ArrayList<String>();

		Mall newMall = newObj.getObject();

		if (newMallSvc.exists(newMall)) {
			messages.add("Mall already exists");
			results.put("messages", messages);
			statusCode = HttpStatus.CONFLICT;
		} else {
			newMallSvc.create(newMall);
			messages.add("Mall created successfully");
			results.put("messages", messages);
			statusCode = HttpStatus.CREATED;
		}

		responseEntity = new ResponseEntity<Map<String, Object>>(results, statusCode);
		return responseEntity;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> update(@RequestBody JSONRequestMapperObject<Mall> newObj) {
		LOG.info("Updating a mall:");
		// TODO: create new response entity and results || statusCode using
		// Factory pattern
		Map<String, Object> results = new HashMap<String, Object>();
		ResponseEntity<Map<String, Object>> responseEntity;
		HttpStatus statusCode;

		// TODO: messages should be well formatted to provide level: info /
		// errors / warning
		List<String> messages = new ArrayList<String>();

		Mall newMall = newObj.getObject();

		if (newMallSvc.exists(newMall)) {
			messages.add("Mall not exist!");
			results.put("messages", messages);
			statusCode = HttpStatus.NOT_FOUND;
		} else {
			newMallSvc.update(newMall);
			messages.add("Mall updated successfully");
			results.put("messages", messages);
			statusCode = HttpStatus.OK;
		}

		responseEntity = new ResponseEntity<Map<String, Object>>(results, statusCode);
		return responseEntity;
	}

	// =========================================== Get com.crystal.tigers.s1.s1ws.dbmodels.Mall By ID
	// =========================================

	@RequestMapping(value = "{mall_id}", method = RequestMethod.GET)
	public ResponseEntity<Mall> get(@PathVariable("mall_id") int id) {
		LOG.info("getting mall with id: {}", id);
		Mall mall = newMallSvc.findById(id);

		if (mall == null) {
			LOG.info("mall with id {} not found", id);

			return new ResponseEntity<Mall>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Mall>(mall, HttpStatus.OK);
	}

}
