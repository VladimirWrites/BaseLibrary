package com.vlad1m1r.baselibrary.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by vladimirjovanovic on 3/17/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}
