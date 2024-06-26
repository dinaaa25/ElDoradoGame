package org.utwente.player.view;

import java.awt.event.ActionListener;
import java.util.List;

import org.utwente.player.model.Player;

public class PlayerCli implements PlayerView {
  List<Player> playerList;

  @Override
  public void showCards() {

  }

  @Override
  public void addPlayCardListener(ActionListener listener) {

  }

  @Override
  public void setPlayers(List<Player> players) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setPlayers'");
  }

}