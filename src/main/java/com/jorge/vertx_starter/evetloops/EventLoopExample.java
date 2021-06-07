package com.jorge.vertx_starter.evetloops;

import com.jorge.vertx_starter.verticles.MainVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class EventLoopExample extends AbstractVerticle {

  static final String ADDRESS = "my.request.address";
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new RequestVerticle());
    vertx.deployVerticle(new ResponseVerticle());
  }

  static class RequestVerticle extends AbstractVerticle{
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      EventBus eventBus = vertx.eventBus();
      String msg = "Hello WORLD";
      System.out.println("S4ending: {} "+ msg);
      eventBus.<String>request(ADDRESS, msg, reply->{
        System.out.println("Response: {} "+ reply.result().body());
      });
    }
  }
  static class ResponseVerticle extends AbstractVerticle{
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().<String>consumer(ADDRESS, message->{
        System.out.println("RECEIVED MESSAGE: {} "+ message.body());
        message.reply("Received your message. Thanks!");
      });
    }
  }
}
