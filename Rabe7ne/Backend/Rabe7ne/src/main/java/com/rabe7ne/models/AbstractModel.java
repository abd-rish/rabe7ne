package com.rabe7ne.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractModel extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void set(String key, Object value) {
		super.put(key, value);
	}
	
	public<T> T get(String key) {
		Object value = super.get(key);
		if(value == null)
			return null;
		return (T) value;
	}
	
	public String getString(String key) {
		Object value = super.get(key);
		if(value == null)
			return null;
		if(value instanceof String)
			return ((String) value).trim();
		value = value.toString().trim();
		super.put(key, value);
		return (String) value;
	}
	
	@SuppressWarnings("rawtypes")
	public List<String> getStringList(String key) {
		Object stringListObject = super.get(key);
		if(stringListObject == null || !(stringListObject instanceof List))
			return null;
		List stringListOfObject = (List) stringListObject;
		List<String> stringList = new ArrayList<String>();
		for(Object stringObject : stringListOfObject) {
			if(stringObject == null)
				return null;
			if(stringObject instanceof String) {
				if(((String) stringObject).isEmpty())
					return null;
				stringList.add((String) stringObject);
				continue;
			}
			return null;
		}
		return stringList;
	}
	
	public Character getCharacter(String key) {
		Object value = super.get(key);
		if(value == null)
			return null;
		if(value instanceof Character)
			return (Character) value;
		if(!(value instanceof String))
			value = value.toString();
		if(((String) value).length() != 1)
			return null;
		value = new Character(((String) value).charAt(0));
		super.put(key, value);
		return (Character) value;
	}
	
	public Long getLong(String key) {
		Object value = super.get(key);
		if(value == null)
			return null;
		if(value instanceof Long)
			return (Long) value;
		value = Long.parseLong(value.toString());
		super.put(key, value);
		return (Long) value;
	}

	@SuppressWarnings("rawtypes")
	public List<Long> getLongList(String key) {
		Object longListObject = super.get(key);
		if(longListObject == null || !(longListObject instanceof List))
			return null;
		List longListOfObject = (List) longListObject;
		List<Long> longList = new ArrayList<Long>();
		for(Object longObject : longListOfObject) {
			if(longObject == null)
				return null;
			if(longObject instanceof Long) {
				longList.add((Long) longObject);
				continue;
			}
			try {
				longList.add(Long.parseLong(longObject.toString()));
			} catch(Exception e) {
				return null;
			}
		}
		return longList;
	}
	
	public Integer getInteger(String key) {
		Object value = super.get(key);
		if(value == null)
			return null;
		if(value instanceof Integer)
			return (Integer) value;
		value = Integer.parseInt(value.toString());
		super.put(key, value);
		return (Integer) value;
	}
	
	public Short getShort(String key) {
		Object value = super.get(key);
		if(value == null)
			return null;
		if(value instanceof Short)
			return (Short) value;
		value = Short.parseShort(value.toString());
		super.put(key, value);
		return (Short) value;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Short> getShortList(String key) {
		Object shortListObject = super.get(key);
		if(shortListObject == null || !(shortListObject instanceof List))
			return null;
		List shortListOfObject = (List) shortListObject;
		List<Short> shortList = new ArrayList<Short>();
		for(Object shortObject : shortListOfObject) {
			if(shortObject == null)
				return null;
			if(shortObject instanceof Short) {
				shortList.add((Short) shortObject);
				continue;
			}
			try {
				shortList.add(Short.parseShort(shortObject.toString()));
			} catch(Exception e) {
				return null;
			}
		}
		return shortList;
	}
	
	public Byte getByte(String key) {
		Object value = super.get(key);
		if(value == null)
			return null;
		if(value instanceof Byte)
			return (Byte) value;
		value = Byte.parseByte(value.toString());
		super.put(key, value);
		return (Byte) value;
	}
	
	public Double getDouble(String key) {
		Object value = super.get(key);
		if(value == null)
			return null;
		if(value instanceof Double)
			return (Double) value;
		value = Double.parseDouble(value.toString());
		super.put(key, value);
		return (Double) value;
	}
	
	public Boolean getBoolean(String key) {
		Object value = super.get(key);
		if(value == null)
			return false;
		if(value instanceof Boolean)
			return (Boolean) value;
		value = Boolean.parseBoolean(value.toString());
		super.put(key, value);
		return (Boolean) value;
	}
	
	public Date getDate(String key) {
		Object value = super.get(key);
		if(value == null)
			return null;
		if(value instanceof Date)
			return (Date) value;
		if(value instanceof Long)
			value = new Date((Long) value);
		else
			value = new Date(Long.parseLong(value.toString()));
		super.put(key, value);
		return (Date) value;
	}
	
	public void setStart(Integer start) {
		set("start", start);
	}
	
	public Integer getStart() {
		return getInteger("start");
	}
	
	public void setLength(Integer length) {
		set("length", length);
	}
	
	public Integer getLength() {
		return getInteger("length");
	}
	
	public void setSizeFlag(Boolean sizeFlag) {
		set("sizeFlag", sizeFlag);
	}
	
	public Boolean getSizeFlag() {
		return getBoolean("sizeFlag");
	}
	
}
