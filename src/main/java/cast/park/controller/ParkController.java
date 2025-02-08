package cast.park.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import cast.park.controller.ParkController;
import cast.park.controller.model.AttractionsData;
import cast.park.controller.model.LotsData;
import cast.park.controller.model.PerformersData;
import cast.park.service.ParkService;

@RestController
@RequestMapping("/cast_park")
@Slf4j
public class ParkController {

	@Autowired
	private ParkService parkService;
	
	@PostMapping("/lots")
	@ResponseStatus(code = HttpStatus.CREATED)
	public LotsData insertLot(
			@RequestBody LotsData lotsData) {
		log.info("Creating lots {}", lotsData);
		return parkService.saveLots(lotsData);
	}
	
	@PutMapping("/lots/{lotsId}")
	public LotsData updateLots(@PathVariable Long lotsId,
			@RequestBody LotsData lotsData) {
		lotsData.setLotsId(lotsId);
		log.info("Updating lots {}", lotsData);
		return parkService.saveLots(lotsData);
	}
	
	@GetMapping("/lots")
	public List<LotsData> retrieveAllLots(){
		log.info("Retrieve all lots called.");
		return parkService.retrieveAllLots();
	}
	
	@GetMapping("/lots/{lotId}")
	public LotsData retrieveLotById(@PathVariable Long lotId) {
		log.info("Retrieving lot with ID={}", lotId);
		return parkService.retrieveLotById(lotId);
	}
	
	@DeleteMapping("/lots")
	public void deleteAllLots() {
		log.info("Attempting to delete all lots");
		throw new UnsupportedOperationException(
				"Deleting all lots is not allowed");
	}
	
	@DeleteMapping("/lots/{lotsId}")
	public Map<String, String> deleteLotsById(
			@PathVariable Long lotsId){
		log.info("Deleteing lots with ID={}" + lotsId);
		
		parkService.deleteLotsById(lotsId);
		
		return Map.of("message", "Deletion of lots with ID=" 
		+ lotsId + " was successful");
	}
	
	@PostMapping("/lots/{lotsId}/attraction")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AttractionsData insertAttractions(@PathVariable Long lotsId,
			@RequestBody AttractionsData attractionsData) {
		log.info("Creating attraction {} for lots with ID={}",
				attractionsData, lotsId);
		return parkService.saveAttractions(lotsId, attractionsData);
	}
	
	@PutMapping("/lots/{lotsId}/attraction/{attractionId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AttractionsData updateAttractions(@PathVariable Long lotsId,@PathVariable Long attractionId,
			@RequestBody AttractionsData attractionsData) {
		
		attractionsData.setAttractionId(attractionId);
		
		log.info("Updating attraction {} for lots with ID={}",
				attractionsData, lotsId);
		return parkService.saveAttractions(lotsId, attractionsData);
	}
	
	@PostMapping("/attractions/{attractionsId}/performer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PerformersData insertPerformer( @PathVariable Long attractionsId,
			@RequestBody PerformersData performersData) {
		log.info("Creating performer {} for attractions with ID={}",
				performersData, attractionsId);
		return parkService.savePerformers(attractionsId, performersData);
	}
	
	@PutMapping("/attractions/{attractionsId}/performer/{performerId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PerformersData updatePerformer(@PathVariable Long attractionsId,@PathVariable Long performerId,
			@RequestBody PerformersData performersData) {
		
		performersData.setPerformerId(performerId);
		
		log.info("Updating performer {} for attractions with ID={}",
				performersData, attractionsId);
		return parkService.savePerformers(attractionsId, performersData);
	}

	@PostMapping("/performers/{performersId}/lots/{lotsId}")
	public Map<String, String> addPerformerToLot(@PathVariable Long performersId, @PathVariable Long lotsId){
		log.info("Adding performer with ID={} to lot with Id={}" + performersId, lotsId);
		
		parkService.addingPtoL(performersId,lotsId);
		
		return Map.of("message", "Performer with ID=" 
		+ performersId + " successfully added to lot with ID=" + lotsId);
	}
}
