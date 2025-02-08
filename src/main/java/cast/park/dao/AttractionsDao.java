package cast.park.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cast.park.entity.Attractions;

public interface AttractionsDao extends JpaRepository<Attractions, Long> {

}
