package com.rocs.osd.controller.offense;

import com.rocs.osd.domain.offense.Offense;
import com.rocs.osd.service.offense.OffenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *RestController for managing offense operations such as GET, POST and PUT requests.
 */

@RestController
@RequestMapping("/offense")
public class OffenseController {

    private OffenseService offenseService;
    /**
     * Constructs a new OffenseController with OffenseService.
     *
     * @param offenseService offense operations
     */

    @Autowired
    public OffenseController(OffenseService offenseService) {
        this.offenseService = offenseService;
    }
    /**
     * Retrieves the list of all offenses.
     *
     * @return list of all offenses
     */
    @GetMapping("/offenseList")
    public ResponseEntity<List<Offense>> getAllOffense() {
        return new ResponseEntity<>(offenseService.getAllOffense(), HttpStatus.OK);
    }
    /**
     * Retrieves the list of offenses by type.
     *
     * @param type type of offenses to retrieve
     * @return list of offenses
     */
    @GetMapping("/offenseType/{type}")
    public ResponseEntity<List<Offense>> getOffenseByType(@PathVariable String type) {
        List<Offense> offenses = offenseService.getOffenseByType(type);
        return new ResponseEntity<>(offenses, HttpStatus.OK);
    }
    /**
     * Add a new offense.
     *
     * @param offense offense to add
     * @return added offense
     */
    @PostMapping("/addOffense")
    public ResponseEntity<String> addOffense(@RequestBody Offense offense) {
        try {
            offenseService.addOffense(offense);
            return new ResponseEntity<>("Offense successfully added", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Offense cannot be added", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Updates an existing offense.
     *
     * @param offense offense with updated information
     * @return updated offense
     */
    @PutMapping("/updateOffense")
    public ResponseEntity<String> updateOffense(@RequestBody Offense offense) {
        Offense updatedOffense = offenseService.updateOffense(offense);
        if (updatedOffense != null) {
            return new ResponseEntity<>("Offense successfully updated", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Offense not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Retrieves an offense by its description.
     *
     * @param description description of the offense to retrieve
     * @return the offense
     */
    @GetMapping("/offenseName/{offenseName}")
    public ResponseEntity<Offense> getOffenseByDescription(@PathVariable("offenseName") String description) {
        Offense offense = offenseService.getOffenseByDescription(description);
        if (offense != null) {
            return new ResponseEntity<>(offense, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
