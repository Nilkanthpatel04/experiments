package com.gemstone.gemfire.restclient;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.gemstone.gemfire.util.RestClientUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class RestClientApp  {
  private static final String PEOPLE_REGION = "/People";
  
  private static final String PERSON1_AS_JSON = "{"
	      + "\"@type\": \"com.gemstone.gemfire.domain.Person\"," + "\"id\": 1,"
	      + " \"firstName\": \"Jane\"," + " \"middleName\": \"H\","
	      + " \"lastName\": \"Doe1\"," + " \"birthDate\": \"04/12/1983\","
	      + "\"gender\": \"MALE\"" + "}";

  public static void main(final String... args) throws Exception {
    for(int i=1; i<=1500000; i++) {
	  doCreate(PEOPLE_REGION, String.valueOf(i));
    }
	//String functionName = "GetAllEntries";
	//executeFunction(functionName);
    System.out.println("Programme has run successfully...!");    
  }
  
  private static  HttpHeaders setAcceptAndContentTypeHeaders(){
    List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
    acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
	    
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(acceptableMediaTypes);
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }
  /*
  private static void executeFunction(final String functionName) {
	    HttpHeaders headers =  setAcceptAndContentTypeHeaders();
	    HttpEntity<String> entity = new HttpEntity<String>(PERSON1_AS_JSON, headers);
	    //http://localhost:8080/gemfire-api/v1/functions/GetAllEntries
	    try { 
	      ResponseEntity<String> result = RestClientUtils.getRestTemplate().exchange(
	            "http://localhost:8080/gemfire-api/v1/functions/GetAllEntries", HttpMethod.POST,
	            null, String.class);
	      String functionResult = result.getBody();
	      System.out.println("Response body = " + result.getBody());
	      System.out.println("STATUS_CODE = " + result.getStatusCode().value());
	      System.out.println("HAS_BODY = " + result.hasBody());
	      System.out.println("LOCATION_HEADER = " + result.getHeaders().getLocation().toString());
	    } catch (HttpClientErrorException e) {
	    	e.printStackTrace();
	      System.out.println("Http Client encountered error, msg:: " + e.getMessage());
	    } catch(HttpServerErrorException se) {
	    	se.printStackTrace();
	      System.out.println("Server encountered error, msg::" + se.getMessage());
	    } catch (Exception e) {
	      System.out.println("Unexpected ERROR...!!");
	  }
  }
  */
  private static void doCreate(final String regionNamePath, final String key) {
    HttpHeaders headers =  setAcceptAndContentTypeHeaders();
    HttpEntity<String> entity = new HttpEntity<String>(PERSON1_AS_JSON, headers);
    try { 
      ResponseEntity<String> result = RestClientUtils.getRestTemplate().exchange(
            "http://localhost:7075/gemfire-api/v1/People?key=" + key, HttpMethod.POST,
            entity, String.class);
        
      System.out.println("STATUS_CODE = " + result.getStatusCode().value());
      System.out.println("HAS_BODY = " + result.hasBody());
      System.out.println("LOCATION_HEADER = " + result.getHeaders().getLocation().toString());
    } catch (HttpClientErrorException e) {
      System.out.println("Http Client encountered error, msg:: " + e.getMessage());
    } catch(HttpServerErrorException se) {
      System.out.println("Server encountered error, msg::" + se.getMessage());
    } catch (Exception e) {
      System.out.println("Unexpected ERROR...!!");
    }
  }
}