<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>socket</title>
      <script type="text/javascript">
          	/*
			 * ----------------------------------
			 * SocketJS Functions
			 * ----------------------------------
			 * (c) 2006 by Manfred Weber
			 * ----------------------------------
			 */
			/*
			 * SocketOnInit()
			 * Event Handler is called when Flash File is loaded
			 */
			 function SocketOnInit(){
			 	SocketConnect('rensea.rj8g.com', 9999);
			 }
			/*
			 * SocketOnData()
			 * Event Handler is called when received Data
			 */
			 function SocketOnData(data){
			 	var jsonMessage = eval('('+data+')');
				MessageManager.newMessage(jsonMessage);
			 }
			/*
			 * SocketOnConnect(success);
			 * Event Handler is called when socket is connected
			 */
			 function SocketOnConnect(success){
			 	if(success=="true"){
					SocketSend("authentication={userId:'<%=request.getParameter("userId")%>',messageTypes:['STATUS','FOLLOW']}")
			    }
			 }
			/*
			 * SocketOnClose
			 * Event Handler is calles when socket is closed
			 */
			 function SocketOnClose(){
				//alert("close")
			 }
			/*
			 * SocketClose()
			 * Close the Socket
			 */
			 function SocketClose(){
			     window.document.socket.TCallLabel("/", "close" );
			 }
			/*
			 * SocketConnect(host,port)
			 * Connect to socket. Notice that host must be the same where the .swf file resides!
			 */
			 function SocketConnect(host,port){
			     window.document.socket.SetVariable("host", host);
			     window.document.socket.SetVariable("port", port);
			     window.document.socket.TCallLabel("/", "connect" );
			 }
			/*
			 * SocketSend(data)
			 * Send data to open socket
			 */
			 function SocketSend(data){
			     window.document.socket.SetVariable("data"+"\0\n", data);
			     window.document.socket.TCallLabel("/", "send" )
			 }
      </script>
  </head>
  <body>
	  <script type="text/javascript">
			var MessageManager = function(){

				var listeners = {};

				return {

					addListener : function(messageType, listener) {
						listeners[messageType] = listener;
					},

					newMessage : function(data) {
						if (!data)
							return;

						var messages = data['messages'];
                        if (!messages || messages.length==0)
							return;

						for(var i=0; i<messages.length; i++) {
							var message = messages[i];
							if (!message || !message.messageType)
								continue;
							listener = listeners[message.messageType];
							if (!listener)
								continue;
							listener(message);
						}
					}

				}

			}();

            <%--//
			$(document).ready(
				function(){
					MessageManager.addListener('STATUS', function(message){
						$("#status").append("ids="+message['ids']).append("<br/>");
					});
				}
			);--%>
		</script>
        <object id="socket" height="1" width="1" align="middle" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000">
              <param value="sameDomain" name="allowScriptAccess"/>
              <param value="true" name="swLiveConnect"/>
              <param value="high" name="quality"/>
              <param value="#ffffff" name="bgcolor"/>
              <embed height="1" width="1" align="middle" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" name="socket" src="socket_new.swf" />
        </object>

  </body>
</html>