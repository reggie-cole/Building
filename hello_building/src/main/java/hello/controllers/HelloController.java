package hello.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.Valid;

import hello.models.BuildingModel;
import hello.models.api.BuildingRepo;
import hello.models.types.BuildingType;
import hello.models.types.ContainerType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
@RequestMapping("/")
public class HelloController {
	public final BuildingRepo buildingList;
	int count ;
	
	@Autowired
	public HelloController(BuildingRepo buildingList) {
		count = 0;
		this.buildingList = buildingList;
	}

	@RequestMapping(method=RequestMethod.GET)
    public String building(@ModelAttribute BuildingModel building) {
        return "greeting";
    }
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView listView(){
		Iterable<BuildingModel> buildings = buildingList.findAll();
		return new ModelAndView("result","buildings",buildings);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView getModel(@Valid BuildingModel model, BindingResult result,
			RedirectAttributes redirect){
			buildingList.saveBuilding(model);
			Iterable<BuildingModel> buildings = buildingList.findAll();
			return new ModelAndView("result","buildings",buildings);
	}

    
}
