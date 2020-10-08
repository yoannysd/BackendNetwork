package cu.musala.network.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gateway", schema = "network")
public class Gateway {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gateway")
    @SequenceGenerator(name = "seq_gateway", sequenceName = "network.seq_gateway_id", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "serialnumber")
    private String serialNumber;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "ipv4")
    private String ipv4;

    @JsonManagedReference
    @OneToMany(mappedBy = "gateway", cascade = CascadeType.ALL)
    private List<PeripheralDevice> peripheralDevices = new ArrayList<>();

    public Gateway() {
    }

    public Gateway(@NotBlank String serialNumber, @NotBlank String name, @NotBlank String ipv4) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.ipv4 = ipv4;
    }

    public Integer getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public List<PeripheralDevice> getPeripheralDevices() {
        return peripheralDevices;
    }

    public void setPeripheralDevices(List<PeripheralDevice> peripheralDevices) {
        this.peripheralDevices = peripheralDevices;
    }
}
