package cu.musala.network.Controller;

import cu.musala.network.Entity.Gateway;
import cu.musala.network.Entity.PeripheralDevice;
import cu.musala.network.Service.NetworkServiceImpl;
import cu.musala.network.Util.LoggerUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/network/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(
        allowCredentials = "",
        allowedHeaders = {},
        exposedHeaders = {},
        maxAge = -1L, //maximo tiempo en la conexion
        methods = {
                RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE
        },
        origins = {}
)
public class NetworkController {

    private NetworkServiceImpl networkServiceImpl;

    public NetworkController(NetworkServiceImpl networkServiceImpl) {
        this.networkServiceImpl = networkServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Gateway>> showAllGateways() {
        return new ResponseEntity<>(networkServiceImpl.showAllGateways(), HttpStatus.OK);
    }

    @GetMapping(params = {"start", "length", "draw", "columns", "order", "search", "filters"})
    public Page<Gateway> showAllGateways(
            @RequestParam("start") int start,
            @RequestParam("length") int size,
            @RequestParam("draw") String draw,
            @RequestParam("columns") String[] columns,
            @RequestParam("order") String order,
            @RequestParam("search") String[] search,
            @RequestParam("filters") String[] filters
    ) {
        int page = start / size;
        Page<Gateway> resultPage = networkServiceImpl.findAllGatewayPaginated(page, size);
        if (page > resultPage.getTotalPages()) {
            System.out.println("Resources weren't found");
        }
        return resultPage;
    }

    @GetMapping(value = "/{idgateway}")
    public ResponseEntity<Gateway> showDetailsGateway(@PathVariable("idgateway") Integer idgateway) {
        Gateway g = networkServiceImpl.showDetailsGateway(idgateway);
        return new ResponseEntity<>(g, g == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping(value = "/{idgateway}")
    public ResponseEntity addPeriphericalDevice(@PathVariable("idgateway") Integer idgateway,
                                                @RequestBody PeripheralDevice peripheralDevice) {
        int answer = networkServiceImpl.addPeripheralDevice(idgateway, peripheralDevice);
        return new ResponseEntity<>(
                (answer == -1) ? HttpStatus.BANDWIDTH_LIMIT_EXCEEDED :
                        (answer == 1) ? HttpStatus.NOT_FOUND :
                                HttpStatus.CREATED
        );
    }

    @DeleteMapping(value = "/{uidperiphericaldevice}")
    public ResponseEntity deletePeriphericalDevice(@PathVariable("uidperiphericaldevice") Long uidPeriphericalDevice) {
        boolean answer = networkServiceImpl.deletePeripheralDevice(uidPeriphericalDevice);
        return new ResponseEntity<>(answer ? HttpStatus.CREATED : HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleException(Exception ex, HttpServletRequest request) {
        LoggerUtil.requestData(request);
        LoggerUtil.exceptionLog(ex);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
