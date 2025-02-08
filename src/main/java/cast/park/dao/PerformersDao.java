package cast.park.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cast.park.entity.Performers;

public interface PerformersDao extends JpaRepository<Performers, Long> {

}
