package com.crystal.tigers.s1.s1ws.controllers

import com.crystal.tigers.s1.s1ws.common.objects.mapper.JSONRequestMapperObject
import com.crystal.tigers.s1.s1ws.dbmodels.Mall
import com.crystal.tigers.s1.s1ws.dbmodels.Store
import com.crystal.tigers.s1.s1ws.services.MallService
import com.crystal.tigers.s1.s1ws.services.StoreService
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.util.ArrayList
import kotlin.collections.HashMap

/**
 * Created by sonyynoss on 8/16/18.
 */
@RestController
@RequestMapping("/stores")
class StoreController(@Autowired private val newStoreSvc: StoreService?,
                      @Autowired private val newMallSvc: MallService?) {

    private val LOG = LoggerFactory.getLogger(StoreController::class.java)

    /**
     * return all Stores from database
     * @return
     */
    //TODO: No content is weird :( let's return an empty list
    //return new ResponseEntity<List<com.crystal.tigers.s1.s1ws.dbmodels.Store>>(HttpStatus.NO_CONTENT);
    //TODO: I'm trying to modify the response this way: { "stores":[ { store1},...], "otherparams": {}}
    //TODO: As mentioned, it would need to go into some functions / class
    val all: ResponseEntity<Map<String, Any>>
        @RequestMapping(value = "/list", method = arrayOf(RequestMethod.GET))
            get() {
            LOG.info("getting all stores")
            val stores = newStoreSvc!!.getStores()
            if (stores.isEmpty()) {
                LOG.info("no stores found")
            }
            val results = HashMap<String, Any>()
            results["stores"] = stores
            results["messages"] = "OK"

            return ResponseEntity(results, HttpStatus.OK)
        }

    /**
     * Simple search for some specific stores.
     * @param jsonStr The JSON String which contains the search criteria
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "search", method = arrayOf(RequestMethod.POST))
    @Throws(JsonParseException::class, JsonMappingException::class, IOException::class)
    fun search(@RequestBody jsonStr: String): ResponseEntity<Any> {

        //TODO: move implementation into another business class / function
        val mapperObj = ObjectMapper()

        val store = mapperObj.readValue(jsonStr, Store::class.java)
        println("jsonStr$jsonStr")


        //TODO: we should return the same HttpStatus.OK when no result. That would be helpful in some cases.
        if (store == null || jsonStr.isEmpty()) {
            return ResponseEntity("no store found", HttpStatus.NOT_FOUND)
        }

        val storeList = newStoreSvc!!.searchStore(store)

        //TODO: handle the results in another class / function
        val results = HashMap<String, Any>()
        results.put("stores", storeList)
        LOG.info("results: ", results)
        return ResponseEntity(results, HttpStatus.OK)
    }

    @RequestMapping(value = "/create", method = arrayOf(RequestMethod.POST))
    fun create(@RequestBody newObj: JSONRequestMapperObject<Store>): ResponseEntity<Map<String, Any>> {
        LOG.info("Creating a store:")
        //TODO: create new response entity and results || statusCode using Factory pattern
        val results = HashMap<String, Any>()
        val responseEntity: ResponseEntity<Map<String, Any>>
        val statusCode: HttpStatus

        //TODO: messages should be well formatted to provide level: info / errors / warning
        val messages = ArrayList<String>()

        val newStore = newObj.`object`

        if (newStoreSvc!!.exists(newStore)) {
            messages.add("Store already exists")
            results["messages"] = messages
            statusCode = HttpStatus.CONFLICT
        } else {
            newStoreSvc.createStore(newStore)
            messages.add("Store created successfully")
            results["messages"] = messages
            statusCode = HttpStatus.CREATED
        }

        responseEntity = ResponseEntity(results, statusCode)
        return responseEntity
    }

    /**
     * Update Store
     */
    @RequestMapping(value = "/update", method = [RequestMethod.POST])
    fun update(@RequestBody newObj: JSONRequestMapperObject<Store>): ResponseEntity<Map<String, Any>> {
        LOG.info("Updating a store:")
        //TODO: create new response entity and results || statusCode using Factory pattern
        val results = HashMap<String, Any>()
        val responseEntity: ResponseEntity<Map<String, Any>>
        val statusCode: HttpStatus

        //TODO: messages should be well formatted to provide level: info / errors / warning
        val messages = ArrayList<String>()

        val updatingStore = newObj.`object`
        val refs:Map<String,Any>? = newObj.`references`;

        if (refs != null
                && refs!!["mall"] != null
                ) {
            try {
                val mallId: Int = (refs["mall"] as Map<String,Any>)["mallId"] as Int
                updatingStore.mall = newMallSvc?.findById(mallId)
            } catch (e: Exception) {
                LOG.warn(e.message);
            }
        }

        if (null == newStoreSvc!!.findById(updatingStore.storeId)) {
            messages.add("Store not exist!")
            results["messages"] = messages
            statusCode = HttpStatus.NOT_FOUND
        } else {
            newStoreSvc.update(updatingStore)
            messages.add("Store updated successfully")
            results["messages"] = messages
            statusCode = HttpStatus.OK
        }

        responseEntity = ResponseEntity(results, statusCode)
        return responseEntity
    }

}