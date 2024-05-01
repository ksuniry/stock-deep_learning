package com.stock.core;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stock.utils.CommonUtil;

//public abstract class ProgresSqlJsonTypeHandler<JSONObject extends Object> extends BaseTypeHandler<JSONObject> {
public class ProgresSqlJsonTypeHandler extends BaseTypeHandler<Object> {
		private static final ObjectMapper mapper = new ObjectMapper();

	    @Override
	    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
	    	
	    	JsonObject p = (JsonObject) parameter;
	    	
	        ps.setObject(i,new Gson().toJson(p));
	    }

	    @Override
	    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
	    	JsonElement jsonElement = JsonParser.parseString(rs.getString(columnName));
	    	JsonObject jsonObject = null;
	    	if(CommonUtil.isEmpty(jsonElement)) {
	    		jsonObject = new JsonObject();
	    	}else {
	    		jsonObject  = (JsonObject) jsonElement;
	    	}
	    	return jsonObject;
	    }

	    @Override
	    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
	    	JsonElement jsonElement = JsonParser.parseString(rs.getString(columnIndex));
	    	JsonObject jsonObject = null;
	        if(CommonUtil.isEmpty(jsonElement)) {
	    		jsonObject = new JsonObject();
	    	}else {
	    		jsonObject  = (JsonObject) jsonElement;
	    	}
	    	return jsonObject;
	    }

	    @Override
	    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
	    	JsonElement jsonElement = JsonParser.parseString(cs.getString(columnIndex));
	    	JsonObject jsonObject = null;
	        if(CommonUtil.isEmpty(jsonElement)) {
	    		jsonObject = new JsonObject();
	    	}else {
	    		jsonObject  = (JsonObject) jsonElement;
	    	}
	    	return jsonObject;
	    }
	    
	    /*
	    private T toObject(String content, Class<?> clazz) {
	        if (content != null && !content.isEmpty()) {
	            try {
	                return (T) mapper.readValue(content, clazz);
	            } catch (Exception e) {
	                throw new RuntimeException(e);
	            }
	        } else {
	            return null;
	        }
	    }
	     */
	   
}