include "time.iol"
include "console.iol"
include "twiceInterface.iol"

outputPort TwiceService {
    Location: "socket://server:8000"
    Protocol: http { .format="json" } 
    Interfaces: TwiceInterface
}

main
{
    counter = 0;
    while (true) {
        counter = counter + 1;
        println@Console( "\nSend " + counter )();
        twice@TwiceService( counter )( response );
        println@Console( "Received" + " " + response + "\n")();
        sleep@Time(2000)()
    }
}
