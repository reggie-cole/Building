package hello.controllers;

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
	
	@Autowired
	public BuidlingModelController(BuildingRepoInterface buildingList) {
		this.buildingList = buildingList;
	}

	@RequestMapping(method=RequestMethod.GET)
    public String createBuilding(@ModelAttribute BuildingModel building) {
        return "createBuilding";
    }
	
	@RequestMapping("/{buildingId}")
	public ModelAndView view(@PathVariable("buildingId") BuildingModel building) {
		return new ModelAndView("editBuilding", "building", building);
	}

	
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
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView getListOfBuildings(@Valid BuildingModel model, BindingResult result,
			RedirectAttributes redirect){
			buildingList.saveBuilding(model);
			Iterable<BuildingModel> buildings = buildingList.findAll();
			return new ModelAndView("result","buildings",buildings);
	}
	
	/**
	 * copy data that shouldnt change
	 * @param building
	 * @return
	 */
	private BuildingModel conformEdits(BuildingModel building) {
		Long buildId = building.getBuildingId();
		BuildingModel copyBuiling = buildingList.getBuildingModel(buildId);
		String name = copyBuiling.getBuildingName();
		Double length = copyBuiling.getLength();
		Double width = copyBuiling.getWidth();
		String address = copyBuiling.getBuildingAddress();
		building.setBuildingName(name);
		building.setLength(length);
		building.setWidth(width);
		building.setBuildingAddress(address);
		return building;
	}


    
}
