# README

This is a Client-Server application built in Java using Sockets.

To compile and run the application, use the `scripts/client` and `scripts/server` scripts.

These scripts do basically the same thing, one for the client application and the other one for the server application.
The scripts can be used to compile each application and then run them. Both scripts allow you to use these flags:

- `-c` or `--compile`: Compile the source code. The output goes into `Client/out/classes/` and `Server/out/classes/`
- `-d` pr `--delete`: Clean the project by deleting all the `*.class` files
- `-r` or `--run`: Compile and run the `*.class` files in`Client/out/classes/` and `Server/out/classes/`

## Setting up the IRC Server and Client

To bring the Server up, all you need to do is run the server application:

```bash
scripts/server -r
```

Once the server is up and running, different clients can connect and start chatting. Each client needs a `properties` file, located at `resources/client.properties`.
To set up the client, you need to rename the `client.properties.example` file to `client.properties` and set the `host` and `port` to the server's hostname and port:

```bash
cp resources/client.properties.example resources/client.properties
```

Then, open the `client.properties` file and set the `host` and `port` to the server's hostname and port.

```properties
# Server-side information
host=SERVER-HOSTNAME
port=SERVER-PORT

# Client properties
username=YOUR-USERNAME-HERE
```

You need to set the server-side values correctly or you won't connect to the server.
After setting the `host` (String) and `port` (Integer), make sure to add a username for yourself.
This will act as an identifier for you when you chat online with other people.

## ToDO
- [x] Add multithreading for multiple clients
- [x] Multiple clients talk together
- [x] Add User Interface
