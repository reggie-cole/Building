package hello.controllers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import hello.models.BuildingModel;
import hello.models.api.BuildingRepoInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;





@Controller
@RequestMapping("/")
public class BuidlingModelController {
	public final BuildingRepoInterface buildingList;
	public final BuildingDataBaseService databaseConnector;
	private LinkedList<Long> deletedBuildings;
	
	@Autowired
	public BuidlingModelController(BuildingRepoInterface buildingList,BuildingDataBaseService saver) {
		this.buildingList = buildingList;
		databaseConnector = saver;
		List<BuildingModel> oldBuildings = databaseConnector.loadAllBuildings();
		for(BuildingModel oldBuilding : oldBuildings ){
			buildingList.addBuildingToList(oldBuilding);
		}
		deletedBuildings = new LinkedList<Long>();
	}

	/**
	 * create a building view
	 * @param building
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
    public String createBuilding(@ModelAttribute BuildingModel building) {
        return "createBuilding";
    }
	
	/**
	 * brings up the edit building view
	 * @param building
	 * @return
	 */
	@RequestMapping("/{buildingId}")
	public ModelAndView view(@PathVariable("buildingId") BuildingModel building) {
		return new ModelAndView("editBuilding", "building", building);
	}

	/**
	 * displays the list of buildings
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView listView(){
		Iterable<BuildingModel> buildings = buildingList.findAll();
		return new ModelAndView("result","buildings",buildings);
	}
	
	@RequestMapping(value="/list/{buildingId}",method=RequestMethod.POST)
	public ModelAndView editListView(@Valid BuildingModel building,RedirectAttributes redirect){
		building = conformEdits(building);
		buildingList.replaceBuilding(building.getBuildingId(), building);
		return new ModelAndView("redirect:/list");
	}
	
	/**
	 * deletes the building
	 * @param building
	 * @param redirect
	 * @return
	 */
	@RequestMapping(value="/delete/{buildingId}")
	public ModelAndView deleteBuilding(@Valid BuildingModel building,RedirectAttributes redirect){
		Long deletee = building.getBuildingId();
		buildingList.removeBuilding(deletee);
		// keep track of deleted buildings but dont remove database untill user save changes
		deletedBuildings.add(deletee);
		return new ModelAndView("redirect:/list");
	}
	
	@RequestMapping(value="/save")
	public ModelAndView saveBuildings(RedirectAttributes redirect){
		
		// remove deleted buildings from database
		for(Long deletee : deletedBuildings ){			
			databaseConnector.deleteBuilding(deletee);
		}
		LinkedList<BuildingModel> buildingsToSave = new LinkedList<BuildingModel>();
		Iterable<BuildingModel> buildList = buildingList.findAll();
		Iterator<BuildingModel> buildingItr = buildList.iterator();
		while(buildingItr.hasNext()){
			buildingsToSave.add(buildingItr.next());
		}
		databaseConnector.saveBuildings(buildingsToSave);
		return new ModelAndView("redirect:/list");
	}
	
	/**
	 *  shows list of buildings in table
	 * @param model
	 * @param result
	 * @param redirect
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView getListOfBuildings(@Valid BuildingModel model, BindingResult result,
			RedirectAttributes redirect){
			buildingList.addBuildingToList(model);
			Iterable<BuildingModel> buildings = buildingList.findAll();
			return new ModelAndView("result","buildings",buildings);
	}
	
	/**
	 * copy data that shouldn't change
	 * @param building
	 * @return
	 */
	private BuildingModel conformEdits(BuildingModel building) {
		Long buildId = building.getBuildingId();
		BuildingModel copyBuiling = buildingList.getBuildingModel(buildId);
		Double length = copyBuiling.getLength();
		Double width = copyBuiling.getWidth();
		String address = copyBuiling.getBuildingAddress();
		building.setLength(length);
		building.setWidth(width);
		building.setBuildingAddress(address);
		return building;
	}


    
}
