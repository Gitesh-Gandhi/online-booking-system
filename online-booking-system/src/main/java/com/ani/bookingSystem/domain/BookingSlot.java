package com.ani.bookingSystem.domain;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class BookingSlot {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "starting_time", nullable = false)
    private LocalTime startingTime;   

    @Column(name = "ending_time", nullable = false)
    private LocalTime endingTime; 

    @Column(name = "price", nullable = false)
    private Double price; 

    @Column(name = "air_conditioning")
    private String airConditioning; 

    @Column(name = "no_of_stops")
    private Integer noOfStops; 

    @Column(name = "service_available")
    private String serviceAvailable;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_booking", 
            joinColumns = @JoinColumn(name = "booking_slot_id"), 
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Users> users = new ArrayList<>();
}

