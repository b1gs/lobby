'use strict';



/* Controllers */

angular.module('springChat.controllers', ['toaster'])
	.controller('ChatController', ['$scope', '$location', '$interval', 'toaster', 'ChatSocket', function($scope, $location, $interval, toaster, chatSocket) {

        var params = (new URL(document.location)).searchParams;
        var roomId = params.get("roomId");
        console.log('RoomId is = ' + roomId);
		var typing = undefined;

		$scope.username     = '';
		$scope.sendTo       = 'everyone';
		$scope.participants = [];
		$scope.messages     = [];
		$scope.newMessage   = '';

		$scope.sendMessage = function() {
			var destination = "/app/" + roomId + "/chat.message/";

			if($scope.sendTo != "everyone") {
				destination = "/app/chat.private." + $scope.sendTo;
				$scope.messages.unshift({message: $scope.newMessage, username: 'you', priv: true, to: $scope.sendTo});
			}
			console.log("Sending Message to destination:  " + destination)
			chatSocket.send(destination, {}, JSON.stringify({message: $scope.newMessage}));
			$scope.newMessage = '';
		};

		$scope.startTyping = function() {
			// Don't send notification if we are still typing or we are typing a private message
	        if (angular.isDefined(typing) || $scope.sendTo != "everyone") return;

	        typing = $interval(function() {
	                $scope.stopTyping();
	            }, 500);

	        chatSocket.send("/topic/" + roomId + "/chat.typing", {}, JSON.stringify({username: $scope.username, typing: true}));
		};

		$scope.stopTyping = function() {
			if (angular.isDefined(typing)) {
		        $interval.cancel(typing);
		        typing = undefined;

		        chatSocket.send("/topic/" + roomId + "/chat.typing", {}, JSON.stringify({username: $scope.username, typing: false}));
			}
		};

		$scope.privateSending = function(username) {
				$scope.sendTo = (username != $scope.sendTo) ? username : 'everyone';
		};

        $scope.disconnect = function () {
        	var redirectLink = "http://localhost:7788/lobby.html";
            if (chatSocket !== null) {
                chatSocket.disconnect();
            }
            console.log("Disconnected. Redirecting to " + redirectLink);
            window.location.replace(redirectLink);
        };

        $scope.startGame = function () {
            var destination = "/app/" + roomId + "/startGame";

            console.log("Sending Message to destination:  " + destination)
            chatSocket.send(destination, {}, JSON.stringify({ "123" : 123 }));
            $scope.newMessage = '';
        };


		var initStompClient = function() {
			chatSocket.init('/ws');

			chatSocket.connect(function(frame) {

				$scope.username = frame.headers['user-name'];

				chatSocket.subscribe("/app/"+roomId+"/chat.participants", function(message) {
					$scope.participants = JSON.parse(message.body);
				});

				chatSocket.subscribe("/topic/"+roomId+"/chat.login", function(message) {
					$scope.participants.unshift({username: JSON.parse(message.body).username, typing : false});
				});

				chatSocket.subscribe("/topic/"+roomId+"/chat.logout", function(message) {
					var username = JSON.parse(message.body).username;
					for(var index in $scope.participants) {
						if($scope.participants[index].username == username) {
							$scope.participants.splice(index, 1);
						}
					}
		        });

				chatSocket.subscribe("/topic/"+roomId+"/chat.typing", function(message) {
					var parsed = JSON.parse(message.body);
					if(parsed.username == $scope.username) return;

					for(var index in $scope.participants) {
						var participant = $scope.participants[index];

						if(participant.username == parsed.username) {
							$scope.participants[index].typing = parsed.typing;
						}
				  	}
				});

				chatSocket.subscribe("/topic/"+roomId+"/chat.message", function(message) {
					$scope.messages.unshift(JSON.parse(message.body));
		        });

                chatSocket.subscribe("/topic/"+roomId+"/game.turn.message", function(message) {
                    $scope.messages.unshift(JSON.parse(message.body));
                });

				chatSocket.subscribe("/user/exchange/amq.direct/chat.message", function(message) {
                    console.log("Plain message.body: " + message.body)
                    console.log("Parsed JSON:  " + JSON.parse(message.body))
					var parsed = JSON.parse(message.body);
                    console.log("parsed.username:  " + parsed.username);
					if (parsed.username == "CARDS_MESSAGE"){
                        var parent = document.getElementById('playingCards');
                        var cards = JSON.parse(parsed.message);
                        for(var i = 0; i < cards.length ; i++) {
                            var div = document.createElement("div");
                            var rankValue = mapRank(cards[i].rank);
                            var suitValue = mapSuit(cards[i].suit);
                            div.classList.add('card');
                            div.classList.add('rank-' + rankValue.toLowerCase());
                            div.classList.add(suitValue);

                            var rankSpan = document.createElement("span");
                            rankSpan.classList.add("rank");
                            rankSpan.innerHTML = rankValue;
							div.appendChild(rankSpan);

                            var suitSpan = document.createElement("span");
                            suitSpan.classList.add("suit");
                            suitSpan.innerHTML = "&" + suitValue + ';';
                            div.appendChild(suitSpan);
                            console.log(i + ' + Image: ' + rankSpan);
                            console.log(i + ' + Image with DIV: ' + div);
                            // div.onclick= function( id ){
                            //     return function (){addRoomHandler(id)}}(id);
                            parent.appendChild(div);
                        }
					}else{
                        parsed.priv = true;
                        $scope.messages.unshift(parsed);
					}
		        });

                function mapSuit(suit) {
                    switch (suit) {
                        case 'HEARTS':
                            return 'hearts'
                        case 'DIAMONDS':
                            return 'diams'
                        case 'CLUBS':
                            return 'clubs'
                        case 'SPADES':
                            return 'spades'
                    }
                }

                function mapRank(rank) {

                    switch (rank) {
                        case 'TWO':
                            return '2'
                        case 'THREE':
                            return '3'
                        case 'FOUR':
                            return '4'
                        case 'FIVE':
                            return '5'
                        case 'SIX':
                            return '6'
                        case 'SEVEN':
                            return '7'
                        case 'EIGHT':
                            return '8'
                        case 'NINE':
                            return '9'
                        case 'TEN':
                            return '10'
                        case 'JACK':
                            return 'J'
                        case 'QUEEN':
                            return 'Q'
                        case 'KING':
                            return 'K'
                        case 'ACE':
                            return 'A'
                    }
                }

				chatSocket.subscribe("/user/exchange/amq.direct/errors", function(message) {
					toaster.pop('error', "Error", message.body);
		        });

			}, function(error) {
				toaster.pop('error', 'Error', 'Connection error ' + error);

		    });
		};
		initStompClient();
	}]);
// function mapRank(rank) {
//
//     switch (rank) {
//         case 'TWO':
//             return '2'
//         case 'THREE':
//             return '3'
//         case 'FOUR':
//             return '4'
//         case 'FIVE':
//             return '5'
//         case 'SIX':
//             return '6'
//         case 'SEVEN':
//             return '7'
//         case 'EIGHT':
//             return '8'
//         case 'NINE':
//             return '9'
//         case 'TEN':
//             return '10'
//         case 'JACK':
//             return 'J'
//         case 'QUEEN':
//             return 'Q'
//         case 'KING':
//             return 'K'
//         case 'ACE':
//             return 'A'
//     }
//
// }
//
// function mapSuit(suit) {
//     switch (suit) {
//         case 'HEARTS':
//             return 'hearts'
//         case 'DIAMONDS':
//             return 'diams'
//         case 'CLUBS':
//             return 'clubs'
//         case 'SPADES':
//             return 'spades'
//     }
// }