package hello.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import hello.models.BuildingModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

public class BuildingSaveService {

	 @Autowired
	 JdbcTemplate jdbcTemplate;
	 
	 @Transactional
	    public void saveBuilding(BuildingModel... buildings) {
	        for (BuildingModel building : buildings) {
	            System.out.println("Booking " + building.getBuildingName() + " in a seat...");
	            jdbcTemplate.update("insert into BUILDING_TABLE(BUILDING_NAME,BUILDING_ID) values (?,?)", building.getBuildingName(),building.getBuildingId());
	        }
	    };
	    
	    public List<String> findAllBuilding() {
	        return jdbcTemplate.query("select FIRST_NAME from BOOKINGS", new RowMapper<String>() {
	            @Override
	            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
	                return rs.getString("FIRST_NAME");
	            }
	        });
	    }
}
