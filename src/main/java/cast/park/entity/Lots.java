package cast.park.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Lots {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  
  private Long lotId;
  
  private String lotName;
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @OneToMany(mappedBy = "lots", cascade = CascadeType.ALL)
  private Set<Attractions> attractions = new HashSet<>();
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "lot_performers",
         joinColumns = @JoinColumn(name = "lot_id"),
         inverseJoinColumns = @JoinColumn(name = "performer_id"))
  private Set<Performers> performers = new HashSet<>();
}
