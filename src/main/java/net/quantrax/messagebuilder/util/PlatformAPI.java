package net.quantrax.messagebuilder.util;

import org.jetbrains.annotations.ApiStatus;

import java.lang.annotation.*;

@ApiStatus.Internal
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PACKAGE, ElementType.ANNOTATION_TYPE})
public @interface PlatformAPI {
}
