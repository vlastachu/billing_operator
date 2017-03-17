package model.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Cacheable(false)
@Table(name = "CALLS")
@NamedQueries({
        @NamedQuery(name = "Call.getAllByPhoneNumber", query = "SELECT call FROM Call call WHERE call.phoneNumber LIKE CONCAT('%', :phoneNumber, '%')"),
        @NamedQuery(name = "Call.getAll", query = "SELECT call FROM Call call")
})
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // on that moment I waste so fucking many time. You can't even image this pain.
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "duration", nullable = false)
    private int duration;

    public Call() {
    }

    public Call(int id, String phoneNumber, int duration) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.duration = duration;
    }

    public Call(String phoneNumber, int duration) {
        this.phoneNumber = phoneNumber;
        this.duration = duration;
    }
}
