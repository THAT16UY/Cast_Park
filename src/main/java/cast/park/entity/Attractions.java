package cast.park.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Attractions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long attractionId;
	
	private String attractionName;
	
	private String attractionType;
	
	private Long waitTime;
	
	private double cost;
	
	@EqualsAndHashCode.Exclude
	  @ToString.Exclude
	  @OneToMany(mappedBy = "attractions", cascade = CascadeType.ALL)
	  private Set<Performers> performers = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	  @ToString.Exclude
	  @ManyToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "lot_id", nullable = false)
	  private Lots lots;
}
