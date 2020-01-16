package gngapps.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "DEVICE_CHECK_PROBLEMS")
@Entity
public class DeviceCheckProblems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DEVICE_CHECK_ID", referencedColumnName = "id")
    private DeviceCheck deviceCheck;

    @ManyToOne
    @JoinColumn(name = "DEVICE_PROBLEM_ID", referencedColumnName = "id")
    private DeviceProblem problems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeviceCheck getDeviceCheck() {
        return deviceCheck;
    }

    public void setDeviceCheck(DeviceCheck deviceCheck) {
        this.deviceCheck = deviceCheck;
    }

    public DeviceProblem getProblems() {
        return problems;
    }

    public void setProblems(DeviceProblem problems) {
        this.problems = problems;
    }

}
