package ntua.softeng28.evcharge.payment;

import java.util.List;

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

@RestController
public class PaymentController {

    private final String baseURL = "/evcharge/api/admin";

    @Autowired
	PaymentRepository paymentRepository;
    
    @GetMapping(path = baseURL + "/payments")
    public ResponseEntity<List<Payment>> all() {
        return new ResponseEntity<>(paymentRepository.findAll(), HttpStatus.OK);
    }

	@PostMapping(path = baseURL + "/payments")
	public ResponseEntity<Payment> createPayment(@RequestBody Payment event) {

		return new ResponseEntity<>(paymentRepository.save(event), HttpStatus.OK);

	}

	@GetMapping(path = baseURL + "/payments/{id}")
	public ResponseEntity<Payment> getPaymentbyId(@PathVariable Long id) {

		Payment event = paymentRepository.findById(id).orElse(null);

		if (event == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(event, HttpStatus.OK);

	}

	@DeleteMapping(path = baseURL + "/payments/{id}")
	public ResponseEntity deleteById(@PathVariable Long id) {
        paymentRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
	}

	@PutMapping(path = baseURL + "/payments/{id}")
	public ResponseEntity<Payment> updateById(@RequestBody Payment newPayment, @PathVariable Long id) {

		Payment paymentToUpdate = paymentRepository.findById(id).orElse(null);

		if (paymentToUpdate == null) 
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else {

            paymentToUpdate.setAmount(newPayment.getAmount());
            paymentToUpdate.setCar(newPayment.getCar());
            paymentToUpdate.setChargingpoint(newPayment.getChargingpoint());
            paymentToUpdate.setChargingstation(newPayment.getChargingstation());
            paymentToUpdate.setTimestamp(newPayment.getTimestamp());
            paymentToUpdate.setUser(newPayment.getUser());

			return new ResponseEntity<>(paymentRepository.save(paymentToUpdate), HttpStatus.OK);
		}

    }

}

