package cu.musala.network.Service;

import cu.musala.network.Entity.Gateway;
import cu.musala.network.Entity.PeripheralDevice;
import org.springframework.data.domain.Page;

public interface NetworkService {

    int addPeripheralDevice(int idgateway, PeripheralDevice peripheralDevice);

    boolean deletePeripheralDevice(long uidPeripheralDevice);

    Gateway showDetailsGateway(int idgateway);

    Page<Gateway> findAllGatewayPaginated(int page, int size);

}
