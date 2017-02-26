package model.entities;

import javax.persistence.*;

@Entity
@Cacheable(false)
@Table(name = "CALLS")
@NamedQueries({
        @NamedQuery(name = "Call.getAllByPhoneNumber", query = "SELECT call FROM Call call WHERE call.phoneNumber = :phoneNumber"),
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Call call = (Call) o;

        if (id != call.id) return false;
        if (duration != call.duration) return false;
        return phoneNumber != null ? phoneNumber.equals(call.phoneNumber) : call.phoneNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + duration;
        return result;
    }

    @Override
    public String toString() {
        return "Call{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", duration=" + duration +
                '}';
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

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
