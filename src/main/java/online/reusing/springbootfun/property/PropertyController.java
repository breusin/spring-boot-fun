package online.reusing.springbootfun.property;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

@RestController
@RequestMapping("/rc")
public class PropertyController {

    @Resource
    private PropertyService propertyService;

    @ApiOperation(value = "Get all properties")
    @GetMapping("/properties")
    public ResponseEntity<Collection<Property>> getProperties() {
        Collection<Property> allProperties = propertyService.getAllProperties();

        //return allProperties;

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "http://localhost:3000");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(allProperties);
    }

    @ApiOperation(value = "Get a property")
    @GetMapping("/properties/{id}")
    public Property getProperty(@PathVariable  String id) {
        return propertyService.getProperty(id);
    }

    @ApiOperation(value = "Delete a property")
    @DeleteMapping("/properties/{id}")
    public void deleteProperty(@PathVariable  String id) {
        propertyService.deleteProperty(id);
    }

    @ApiOperation(value = "Add a property")
    @ApiResponses({
            @ApiResponse(code = 200, message = "foo"),
    })
    @PostMapping("properties")
    public void addProperty(@RequestBody Property property) {
        propertyService.addProperty(property);
    }

}
