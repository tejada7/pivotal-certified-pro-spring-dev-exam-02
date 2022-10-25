package com.apress.cems.scopes;

import org.springframework.context.annotation.Description;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;

@Description("Salary for an employee might change, so this is a suitable example for a prototype scoped bean")
public interface Salariable {
    Integer getAmount();
}
