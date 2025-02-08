package cast.park.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cast.park.entity.Lots;

public interface LotsDao extends JpaRepository<Lots, Long> {

}
