package cu.musala.network.Service;

import cu.musala.network.Entity.Gateway;
import cu.musala.network.Entity.PeripheralDevice;
import cu.musala.network.Repository.GatewayRepository;
import cu.musala.network.Repository.PeripheralDeviceRepository;
import cu.musala.network.Util.LoggerUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NetworkServiceImpl implements NetworkService {

    private GatewayRepository gatewayRepository;
    private PeripheralDeviceRepository peripheralDeviceRepository;


    public NetworkServiceImpl(GatewayRepository gatewayRepository, PeripheralDeviceRepository peripheralDeviceRepository) {
        this.gatewayRepository = gatewayRepository;
        this.peripheralDeviceRepository = peripheralDeviceRepository;
    }

    public List<Gateway> showAllGateways() {
        return new ArrayList<>(gatewayRepository.findAll());
    }

    @Override
    public Page<Gateway> findAllGatewayPaginated(int page, int size) {
        return gatewayRepository.findAll(PageRequest.of(page, size, Sort.Direction.ASC, "name"));
    }

    @Override
    public Gateway showDetailsGateway(int idgateway) {
        Optional<Gateway> optionalGateway = gatewayRepository.findById(idgateway);
        if (optionalGateway.isPresent()) {
            return optionalGateway.get();
        } else {
            LoggerUtil.logger.error("The gateway with id " + idgateway + " wasn't found.");
            return null;
        }
    }

    @Override
    public int addPeripheralDevice(int idgateway, PeripheralDevice peripheralDevice) {
        Optional<Gateway> optionalGateway = gatewayRepository.findById(idgateway);
        if (optionalGateway.isPresent()) {
            Gateway g = optionalGateway.get();

            List<PeripheralDevice> peripheralDevices = peripheralDeviceRepository.findByGatewayId(idgateway);

            if (peripheralDevices.size() <= 9) {
                peripheralDevice.setDate(LocalDate.now());
                peripheralDevice.setStatus(PeripheralDevice.OFFLINE);
                peripheralDevice.setGateway(g);
                peripheralDeviceRepository.save(peripheralDevice);
                return 0;
            }
            LoggerUtil.logger.error("The gateway with id " + idgateway + " already has the maximum number of peripherical devices.");
            return -1;
        }

        LoggerUtil.logger.error("The gateway with id " + idgateway + " wasn't found.");
        return 1;
    }

    @Override
    public boolean deletePeripheralDevice(long uidPeriphericalDevice) {
        Optional<PeripheralDevice> optionalPeripheralDevice = peripheralDeviceRepository.findById(uidPeriphericalDevice);
        if (optionalPeripheralDevice.isPresent()) {
            PeripheralDevice p = optionalPeripheralDevice.get();

            //The peripheral devices is not actually removed, the relation with gateway is what is removed.
            p.setGateway(null);
            peripheralDeviceRepository.save(p);
            return true;
        } else {
            LoggerUtil.logger.error("The peripherical device with uid " + uidPeriphericalDevice + " wasn't found.");
        }
        return false;
    }
}
