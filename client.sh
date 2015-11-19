#!/bin/bash

if [ $# -ne 1 ]; then
  echo "Usage: "
  echo " $0 [-c|--compile]      lorem ipsum"
  echo " $0 [-r|--run]          lorem ipsum"
  exit
fi

if [ "$1" == -c ] || [ "$1" == --compile ]; then
  echo "compiling"
  mkdir -p Client/out/classes
  javac -cp Client/src Client/src/com/aziflaj/irc/client/*.java -d Client/out/classes
elif [ "$1" == -r ] || [ "$1" == --run ]; then
  echo "running"
  java -cp Client/out/classes com.aziflaj.irc.client.Client
else
  echo "I'm confused, what should I do again?"
  exit
fi
