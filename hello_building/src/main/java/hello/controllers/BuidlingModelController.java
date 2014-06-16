package hello.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.Valid;

import hello.models.BuildingModel;
import hello.models.api.BuildingRepoInterface;
import hello.models.types.BuildingType;
import hello.models.types.ContainerType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
	int count ;
	
	@Autowired
	public BuidlingModelController(BuildingRepoInterface buildingList) {
		count = 0;
		this.buildingList = buildingList;
	}

	@RequestMapping(method=RequestMethod.GET)
    public String building(@ModelAttribute BuildingModel building) {
        return "createBuilding";
    }
	
	@RequestMapping("/{buildingId}")
	public ModelAndView view(@PathVariable("buildingId") BuildingModel building) {
		System.out.println("id: "+building.getBuildingId());
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

	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView getModel(@Valid BuildingModel model, BindingResult result,
			RedirectAttributes redirect){
			buildingList.saveBuilding(model);
			Iterable<BuildingModel> buildings = buildingList.findAll();
			return new ModelAndView("result","buildings",buildings);
	}

    
}
