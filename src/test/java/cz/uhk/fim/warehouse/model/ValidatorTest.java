package cz.uhk.fim.warehouse.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import cz.uhk.fim.warehouse.part.PartEntity;
import cz.uhk.fim.warehouse.price.PriceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

class ValidatorTests {

    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    @Test
    void shouldNotValidateWhenSaleIsLessThanZero() {

        LocaleContextHolder.setLocale(Locale.ENGLISH);
        PriceEntity price = new PriceEntity();
        price.setName("17C");
        price.setPurchase(BigDecimal.valueOf(15));
        price.setSale(BigDecimal.valueOf(-10));

        Validator validator = createValidator();
        Set<ConstraintViolation<PriceEntity>> constraintViolations = validator.validate(price);

        assertThat(constraintViolations.size()).isEqualTo(1);
        ConstraintViolation<PriceEntity> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("sale");
        assertThat(violation.getMessage()).isEqualTo("Cena musí být nezáporná");
    }

    @Test
    void shouldNotValidateWhenNameLengthLessThanTwo() {
        LocaleContextHolder.setLocale(Locale.ENGLISH);
        PriceEntity price = new PriceEntity();
        price.setName("18A");
        price.setPurchase(BigDecimal.valueOf(15));
        price.setSale(BigDecimal.valueOf(12));

        PartEntity part = new PartEntity();
        part.setName("a");
        part.setCode("abc123");
        part.setPrice(price);
        part.setQty(5);
        part.setMin(1);

        Validator validator = createValidator();
        Set<ConstraintViolation<PartEntity>> constraintViolations = validator.validate(part);

        assertThat(constraintViolations.size()).isEqualTo(1);
        ConstraintViolation<PartEntity> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
        assertThat(violation.getMessage()).isEqualTo("Pole musí obsahovat 2-50 znaků");
    }

}

