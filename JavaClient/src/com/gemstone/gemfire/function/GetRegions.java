package com.gemstone.gemfire.function;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheClosedException;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.execute.FunctionAdapter;
import com.gemstone.gemfire.cache.execute.FunctionContext;

public class GetRegions extends FunctionAdapter implements Declarable {

	  public void execute(FunctionContext context) {
	    Cache c = null;
	    ArrayList<Object> vals = new ArrayList<Object>();
	    try {
	      c = CacheFactory.getAnyInstance();
	      final Set<Region<?, ?>> regions = c.rootRegions();
	     
	      for (Object item : regions) {
		    vals.add(item);
		  }
		} catch (CacheClosedException ex) {
	      vals.add("[Error:CacheClosedException]");
	      context.getResultSender().lastResult(vals);
	    }
	    
	    
	    System.out.println("Length of vals = " + vals.size());
	    context.getResultSender().lastResult(vals);
	  }

	  public String getId() {
	    return "GetRegions";
	  }

	  public void init(Properties arg0) {
	  }
	}