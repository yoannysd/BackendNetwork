package cu.musala.network.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "peripheraldevice", schema = "network")
public class PeripheralDevice {

    public static final Integer OFFLINE = 1;
    public static final Integer ONLINE = 2;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_peripheraldevice")
    @SequenceGenerator(name = "seq_peripheraldevice", sequenceName = "network.seq_peripheraldevice_uid", initialValue = 1, allocationSize = 1)
    @Column(name = "uid")
    private Long uid;

    @NotBlank
    @Column(name = "vendor")
    private String vendor;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @NotNull
    @Column(name = "status")
    private Integer status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "gatewayid", referencedColumnName = "id")
    private Gateway gateway;

    public PeripheralDevice() {
    }

    public PeripheralDevice(@NotBlank String vendor) {
        this.vendor = vendor;
        this.date = LocalDate.now();
        this.status = PeripheralDevice.OFFLINE;
    }

    public PeripheralDevice(@NotBlank String vendor, Gateway gateway) {
        this.vendor = vendor;
        this.date = LocalDate.now();
        this.status = PeripheralDevice.OFFLINE;
        this.gateway = gateway;
    }

    public PeripheralDevice(@NotBlank String vendor, @NotNull LocalDate date, @NotNull Integer status, Gateway gateway) {
        this.vendor = vendor;
        this.date = date;
        this.status = status;
        this.gateway = gateway;
    }

    public Long getUid() {
        return uid;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(@NotBlank String vendor) {
        this.vendor = vendor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(@NotNull Integer status) {
        this.status = status;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }
}
