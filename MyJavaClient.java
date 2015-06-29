
import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gopivotal.app.domain.Gender;
import org.gopivotal.app.domain.Person;

//import org.gopivotal.app.domain.TestObjectForPdxFormatter;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.gemstone.gemfire.internal.Assert;
import com.gemstone.gemfire.pdx.JSONFormatter;
import com.gemstone.gemfire.pdx.PdxFieldTypeMismatchException;
import com.gemstone.gemfire.pdx.PdxInstance;
import com.gemstone.gemfire.pdx.PdxInstanceFactory;
import com.gemstone.gemfire.pdx.PdxSerializationException;
import com.gemstone.gemfire.rest.internal.web.util.DateTimeUtils;

//import com.gemstone.gemfire.rest.internal.web.util.DateTimeUtils;
import com.gemstone.org.json.JSONException;


class RegionOps {
	
	public void getRESTClientObject(Region region){
		
		Object value1 =  region.get("1");
		
		if(value1 instanceof PdxInstance){
			System.out.println("InstanceOf PdxInstance " + value1.toString());
			
		}else
			System.out.println("NOt InstanceOf PdxInstance");
		/*
		Person value1 = (Person) region.get("1");
        System.out.println("Person Received on String type key =1 : " +  value1.toString());
        */
        /*
        Person value = (Person) region.get(1);
        if(value != null)
          System.out.println("Person Received on INT type key =1 : " +  value.toString());
        else
          System.out.println("Value got on INT key 1 is null");
        
        Person value2 = (Person) region.get(2);
        System.out.println("Person Received on int type key = 2 : " +  value2.toString());
        
        Person value3 = (Person) region.get("2");
        if(value3 != null){
          System.out.println("Person Received on String type key = 2 : " +  value3.toString());
        }else
          System.out.println("Value got on String key --> 2 is NULL VALUE ....!!");
        */
    }
	
	public void putJavaClientObject(Region region){
		//Test cases with Person of type Serializable, DataSerializable
        //Create Person Object and set its fields.
		/*
		Person p = new Person(1l);
        p.setFirstName("Diya");
        p.setMiddleName("Sandip");
        p.setLastName("Patel");
        p.setGender(Gender.FEMALE);
        p.setBirthDate(DateTimeUtils.createDate(2009, Calendar.OCTOBER, 03));
        */
		
        //region.put("1", "value1");
        region.put("11", 1101);
        region.put("12", "value-12");
        region.put("13", 9001L);
        
        
        System.out.println("puts successful on string type keys 11, 12, 13");
        
        
	}
}

public class MyJavaClient {

        /**
         * @param args
         */
	 /*************   
	 private  static String verifyPdxInstanceToJSONConversion(Region region) {
		    System.out.println("START Execute REST-APIs testcases...");
		    System.out.println("Step:1 PdxInstance ---------> JSON conversion");
		    Cache c = CacheFactory.getAnyInstance();
		    region = c.getRegion("primitiveKVStore");
		    
		    
		    
		    
		    //PUT
		    TestObjectForPdxFormatter testObject = new TestObjectForPdxFormatter();
		    testObject.defaultInitialization();
		    
		    //
		    ObjectMapper mapper = new ObjectMapper();
	          mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
	          mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	          String jsonString = null;
			try {
				jsonString = mapper.writeValueAsString(testObject);
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	          
	          //CollectionType mapCollectionType = mapper.getTypeFactory().defaultInstance() constructCollectionType(List.class, Map.class);
	          //TestObjectForPdxFormatter newObj = mapper.readValue(jsonStr, clazz);
	          //Object newObj = clazz.newInstance();
	          //String jsonStr = JSONFormatter.toJSON(this);
	          
	          System.out.println("PdxInstance converted to JSON is :: " + jsonString);
	          //JavaType type = mapper.getTypeFactory().constructType(newObj.getClass());
	          
		    
	        try {
				TestObjectForPdxFormatter newObj = mapper.readValue(jsonString, TestObjectForPdxFormatter.class);
				
				System.out.println("JSON to Object Conversion successful");
			} catch (JsonParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	          
	        System.out.println("TEST END");
		    //
		    
		    
		    System.out.println("-----------------------------------JAVA CLIENT PUT-14 START  -----------------------------------------------------");
		    region.put("14", testObject);
		    System.out.println("-----------------------------------JAVA CLIENT PUT-14   END ------------------------------------------------------");
		    System.out.println();
		    
		    System.out.println("-----------------------------------JAVA CLIENT GET('13'), this was put by peer cache - START  -----------------------------------------------------");
		    Object clientObj = (Object)region.get("13");
		    if (clientObj instanceof PdxInstance) {
		      PdxInstance pi = (PdxInstance)clientObj;
		      String clazzName = pi.getClassName();
		      System.out.println("CLAZZ_1 : " +  clazzName);
		    }else {
		      System.out.println("clientObj is not PdxInstance, clientObj.getClass().getName() : " +  clientObj.getClass().getName());
		    }
		    System.out.println("-----------------------------------JAVA CLIENT GET('13') - END ------------------------------------------------------");
		    System.out.println();
		    
		    System.out.println("-----------------------------------JAVA CLIENT GET('14'), this was put by Java client - START  -----------------------------------------------------");
		    Object clientObj2 = (Object)region.get("14");
		    if (clientObj2 instanceof PdxInstance) {
		      PdxInstance pi = (PdxInstance)clientObj2;
		      String clazzName = pi.getClassName();
		      System.out.println("CLAZZ_2 : " +  clazzName);
		    }else {
		      System.out.println("clientObj2 is not PdxInstance, clientObj2.getClass().getName() : " +  clientObj2.getClass().getName());
		    }
		    System.out.println("-----------------------------------JAVA CLIENT GET('14') - END ------------------------------------------------------");
		    System.out.println();
		    
		    
		    //Put PdxInstance
		    System.out.println("-----------------------------------JAVA CLIENT PDXInstance Put('15') - START  -----------------------------------------");
		    //PdxInstanceFactory pif = PdxInstanceFactoryImpl.newCreator("com.gemstone.gemfire.pdx.TestObjectForPdxFormatter", true);
		    PdxInstanceFactory pif = c.createPdxInstanceFactory("com.gemstone.gemfire.pdx.TestObjectForPdxFormatter");
		    		
		    pif.writeLong("p_long", 1000000000000L);
		    pif.writeFloat("p_float",100.001f);
		    pif.writeDouble("p_double", 100000.100000);
		    pif.writeBoolean("p_bool", true);
		    pif.writeByte("p_byte", (byte)10);
		    pif.writeInt("p_int", 1000);
		    pif.writeShort("p_short", (short)100);
		    
		    PdxInstance pInst = pif.create();
		    region.put("15", pInst);
		    System.out.println("-----------------------------------JAVA CLIENT PDXInstance Put('15') - END -----------------------------------------");
		    System.out.println();
		    
		    //GET
		    System.out.println("-----------------------------------JAVA CLIENT PDXInstance Get('15') - START  -----------------------------------------");
		    Object result = (Object)region.get("15");
		    if (result instanceof PdxInstance) { //Output: result is instance of PdxInstance    
		      PdxInstance pi = (PdxInstance)result;
		      String clazzName = pi.getClassName();
		      System.out.println("CLAZZ_2 : " +  clazzName);
		      //1. check for getObject() behaviour
		      try{ 
		        Object obj = pi.getObject();
		       
		        if(obj instanceof TestObjectForPdxFormatter){ //OutPut: piAsObject instanceof TestObjectForPdxFormatter
		          System.out.println(" piAsObject instanceof TestObjectForPdxFormatter ");
		        }else
		          System.out.println(" piAsObject NOT TestObjectForPdxFormatter "); 
		      }catch(PdxSerializationException ex){
		        System.out.println("Error " + ex.getMessage());
		        ex.printStackTrace();
		      }
		      //2. introspectPdxInstanceFields
		     //introspectPdxInstanceFields(pi);
		      
		      //3. Verify pi to json conversion
		      String json = JSONFormatter.toJSON(pi);
		      System.out.println("JSON Result " + json );
		      return json; 
		    } else {
		      try {
		        System.out.println("Result not a pdxInstance result.getClass().getName():: " + result.getClass().getName());
		      }catch(PdxFieldTypeMismatchException e){
		    	System.out.println("Error PdxFieldTypeMismatchException : " + e.getMessage());  
		      }
		    }
		    
		    System.out.println("-----------------------------------JAVA CLIENT PDXInstance Get('15') - END  -----------------------------------------");
		    System.out.println();
		    
		    //TODO:: REST CLEINT Does PUT on key 16
		    
		    //GET
		    System.out.println("-----------------------------------REST CLIENT PDXInstance Get('16') - START  -----------------------------------------");
		    Object result2 = (Object)region.get("16");
		    if (result2 instanceof PdxInstance) { //Output: result is instance of PdxInstance    
		      PdxInstance pi = (PdxInstance)result2;
		      String clazzName = pi.getClassName();
		      System.out.println("CLAZZ_3 : " +  clazzName);
		      //1. check for getObject() behaviour
		      try{ 
		        Object obj = pi.getObject();
		       
		        if(obj instanceof TestObjectForPdxFormatter){ //OutPut: piAsObject instanceof TestObjectForPdxFormatter
		          System.out.println(" piAsObject instanceof TestObjectForPdxFormatter ");
		        }else
		          System.out.println(" piAsObject NOT TestObjectForPdxFormatter "); 
		      }catch(PdxFieldTypeMismatchException e){
		    	System.out.println("Error PdxFieldTypeMismatchException : " + e.getMessage());  
		      }catch(PdxSerializationException ex){
		        System.out.println("Error " + ex.getMessage());
		        ex.printStackTrace();
		      }
		      //2. introspectPdxInstanceFields
		      //introspectPdxInstanceFields(pi);
		      
		      //3. Verify pi to json conversion
		      String json = JSONFormatter.toJSON(pi);
		      System.out.println("JSON Result " + json );
		      return json; 
		    } else {
			      System.out.println("Result not a pdxInstance result2.getClass().getName():: " + result2.getClass().getName());
			}
			    
		    System.out.println("-----------------------------------JAVA CLIENT PDXInstance Get('15') - END  -----------------------------------------");
		    System.out.println();
		    return null;
		  }
	 
	 private static final String ORDER2_AS_JSON = "{"
		      + "\"@type\": \"com.gemstone.gemfire.web.rest.domain.Order\","
		      + "\"purchaseOrderNo\": 112," + "\"customerId\": 102,"
		      + "\"description\": \"Purchase order for company - B\"," + "\"orderDate\": \"02/10/2014\"," + "\"deliveryDate\": \"02/20/2014\","
		      + "\"contact\": \"John Blum\","
		      + "\"email\": \"jblum@gopivotal.com\"," + "\"phone\": \"01-2048096\"," + "\"totalPrice\": 225,"
		      + "\"items\":" + "[" + "{" + "\"itemNo\": 1,"
		      + "\"description\": \"Product-3\"," + "\"quantity\": 6,"
		      + "\"unitPrice\": 20," + "\"totalPrice\": 120" + "}," + "{"
		      + "\"itemNo\": 2," + "\"description\": \"Product-4\","
		      + "\"quantity\": 10," + "\"unitPrice\": 10.5," + "\"totalPrice\": 105"
		      + "}" + "]" + "}";
	 
	 private static HttpHeaders setAcceptAndContentTypeHeaders() {
		    List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		    acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

		    HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(acceptableMediaTypes);
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    return headers;
		  }
	 
	 private static void ValidateTestCase(){
		    
		 Cache c = CacheFactory.getAnyInstance();
		    Region region = c.getRegion("orders");
		 
		//Rest put on key:2
		    HttpHeaders headers = setAcceptAndContentTypeHeaders();
		    HttpEntity<Object> entity = new HttpEntity<Object>(ORDER2_AS_JSON, headers);
		    
		    ResponseEntity<String> result = RestTestUtils.getRestTemplate().exchange(
		        RestTestUtils.GEMFIRE_REST_API_WEB_SERVICE_URL + "/orders/2",
		        HttpMethod.PUT, entity, String.class);
		    
		    //PUT
		    Item it = new Item();
		    it.setItemNo(1L);
		    it.setDescription("Free Item -- testing");
		    it.setQuantity(10);
		    it.setTotalPrice(250.10f);
		    it.setUnitPrice(25.01f);
		    
		    Object obj = region.get("2");
		    if(obj instanceof PdxInstance) {
		      PdxInstance pi = (PdxInstance)obj;
		      Order receivedOrder = (Order)pi.getObject();
		      
		      //System.out.println("Original order size:: " + receivedOrder.getItems().size());
		      //receivedOrder.addItem(it);
		      //System.out.println("Updated order size:: " + receivedOrder.getItems().size());
		      region.put("3", receivedOrder);
		    }
		    
		    //GET
		    Object updResult = region.get("3");
		    if (updResult instanceof PdxInstance) { 
		      System.out.println("updResult is instance of PdxInstance...!!");
		      
		      System.out.println("Step:1 PdxInstance ---------> JSON conversion");
		      PdxInstance pi = (PdxInstance)updResult;
		      
		      try{ 
		        
		        Object piObj = pi.getObject(); 
		        if(piObj instanceof Order){
		          System.out.println("step-1. piAsObject instanceof Order ");
		        }else
		          System.out.println("step-1: piAsObject NOT Order ");
		        
		      }catch(PdxSerializationException ex){
		        System.out.println("Error " + ex.getMessage());
		        ex.printStackTrace();
		      }
		    }
		    
		    System.out.println("successfully Put TestObjectForPdxFormatter into the cache");
		 
		    ///System.out.println("Nilkanth :  TestCase : Validate Java-->JSON-->PdxInstance --> Java Mapping");
		    
		    System.out.println("START Execute REST-APIs testcases...");
		    System.out.println("Step:1 PdxInstance ---------> JSON conversion");
		    //Cache c = CacheFactory.getAnyInstance();
		    region = c.getRegion("primitiveKVStore");
		    
		    TestObjectForPdxFormatter actualTestObject = new TestObjectForPdxFormatter();
		    actualTestObject.defaultInitialization();
		    ObjectMapper objectMapper = new ObjectMapper();
		    objectMapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
		    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		    try {
		      //1. get the json from the object using Jackosn Object Mapper
		      String json = objectMapper.writeValueAsString(actualTestObject);
		      String jsonWithClassType = actualTestObject.addClassTypeToJson(json);
		      System.out.println("Nilkanth : jsonWithClassType:: " + jsonWithClassType);
		      
		      //2. convert json into the PdxInstance and put it into the region
		      PdxInstance pi = JSONFormatter.fromJSON(jsonWithClassType);
		      region.put("201", pi);
		      
		      //3. get the value on key "201" and validate PdxInstance.getObject() API.
		      Object receivedObject = region.get("201");
		      if (receivedObject instanceof PdxInstance){
		        PdxInstance receivedPdxInstance = (PdxInstance)receivedObject;
		        
		        //4. get the actual testObject from the pdxInstance and compare it with actualTestObject
		        Object getObj = receivedPdxInstance.getObject();
		        if(getObj instanceof TestObjectForPdxFormatter){
		          TestObjectForPdxFormatter receivedTestObject = (TestObjectForPdxFormatter)getObj;
		          
		          boolean isEqual = actualTestObject.equals(receivedTestObject);
		          Assert.assertTrue(isEqual, "actualTestObject and receivedTestObject should be equal");
		        }else {
		          System.out.println("Error: getObj is expected to be an instance of TestObjectForPdxFormatter");
		        }
		      }else {
		    	  System.out.println("ERROR:: receivedObject is expected to be of type PdxInstance");
		      }
		      
		    } catch (JsonProcessingException e) {
		      System.out.println("Error:JsonProcessingException:  error encountered while converting JSON from Java object: " + e.getMessage());
		      
		    } catch (JSONException e) {
		    	System.out.println("Error:JSONException:  error encountered while adding @type classType into Json: " + e.getMessage());
		    }
		  }
		  */

        public static void main(String[] args) {
                /*CacheFactory cf = new CacheFactory();
                Cache cache = cf.create();
                RegionFactory rf = cache.createRegionFactory();
                Region region = rf.create("Test1");*/
                
                ClientCacheFactory cf = new ClientCacheFactory().addPoolServer("localhost", 40405);///*.addPoolLocator("10.112.204.2" /*pnq-npatel1*//*"localhost"*/, 11240)*/;
                //ClientCache cache = cf.setPdxReadSerialized(true).create();
                ClientCache cache = cf.create();
                ClientRegionFactory rf = cache.createClientRegionFactory(ClientRegionShortcut.PROXY);
                
                Region region = rf.create("People");
                //Region region1 = rf.create("orders");
                
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
                  actualObj.setFirstName("Nilkanth_updated");
                  actualObj.setLastName("Patel_updated");
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
			    
			    region.putAll(userMap);
                
                
                System.out.println("successfully Put set of Person objects into the cache");
                
                region.invalidate("2");
                region.invalidate("3");
                region.invalidate("4");
                region.invalidate("5");
                region.invalidate("6");
                region.invalidate("7");
                region.invalidate("8");
                
                region.remove("9");
                region.remove("10");
                
                /*
                Object obj = region.get("13");
                if(obj instanceof PdxInstance){
                	System.out.println("Obj is PdxInstance");
                }else {
                	System.out.println("Obj isNOT  PdxInstance");
                }
                */
                
                //verifyPdxInstanceToJSONConversion(region);
                //ValidateTestCase();
                
                /*
                RegionOps regionOps = new RegionOps();
                
                regionOps.getRESTClientObject(region);
                
                //2.Test cases with PortfolioPdx of type PdxSerialization
                //create PortfolioPdx object
                PortfolioPdx portPdx = new PortfolioPdx(10);
                System.out.println("portPdx.toString() = " + portPdx.toString());

                ObjectMapper mapper = new ObjectMapper();
                mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                String jsonString = null;
                try {
                	jsonString = mapper.writeValueAsString(portPdx);

                } catch (JsonProcessingException e) {
                	System.out.println("Error: JsonProcessingException caught");
                	// 	TODO Auto-generated catch block
                	e.printStackTrace();
                }

                System.out.println("Success: cust1 as JSON " + jsonString);
                
                //put the object into cache.
                region.put("11", portPdx);
                System.out.println("successfully Put PortfolioPdx into the cache");
                
                Boolean b = new Boolean(false);
                Byte by = new Byte((byte)11);
                Short sh = new Short((short)101);
                Integer in = new Integer(1001);
                Long lo = new Long(987654321234567L);
                BigInteger bi = new BigInteger("12345678910");
                Float fl = new Float(789.456f);
                BigDecimal bd = new BigDecimal(8866333);
                Double doub = new Double(123456.9876d);
                String str = new String("Nilkanth Patel");
                */
                
                /*
                TestObjectForPdxFormatter testObject = new TestObjectForPdxFormatter(true, (byte)10, (short)100, 1000, 1234567898765432L, 123.456f, 98765.12345d,
                		b, by, sh, in, lo, bi ,fl, bd ,doub, str
                		);
                */
                
                /*
                TestObjectForPdxFormatter testObject = new TestObjectForPdxFormatter();
                testObject.defaultInitialization();
                
                System.out.println("testObject.toString() = " + testObject.toString());

                jsonString = null;
                try {
                	jsonString = mapper.writeValueAsString(testObject);

                } catch (JsonProcessingException e) {
                	System.out.println("Error: JsonProcessingException caught");
                	// 	TODO Auto-generated catch block
                	e.printStackTrace();
                }

                System.out.println("Success: testObject as JSON " + jsonString);
                
                //put the object into cache.
                region.put("13", testObject);
                */
                /*
                Object result = (Object)region.get("13");
                if (result instanceof PdxInstance) { 
                  System.out.println("result is instance of PdxInstance...!!");
                  
                  System.out.println("Step:1 PdxInstance to JSON conversion");
                  PdxInstance pi = (PdxInstance)result;
                  
                  String json = JSONFormatter.toJSON(pi);
                  System.out.println("JSON Result " + json );
                
                
                  System.out.println("Step:2 JSON to PdxInstance conversion");
                  PdxInstance pInstance = JSONFormatter.fromJSON(json);
                  Object piAsObject = pInstance.getObject();
                  
                  
                  if(piAsObject instanceof TestObjectForPdxFormatter){
                	  System.out.println("piAsObject instanceof TestObjectForPdxFormatter ");
                  }else {
                	  System.out.println("piAsObject NOT of type TestObjectForPdxFormatter");
                  }
                }else {
                	System.out.println("Error: result not PDXInstance type");
                }
                
                */
                
                //
                //regionOps.putJavaClientObject(region);
                
                /*JSONObject json = new JSONObject();
                
                String key = "key";
                Object value = "501";
                try {
					json.put(key, value);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //String json = "{ " + "\"" + key.toString() +  "\"" + ":" + value + "}";
                //System.out.println(" JSON :: " + json);
                System.out.println("JSON :: " + json.toString());
                */
                
                //1.START:: Test cases with Person of type DataSerializable
                //put and then get Person object
                /***************************************
                Person p1 = new Person(10l);
                p1.setFirstName("Diya");
                p1.setMiddleName("Sandipkumar");
                p1.setLastName("Patel");
                p1.setGender(Gender.FEMALE);
                p1.setBirthDate(DateTimeUtils.createDate(2009, Calendar.OCTOBER, 3));
                
                //put
                region.put("10", p1);
                System.out.println("person1.toString() = " + p1.toString());
                
                //get
                Person person = (Person)region.get("10");
                if(person instanceof Person){
                	System.out.println("Instance of Person");
                	System.out.println("firstname = " + person.getFirstName() + " Lastname = " + person.getLastName());
                }else{
                	System.out.println("NOT Instance of Person");
                }
                //END:: Test cases with Person of type DataSerializable
                
                
                //PdxInstance pi = (PdxInstance) cache.createPdxInstanceFactory("").writeString("key1", "key2").create();
                //PPerson person = new PPerson(1, "Sachin", "Tendulkar");
                //PdxInstanceFactory pdxFact = PdxInstanceFactoryImpl.newCreator(name, expectDomainClass)
                
                //2.Test cases with PortfolioPdx of type PdxSerialization
                //create PortfolioPdx object
                PortfolioPdx portPdx = new PortfolioPdx(10);
                System.out.println("portPdx.toString() = " + portPdx.toString());
                
                //put the object into cache.
                region.put("11", portPdx);
                *****************************************/
                //
                // put/get code with serialized read behavior
                // put is done as normal
                //region.put("11", portPdx);
                
                // get checks Object type and handles each appropriately
                //Object myObject = region.get("11");
                /*
                if (myObject instanceof PdxInstance) {
                	// get returned PdxInstance instead of domain object
                	PdxInstance myPdxInstance = (PdxInstance)myObject;
                	
                	String myJson = JSONFormatter.toJSON(myPdxInstance);
                	
                	System.out.println(" my JSOn :: " + myJson);
                	
                	// PdxInstance.getField deserializes the field, but not the object
                	String fieldValue = (String) myPdxInstance.getField("pkid");
                	
                	System.out.println("RESULT ---> " + fieldValue);
                	// Update a field and put it back into the cache
                	// without deserializing the entire object
                	
                	//WritablePdxInstance myWritablePdxI = myPdxInstance.createWriter();
                	//myWritablePdxI.setField("fieldName", fieldValue);
                	//region.put(key, myWritablePdxI);
                	
                	// Deserialize the entire object if needed, from the PdxInstance
                	
                	//DomainClass myPdxObject = (DomainClass);
                	
                //
                } else if (myObject instanceof PdxSerializable) {
                	System.out.println("Object is PDXSerializable...");
                	
                } else {
                	System.out.println("ERROR ----> Object is not PDXInstance NOR PDXSerializable ");
                }
                */
                System.out.println("TEST Ended Successfully....!!!!");
                
                
                
                /*
                final Set<Region<?, ?>> regionSet =  cache.rootRegions();
                
                System.out.println("Following are the regions found in the Server");
                for(Region<?, ?> r : regionSet){
                	System.out.println(r.getName());
                	
                }
                GetRegions myFunction = new GetRegions();
                
                //PoolImpl p = (PoolImpl) PoolManager.createFactory().addServer("localhost", 12480);
                FunctionService.registerFunction(myFunction);
                
                //Map<String, Function> myMap = FunctionService.getRegisteredFunctions();
                
                //System.out.println("Total size for registered functions ::: " + myMap.size());
                
                Execution exe = FunctionService.onServer(cache.getDefaultPool());
                
                System.out.println("Executing the function");
                
                ResultCollector<?, ?> rc =  exe.execute("GetRegions");
                
                ArrayList result = (ArrayList)rc.getResult();
                
                List<String> list = (List<String>)result.get(0);
                System.out.println(" Region at the index 0 is " +  list.get(0));

                System.out.println(" Region at the index 1 is " +  list.get(1));
                
              System.out.println("Function executed remotelly successfully , Size is = "  + result.size());
                
                //ResultCollector rc = exe.execute(new PutKeyFunction() {  GemFireCacheImpl.getExisting(null).rootRegions()});
                
                //Set result = (Set) rc.getResult();
                
                //System.out.println("Function executed remotelly successfully , Size is = "  + result.size());
                */
                /*Execution execution = FunctionService.onRegion(exampleRegion).withFilter(
                        keysForGet).withArgs(Boolean.TRUE).withCollector(
                        new MyArrayListResultCollector());
                    ResultCollector rc = execution.execute(function);
                    
               */
                /*
                Person p = new Person();
                
                p.setId(101L);
                p.setFirstName("Nilkanth");
                p.setLastName("Patel");
                p.setMiddleName("Hirabhai");
                p.setBirthDate(DateTimeUtils.createDate(1983, Calendar.JUNE, 1));
                p.setGender(Gender.MALE);
                
                region.put(1,p);
                
                ////////////////////////////
                p.setId(102L);
                p.setFirstName("Hemant");
                p.setLastName("Bhanavat");
                p.setMiddleName("Unknown");
                p.setBirthDate(DateTimeUtils.createDate(1980, Calendar.JANUARY, 1));
                p.setGender(Gender.MALE);
                
                region.put(2,p);
                
                //////////////////////////
                p.setId(103L);
                p.setFirstName("TUshar");
                p.setLastName("Khairnar");
                p.setMiddleName("Unknown");
                p.setBirthDate(DateTimeUtils.createDate(1983, Calendar.JUNE, 15));
                p.setGender(Gender.MALE);
                
                region.put(3,p);
                region.put(2,2);
                region.put(3,3);
                region.put(4,4);
                region.put(5,5);
                */
                //System.out.println("Hello!!!!!");
                
                /*System.out.println(region.get(1));
                System.out.println(region.get(2));
                System.out.println(region.get(3));
                System.out.println(region.get(4));
                System.out.println(region.get(5));
                */

        }

}
