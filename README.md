# README

This is a Client-Server application built in Java using Sockets.

To compile and run the application, firstly make sure to run these two lines of code:

```bash
$ chmod +x client.sh
$ chmod +x server.sh
```

While not necessary, this `chmod +x` command allows you to execute the `client.sh` and `server.sh` scripts.

These scripts do exactly the same thing, one for the client application and the other one for the server application. The scripts can be used to compile each application and then run them. Both scripts allow you to use these flags:

- `-c` or `--compile`: Compile the source code. The output goes into `Client/out/classes/` and `Server/out/classes/`
- `-r` or `--run`: Compile and run the `*.class` files in`Client/out/classes/` and `Server/out/classes/`

## ToDO
- [ ] Add multithreading for multiple clients
- [ ] Add User Interface
- [ ] Create JARs
