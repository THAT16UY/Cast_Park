package cast.park.controller.model;

import cast.park.entity.Performers;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PerformersData {
	private Long performerId; 
	  
	private String performerName;
	  
	private String role;
	
	public PerformersData(Performers performer) {
		performerId = performer.getPerformerId();
		performerName = performer.getPerformerName();
		role = performer.getRole();
	}
}
