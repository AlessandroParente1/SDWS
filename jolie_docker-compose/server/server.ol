include "console.iol"
include "twiceInterface.iol"

inputPort TwiceService {
    Location: "socket://localhost:8000"
    Protocol: http { .format = "json";
                     .compression = false 
    }
    Interfaces: TwiceInterface
}

execution {concurrent}

main
{
    twice( number )( result ) {
        println@Console( "Received " + number )();
        result = number * 2;
        println@Console( "Sending " + result)()
    }

}
