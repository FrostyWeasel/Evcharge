package ntua.softeng28.evcharge.payment_card;

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
public class PaymentCardController {

	private final String baseURL = "/evcharge/api/admin";

    @Autowired
	PaymentCardRepository paymentCardRepository;
    
    @GetMapping(path = baseURL + "/paymentCards")
    public ResponseEntity<List<PaymentCard>> all() {
        return new ResponseEntity<>(paymentCardRepository.findAll(), HttpStatus.OK);
    }

	@PostMapping(path = baseURL + "/paymentCards")
	public ResponseEntity<PaymentCard> createPaymentCard(@RequestBody PaymentCard paymentCard) {

		return new ResponseEntity<>(paymentCardRepository.save(paymentCard), HttpStatus.OK);

	}

	@GetMapping(path = baseURL + "/paymentCards/{id}")
	public ResponseEntity<PaymentCard> getPaymentCardbyId(@PathVariable Long id) {

		PaymentCard paymentCard = paymentCardRepository.findById(id).orElse(null);

		if (paymentCard == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(paymentCard, HttpStatus.OK);

	}

	@DeleteMapping(path = baseURL + "/paymentCards/{id}")
	public ResponseEntity deleteById(@PathVariable Long id) {
        paymentCardRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
	}

	@PutMapping(path = baseURL + "/paymentCards/{id}")
	public ResponseEntity<PaymentCard> updateById(@RequestBody PaymentCard newPaymentCard, @PathVariable Long id) {

		PaymentCard paymentCardToUpdate = paymentCardRepository.findById(id).orElse(null);

		if (paymentCardToUpdate == null) 
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else {

            paymentCardToUpdate.setCard_number(newPaymentCard.getCard_number());
            paymentCardToUpdate.setType(newPaymentCard.getType());
            paymentCardToUpdate.setUser(newPaymentCard.getUser());

			return new ResponseEntity<>(paymentCardRepository.save(paymentCardToUpdate), HttpStatus.OK);
		}

    }

}

