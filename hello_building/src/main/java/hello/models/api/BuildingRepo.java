package hello.models.api;

import hello.models.BuildingModel;

public interface BuildingRepo {

	Iterable<BuildingModel> findAll();
	
	BuildingModel saveBuilding(BuildingModel building);
	
	BuildingModel getBuildingModel(Long buildingId);
}
