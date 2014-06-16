package hello.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import hello.Application;
import hello.models.BuildingModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

public class BuildingDataBaseService {

	 @Autowired
	 JdbcTemplate jdbcTemplate;
	 
	    @Transactional
	    public void saveBuilding(BuildingModel... buildings) {
	        for (BuildingModel building : buildings) {
	        	String name = building.getBuildingName();
	        	String address = building.getBuildingAddress();
	        	int room = building.getRooms();
	        	int floor = building.getFloorCount();
	        	Long id = building.getBuildingId();
	            System.out.println("Booking " + building.getBuildingName() + " in a seat...");
	            //UPDATE TEST SET NAME='Hi' WHERE ID=1;
	            String query = "UPDATE "+Application.BUILDING_TABLE+
	            				" SET "+Application.BUILDING_NAME+" =? , "+Application.BUILDING_ADDRESS+" =?, "+Application.BUILDING_FLOOR+" =?, "+Application.BUILDING_ROOM+" =? " +
	            				" WHERE "+Application.BUILDING_ID+"=? ";
	            Object[] args = new Object[]{building.getBuildingName(),building.getBuildingAddress(),building.getFloorCount(),building.getRooms(),building.getBuildingId()};
	            // try to update first
	            int queryResult = jdbcTemplate.update(query, args);
	          
	            if(queryResult < 1){
	            	System.out.println("building not found: inserting new");
	            	 query = "insert into "+ Application.BUILDING_TABLE+"("+Application.BUILDING_NAME+","+Application.BUILDING_ID+","+Application.BUILDING_ADDRESS+","+Application.BUILDING_FLOOR+","+Application.BUILDING_ROOM+") "
	 	            		+ "     values (\'"+name+"\',"+id+",\'"+address+"\',"+floor+","+room+") " ;
	            	 jdbcTemplate.execute(query);
	            }else{
	            	System.out.println(queryResult+" rows updated");
	            }
	
	           
	        }
	    };
	    
	    public List<String> getAllBuildingNames() {
	        return jdbcTemplate.query("select "+Application.BUILDING_NAME+" from "+ Application.BUILDING_TABLE, new RowMapper<String>() {
	            @Override
	            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
	            
	            			 
	                return rs.getString(Application.BUILDING_NAME);
	            }
	        });
	    }
	    
	    /**
	     * loads buildings from database
	     * @return
	     */
	    public List<BuildingModel> loadAllBuildings(){
	    	return jdbcTemplate.query("select * from "+ Application.BUILDING_TABLE, new RowMapper<BuildingModel>() {
	            @Override
	            public BuildingModel mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	BuildingModel building = new BuildingModel();
	            	building.setBuildingAddress(rs.getString(Application.BUILDING_NAME));
	            	building.setBuildingId(rs.getLong(Application.BUILDING_ID));	
	            	building.setBuildingName(rs.getString(Application.BUILDING_NAME));
	            	building.setFloorCount(rs.getInt(Application.BUILDING_FLOOR));
	            	building.setRooms(rs.getInt(Application.BUILDING_ROOM));
	                return building;
	            }
	        });
	    }

	    /**
	     * deletes building
	     * @param buildingId
	     */
	    @Transactional
	    public void deleteBuilding(Long buildingId){
		   String query = "DELETE FROM "+Application.BUILDING_TABLE+" WHERE "+Application.BUILDING_ID+" ="+buildingId;
		   jdbcTemplate.execute(query);
	    }
	    
	    /**
	     * Saves buildins to database
	     * @param buildingsToSave
	     */
	    @Transactional
		public void saveBuildings(LinkedList<BuildingModel> buildingsToSave) {
			 for (BuildingModel building : buildingsToSave) {
		        	String name = building.getBuildingName();
		        	String address = building.getBuildingAddress();
		        	int room = building.getRooms();
		        	int floor = building.getFloorCount();
		        	Long id = building.getBuildingId();
		        	building.buildingPrintInfo();
		            String query = "UPDATE "+Application.BUILDING_TABLE+
		            				" SET "+Application.BUILDING_NAME+" =? , "+Application.BUILDING_ADDRESS+" =?, "+Application.BUILDING_FLOOR+" =?, "+Application.BUILDING_ROOM+" =? " +
		            				" WHERE "+Application.BUILDING_ID+"=? ";
		            Object[] args = new Object[]{building.getBuildingName(),building.getBuildingAddress(),building.getFloorCount(),building.getRooms(),building.getBuildingId()};
		            // try to update first
		            int queryResult = jdbcTemplate.update(query, args);
		          
		            if(queryResult < 1){
		            	System.out.println("building not found: inserting new");
		            	 query = "INSERT into "+ Application.BUILDING_TABLE+"("+Application.BUILDING_NAME+","+Application.BUILDING_ID+","+Application.BUILDING_ADDRESS+","+Application.BUILDING_FLOOR+","+Application.BUILDING_ROOM+") "
		 	            		+ "     values (\'"+name+"\',"+id+",\'"+address+"\',"+floor+","+room+") " ;
		            	 jdbcTemplate.execute(query);
		            }else{
		            	System.out.println(queryResult+" rows updated");
		            }
		
		           
		        }
			
		};
}
