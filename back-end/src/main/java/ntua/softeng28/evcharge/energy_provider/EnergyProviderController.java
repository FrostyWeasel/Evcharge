package ntua.softeng28.evcharge.energy_provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EnergyProviderController {
	
	private final String baseURL = "/evcharge/api/";

    @Autowired
    EnergyProviderRepository energyproviderrepository;
    
    @GetMapping(path = baseURL + "/energyproviders")
    public ResponseEntity<List<EnergyProvider>> all() {
        return new ResponseEntity<>(energyproviderrepository.findAll(), HttpStatus.OK);
    }

	@PostMapping(path = baseURL + "admin/energyproviders")
	public ResponseEntity<EnergyProvider> createProvider(@RequestBody EnergyProvider energyprovider) {

		return new ResponseEntity<>(energyproviderrepository.save(energyprovider), HttpStatus.OK);

	}

	@GetMapping(path = baseURL + "/energyproviders/{id}")
	public ResponseEntity<EnergyProvider> ProviderById(@PathVariable Long id) {

		EnergyProvider provider = energyproviderrepository.findById(id).orElse(null);

		if (provider == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(provider, HttpStatus.OK);

	}

	@DeleteMapping(path = baseURL + "admin/energyproviders/{id}")
	public ResponseEntity deleteById(@PathVariable Long id) {
        energyproviderrepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
	}

	@PutMapping(path = baseURL + "admin/energyproviders/{id}")
	public ResponseEntity<EnergyProvider> updateById(@RequestBody EnergyProvider newProvider, @PathVariable Long id) {

		EnergyProvider providertoupdate = energyproviderrepository.findById(id).orElse(null);

		if (providertoupdate == null) 
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else {

            providertoupdate.setBrandName(newProvider.getBrandName());
            providertoupdate.setLowPrice(newProvider.getLowPrice());
            providertoupdate.setMidPrice(newProvider.getMidPrice());
            providertoupdate.setHighPrice(newProvider.getHighPrice());
            providertoupdate.setLowtoMidLimit(newProvider.getLowtoMidLimit());
            providertoupdate.setMidtoHighLimit(newProvider.getMidtoHighLimit());

			return new ResponseEntity<>(energyproviderrepository.save(providertoupdate), HttpStatus.OK);
		}

    }
}
