package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.CityEntity;
import in.ashokit.entity.StateEntity;

public interface CityRepo extends JpaRepository<CityEntity,Integer>{

	List<CityEntity> findByStateStateId(Integer stateId);

}
