package com.github.aproxy.directive

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.Directive1
import akka.http.scaladsl.server.Directives._
import com.github.aproxy.http.ProxyServer

import scala.concurrent.ExecutionContext.Implicits.global

trait ProxyDirective {
  def proxy(proxyServer: ProxyServer): Directive1[HttpResponse] =
    extractRequestContext.flatMap { ctx =>
      onSuccess(proxyServer.doRequest(ctx))
    }
}

object ProxyDirective extends ProxyDirective