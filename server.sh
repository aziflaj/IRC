#!/bin/bash

if [ $# -ne 1 ]; then
    echo "Usage: "
    echo " $0 [-c|--compile]      Compile the Server application files"
    echo " $0 [-r|--run]          Compile and run the Server"
    exit
fi

if [ "$1" == -c ] || [ "$1" == --compile ]; then
    echo "compiling"
    mkdir -p Server/out/classes
    javac -cp Server/src Server/src/com/aziflaj/irc/server/*.java -d Server/out/classes
elif [ "$1" == -r ] || [ "$1" == --run ]; then
    ./$0 --compile
    echo "running"
    java -cp Server/out/classes com.aziflaj.irc.server.Server
else
    echo "I'm confused, what should I do again?"
    exit
fi
