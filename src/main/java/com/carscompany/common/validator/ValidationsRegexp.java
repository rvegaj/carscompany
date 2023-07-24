package com.carscompany.common.validator;

import com.carscompany.common.Utils;
import com.carscompany.common.Constants;
import com.carscompany.dto.enums.RegularExpresion;
import com.carscompany.infraestructure.web.exceptions.ExceptionRequestInvalid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ValidationsRegexp {
  public  static void validateEmail(String email) throws ExceptionRequestInvalid {

      log.info("validateEmail: " + email);
      if (!Utils.validateRegex(email, RegularExpresion.REGEX_EMAIL.getExpression())) {
        log.error("Correo Electrónico no válido - validateEmail: " + email);
        throw new ExceptionRequestInvalid(Constants.MESSAGE_ERROR_EMAIL_INVALID);
      }
  }

  public static void validatePassword(String password) throws ExceptionRequestInvalid {
      if (!Utils.validateRegex(password, RegularExpresion.REGEX_PASSWORD.getExpression())) {
        log.error("Contraseña no válida - validatePassword: " + password);
        throw new ExceptionRequestInvalid(Constants.MESSAGE_ERROR_PASSWORD_BAD);
      }
  }
}
