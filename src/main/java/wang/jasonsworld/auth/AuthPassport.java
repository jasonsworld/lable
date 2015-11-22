package wang.jasonsworld.auth;

import java.lang.annotation.*;

/**
 * @brief 权限验证注解，方便后续权限验证工作
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPassport {
    boolean validate() default true;
}
