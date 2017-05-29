package com.crystal.tigers.s1.ws.controller;

import com.crystal.tigers.s1.ws.common.objects.mapper.JSONRequestMapperObject;
import com.crystal.tigers.s1.ws.model.RecentSearch;
import com.crystal.tigers.s1.ws.model.Store;
import com.crystal.tigers.s1.ws.model.User;
import com.crystal.tigers.s1.ws.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sonyynoss on 5/30/17.
 */
@RestController
@RequestMapping("/users/recent")
public class RecentSearchController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    /**
     * Get all user recent searches
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAll(@RequestBody JSONRequestMapperObject<User> usrObj) {
        Map<String, Object> results = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity;
        HttpStatus statusCode;

        //TODO: messages should be well formatted to provide level: info / errors / warning
        List<String> messages = new ArrayList<String>();

        User user = usrObj.getObject();
        List<RecentSearch> recentSearches = userService.getUserRecentSearches(user);

        results.put("objects", recentSearches);
        results.put("messages", messages);
        statusCode = HttpStatus.OK;

        responseEntity = new ResponseEntity<Map<String, Object>>(results, statusCode);

        return responseEntity;
    }

    @RequestMapping(value="/list/{max}", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> getTopRecent(@RequestBody JSONRequestMapperObject<User> usrObj) {
        //TODO: implementation needed
        throw new NotImplementedException();
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> create(@RequestBody JSONRequestMapperObject<RecentSearch> newObj) {
        LOG.info("Creating a recent search:");
        //TODO: create new response entity and results || statusCode using Factory pattern
        Map<String, Object> results = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity;
        HttpStatus statusCode;

        //TODO: messages should be well formatted to provide level: info / errors / warning
        List<String> messages = new ArrayList<String>();

        RecentSearch newRecentSearch = newObj.getObject();


        //TODO: directs the logic works to another service. Controller should be only responsible for handling in/out
        if (userService.exists(newRecentSearch)) {
            messages.add("Recent search already exists");
            results.put("messages",messages);
            statusCode = HttpStatus.CONFLICT;
        } else {
            userService.saveRecentSearch(newRecentSearch);
            messages.add("Recent Search created successfully");
            results.put("messages",messages);
            statusCode = HttpStatus.CREATED;
        }

        responseEntity = new ResponseEntity<Map<String, Object>>(results, statusCode);
        return responseEntity;
    }

    /**
     * Get details of a specific store
     * @return
     */
    @RequestMapping(value="/id/{recent_id}", method= RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getOne(@PathVariable("store_id") int storeId) {
        //TODO: implementation needed
        throw new NotImplementedException();
    }

    @RequestMapping(value="/deleteAll", method=RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> deleteAll(JSONRequestMapperObject<User> deleteObj) {
        //TODO: implementation needed
        throw new NotImplementedException();
    }
}
