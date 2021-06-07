package com.jorge.vertx_starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleA extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("Start: "+getClass().getName());
    vertx.deployVerticle(new VerticleAA());
    vertx.deployVerticle(new VerticleAB());
    startPromise.complete();
  }
}
