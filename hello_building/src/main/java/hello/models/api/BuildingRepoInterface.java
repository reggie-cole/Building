package hello.models.api;

import hello.models.BuildingModel;

public interface BuildingRepoInterface {

	Iterable<BuildingModel> findAll();
	
	BuildingModel addBuildingToList(BuildingModel building);
	
	BuildingModel getBuildingModel(Long buildingId);
	
	void replaceBuilding(Long buildingId,BuildingModel building);
	void removeBuilding(Long buildingId);
}
