package hello.controllers;

import hello.models.BuildingModel;
import hello.models.types.BuildingType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    
    @RequestMapping("/")
    public String building() {
    	//model.addAttribute("buildingModel", new BuildingModel("blank", BuildingType.COMMERCIAL, 0.0, 0.0) );
        return "greeting";
    }
//    
//    @RequestMapping(value="/building", method=RequestMethod.POST)
//    public String buildingSubmit(@ModelAttribute BuildingModel building, Model model){
//    	model.addAttribute("buildingModel", building);
//    	return "greeting";
//    }
    
}
