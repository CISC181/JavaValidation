package pkgValidation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class NullTest {

	private static Validator validator;

	@BeforeAll
	public static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	private User createUser() {
		User user = new User();
		user.setName("John");
		user.setWorking(true);
		user.setAge(18);
		return user;
	}

	@Test
	public void ifNameIsNull_nameValidationFails() {
		User user = createUser();
		user.setWorking(true);
		user.setAboutMe("Its all about me!!");
		user.setAge(50);
		user.setName(null);
	 
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		assertEquals(violations.isEmpty(), false);

		StringBuilder message = new StringBuilder();

		for (ConstraintViolation<?> violation : violations) {
			message.append(violation.getMessage().concat(";"));
		}

		System.out.println(message);
	 
	}

}
