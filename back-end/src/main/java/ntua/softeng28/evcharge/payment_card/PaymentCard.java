package ntua.softeng28.evcharge.payment_card;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ntua.softeng28.evcharge.user.User;

@Entity
public class PaymentCard {

	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	private String card_number;
	private String type;

	@ManyToOne
	private User user;

	public PaymentCard() {
	}

	public PaymentCard(String card_number, String type, User user) {
		this.card_number = card_number;
		this.type = type;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "PaymentCard [id=" + id + ", card_number=" + card_number + ", type=" + type + ", user=" + user + "]";
	}

}

