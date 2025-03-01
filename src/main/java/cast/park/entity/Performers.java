package cast.park.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Performers {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long performerId; 
  
  private String performerName;
  
  private String role;
  
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(mappedBy = "performers")
  private Set<Lots> lots = new HashSet<>();
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "attraction_id", nullable = false)
  private Attractions attractions;
}
