package junk;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.http.HttpHeaders;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheClosedException;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.execute.Function;
import com.gemstone.gemfire.cache.execute.FunctionContext;
import com.gemstone.gemfire.pdx.PdxInstance;


public class TestFunction implements Function {

	public void execute(FunctionContext context) {
	    
	    ArrayList<String> vals = new ArrayList<String>();
	    
	    Cache c = null;
	    try {
	      c = CacheFactory.getAnyInstance();
	    } catch (CacheClosedException ex) {
	      vals.add("NoCacheResult");
	      context.getResultSender().lastResult(vals);
	    }
	    
	    Region r = c.getRegion("People");
	    Object result = r.get("1");
	    if(result instanceof PdxInstance){
	    	PdxInstance pi = (PdxInstance)result;
	    	JsonA actualObj = (JsonA)pi.getObject();
	    	System.out.println("Function TestFunction executed successfully!");
	    	vals.add(actualObj.getId().toString());
	    	vals.add(actualObj.getFirstName());
	    	vals.add(actualObj.getMiddleName());
	    	vals.add(actualObj.getLastName());
	    	context.getResultSender().lastResult(vals);
	    }else {
	    	context.getResultSender().lastResult("Error: result is not PdxInstance");	
	    }
	    
	  }

	  public String getId() {
	    return "TestFunction";
	  }

	  @Override
	  public boolean hasResult() {
	    return true;
	  }

	  @Override
	  public boolean optimizeForWrite() {
	    return false;
	  }

	  @Override
	  public boolean isHA() {
	    return false;
	  }

}
