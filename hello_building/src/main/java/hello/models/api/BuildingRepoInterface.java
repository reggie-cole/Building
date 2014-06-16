package hello.models.api;

import hello.models.BuildingModel;

public interface BuildingRepoInterface {

	Iterable<BuildingModel> findAll();
	
	BuildingModel saveBuilding(BuildingModel building);
	
	BuildingModel getBuildingModel(Long buildingId);
	
	void replaceBuilding(Long buildingId,BuildingModel building);
}
