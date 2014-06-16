package hello.models;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;



import java.util.concurrent.atomic.AtomicLong;

import hello.models.api.BuildingRepoInterface;

public class InternalBuildingRepo implements BuildingRepoInterface {

	private static AtomicLong counter = new AtomicLong();
	private final ConcurrentMap<Long, BuildingModel > buildings = new ConcurrentHashMap<Long, BuildingModel>();
	
	@Override
	public Iterable<BuildingModel> findAll() {
		return this.buildings.values();
	}

	@Override
	public BuildingModel saveBuilding(BuildingModel building) {
		Long id = building.getBuildingId();
		if (id == null) {
			id = counter.incrementAndGet();
			building.setBuildingId(id);
		}
		buildings.put(building.getBuildingId(), building);
		return building;
	}

	@Override
	public BuildingModel getBuildingModel(Long id) {
		
		return buildings.get(id);
	}

	@Override
	public void replaceBuilding(Long buildingId, BuildingModel building) {
		buildings.replace(buildingId, building);
		
	}

}
