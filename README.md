# Still WIP

# Kebacoin-kebacoinCasino.casino
It's a project for my uni class. 
The general rule was that it needed to be a project using the Spring framework, and either SpringController or RestController.
I used REST in this project, but the requests are not stateless, because I need to check if the player doesnt want to cheat.

I implemented three basic games from casinos: kebacoinCasino.roulette, slot machine and kebacoinCasino.blackjack. These not include any advanced logic, just basic Collection shuffles, and the game logic itself.

### I'm not using this software to earn any money in any form and the software uses a made up currency kebacoin.

For running the application

```batch
gradlew bootRun
```

## Roulette

### REST definitions

The betting rules are implemented via the rules of the american kebacoinCasino.roulette. 
These betting types are included bellow under the rouletteBet section.

'GET /kebacoinCasino.roulette/table'

Returns the table type, and the id of the next free table.

**Response**

-'200 OK' on success

```json 
{
  "type": "kebacoinCasino.roulette",
  "tableNumber": 0
}
```

'POST /kebacoinCasino.roulette/table/{tableId}'

Reserves the table for the player sent in the body, sends back the current table with the player.

**Arguments**

-`"tableId"` the tableId from the get requestRoulette

**Body**

-`"player` a player object that reserves the table a player has a `"name"` and an `"amount"` which he will start playing with.

```json
{
	"player":{
		"name":"asder",
		"amount":1000
	}
}
```

**Response**

-'200 OK' on success

```json
{
  "tableId": 0,
  "player": {
    "name": "asder",
    "amount": 1000
  }
}
```

'POST /kebacoinCasino.roulette/table/{tableId}/rouletteBet'

Will make a rouletteBet with the amount in the body on the fields specified.

**Arguments**

-`"tableId"` the tableId from the get requestRoulette

**Body**

Contains the current state of the player before the rouletteBet, in the `"player"` object.

Contains the rouletteBet amount for this rounds `"betAmount""` which is an integer.

Contains the the rouletteBet type in the `"type"` fields, which can be from the following:

```
"plain","split","firstFour","street,"sixLine","dozen","column","color"
```

And contains the fields for that type under the `"fields"` tag. Every fields has a `"number"` which is an integer property.

```json
{
	"player": {
		"name": "asder",
		"amount": 1000
	},
	"betAmount": 10,
	"type": "split",
	"fields": [
		{
			"number": 1
		},
		{
			"number": 2
		}
	]
}
```

**Response**

-'200 OK' on success

Returns the map state after the rouletteBet, with the winning field and the updated user balance.

```json
{
  "player": {
    "name": "asder",
    "amount": 990
  },
  "winner": {
    "number": 22
  },
  "tableId": 0
}
```

## Slot machines

The logic was implemented from a picture I found in Google.

Link: http://www.slotmachinebasics.com/images/diagrams/3coin-paytable.gif

### REST definitions

'GET /kebacoinCasino.slotmachine/machine'

Returns the game type, and the id of the next free machine.

**Response**

-'200 OK' on success

```json 
{
  "type": "slotMachine",
  "machineId": 0
}
```

'POST /kebacoinCasino.slotmachine/machine/{machineId}'

Reserves the machine for the player sent in the body, sends back the current machine with the player.

**Arguments**

-`"machineId"` the machineId from the get requestRoulette

**Body**

-`"player` a player object that reserves the machine a player has a `"name"` and an `"amount"` which he will start playing with.

```json
{
	"player":{
		"name":"asder",
		"amount":1000
	}
}
```

**Response**

-'200 OK' on success

```json
{
  "machineId": 0,
  "player": {
    "name": "asder",
    "amount": 1000
  }
}
```

'POST /kebacoinCasino.roulette/table/{machineId}/rouletteBet'

Will make a rouletteBet with the amount in the body.

**Arguments**

-`"machineId"` the tableId from the get requestRoulette

**Body**

Contains the current state of the player before the rouletteBet, in the `"player"` object.

Contains the rouletteBet amount for this rounds `"betAmount""` which is an integer.


```json
{
	"player": {
		"name": "asder",
		"amount": 1000
	},
	"betAmount": 10
}
```

**Response**

-'200 OK' on success

Returns the current roll after the rouletteBet and the updated user balance.

```json
{
  "player": {
    "name": "asder",
    "amount": 990
  },
  "firstSlot": {
    "type": "pinkSeven"
  },
  "secondSlot": {
    "type": "green"
  },
  "thirdSlot": {
    "type": "blue"
  },
  "machineId": 0
}
```
