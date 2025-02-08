package cast.park.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cast.park.controller.model.AttractionsData;
import cast.park.controller.model.LotsData;
import cast.park.controller.model.PerformersData;
import cast.park.dao.AttractionsDao;
import cast.park.dao.LotsDao;
import cast.park.dao.PerformersDao;
import cast.park.entity.Attractions;
import cast.park.entity.Lots;
import cast.park.entity.Performers;


@Service
public class ParkService {

	@Autowired
	private AttractionsDao attractionsDao;
	
	@Autowired
	private PerformersDao performersDao;
	
	@Autowired
	private LotsDao lotsDao;

	@Transactional(readOnly = false)
	public LotsData saveLots(LotsData lotsData) {
		Long lotsId = lotsData.getLotsId();
		Lots lots = findOrCreateLots(lotsId);
		
		setFieldsInLots(lots, lotsData);
		return new LotsData(lotsDao.save(lots));
	}

	private void setFieldsInLots(Lots lots, LotsData lotsData) {
		lots.setLotId(lotsData.getLotsId());
		lots.setLotName(lotsData.getLotName());	
	}

	private Lots findOrCreateLots(Long lotsId) {
		Lots lots;
		if(Objects.isNull(lotsId)) {
			lots = new Lots();
		}
		else {
			lots = findLotsById(lotsId);
		}
		return lots;
	}

	private Lots findLotsById(Long lotsId) {
		return lotsDao.findById(lotsId)
				.orElseThrow(() -> new NoSuchElementException("lots with ID="
						+  lotsId + " was not found."));
	}

	public LotsData retrieveLotById(Long lotId) {
		Lots contributor = findLotsById(lotId);
		return new LotsData(contributor);
	}

	@Transactional(readOnly = true)
	public List<LotsData> retrieveAllLots() {
		List<Lots> lots = lotsDao.findAll();
		List<LotsData> responce = new LinkedList<>();
		
		for(Lots contributor : lots) {
			responce.add(new LotsData(contributor));
		}
		
		return responce;
	}

	@Transactional(readOnly = false)
	public void deleteLotsById(Long lotsId) {
		Lots lots = findLotsById(lotsId);
		lotsDao.delete(lots);
	}

	@Transactional(readOnly = false)
	public AttractionsData saveAttractions(Long lotsId,
			AttractionsData attractionsData) {
		
		Lots lots = findLotsById(lotsId);
		
		Attractions attractions = findOrCreateAttractions(lotsId,attractionsData.getAttractionId());
		setAttractionsFields(attractions, attractionsData);
		
		attractions.setLots(lots);
		lots.getAttractions().add(attractions);
		
		Attractions dbAttractions = attractionsDao.save(attractions);
		return new AttractionsData(dbAttractions);


	}

	private Attractions findOrCreateAttractions(Long lotsId, Long attractionsId) {
		Attractions attractions;
	
		if(Objects.isNull(attractionsId)) {
			attractions = new Attractions();
		}
		else {
			attractions = findAttractionsById(attractionsId, lotsId);
		}
		
		return attractions;

	}

	private Attractions findAttractionsById(Long attractionsId, Long lotsId) {
		 Attractions attractions = attractionsDao.findById(attractionsId).orElseThrow(() -> new NoSuchElementException(
				"Attractions with ID=" + attractionsId + " dose not exisit"));
		 if(attractions.getLots().getLotId() != lotsId) {
			 throw new IllegalArgumentException("The attraction with ID= " + attractionsId + " does not belong to the lot with ID = "
					 + lotsId);
		 }
		 return attractions;
	}

	private void setAttractionsFields(Attractions attractions, AttractionsData attractionsData) {
		attractions.setAttractionId(attractionsData.getAttractionId());
		attractions.setAttractionName(attractionsData.getAttractionName());
		attractions.setAttractionType(attractionsData.getAttractionType());
		attractions.setWaitTime(attractionsData.getWaitTime());
		attractions.setCost(attractionsData.getCost());
	}

	@Transactional(readOnly = false)
	public PerformersData savePerformers(Long attractionsId,
			PerformersData performersData) {
		
		Attractions attractions = findAttractionsById(attractionsId);
		
		Performers performers = findOrCreatePerformers(attractionsId,performersData.getPerformerId());
		setPerformersFields(performers, performersData);
		
		performers.setAttractions(attractions);
		attractions.getPerformers().add(performers);
		
		Performers dbPerformers = performersDao.save(performers);
		return new PerformersData(dbPerformers);


	}
	
	private Attractions findAttractionsById(Long attractionsId) {
		 Attractions attractions = attractionsDao.findById(attractionsId).orElseThrow(() -> new NoSuchElementException(
				"Attractions with ID=" + attractionsId + " dose not exisit"));
		 return attractions;
	}

	private void setPerformersFields(Performers performers, PerformersData performersData) {
		performers.setPerformerId(performersData.getPerformerId());
		performers.setPerformerName(performersData.getPerformerName());
		performers.setRole(performersData.getRole());
	}

	private Performers findOrCreatePerformers(Long attractionsId, Long performersId) {
		Performers performers;
	
		if(Objects.isNull(performersId)) {
			performers = new Performers();
		}
		else {
			performers = findPerformersById(performersId, attractionsId);
		}
		
		return performers;

	}

	private Performers findPerformersById(Long performersId, Long attractionsId) {
		 Performers performers = performersDao.findById(performersId).orElseThrow(() -> new NoSuchElementException(
				"Performers with ID=" + performersId + " dose not exisit"));
		 if(performers.getAttractions().getAttractionId() != attractionsId) {
			 throw new IllegalArgumentException("The attraction with ID= " + performersId + " does not belong to the lot with ID = "
					 + attractionsId);
		 }
		 return performers;
	}

	public void addingPtoL(Long performersId, Long lotsId) {
		
		Performers performers = findPerformersById(performersId);
		Lots lots = findLotsById(lotsId);
		
		performers.getLots().add(lots);
		lots.getPerformers().add(performers);
		
		lotsDao.save(lots);
		
	}

	private Performers findPerformersById(Long performersId) {
		Performers performers = performersDao.findById(performersId).orElseThrow(() -> new NoSuchElementException(
				"Performers with ID=" + performersId + " dose not exisit"));
		return performers;
	}




}
