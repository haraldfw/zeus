package com.polarbirds.zeus.net.chat;

import com.polarbirds.zeus.ZeusGame;
import com.polarbirds.zeus.net.Shell;
import com.polarbirds.zeus.net.UDPClient;
import com.polarbirds.zeus.net.UDPServer;

/**
 * Created by Harald on 27.1.16.
 */
public class CommandInterpreter {

  private UDPServer udpServer;
  private UDPClient udpClient;
  private TCPServer tcpServer;
  private TCPClient tcpClient;
  private ZeusGame zeusGame;
  private Shell shell;

  public CommandInterpreter(UDPServer udpServer, UDPClient udpClient,
                            TCPServer tcpServer, TCPClient tcpClient,
                            ZeusGame zeusGame, Shell shell) {
    this.udpServer = udpServer;
    this.udpClient = udpClient;
    this.tcpServer = tcpServer;
    this.tcpClient = tcpClient;
    this.zeusGame = zeusGame;
    this.shell = shell;
  }

  public String interpretCommand(String command) {
    String[] commands = command.split(" ");
    switch (commands[0]) {
      case "kick":
        break;
      case "exit":
        break;
      default:
        shell.addCommand(command);
    }
    return "Invalid command";
  }
}
