package hello.models;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;



import java.util.concurrent.atomic.AtomicLong;

import hello.models.api.BuildingRepo;

public class InternalBuildingRepo implements BuildingRepo {

	private static AtomicLong counter = new AtomicLong();
	private final ConcurrentMap<Long, BuildingModel > buildings = new ConcurrentHashMap<Long, BuildingModel>();
	
	@Override
	public Iterable<BuildingModel> findAll() {
		// TODO Auto-generated method stub
		return this.buildings.values();
	}

	@Override
	public BuildingModel saveBuilding(BuildingModel building) {
		building.setBuildingId(counter.addAndGet(1));
		buildings.put(building.getBuildingId(), building);
		return building;
	}

	@Override
	public BuildingModel getBuildingModel(Long id) {
		
		return buildings.get(id);
	}

}
