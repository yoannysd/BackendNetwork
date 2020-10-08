package cu.musala.network;

import cu.musala.network.Controller.NetworkController;
import cu.musala.network.Entity.Gateway;
import cu.musala.network.Entity.PeripheralDevice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class NetworkControllerTests {

    @Autowired
    private NetworkController networkController;

    @Test
    void test_showAllGateways() {
        ResponseEntity responseEntity = networkController.showAllGateways();

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void test_addPeriphericalDevice_notAllowingMoreThan_10() {
        ResponseEntity<Gateway> response = networkController.showDetailsGateway(1);

        System.out.println(response.getStatusCode().toString());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        //1
        ResponseEntity responseEntity = networkController.addPeriphericalDevice(1, new PeripheralDevice("Oracle"));
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        //2
        responseEntity = networkController.addPeriphericalDevice(1, new PeripheralDevice("IBM"));
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        //3
        responseEntity = networkController.addPeriphericalDevice(1, new PeripheralDevice("Microsoft"));
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        //4
        responseEntity = networkController.addPeriphericalDevice(1, new PeripheralDevice("Musala"));
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        //5
        responseEntity = networkController.addPeriphericalDevice(1, new PeripheralDevice("UCI"));
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        //6
        responseEntity = networkController.addPeriphericalDevice(1, new PeripheralDevice("Desoft"));
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        //7
        responseEntity = networkController.addPeriphericalDevice(1, new PeripheralDevice("CITMATEL"));
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        //8
        responseEntity = networkController.addPeriphericalDevice(1, new PeripheralDevice("CUJAE"));
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        //9
        responseEntity = networkController.addPeriphericalDevice(1, new PeripheralDevice("UCLV"));
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        //10
        responseEntity = networkController.addPeriphericalDevice(1, new PeripheralDevice("UH"));
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        //11 Here it is not allowed to insert this Peripherical device.
        responseEntity = networkController.addPeriphericalDevice(1, new PeripheralDevice("NOT ALLOWED"));
        Assertions.assertEquals(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED, responseEntity.getStatusCode());

    }

}
