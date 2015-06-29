package com.gemstone.gemfire.restclient;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.gemstone.gemfire.cache.client.Pool;
import com.gemstone.gemfire.cache.client.PoolManager;
import com.gemstone.gemfire.cache.client.internal.PoolImpl;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.Function;
//import com.gemstone.gemfire.util.DateTimeUtils;
import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;


@SuppressWarnings("unused")
public class FunctionExecutionWithRestAPI  {
  
  private static final String PEOPLE_REGION = "People";
  
  private static final int TOTAL_NUMBER_OF_KEYS = 100000; 
  
  private static final String REST_URL = "http://localhost:8080/gemfire-api/v1/People/";
  
  private static final int SERVER_PORT = 40410;
  
  private static final String JSON_DOCUMENT1 = "{"
	      + "\"@type\": \"org.gopivotal.app.domain.Person\"," + "\"id\":  %1$s,"
	      + " \"firstName\": \"Nilkanth%2$s\" ," + " \"middleName\": \"H\","
	      + " \"lastName\": \"Patel1%3$s\" ," + " \"birthDate\": \"04/12/1983\","
	      + "\"gender\": \"MALE\"" + "}";

   
  private static final String JSON_DOCUMENT2 = "{ " +
      "    \"store\": " +
      "    {" +
      "        \"book\": " +
      "        [ " +
      "            { " +
      "                \"category\": \"reference\"," +
      "                \"author\": \"Nigel Rees\"," +
      "                \"title\": \"Sayings of the Century\"," +
      "                \"price\": %1$s" +
      "            }," +
      "            { " +
      "                \"category\": \"fiction\"," +
      "                \"author\": \"Evelyn Waugh\"," +
      "                \"title\": \"Sword of Honour\"," +
      "                \"price\": %2$s" +
      "            }," +
      "            { " +
      "                \"category\": \"fiction\"," +
      "                \"author\": \"Herman Melville\"," +
      "                \"title\": \"Moby Dick\"," +
      "                \"isbn\": \"0-553-21311-3\"," +
      "                \"price\": %3$s" +
      "            }," +
      "            { " +
      "                \"category\": \"fiction\"," +
      "                \"author\": \"J. R. R. Tolkien\"," +
      "                \"title\": \"The Lord of the Rings\"," +
      "                \"isbn\": \"0-395-19395-8\"," +
      "                \"price\": %4$s" +
      "            }" +
      "        ]," +
      "    \"bicycle\": " +
      "    {" +
      "        \"color\": \"red\"," +
      "        \"price\": %5$s" +
      "    }" +
      "}" +
      "} ";
  
  private static final String FUNCTION_ARGS1 = "["
		    +    "{"
		    +        "\"@type\": \"double\","
		    +        "\"@value\": 210"
		    +    "},"
		    +    "{"
		    +        "\"@type\": \"com.gemstone.gemfire.rest.internal.web.controllers.Item\","
		    +        "\"itemNo\": \"599\","
		    +        "\"description\": \"Part X Free on Bumper Offer\","
		    +        "\"quantity\": \"2\","
		    +        "\"unitprice\": \"5\","
		    +        "\"totalprice\": \"10.00\""
		    +    "}"
		    +"]";
  
  

  public static void main(final String... args) throws Exception {
	ClientCacheFactory cf = new ClientCacheFactory();
	//.addPoolServer("localhost", SERVER_PORT);
    ClientCache cache = cf.create();
    ClientRegionFactory rf = cache.createClientRegionFactory(ClientRegionShortcut.PROXY);
    
    //Register functions
    /*
    FunctionService.registerFunction(new GetAllEntries());
    FunctionService.registerFunction(new GetRegions());
    FunctionService.registerFunction(new PutKeyFunction());
    FunctionService.registerFunction(new GetDeliveredOrders());
    FunctionService.registerFunction(new AddFreeItemToOrders());
    */
    /*
    Region region = rf.create("People");
	region.put("1", JSON_DOCUMENT1);
	region.put("2", JSON_DOCUMENT2);
    
	
	///functions/AddFreeItemToOrders?onRegion=orders
	String restRequestUrl = "http://localhost:8080/gemfire-api/v1/functions/AddFreeItemToOrders?onRegion=People";
	HttpHeaders headers = setAcceptAndContentTypeHeaders();
	HttpEntity<Object> entity =  new HttpEntity<Object>(FUNCTION_ARGS1, headers);
    ResponseEntity<String> result =  RestClientUtils.getRestTemplate().exchange(
        restRequestUrl,
        HttpMethod.POST, entity, String.class);
    
    System.out.println("NIlkanth:: " + result.getBody());
    int statusCode = result.getStatusCode().value();
    boolean hasBody = result.hasBody();
	*/
    PoolImpl pool;
    Pool p;
    try {
      p = PoolManager.createFactory().addServer("localhost", SERVER_PORT).create("FunctionExecutionWithRestAPI");
          /*
          .addServer(host, port2.intValue()).addServer(host, port3.intValue())
          .setPingInterval(2000).setSubscriptionEnabled(true)
          .setSubscriptionRedundancy(-1).setReadTimeout(2000)
          .setSocketBufferSize(1000).setMinConnections(6).setMaxConnections(10)
          .setRetryAttempts(2).setPRSingleHopEnabled(true).create("PRClientServerTestBase");
          */
    }
    finally {
      //CacheServerTestUtil.enableShufflingOfEndpoints();
    }
    pool = (PoolImpl)p;
    
    
    try {
    	Execution member = FunctionService.onServer(pool);
        Execution me = member.withArgs(args);
        ResultCollector rs =  me.execute("TestFunction");
        
        List resultList = (List)rs.getResult();
    	
        
        System.out.println("Function execution Result " + resultList.size() + resultList.get(0));
    }catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("Encounter Error: " + ex.getMessage());
    }
    
	/*
    //warm-up the VM using some region ops
    doWarmup(region);
	
	//evaluate Gemfire java client performance
	//doOpsFromGemfireCLient(region);
	 
	//put using REST APIs REST   
	doOpsUsingRESTAPIs(PEOPLE_REGION);
	*/
    
	cache.close();
    System.out.println("Programme has run successfully...!");
    
  }
  
  
  private static void waitForMilliSec(int milliSec){
	  
    try {
      Thread.sleep(milliSec);
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }   
  }
  
  private static  HttpHeaders setAcceptAndContentTypeHeaders(){
	List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
	acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
	    
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(acceptableMediaTypes);
	headers.setContentType(MediaType.APPLICATION_JSON);
	//headers.set("Connection", "Keep-Alive");
	return headers;
  }
  
private static void doWarmup ( Region region ) {
    // Warm up jvm
    System.out.println("doWarmup task started!");
    List<String> jsonDocList = new ArrayList<String>();
    List<String> keysList = new ArrayList<String>();
    for (int key = 0; key < TOTAL_NUMBER_OF_KEYS; key++)
    {
    	String jsonString = String.format(JSON_DOCUMENT1, key, key + 1, key + 2);
    	jsonDocList.add(jsonString);
    	keysList.add(String.valueOf(key));
    }

    //System.out.println("Nilkanth: Length of jsonDocList " + jsonDocList.size());
    //System.out.println("JSON[500]: " + jsonDocList.get(500));
    /*
    //gemfire put
    for (int key = 0; key < TOTAL_NUMBER_OF_KEYS; key++)
    {
      region.put(keysList.get(key), jsonDocList.get(key));
    }
    
    //gemfire get
    for (int key = 0; key < TOTAL_NUMBER_OF_KEYS; key++)
    {
    	Object value = region.get(keysList.get(key));
    }
    
    //gemfire remove
    for (int key = 0; key < TOTAL_NUMBER_OF_KEYS; key++)
    {
        region.remove(keysList.get(key));
    }
    */
    //TODO: warmup vm by doing REST ops
	
    List<HttpEntity<String>> entityList = new ArrayList<HttpEntity<String>>();
	List<String> urlList = new ArrayList<String>();
	  
	HttpHeaders headers =  setAcceptAndContentTypeHeaders();
	
	for (int key = 0; key < TOTAL_NUMBER_OF_KEYS; key++)
	{
	  //System.out.println("doCreate--> JSON [ " + key + " ]" + " = " + jsonString);
	  entityList.add(new HttpEntity<String>(jsonDocList.get(key), headers));
	  urlList.add(REST_URL + key);
	}
	
    try { 
      {
        RestTemplate restTemplate1 = RestClientUtils.getRestTemplate();
  	  
  	    //PUT
  	    for(int i=0; i< TOTAL_NUMBER_OF_KEYS; i++){
  	      ResponseEntity<String> result1 = restTemplate1.exchange(
  			  urlList.get(i), HttpMethod.PUT,
            entityList.get(i), String.class);
  	    }
      }
  	  
  	  //GET
      {
    	RestTemplate restTemplate2 = RestClientUtils.getRestTemplate();
    	for(int i=0; i< TOTAL_NUMBER_OF_KEYS; i++){
  		  restTemplate2.getForObject( urlList.get(i), String.class);
  	    }
      }
  	  
  	  //DELETE
      {
    	RestTemplate restTemplate3 = RestClientUtils.getRestTemplate();
  	    for(int i=0; i< TOTAL_NUMBER_OF_KEYS; i++){
  	      restTemplate3.exchange(
  			  urlList.get(i), HttpMethod.DELETE,
            null, String.class);
  	    }
      }
    }catch(Exception e) {
    	e.printStackTrace();
    }
    
    System.out.println("doWarmup task Completed!");
    
  }

  private static void doOpsFromGemfireCLient ( Region region) {
	  System.out.println("doOpsFromGemfireCLient task started!");
	  List<String> jsonDocList = new ArrayList<String>();
	  List<String> keysList = new ArrayList<String>();
	  
	  //for (int key = numOfKeys * 2; key < numOfKeys * 3; key++)
		  
	  for (int key = TOTAL_NUMBER_OF_KEYS * 2; key < TOTAL_NUMBER_OF_KEYS * 3; key++)
	  {
	    String jsonString = String.format(JSON_DOCUMENT2, key, key + 1, key + 2, key + 3, key + 4);
	  	jsonDocList.add(jsonString);
	  	keysList.add(String.valueOf(key));
	  }
	  
	  //evaluate gemfire put performance
      long putStartTime = System.nanoTime();
      for(int i=0; i< TOTAL_NUMBER_OF_KEYS; i++){
        region.put(keysList.get(i), jsonDocList.get(i));
      }
      long putEndtime = System.nanoTime();
      double putThroughputInMilliSec = (putEndtime - putStartTime)/1e6;
      System.out.println("Throughput, Gemfire PUT :: " + putThroughputInMilliSec);
      
      
      //evaluate gemfire get performance
      long getStartTime = System.nanoTime();
      for(int i=0; i< TOTAL_NUMBER_OF_KEYS; i++){
        region.get(keysList.get(i));
      }
      long getEndtime = System.nanoTime();
      double getThroughputInMilliSec = (getEndtime - getStartTime)/1e6;
      System.out.println("Throughput, Gemfire GET :: " + getThroughputInMilliSec);
      
      
      //evaluate gemfire remove performance
      long removeStartTime = System.nanoTime();
      for(int i=0; i< TOTAL_NUMBER_OF_KEYS; i++){
        region.remove(keysList.get(i));
      }
      long removeEndtime = System.nanoTime();
      double removeThroughputInMilliSec = (removeEndtime - removeStartTime)/1e6;
      System.out.println("Throughput, Gemfire REMOVE :: " + removeThroughputInMilliSec);
      
      System.out.println("doOpsFromGemfireCLient task Completed!");
  }
  
  private static void doOpsUsingRESTAPIs(final String regionNamePath) {
	  System.out.println("doOpsUsingRESTAPIs task started!");
	  List<String> jsonDocList = new ArrayList<String>();
	  List<HttpEntity<String>> entityList = new ArrayList<HttpEntity<String>>();
	  List<String> urlList = new ArrayList<String>();
	  
	  HttpHeaders headers =  setAcceptAndContentTypeHeaders();
	  //String restURL = "http://10.112.204.212:9099/gemfire-api/v1/People/";
	  for (int key = TOTAL_NUMBER_OF_KEYS *1; key < TOTAL_NUMBER_OF_KEYS*2; key++)
	  {
		String jsonString = String.format(JSON_DOCUMENT2, key, key + 1, key + 2, key + 3, key + 4);
	  	jsonDocList.add(jsonString);
	  	entityList.add(new HttpEntity<String>(jsonString, headers));
	  	urlList.add(REST_URL + key);
	  }
	  
      try { 
    	{
    	  RestTemplate restTemplate1 = RestClientUtils.getRestTemplate();
    	
    	  //REST PUT
    	  long putStartTime = System.nanoTime();
    	  for(int i=0; i< TOTAL_NUMBER_OF_KEYS; i++){
    	    /*ResponseEntity<String> result =*/ restTemplate1.exchange(
    			  urlList.get(i), HttpMethod.PUT,
               entityList.get(i), String.class);
    	  }
          long putEndTime = System.nanoTime();
          double putThroughputInMillSec = (putEndTime - putStartTime)/1e6;
          System.out.println("Throughput, REST PUT :: " + putThroughputInMillSec);
    	}
    	
    	{
          RestTemplate restTemplate2 = RestClientUtils.getRestTemplate();
    	  
          //REST GET
          long getStartTime = System.nanoTime();
    	  for(int i=0; i< TOTAL_NUMBER_OF_KEYS; i++){
    	    //restTemplate.exchange(urlList.get(i), HttpMethod.GET, null, String.class);  
    		  restTemplate2.getForObject( urlList.get(i), String.class);
    	  }
          long getEndTime = System.nanoTime();
          double getThroughputInMillSec = (getEndTime - getStartTime)/1e6;
          System.out.println("Throughput, REST GET :: " + getThroughputInMillSec);
    	}
        
    	{
    	  RestTemplate restTemplate3 = RestClientUtils.getRestTemplate();
    	  //REST DELETE
    	  long deleteStartTime = System.nanoTime();
    	  for(int i=0; i< TOTAL_NUMBER_OF_KEYS; i++){
    	    restTemplate3.exchange(
    			  urlList.get(i), HttpMethod.DELETE,
              null, String.class);
    	  }
          long deleteEndTime = System.nanoTime();
          double deleteThroughputInMillSec = (deleteEndTime - deleteStartTime)/1e6;
          System.out.println("Throughput, REST DELETE :: " + deleteThroughputInMillSec);
          
    	}
        
    	System.out.println("doOpsUsingRESTAPIs task completed!");
        
        
      } catch (HttpClientErrorException e) {
        e.printStackTrace();        
        System.out.println("Error msg:: " + e.getMessage());
        
      } catch(HttpServerErrorException se) {
    	  se.printStackTrace();
    	  System.out.println("CREATE :: Unexpected HttpServerErrorException ERROR occurred!!");
      } catch (Exception e) {
        e.printStackTrace();
      }
      
  }
  
}

