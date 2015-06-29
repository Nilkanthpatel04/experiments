package com.gemstone.gemfire.domain;


import java.util.Date;

import com.gemstone.gemfire.internal.lang.ObjectUtils;
import com.gemstone.gemfire.pdx.PdxReader;
import com.gemstone.gemfire.pdx.PdxSerializable;
import com.gemstone.gemfire.pdx.PdxWriter;

import com.gemstone.gemfire.util.DateTimeUtils;

/**
 * The Person class is an abstraction modeling a person.
 * <p/>
 * @author Nilkanth Patel
 * @see org.gopivotal.app.domain.DomainObject
 * @see org.gopivotal.app.domain.support.ResourceSupport
 * @since 7.5
 */

public class Person implements PdxSerializable /*ResourceSupport implements DomainObject<Long>*/  {

  private static final long serialVersionUID = 42108163264l;

  protected static final String DOB_FORMAT_PATTERN = "MM/dd/yyyy";

  private Long id;

  private Date birthDate;

  private Gender gender;

  private String firstName;
  private String middleName;
  private String lastName;

  public Person() {
  }

  public Person(final Long id) {
    this.id = id;
  }

  public Person(final String firstName, final String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  //he constructor Person(long, String, String, String, Date, Gender) is undefined
  public Person(final Long id, final String firstName, final String middleName, final String lastName, Date date, Gender gender) {
    this.id = id;
	this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.birthDate = date;
    this.gender = gender;
  }

  //@XmlAttribute(name = "id")
  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  //@XmlElement(name = "firstName")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  //@XmlElement(name = "lastName")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  //@XmlElement(name = "middleName")
  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(final String middleName) {
    this.middleName = middleName;
  }

  //@XmlElement(name = "birthDate")
  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(final Date birthDate) {
    this.birthDate = birthDate;
  }

  //@XmlElement(name = "gender")
  public Gender getGender() {
    return gender;
  }

  public void setGender(final Gender gender) {
    this.gender = gender;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Person)) {
      return false;
    }

    final Person that = (Person) obj;

    return (ObjectUtils.equals(this.getId(), that.getId())
      || (ObjectUtils.equals(this.getBirthDate(), that.getBirthDate())
      && ObjectUtils.equals(this.getLastName(), that.getLastName())
      && ObjectUtils.equals(this.getFirstName(), that.getFirstName())));
  }

  @Override
  public int hashCode() {
    int hashValue = 17;
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getId());
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getBirthDate());
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
    buffer.append(", birthDate = ").append(DateTimeUtils.format(getBirthDate(), DOB_FORMAT_PATTERN));
    buffer.append(", gender = ").append(getGender());
    buffer.append(" }");
    return buffer.toString();
  }

  @Override
  public void fromData(PdxReader pr) {
	
	id = pr.readLong("id");
	firstName = pr.readString("firstName");
	middleName = pr.readString("middleName");
	lastName = pr.readString("lastName");
	birthDate = pr.readDate("birthDate");
	gender = (Gender)pr.readObject("gender");
  }

  @Override
  public void toData(PdxWriter pw) {
    pw.writeLong("id", id);
    pw.writeString("firstName", firstName);
    pw.writeString("middleName", middleName);
    pw.writeString("lastName", lastName);
    pw.writeDate("birthDate", birthDate);
    pw.writeObject("gender", gender);	
  }

}