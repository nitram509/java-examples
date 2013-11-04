var MemoryTicker = MemoryTicker || {};

(function() {
  "use strict";

  var request = {
    url: '/websocket-example/memoryTicker',
    contentType: 'application/json',
    logLevel: 'debug',
    transport: 'websocket',
    trackMessageLength: true,
    reconnectInterval: 5000,
    enableXDR: true,
    timeout: 60000
  };

  request.onOpen = function(response){
  };

  request.onClientTimeout = function(response){
  };

  request.onReopen = function(response){
  };

  request.onTransportFailure = function(errorMsg, request){
  };

  request.onMessage = function(response){
  };

  request.onClose = function(response){
  };

  request.onError = function(response){
  };

  request.onReconnect = function(request, response){
  };

  MemoryTicker.socket = atmosphere.subscribe(request);

})()


