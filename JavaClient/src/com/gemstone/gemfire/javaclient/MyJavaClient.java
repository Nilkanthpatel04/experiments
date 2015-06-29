package com.gemstone.gemfire.javaclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.gemstone.gemfire.domain.Gender;
import com.gemstone.gemfire.domain.Person;
import com.gemstone.gemfire.pdx.PdxInstance;
import com.gemstone.gemfire.util.DateTimeUtils;

public class MyJavaClient {

	  public static void main(String[] args) {
	    CacheFactory cf = new CacheFactory().setPdxReadSerialized(true)
	    		.set("start-dev-rest-api", "true")
	    	    .set("http-service-port", String.valueOf(8080))
	    	    .set("http-service-bind-address", /*"pnq-npatel1.pune.gemstone.com"*/ "localhost");
	    		
	    cf.create();
	    
	    //BufferedReader br = new BufferedReader(new )
	    try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	   /*1
	    ClientCacheFactory cf = new ClientCacheFactory().addPoolServer("localhost", 40405);
	    ClientCache cache = cf.setPdxReadSerialized(true).create();
	    //ClientCache cache = cf.create();
	    ClientRegionFactory rf = cache.createClientRegionFactory(ClientRegionShortcut.PROXY);
	                
	    Region region = rf.create("People");
	                
	    //Get data on key "1", update it and put it again in cache
	    Person actualObj = null;
	    Object obj = region.get("1");
	    if(obj instanceof PdxInstance){
	      System.out.println("Obj is PdxInstance");
	      PdxInstance pi = (PdxInstance)obj;
	      Object obj2 = pi.getObject();
	      if(obj2 instanceof Person){
	        actualObj = (Person)obj2;
	        System.out.println("Received Person :" + actualObj.toString());
	      }else {
	        System.out.println("Error: obj2 is expected to be of type Person");
	      }
	    }else {
	      System.out.println("Error: obj is expected to be of type PdxInstance");
	    } 
	                
	    //update the received object and put it in cache
	    if(actualObj != null){
	      actualObj.setFirstName("Jane_updated");
	      actualObj.setLastName("Doe_updated");
	      region.put("1", actualObj);
	    }
	                
	    //Add/putAll set of person objects
	    final Person person2 = new Person(102L, "Sachin", "Ramesh", "Tendulkar", DateTimeUtils.createDate(1975, Calendar.DECEMBER, 14), Gender.MALE);
	    final Person person3 = new Person(103L, "Saurabh", "Baburav", "Ganguly", DateTimeUtils.createDate(1972, Calendar.AUGUST, 29), Gender.MALE);
	    final Person person4 = new Person(104L, "Rahul", "subrymanyam", "Dravid", DateTimeUtils.createDate(1979, Calendar.MARCH, 17), Gender.MALE);
	    final Person person5 = new Person(105L, "Jhulan", "Chidambaram", "Goswami", DateTimeUtils.createDate(1983, Calendar.NOVEMBER, 25), Gender.FEMALE);			    
	    final Person person6 = new Person(101L, "Rahul", "Rajiv", "Gndhi", DateTimeUtils.createDate(1970, Calendar.MAY, 14), Gender.MALE);
	    final Person person7 = new Person(102L, "Narendra", "Damodar", "Modi", DateTimeUtils.createDate(1945, Calendar.DECEMBER, 24), Gender.MALE);
	    final Person person8 = new Person(103L, "Atal", "Bihari", "Vajpayee", DateTimeUtils.createDate(1920, Calendar.AUGUST, 9), Gender.MALE);
	    final Person person9 = new Person(104L, "Soniya", "Rajiv", "Gandhi", DateTimeUtils.createDate(1929, Calendar.MARCH, 27), Gender.FEMALE);
	    final Person person10 = new Person(104L, "Priyanka", "Robert", "Gandhi", DateTimeUtils.createDate(1973, Calendar.APRIL, 15), Gender.FEMALE);
	    final Person person11 = new Person(104L, "Murali", "Manohar", "Joshi", DateTimeUtils.createDate(1923, Calendar.APRIL, 25), Gender.MALE);
	    final Person person12 = new Person(104L, "Lalkrishna", "Parmhansh", "Advani", DateTimeUtils.createDate(1910, Calendar.JANUARY, 01), Gender.MALE);
	    final Person person13 = new Person(104L, "Shushma", "kumari", "Swaraj", DateTimeUtils.createDate(1943, Calendar.AUGUST, 10), Gender.FEMALE);
	    final Person person14 = new Person(104L, "Arun", "raman", "jetly", DateTimeUtils.createDate(1942, Calendar.OCTOBER, 27), Gender.MALE);  
	    final Person person15 = new Person(104L, "Amit", "kumar", "shah", DateTimeUtils.createDate(1958, Calendar.DECEMBER, 21), Gender.MALE);
	    final Person person16 = new Person(104L, "Shila", "kumari", "Dixit", DateTimeUtils.createDate(1927, Calendar.FEBRUARY, 15), Gender.FEMALE);
	  			    
	    Map<String, Object> userMap = new HashMap<String, Object>();
	    userMap.put("2", person6);
	    userMap.put("3", person6);
	    userMap.put("4", person6);
	    userMap.put("5", person6);
	    userMap.put("6", person6);
	    userMap.put("7", person7);
	    userMap.put("8", person8);
	    userMap.put("9", person9);
	    userMap.put("10", person10);
	    userMap.put("11", person11);
	    userMap.put("12", person12);
	    userMap.put("13", person13);
	    userMap.put("14", person14);
	    userMap.put("15", person15);
	    userMap.put("16", person16);
	    
	    //putAll all person 			    
	    region.putAll(userMap);
	                
	    */
	    System.out.println("successfully Put set of Person objects into the cache");
	  }

	}