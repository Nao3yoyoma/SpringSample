package jp.co.nao;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserController {

	private Logger log = Logger.getLogger(UserController.class);

	//	@Resource // LocalValidatorFactoryBean
	//	private Validator validator;

	@Resource // SmartValidatorは<mvc:annotation-driven>の設定が行われていれば使用できるため、Bean定義不要
	private SmartValidator smartValidator;

	@ModelAttribute
	public UserForm setupForm() {
		return new UserForm();
	}

	@RequestMapping(value = "create", method = RequestMethod.GET, params = "form")
	public String createForm() {
		return "user/createForm";
	}

	/**
	 * (1) Spring のValidator を Annotetaionから使用した場合
	 *
	 */
	//	  @RequestMapping(value = "create", method = RequestMethod.POST, params = "confirm")
	//	  public String createConfirm(@Validated UserForm form, BindingResult result) {
	//		  if (result.hasErrors()) {
	//	      return "user/createForm";
	//	    }
	//	    return "user/createConfirm";
	//	  }

	/**
	 * (2) LocalValidatorFactoryBean の Validator を使用した場合
	 *
	 */
	//	@RequestMapping(value = "create", method = RequestMethod.POST, params = "confirm")
	//	public String createConfirm(UserForm form, BindingResult result) {
	//		Set<ConstraintViolation<UserForm>> violations = validator.validate(form);
	//		if (!violations.isEmpty()) {
	//			for (ConstraintViolation<UserForm> v : violations) {
	//				log.info(v.getMessage());
	//			}
	//			return "user/createForm";
	//		}
	//		return "user/createConfirm";
	//	}

	/**
	 * (3) SmartValidator を使用した場合
	 *
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST, params = "confirm")
	public String createConfirm(UserForm form, BindingResult result) {
		smartValidator.validate(form, result);
		if (result.hasErrors()) {
			return "user/createForm";
		}
		return "user/createConfirm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Validated UserForm form, BindingResult result) {
		if (result.hasErrors()) {
			return "user/createForm";
		}
		// omitted business logic
		return "redirect:/user/create?complete";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET, params = "complete")
	public String createComplete() {
		return "user/createComplete";
	}
}