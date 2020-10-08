package cu.musala.network.Repository;

import cu.musala.network.Entity.PeripheralDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeripheralDeviceRepository extends JpaRepository<PeripheralDevice, Long> {
    List<PeripheralDevice> findByGatewayId(int idgateway);
}
