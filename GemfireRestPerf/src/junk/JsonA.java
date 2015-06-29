package junk;

import java.util.Date;

import com.gemstone.gemfire.internal.lang.ObjectUtils;
import com.gemstone.gemfire.pdx.PdxReader;
import com.gemstone.gemfire.pdx.PdxSerializable;
import com.gemstone.gemfire.pdx.PdxWriter;


public class JsonA implements PdxSerializable {

	private static final long serialVersionUID = 42108163264l;
	  
	  protected static final String DOB_FORMAT_PATTERN = "MM/dd/yyyy";
	  
	  private  Long id;
	  
	  private String firstName;
	  private String middleName;
	  private String lastName;
	  
	  public JsonA() {
	  }
	  
	  public JsonA(final Long id) {
	    this.id = id;
	  }
	  
	  public JsonA(final String firstName, final String lastName) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	  }
	  
	  public JsonA(Long id, String fn, String mn, String ln) {
	    this.id = id;
	    this.firstName = fn;
	    this.middleName = mn;
	    this.lastName = ln;
	   
	  }
	  
	  public Long getId() {
	    return id;
	  }
	  
	  public void setId(final Long id) {
	    this.id = id;
	  }
	  
	  public String getFirstName() {
	    return firstName;
	  }
	  
	  public void setFirstName(final String firstName) {
	    this.firstName = firstName;
	  }
	  
	  public String getLastName() {
	    return lastName;
	  }
	  
	  public void setLastName(final String lastName) {
	    this.lastName = lastName;
	  }
	  
	  public String getMiddleName() {
	    return middleName;
	  }
	  
	  public void setMiddleName(final String middleName) {
	    this.middleName = middleName;
	  }
	  
	  
	  
	  @Override
	  public boolean equals(final Object obj) {
	    if (obj == this) {
	      return true;
	    }
	  
	    if (!(obj instanceof JsonA)) {
	      return false;
	    }
	  
	    final JsonA that = (JsonA) obj;
	  
	    return (ObjectUtils.equals(this.getId(), that.getId())
	      && ObjectUtils.equals(this.getLastName(), that.getLastName())
	      && ObjectUtils.equals(this.getFirstName(), that.getFirstName()));
	  }
	  
	  @Override
	  public int hashCode() {
	    int hashValue = 17;
	    hashValue = 37 * hashValue + ObjectUtils.hashCode(getId());
	    hashValue = 37 * hashValue + ObjectUtils.hashCode(getLastName());
	    hashValue = 37 * hashValue + ObjectUtils.hashCode(getFirstName());
	    return hashValue;
	  }
	  
	  @Override
	  public String toString() {
	    final StringBuilder buffer = new StringBuilder("{ type = ");
	    buffer.append(getClass().getName());
	    buffer.append(", id = ").append(getId());
	    buffer.append(", firstName = ").append(getFirstName());
	    buffer.append(", middleName = ").append(getMiddleName());
	    buffer.append(", lastName = ").append(getLastName());
	    buffer.append(" }");
	    return buffer.toString();
	  }
	  
	  @Override
	  public void toData(PdxWriter writer) {
	    writer.writeString("@type", getClass().getName());
	    writer.writeLong("id", id);
	    writer.writeString("firstName", firstName);
	    writer.writeString("middleName", middleName);
	    writer.writeString("lastName", lastName);
	    
	  }
	  
	  @Override
	  public void fromData(PdxReader reader) {
	    String type = reader.readString("@type");
	    id = reader.readLong("id");
	    firstName = reader.readString("firstName");
	    middleName = reader.readString("middleName");
	    lastName = reader.readString("lastName");
	  }
}
